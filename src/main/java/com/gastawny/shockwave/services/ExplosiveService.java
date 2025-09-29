package com.gastawny.shockwave.services;

import com.gastawny.shockwave.models.Explosive;
import com.gastawny.shockwave.repositories.ExplosiveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExplosiveService {

    private ExplosiveRepository explosiveRepository;

    public ExplosiveService(ExplosiveRepository explosiveRepository) {
        this.explosiveRepository = explosiveRepository;
    }

    public List<Explosive> findAll() {
        return explosiveRepository.findAll();
    }

    public List<Map<String, Object>> getDataByExplosiveId(Long id) {
        return (List<Map<String, Object>>) explosiveRepository.findDataByExplosiveId(id);
    }
}
