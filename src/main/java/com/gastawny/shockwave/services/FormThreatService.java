package com.gastawny.shockwave.services;

import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.models.FormThreat;
import com.gastawny.shockwave.repositories.FormThreatRepository;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FormThreatService implements Handler<FormThreat> {

    private final FormThreatRepository formThreatRepository;

    public FormThreatService(FormThreatRepository formThreatRepository) {
        this.formThreatRepository = formThreatRepository;
    }

    @Override
    public String getType() {
        return "formThreats";
    }

    @Override
    public Class<FormThreat> getEntityClass() {
        return FormThreat.class;
    }

    @Override
    public Object getService() {
        return new FormThreatService(formThreatRepository);
    }

    @Override
    public List<FormThreat> findAll() {
        return formThreatRepository.findAll();
    }

    @Override
    public FormThreat findById(Long id) {
        return formThreatRepository.findById(id).orElse(null);
    }

    @Override
    public FormThreat save(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        return formThreatRepository.save(mapper.convertValue(entity, FormThreat.class));
    }

    @Override
    public FormThreat update(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        FormThreat ft = mapper.convertValue(entity, FormThreat.class);
        return formThreatRepository.save(ft);
    }

    @Override
    public void deleteById(Long id) {
        formThreatRepository.deleteById(id);
    }

    @Override
    public List<GenericList> find2Select() {
        return formThreatRepository.findBy(GenericList.class);
    }
}
