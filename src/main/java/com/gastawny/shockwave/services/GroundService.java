package com.gastawny.shockwave.services;

import com.gastawny.shockwave.models.Ground;
import com.gastawny.shockwave.repositories.GroundRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroundService {

    GroundRepository groundRepository;

    public GroundService(GroundRepository groundRepository) {
        this.groundRepository = groundRepository;
    }

    public List<Ground> findAll() {
        return groundRepository.findAll();
    }

    public Ground findById(Long id) {
        return groundRepository.findById(id).orElse(null);
    }

    public Ground save(Ground ground) {
        return groundRepository.save(ground);
    }

    public Ground update(Ground ground) {
        return groundRepository.save(ground);
    }

    public void deleteById(Long id) {
        groundRepository.deleteById(id);
    }
}
