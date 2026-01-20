package com.gastawny.shockwave.services;

import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.models.Ground;
import com.gastawny.shockwave.repositories.GroundRepository;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroundService implements Handler<Ground> {

    private final GroundRepository groundRepository;

    public GroundService(GroundRepository groundRepository) {
        this.groundRepository = groundRepository;
    }

    @Override
    public String getType() {
        return "grounds";
    }

    @Override
    public Class<Ground> getEntityClass() {
        return Ground.class;
    }

    @Override
    public Object getService() {
        return new GroundService(groundRepository);
    }

    @Override
    public List<Ground> findAll() {
        return groundRepository.findAll();
    }

    public Ground findById(Long id) {
        return groundRepository.findById(id).orElse(null);
    }

    @Override
    public Ground save(Object ground) {
        return groundRepository.save((Ground) ground);
    }

    @Override
    public Ground update(Ground ground) {
        return groundRepository.save(ground);
    }

    @Override
    public void deleteById(Long id) {
        groundRepository.deleteById(id);
    }

    @Override
    public List<GenericList> find2Select() {
        return groundRepository.findBy(GenericList.class);
    }
}