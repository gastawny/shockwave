package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.shared.ShockwaveResponse;
import com.gastawny.shockwave.shared.exceptions.BadRequestException;
import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.shared.handlers.HandlerFactory;
import com.gastawny.shockwave.shared.handlers.HandlerReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Tag(name = "Handlers Endpoint")
@RestController
@RequestMapping(path = "/api/handlers")
public class HandlerController {

    private HandlerFactory handlerFactory;

    public HandlerController(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    @GetMapping
    public ResponseEntity<?> findAvailableHandlers() throws BadRequestException {
        return ResponseEntity.ok(handlerFactory.findAvailableHandlers());
    }

    @GetMapping("/{type}")
    public ResponseEntity<?> findAll(@PathVariable("type") String type) throws BadRequestException {
        return ResponseEntity.ok(handlerFactory.getHandler(type).findAll());
    }

    @GetMapping("/{type}/find2Select")
    public ResponseEntity<?> find2Select(@PathVariable("type") String type) throws BadRequestException, InterruptedException {
        return ResponseEntity.ok(handlerFactory.getHandler(type).find2Select());
    }

    @GetMapping("/{type}/{id}")
    public ResponseEntity<?> findById(@PathVariable("type") String type, @PathVariable("id") Long id) throws BadRequestException {
        return ResponseEntity.ok(handlerFactory.getHandler(type).findById(id));
    }

    @GetMapping("/{type}/attributes")
    public ResponseEntity<?> getAttributes(@PathVariable("type") String type) throws BadRequestException {
        return ResponseEntity.ok(handlerFactory.getHandlerAttributes(type));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody HandlerReq req) throws BadRequestException {
        Handler<?> handler = handlerFactory.getHandler(req.getType());

        return ResponseEntity.ok(((Handler<Object>) handler).save((Map<String, Object>) req.getData()));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody HandlerReq req) throws BadRequestException {
        Handler<?> handler = handlerFactory.getHandler(req.getType());

        return ResponseEntity.ok(((Handler<Object>) handler).update((Map<String, Object>) req.getData()));
    }

    @DeleteMapping("/{type}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("type") String type, @PathVariable("id") Long id) throws BadRequestException {
        Handler<?> handler = handlerFactory.getHandler(type);
        handler.deleteById(id);

        return ResponseEntity.ok(id);
    }

    @PostMapping("/generic/{type}/{method}")
    public ResponseEntity<?> genericHandler(
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

        return ResponseEntity.ok(result);
    }
}
