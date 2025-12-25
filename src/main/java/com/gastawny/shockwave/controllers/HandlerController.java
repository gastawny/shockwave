package com.gastawny.shockwave.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.handlers.Handler;
import com.gastawny.shockwave.handlers.HandlerFactory;
import com.gastawny.shockwave.handlers.HandlerReq;
import com.gastawny.shockwave.shared.ShockwaveResponse;
import com.gastawny.shockwave.shared.exceptions.BadRequestException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Tag(name = "Handlers Endpoint")
@RestController
@RequestMapping(path = "/api/handlers")
public class HandlerController {

    private HandlerFactory handlerFactory;

    public HandlerController(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    @GetMapping
    public ResponseEntity<ShockwaveResponse<?>> findAvailableHandlers() throws BadRequestException {
        return ResponseEntity.ok(new ShockwaveResponse<>(
                handlerFactory.findAvailableHandlers()
        ));
    }

    @GetMapping("/{type}")
    public ResponseEntity<ShockwaveResponse<?>> findAll(@PathVariable("type") String type) throws BadRequestException {
        return ResponseEntity.ok(new ShockwaveResponse<>(
                handlerFactory.getHandler(type).findAll()
        ));
    }

    @GetMapping("/{type}/find2Select")
    public ResponseEntity<ShockwaveResponse<?>> find2Select(@PathVariable("type") String type) throws BadRequestException {
        return ResponseEntity.ok(new ShockwaveResponse<>(
                handlerFactory.getHandler(type).find2Select()
        ));
    }

    @GetMapping("/{type}/{id}")
    public ResponseEntity<ShockwaveResponse<?>> findById(@PathVariable("type") String type, @PathVariable("id") Long id) throws BadRequestException {
        return ResponseEntity.ok(new ShockwaveResponse<>(
                handlerFactory.getHandler(type).findById(id)
        ));
    }

    @GetMapping("/{type}/attributes")
    public ResponseEntity<ShockwaveResponse<?>> getAttributes(@PathVariable("type") String type) throws BadRequestException {
        return ResponseEntity.ok(new ShockwaveResponse<>(
                handlerFactory.getHandlerAttributes(type)
        ));
    }

    @PostMapping
    public ResponseEntity<ShockwaveResponse<?>> save(@RequestBody HandlerReq req) throws BadRequestException {
        Handler<?> handler = handlerFactory.getHandler(req.getType());

        var obj = (new ObjectMapper()).convertValue(req.getData(), Object.class);

        return ResponseEntity.ok(new ShockwaveResponse<>(((Handler<Object>) handler).save(obj)));
    }

    @PutMapping
    public ResponseEntity<ShockwaveResponse<?>> update(@RequestBody HandlerReq req) throws BadRequestException {
        Handler<?> handler = handlerFactory.getHandler(req.getType());
        Class<?> clazz = handler.getEntityClass();

        Object obj = new ObjectMapper().convertValue(req.getData(), clazz);

        return ResponseEntity.ok(new ShockwaveResponse<>(((Handler<Object>) handler).update(obj)));
    }

    @DeleteMapping("/{type}/{id}")
    public ResponseEntity<ShockwaveResponse<?>> deleteById(@PathVariable("type") String type, @PathVariable("id") Long id) throws BadRequestException {
        Handler<?> handler = handlerFactory.getHandler(type);
        handler.deleteById(id);

        return ResponseEntity.ok(new ShockwaveResponse<>(id));
    }

    @PostMapping("/generic/{type}/{method}")
    public ResponseEntity<ShockwaveResponse<?>> genericHandler(
            @RequestBody Object req,
            @PathVariable("method") String method,
            @PathVariable("type") String type
    ) throws BadRequestException {
        Handler<?> handler = handlerFactory.getHandler(type);
        Object service = handler.getService();
        Object result;

        try {
            Method m = service.getClass().getMethod(method, req.getClass());
            result = m.invoke(service, req);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new BadRequestException("Error invoking method: " + e.getMessage());
        }

        return ResponseEntity.ok(new ShockwaveResponse<>(result));
    }
}
