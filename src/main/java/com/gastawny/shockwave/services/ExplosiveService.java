package com.gastawny.shockwave.services;

import com.gastawny.shockwave.models.Parameter;
import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.models.Explosive;
import com.gastawny.shockwave.repositories.ExplosiveRepository;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExplosiveService implements Handler<Explosive> {

    private final ExplosiveRepository explosiveRepository;

    public ExplosiveService(ExplosiveRepository explosiveRepository) {
        this.explosiveRepository = explosiveRepository;
    }

    @Override
    public String getType() {
        return "explosives";
    }

    @Override
    public Class<Explosive> getEntityClass() {
        return Explosive.class;
    }

    @Override
    public Object getService() {
        return new ExplosiveService(explosiveRepository);
    }

    @Override
    public List<Explosive> findAll() {
        return explosiveRepository.findAll();
    }

    @Override
    public Explosive findById(Long id) {
        return null;
    }

    @Override
    public Explosive save(Map<String, Object> entity) {
        return null;
    }

    @Override
    public Explosive update(Map<String, Object> entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<GenericList> find2Select() {
        return explosiveRepository.findBy(GenericList.class);
    }

    public List<Map<String, Object>> getDataByExplosiveId(LinkedHashMap<String, Object> req) {
        Long id = Long.valueOf(req.get("id").toString());

        System.out.println(id);

        return (List<Map<String, Object>>) explosiveRepository.findDataByExplosiveId(id);
    }
}
