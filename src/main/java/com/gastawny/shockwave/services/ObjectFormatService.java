package com.gastawny.shockwave.services;

import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.models.ObjectFormat;
import com.gastawny.shockwave.repositories.ObjectFormatRepository;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ObjectFormatService implements Handler<ObjectFormat> {

    private final ObjectFormatRepository objectFormatRepository;

    public ObjectFormatService(ObjectFormatRepository objectFormatRepository) {
        this.objectFormatRepository = objectFormatRepository;
    }

    @Override
    public String getType() {
        return "objectFormats";
    }

    @Override
    public Class<ObjectFormat> getEntityClass() {
        return ObjectFormat.class;
    }

    @Override
    public Object getService() {
        return new ObjectFormatService(objectFormatRepository);
    }

    @Override
    public List<ObjectFormat> findAll() {
        return objectFormatRepository.findAll();
    }

    @Override
    public ObjectFormat findById(Long id) {
        return objectFormatRepository.findById(id).orElse(null);
    }

    @Override
    public ObjectFormat save(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        return objectFormatRepository.save(mapper.convertValue(entity, ObjectFormat.class));
    }

    @Override
    public ObjectFormat update(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectFormat of = mapper.convertValue(entity, ObjectFormat.class);
        return objectFormatRepository.save(of);
    }

    @Override
    public void deleteById(Long id) {
        objectFormatRepository.deleteById(id);
    }

    @Override
    public List<GenericList> find2Select() {
        return objectFormatRepository.findBy(GenericList.class);
    }
}
