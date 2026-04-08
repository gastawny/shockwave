package com.gastawny.shockwave.services;

import com.gastawny.shockwave.dto.AuditLogResponseDTO;
import com.gastawny.shockwave.models.AuditLog;
import com.gastawny.shockwave.models.User;
import com.gastawny.shockwave.repositories.AuditLogRepository;
import com.gastawny.shockwave.repositories.UserRepository;
import com.gastawny.shockwave.shared.enums.AuditAction;
import com.gastawny.shockwave.shared.handlers.HandlerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class AuditLogService {

    private final AuditLogRepository repository;
    private final UserRepository userRepository;
    private final HandlerFactory handlerFactory;

    public AuditLogService(AuditLogRepository repository, UserRepository userRepository, HandlerFactory handlerFactory) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.handlerFactory = handlerFactory;
    }

    public void log(AuditAction action, String entityType, Long entityId) {
        log(action, entityType, entityId, resolveCurrentUserId());
    }

    public void log(AuditAction action, String entityType, Long entityId, Long performedById) {
        AuditLog entry = new AuditLog();
        entry.setAction(action);
        entry.setEntityType(entityType);
        entry.setEntityId(entityId);
        entry.setPerformedById(performedById);
        repository.save(entry);
    }

    public List<AuditLogResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(log -> new AuditLogResponseDTO(
                        log.getId(),
                        log.getAction(),
                        log.getEntityType(),
                        resolveEntityValue(log.getEntityType(), log.getEntityId()),
                        log.getEntityId(),
                        log.getPerformedById(),
                        resolveUserName(log.getPerformedById()),
                        log.getCreatedAt()
                ))
                .toList();
    }

    private Long resolveCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return null;
        }
        if (auth.getPrincipal() instanceof User user) {
            return user.getId();
        }
        return null;
    }

    private String resolveEntityValue(String entityType, Long entityId) {
        if (entityType == null || entityId == null) return null;
        try {
            Object entity = handlerFactory.getHandler(entityType).findById(entityId);
            if (entity instanceof User u) {
                return u.getFirstName() + " " + u.getLastName();
            }
            return resolveNameField(entity);
        } catch (Exception e) {
            return null;
        }
    }

    private String resolveUserName(Long userId) {
        if (userId == null) return null;
        return userRepository.findById(userId)
                .map(u -> u.getFirstName() + " " + u.getLastName())
                .orElse(null);
    }

    private String resolveNameField(Object entity) {
        if (entity == null) return null;
        Class<?> clazz = entity.getClass();
        while (clazz != null) {
            try {
                Field f = clazz.getDeclaredField("name");
                f.setAccessible(true);
                Object val = f.get(entity);
                return val != null ? val.toString() : null;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return null;
    }
}
