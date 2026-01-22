package com.gastawny.shockwave.services;

import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.models.Ground;
import com.gastawny.shockwave.repositories.GroundRepository;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

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
    public Ground save(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        return groundRepository.save(mapper.convertValue(entity, Ground.class));
    }

    @Override
    public Ground update(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        Ground g = mapper.convertValue(entity, Ground.class);
        return groundRepository.save(g);
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