package com.gastawny.shockwave.shared.aspects;

import com.gastawny.shockwave.services.AuditLogService;
import com.gastawny.shockwave.shared.enums.AuditAction;
import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.shared.handlers.HandlerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class AuditAspect {

    private final AuditLogService auditLogService;
    private final HandlerFactory handlerFactory;

    public AuditAspect(AuditLogService auditLogService, HandlerFactory handlerFactory) {
        this.auditLogService = auditLogService;
        this.handlerFactory = handlerFactory;
    }

    @AfterReturning(
            pointcut = "execution(* com.gastawny.shockwave.services.*.save(java.util.Map))",
            returning = "result"
    )
    public void afterSave(JoinPoint jp, Object result) {
        if (result == null) return;
        auditLogService.log(AuditAction.CREATE, resolveEntityType(jp), resolveId(result));
    }

    @AfterReturning(
            pointcut = "execution(* com.gastawny.shockwave.services.*.update(java.util.Map))",
            returning = "result"
    )
    public void afterUpdate(JoinPoint jp, Object result) {
        if (result == null) return;
        auditLogService.log(AuditAction.UPDATE, resolveEntityType(jp), resolveId(result));
    }

    @Around("execution(* com.gastawny.shockwave.services.*.saveOrUpdate(..))")
    public Object aroundSaveOrUpdate(ProceedingJoinPoint jp) throws Throwable {
        String entityType = resolveEntityType(jp);
        Long incomingId = resolveId(jp.getArgs()[0]);
        boolean isUpdate = existsInDb(entityType, incomingId);

        Object result = jp.proceed();

        if (result != null) {
            AuditAction action = isUpdate ? AuditAction.UPDATE : AuditAction.CREATE;
            auditLogService.log(action, entityType, resolveId(result));
        }
        return result;
    }

    @Before("execution(* com.gastawny.shockwave.services.*.deleteById(Long))")
    public void beforeDelete(JoinPoint jp) {
        auditLogService.log(AuditAction.DELETE, resolveEntityType(jp), (Long) jp.getArgs()[0]);
    }

    private boolean existsInDb(String entityType, Long id) {
        if (id == null) return false;
        try {
            return handlerFactory.getHandler(entityType).findById(id) != null;
        } catch (Exception e) {
            return false;
        }
    }

    private String resolveEntityType(JoinPoint jp) {
        return ((Handler<?>) jp.getTarget()).getType();
    }

    private Long resolveId(Object entity) {
        if (entity == null) return null;
        Class<?> clazz = entity.getClass();
        while (clazz != null) {
            try {
                Field f = clazz.getDeclaredField("id");
                f.setAccessible(true);
                Object val = f.get(entity);
                return val instanceof Long ? (Long) val : null;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return null;
    }
}
