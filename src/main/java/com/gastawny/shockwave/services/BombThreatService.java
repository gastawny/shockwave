package com.gastawny.shockwave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.dto.bombThreat.BombThreatReportDTO;
import com.gastawny.shockwave.models.ParameterDependency;
import com.gastawny.shockwave.reports.BombThreatReport;
import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.models.BombThreat;
import com.gastawny.shockwave.repositories.BombThreatRepository;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
public class BombThreatService implements Handler<BombThreat> {

    private final BombThreatRepository bombThreatRepository;
    private final BombThreatReport bombThreatReport;

    public BombThreatService(BombThreatRepository bombThreatRepository, BombThreatReport bombThreatReport) {
        this.bombThreatRepository = bombThreatRepository;
        this.bombThreatReport = bombThreatReport;
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
        return new BombThreatService(bombThreatRepository, bombThreatReport);
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

    public void getReportById(OutputStream outputStream, Long id) throws IOException {
        bombThreatReport.getById(outputStream, id);
    }

    public List<BombThreatReportDTO> getReports() {
        return bombThreatRepository.findBy();
    }
}
