package com.gastawny.shockwave.services;

import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.models.ObjectFormatParameter;
import com.gastawny.shockwave.repositories.ObjectFormatParameterRepository;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ObjectFormatParameterService implements Handler<ObjectFormatParameter> {

    private final ObjectFormatParameterRepository objectFormatParameterRepository;

    public ObjectFormatParameterService(ObjectFormatParameterRepository objectFormatParameterRepository) {
        this.objectFormatParameterRepository = objectFormatParameterRepository;
    }

    @Override
    public String getType() {
        return "objectFormatParameters";
    }

    @Override
    public Class<ObjectFormatParameter> getEntityClass() {
        return ObjectFormatParameter.class;
    }

    @Override
    public Object getService() {
        return new ObjectFormatParameterService(objectFormatParameterRepository);
    }

    @Override
    public List<ObjectFormatParameter> findAll() {
        return List.of();
    }

    @Override
    public ObjectFormatParameter findById(Long id) {
        return null;
    }

    @Override
    public ObjectFormatParameter save(Map<String, Object> entity) {
        return null;
    }

    @Override
    public ObjectFormatParameter update(Map<String, Object> entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<GenericList> find2Select() {
        return objectFormatParameterRepository.findBy(GenericList.class);
    }

    public List<ObjectFormatParameter> findByObjectFormatId(LinkedHashMap<String, Object> req) {
        Long objectFormatId = Long.valueOf(req.get("id").toString());

        return objectFormatParameterRepository.findByObjectFormatId(objectFormatId);
    }
}
