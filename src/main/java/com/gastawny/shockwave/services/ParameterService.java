package com.gastawny.shockwave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.models.Ground;
import com.gastawny.shockwave.models.Parameter;
import com.gastawny.shockwave.models.ParameterDependency;
import com.gastawny.shockwave.repositories.MetadataRepository;
import com.gastawny.shockwave.repositories.ParameterRepository;
import com.gastawny.shockwave.shared.GenericList;
import com.gastawny.shockwave.shared.enums.ValueType;
import com.gastawny.shockwave.shared.handlers.Handler;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParameterService implements Handler<Parameter> {

    private final MetadataRepository metadataRepository;
    private final ParameterRepository parameterRepository;

    public ParameterService(MetadataRepository metadataRepository, ParameterRepository parameterRepository) {
        this.metadataRepository = metadataRepository;
        this.parameterRepository = parameterRepository;
    }

    public Double resolveParameterDependencySymbol(ParameterDependency dependency, Long dataId) {
        if (dependency == null) {
            return null;
        }

        return metadataRepository.resolveParameterDependencySymbol(
                dependency.getReferenceTable(),
                dependency.getReferenceColumn(),
                dataId
        );
    }

    @Override
    public String getType() {
        return "parameters";
    }

    @Override
    public Class<Parameter> getEntityClass() {
        return Parameter.class;
    }

    @Override
    public Object getService() {
        return new ParameterService(metadataRepository, parameterRepository);
    }

    @Override
    public List<Parameter> findAll() {
        return parameterRepository.findAll();
    }

    @Override
    public Parameter findById(Long id) {
        return parameterRepository.findById(id).orElse(null);
    }

    @Override
    public Parameter save(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        var parameter = mapper.convertValue(entity, Parameter.class);
        parameter.setValueType(ValueType.STRING);
        parameter.setSymbol(parameter.getName().toUpperCase().replaceAll("[^A-Z0-9]", "_"));
        parameter.setTableName(entity.get("tableName").toString());

        return parameterRepository.save(parameter);
    }

    @Override
    public Parameter update(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        var parameter = mapper.convertValue(entity, Parameter.class);
        parameter.setValueType(ValueType.STRING);
        parameter.setSymbol(parameter.getName().toUpperCase().replaceAll("[^A-Z0-9]", "_"));
        parameter.setTableName(entity.get("tableName").toString());

        return parameterRepository.save(parameter);
    }

    @Override
    public void deleteById(Long id) {
        parameterRepository.deleteById(id);
    }

    @Override
    public List<GenericList> find2Select() {
        return parameterRepository.findBy(GenericList.class);
    }

    public List<Parameter> getTableParameters(LinkedHashMap<String, Object> req) {
        String table = (String) req.get("tableName");

        return parameterRepository.findByTableNameLike(table);
    }
}