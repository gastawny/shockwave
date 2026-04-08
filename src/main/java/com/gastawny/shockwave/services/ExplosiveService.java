package com.gastawny.shockwave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.dto.bombThreat.BombThreatReportDTO;
import com.gastawny.shockwave.dto.explosive.ExplosiveDataDTO;
import com.gastawny.shockwave.models.*;
import com.gastawny.shockwave.reports.ExplosiveReport;
import com.gastawny.shockwave.repositories.ExplosiveRepository;
import com.gastawny.shockwave.repositories.FileRepository;
import com.gastawny.shockwave.shared.GenericList;
import com.gastawny.shockwave.shared.enums.ValueType;
import com.gastawny.shockwave.shared.handlers.Handler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Service
public class ExplosiveService implements Handler<Explosive> {

    private final ExplosiveRepository explosiveRepository;
    private final ExplosiveReport explosiveReport;
    private final FileRepository fileRepository;

    public ExplosiveService(ExplosiveRepository explosiveRepository, ExplosiveReport formulaReport, FileRepository fileRepository) {
        this.explosiveReport = formulaReport;
        this.explosiveRepository = explosiveRepository;
        this.fileRepository = fileRepository;
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
        return new ExplosiveService(explosiveRepository, explosiveReport, fileRepository);
    }

    @Override
    public List<Explosive> findAll() {
        return explosiveRepository.findAll();
    }

    @Override
    public Explosive findById(Long id) {
        return explosiveRepository.findById(id).orElse(null);
    }

    @Override
    public Explosive save(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        var dto = mapper.convertValue(entity, ExplosiveDataDTO.class);
        var explosive = dto.getExplosive();

        explosive.setName((explosive.getName()));

        explosive.setExplosiveParameters(new ArrayList<>());

        for (Map<String, Object> pv : dto.getParametersValues()) {
            Long parameterId = parseLongSafe(pv.get("parameterId"));
            String value = pv.get("value") == null ? "" : pv.get("value").toString();

            ExplosiveParameter ep = new ExplosiveParameter();

            ep.setExplosive(explosive);

            Parameter parameter = new Parameter();
            parameter.setId(parameterId);
            ep.setParameter(parameter);

            ValueType inferredType = pv.get("valueType") != null
                ? ValueType.valueOf(pv.get("valueType").toString())
                : ValueType.STRING;

            if(inferredType == ValueType.STRING) {
                ValueStr valueStr = new ValueStr();
                valueStr.setRaw(value);
                ep.setValue(valueStr);
            } else if(inferredType == ValueType.NUMBER) {
                ValueNum valueNum = new ValueNum();
                if(value != null && !value.isEmpty()) {
                    try {
                        valueNum.setRaw(Double.parseDouble(value));
                    } catch (NumberFormatException ex) {
                        valueNum.setRaw(null);
                    }
                } else {
                    valueNum.setRaw(null);
                }
                ep.setValue(valueNum);
            } else {
                ValueText valueText = new ValueText();
                valueText.setRaw(value);
                ep.setValue(valueText);
            }

            explosive.addExplosiveParameter(ep);
        }

        return explosiveRepository.save(explosive);
    }

    @Override
    public Explosive update(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        var dto = mapper.convertValue(entity, ExplosiveDataDTO.class);
        var explosive = dto.getExplosive();

        explosive.setName((explosive.getName()));
        explosive.setId(dto.getExplosive().getId());

        explosive.setExplosiveParameters(new ArrayList<>());

        for(Map<String, Object> pv : dto.getParametersValues()) {
            Long parameterId = parseLongSafe(pv.get("parameterId"));
            String value = pv.get("value") == null ? "" : pv.get("value").toString();

            ExplosiveParameter ep = new ExplosiveParameter();

            ep.setExplosive(explosive);
            ep.setId(parseLongSafe(pv.get("explosiveParameterId")));

            Parameter parameter = new Parameter();
            parameter.setId(parameterId);
            ep.setParameter(parameter);

            ValueType inferredType = pv.get("valueType") != null
                ? ValueType.valueOf(pv.get("valueType").toString())
                : ValueType.STRING;

            if(inferredType == ValueType.STRING) {
                ValueStr valueStr = new ValueStr();
                valueStr.setRaw(value);
                ep.setValue(valueStr);
            } else if(inferredType == ValueType.NUMBER) {
                ValueNum valueNum = new ValueNum();
                if(value != null && !value.isEmpty()) {
                    try {
                        valueNum.setRaw(Double.parseDouble(value));
                    } catch (NumberFormatException ex) {
                        valueNum.setRaw(null);
                    }
                } else {
                    valueNum.setRaw(null);
                }
                ep.setValue(valueNum);
            } else {
                ValueText valueText = new ValueText();
                valueText.setRaw(value);
                ep.setValue(valueText);
            }

            explosive.addExplosiveParameter(ep);
        }

        return explosiveRepository.save(explosive);
    }

    @Override
    public void deleteById(Long id) {
        explosiveRepository.deleteById(id);
    }

    @Override
    public List<GenericList> find2Select() {
        return explosiveRepository.findBy(GenericList.class);
    }

    public ExplosiveDataDTO getDataByExplosiveId(LinkedHashMap<String, Object> req) {
        Long id = Long.valueOf(req.get("id").toString());

        var explosive = explosiveRepository.findById(id).orElse(null);

        var dto = new ExplosiveDataDTO();
        dto.setExplosive(explosive);
        dto.setParametersValues(explosiveRepository.findDataByExplosiveId(id));

        return dto;
    }

    private Long parseLongSafe(Object o) {
        if (o == null) return null;
        String s = o.toString().trim();
        if (s.isEmpty()) return null;
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public File getImage(Long id) {
        Explosive explosive = explosiveRepository.findById(id).orElse(null);
        if (explosive == null) return null;
        return explosive.getImage();
    }

    public Explosive updateImage(Long id, MultipartFile imageFile) throws IOException {
        Explosive explosive = explosiveRepository.findById(id).orElse(null);
        if (explosive == null) return null;

        File oldImage = explosive.getImage();
        if (oldImage != null) {
            explosive.setImage(null);
            explosiveRepository.save(explosive);
            fileRepository.delete(oldImage);
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            File file = new File();
            file.setName(imageFile.getOriginalFilename());
            file.setData(imageFile.getBytes());
            fileRepository.save(file);
            explosive.setImage(file);
        }

        return explosiveRepository.save(explosive);
    }

    public void getReportById(OutputStream outputStream) throws IOException {
        explosiveReport.getById(outputStream);
    }
}
