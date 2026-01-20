package com.gastawny.shockwave.services;

import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.models.ObjectFormat;
import com.gastawny.shockwave.repositories.ObjectFormatRepository;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public ObjectFormat save(Object entity) {
        return null;
    }

    @Override
    public ObjectFormat update(ObjectFormat entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<GenericList> find2Select() {
        return objectFormatRepository.findBy(GenericList.class);
    }
}
