package com.gastawny.shockwave.actions;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActionRegistry {
    private final Map<String, ActionCommand> actions = new HashMap<>();

    public void register(String key, ActionCommand action) {
        actions.put(key, action);
    }

    public Object execute(String key, Object... args) {
        ActionCommand action = actions.get(key);
        if (action == null) throw new IllegalArgumentException("No action registered for key: " + key);
        return action.execute(args);
    }
}
