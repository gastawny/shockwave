package com.gastawny.shockwave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.dto.locatedObject.ReqLocatedObjectDTO;
import com.gastawny.shockwave.handlers.Handler;
import com.gastawny.shockwave.models.*;
import com.gastawny.shockwave.repositories.*;
import com.gastawny.shockwave.shared.GenericList;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocatedObjectService implements Handler<LocatedObject> {

    private final ExplosiveRepository explosiveRepository;
    private final GroundRepository groundRepository;
    private final LocatedObjectRepository locatedObjectRepository;
    private final ObjectFormatParameterValueRepository objectFormatParameterValueRepository;
    private final ObjectFormatParameterRepository objectFormatParameterRepository;

    public LocatedObjectService(
            LocatedObjectRepository locatedObjectRepository,
            ExplosiveRepository explosiveRepository,
            GroundRepository groundRepository,
            ObjectFormatParameterValueRepository objectFormatParameterValueRepository,
            ObjectFormatParameterRepository objectFormatParameterRepository
    ) {
        this.locatedObjectRepository = locatedObjectRepository;
        this.explosiveRepository = explosiveRepository;
        this.groundRepository = groundRepository;
        this.objectFormatParameterValueRepository = objectFormatParameterValueRepository;
        this.objectFormatParameterRepository = objectFormatParameterRepository;
    }

    @Override
    public String getType() {
        return "locatedObjects";
    }

    @Override
    public Class<LocatedObject> getEntityClass() {
        return LocatedObject.class;
    }

    @Override
    public Object getService() {
        return new LocatedObjectService(locatedObjectRepository, explosiveRepository, groundRepository, objectFormatParameterValueRepository, objectFormatParameterRepository);
    }

    @Override
    public List<LocatedObject> findAll() {
        return locatedObjectRepository.findAll();
    }

    @Override
    public LocatedObject findById(Long id) {
        return locatedObjectRepository.findById(id).orElse(null);
    }

    @Override
    public LocatedObject save(Object entity) {
        ObjectMapper mapper = new ObjectMapper();
        LocatedObject locatedObject = mapper.convertValue(entity, LocatedObject.class);

        locatedObject.getObjectFormatParameterValues()
                .stream()
                .toList()
                .forEach(locatedObject::addObjectFormatParameterValue);

        return locatedObjectRepository.save(locatedObject);
    }


    @Override
    public LocatedObject update(LocatedObject entity) {
        entity.getObjectFormatParameterValues()
                .stream()
                .toList()
                .forEach(entity::addObjectFormatParameterValue);

        return locatedObjectRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<GenericList> find2Select() {
        return locatedObjectRepository.findBy(GenericList.class);
    }
}
