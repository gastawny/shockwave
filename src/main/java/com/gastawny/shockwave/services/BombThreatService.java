package com.gastawny.shockwave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.dto.bombThreat.ReqBombThreatDTO;
import com.gastawny.shockwave.handlers.Handler;
import com.gastawny.shockwave.models.BombThreat;
import com.gastawny.shockwave.models.LocatedObject;
import com.gastawny.shockwave.repositories.BombThreatRepository;
import com.gastawny.shockwave.repositories.FormThreatRepository;
import com.gastawny.shockwave.repositories.LocatedObjectRepository;
import com.gastawny.shockwave.repositories.UserRepository;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BombThreatService implements Handler<BombThreat> {

    private final BombThreatRepository bombThreatRepository;

    public BombThreatService(BombThreatRepository bombThreatRepository) {
        this.bombThreatRepository = bombThreatRepository;
    }

    @Override
    public String getType() {
        return "bombThreats";
    }

    @Override
    public Class<BombThreat> getEntityClass() {
        return BombThreat.class;
    }

    @Override
    public Object getService() {
        return new BombThreatService(bombThreatRepository);
    }

    @Override
    public List<BombThreat> findAll() {
        return List.of();
    }

    @Override
    public BombThreat findById(Long id) {
        return bombThreatRepository.findById(id).orElse(null);
    }

    @Override
    public BombThreat save(Object entity) {
        ObjectMapper mapper = new ObjectMapper();
        BombThreat bombThreat = mapper.convertValue(entity, BombThreat.class);

        return bombThreatRepository.save(bombThreat);
    }

    @Override
    public BombThreat update(BombThreat entity) {
        return bombThreatRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<GenericList> find2Select() {
        return bombThreatRepository.findBy(GenericList.class);
    }
}
