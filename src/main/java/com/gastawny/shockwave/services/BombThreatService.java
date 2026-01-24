package com.gastawny.shockwave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.dto.bombThreat.BombThreatReportDTO;
import com.gastawny.shockwave.models.BombThreat;
import com.gastawny.shockwave.models.File;
import com.gastawny.shockwave.reports.BombThreatReport;
import com.gastawny.shockwave.repositories.BombThreatRepository;
import com.gastawny.shockwave.repositories.FileRepository;
import com.gastawny.shockwave.shared.GenericList;
import com.gastawny.shockwave.shared.handlers.Handler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BombThreatService implements Handler<BombThreat> {

    private final BombThreatRepository bombThreatRepository;
    private final BombThreatReport bombThreatReport;
    private final FileRepository fileRepository;

    public BombThreatService(BombThreatRepository bombThreatRepository, BombThreatReport bombThreatReport, FileRepository fileRepository) {
        this.bombThreatRepository = bombThreatRepository;
        this.bombThreatReport = bombThreatReport;
        this.fileRepository = fileRepository;
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
        return new BombThreatService(bombThreatRepository, bombThreatReport, fileRepository);
    }

    @Override
    public List<BombThreat> findAll() {
        return  bombThreatRepository.findAll();
    }

    @Override
    public BombThreat findById(Long id) {
        return bombThreatRepository.findById(id).orElse(null);
    }

    public BombThreat save(Map<String, Object> entity) {
        return null;
    }

    public BombThreat saveOrUpdate(BombThreat bombThreat, MultipartFile[] multipartFiles) {
        if (bombThreat.getId() != null && multipartFiles != null && multipartFiles.length > 0 && multipartFiles[0] == null) {
            BombThreat existingBt = bombThreatRepository.findById(bombThreat.getId()).orElse(null);

            if (existingBt != null) {
                List<File> filesToDelete = existingBt.getFiles();

                if (filesToDelete != null && !filesToDelete.isEmpty()) {
                    existingBt.setFiles(new ArrayList<>());
                    bombThreatRepository.save(existingBt);
                    fileRepository.deleteAll(filesToDelete);
                }
            }
        }

        List<File> existing = bombThreat.getFiles();
        if (existing == null) existing = new ArrayList<>();

        if (multipartFiles == null || multipartFiles.length == 0) {
            bombThreat.setFiles(existing);
            return bombThreatRepository.save(bombThreat);
        }

        List<File> newFiles = new ArrayList<>();
        Arrays.stream(multipartFiles).forEach(multipartFile -> {
            if (multipartFile == null || multipartFile.isEmpty()) return;
            try {
                File file = new File();
                file.setName(multipartFile.getOriginalFilename());
                file.setData(multipartFile.getBytes());
                newFiles.add(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        existing.addAll(newFiles);
        bombThreat.setFiles(existing);

        return bombThreatRepository.save(bombThreat);
    }

    @Override
    public BombThreat update(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();
        BombThreat bt = mapper.convertValue(entity, BombThreat.class);
        return bombThreatRepository.save(bt);
    }

    @Override
    public void deleteById(Long id) {
        bombThreatRepository.deleteById(id);
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
