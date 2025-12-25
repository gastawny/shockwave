package com.gastawny.shockwave.handlers;

import com.gastawny.shockwave.shared.enums.ValueType;
import com.gastawny.shockwave.shared.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HandlerFactory {

    private final Map<String, Handler<?>> handlers = new HashMap<>();

    public HandlerFactory(List<Handler<?>> handlersList) {
        for (Handler<?> handler : handlersList) {
            handlers.put(handler.getType(), handler);
        }
    }

    public Class<?> getHandlerClass(String type) throws BadRequestException {
        var handler = handlers.get(type);
        if (handler == null) throw new BadRequestException("Handler " + type + " not found");

        return handler.getEntityClass();
    }

    public Handler<?> getHandler(String type) throws BadRequestException {
        var handler = handlers.get(type);

        if(handler == null) throw new BadRequestException("Handler for type " + type + " not found");

        return handler;
    }

    public Map<String, String> getHandlerAttributes(String type) throws BadRequestException {
        Map<String, String> result = new HashMap<>();

        for (Object field : this.getHandlerClass(type).getDeclaredFields()) {
            String fieldName = field.toString().substring(field.toString().lastIndexOf(".") + 1);
            String fieldType = String.valueOf(field.toString().substring(0, field.toString().lastIndexOf(" ")).split(" ")[1]).replace("java.lang.", "");
            result.put(fieldName, ValueType.fromString(fieldType).name());
        }

        return result;
    }

    public List<String> findAvailableHandlers() {
        return handlers.keySet().stream().toList();
    }
}
