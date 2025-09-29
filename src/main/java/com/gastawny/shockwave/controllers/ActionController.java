package com.gastawny.shockwave.controllers;

import com.gastawny.shockwave.actions.ActionRegistry;
import com.gastawny.shockwave.actions.ActionRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Actions Endpoint")
@Controller
@RequestMapping(path = "/api/actions")
public class ActionController {

    private ActionRegistry actionRegistry;

    public ActionController(ActionRegistry actionRegistry) {
        this.actionRegistry = actionRegistry;
    }

    @PostMapping
    public Object execute(@RequestBody ActionRequest request) {
        return actionRegistry.execute(request.getActionKey(), request.getArgs());
    }
}
