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

    public List<Ground> getAllGrounds() {
        return groundRepository.findAll();
    }

    public Ground getGroundById(Long id) {
        return groundRepository.findById(id).orElse(null);
    }

    public Ground saveGround(Ground ground) {
        return groundRepository.save(ground);
    }

    public Ground updateGround(Ground ground) {
        return groundRepository.save(ground);
    }

    public void deleteGroundById(Long id) {
        groundRepository.deleteById(id);
    }
}
