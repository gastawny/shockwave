package com.gastawny.shockwave.services;

import com.gastawny.shockwave.handlers.Handler;
import com.gastawny.shockwave.models.FormThreat;
import com.gastawny.shockwave.repositories.FormThreatRepository;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public FormThreat save(Object entity) {
        return null;
    }

    @Override
    public FormThreat update(FormThreat entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<GenericList> find2Select() {
        return formThreatRepository.findBy(GenericList.class);
    }
}
