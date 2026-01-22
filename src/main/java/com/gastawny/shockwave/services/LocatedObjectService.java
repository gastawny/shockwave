package com.gastawny.shockwave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.models.*;
import com.gastawny.shockwave.repositories.*;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocatedObjectService implements Handler<LocatedObject> {

    private final ExplosiveRepository explosiveRepository;
    private final GroundRepository groundRepository;
    private final LocatedObjectRepository  locatedObjectRepository;
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
    public LocatedObject save(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        LocatedObject locatedObject = mapper.convertValue(entity, LocatedObject.class);

        locatedObject.getObjectFormatParameterValues()
                .stream()
                .toList()
                .forEach(locatedObject::addObjectFormatParameterValue);

        return locatedObjectRepository.save(locatedObject);
    }


    @Override
    public LocatedObject update(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        LocatedObject lo = mapper.convertValue(entity, LocatedObject.class);
        lo.getObjectFormatParameterValues()
                .stream()
                .toList()
                .forEach(lo::addObjectFormatParameterValue);

        return locatedObjectRepository.save(lo);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<GenericList> find2Select() {
        return locatedObjectRepository.findBy(GenericList.class);
    }

    public Map<String, String> getValues(Long locatedObjectId) {
        var locatedObject = findById(locatedObjectId);
        Map<String, String> values = new HashMap<>(Map.of());

        values.put("tab_k", locatedObject.getGround().getId().toString());
        values.put("dep_volume", locatedObject.getObjectFormat().getId().toString());
        values.put("densidade", explosiveRepository.findValueByParameterSymbol("densidade", locatedObject.getExplosive().getId()).get("value").toString());
        values.put("efeito_relativo_tnt", explosiveRepository.findValueByParameterSymbol("efeito_relativo_tnt", locatedObject.getExplosive().getId()).get("value").toString());

        for (var paramValue : locatedObject.getObjectFormatParameterValues()) {
            values.put(paramValue.getObjectFormatParameter().getParameter().getSymbol(), paramValue.getValue().getValue().toString());
        }
        return values;
    }
}
