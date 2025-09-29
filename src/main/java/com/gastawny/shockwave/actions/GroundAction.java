package com.gastawny.shockwave.actions;

import com.gastawny.shockwave.models.Ground;
import com.gastawny.shockwave.services.GroundService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroundAction implements ActionHandler<Ground> {

    private final GroundService groundService;

    public GroundAction(GroundService groundService) {
        this.groundService = groundService;
    }

    @Override
    public String getActionName() {
        return "";
    }

    @Override
    public List<Ground> findAll() {
        return List.of();
    }

    @Override
    public Ground findById() {
        return null;
    }

    @Override
    public Ground save() {
        return null;
    }

    @Override
    public Ground update() {
        return null;
    }

    @Override
    public void deleteById() {

    }
}
