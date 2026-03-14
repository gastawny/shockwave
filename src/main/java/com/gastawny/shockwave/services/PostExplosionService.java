package com.gastawny.shockwave.services;

import com.gastawny.shockwave.dto.formula.CircleDTO;
import com.gastawny.shockwave.dto.formula.FormulaResult;
import com.gastawny.shockwave.models.File;
import com.gastawny.shockwave.models.PostExplosion;
import com.gastawny.shockwave.repositories.FileRepository;
import com.gastawny.shockwave.repositories.FormulaRepository;
import com.gastawny.shockwave.repositories.PostExplosionRepository;
import com.gastawny.shockwave.shared.GenericList;
import com.gastawny.shockwave.shared.handlers.Handler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PostExplosionService implements Handler<PostExplosion> {

    private final PostExplosionRepository postExplosionRepository;
    private final FileRepository fileRepository;
    private final FormulaRepository formulaRepository;
    private final FormulaService formulaService;

    public PostExplosionService(PostExplosionRepository postExplosionRepository, FileRepository fileRepository, FormulaRepository formulaRepository, FormulaService formulaService) {
        this.postExplosionRepository = postExplosionRepository;
        this.fileRepository = fileRepository;
        this.formulaRepository = formulaRepository;
        this.formulaService = formulaService;
    }

    @Override
    public String getType() {
        return "postExplosions";
    }

    @Override
    public Class<PostExplosion> getEntityClass() {
        return PostExplosion.class;
    }

    @Override
    public Object getService() {
        return new PostExplosionService(postExplosionRepository, fileRepository, formulaRepository, formulaService);
    }

    @Override
    public List<PostExplosion> findAll() {
        return postExplosionRepository.findAll();
    }

    @Override
    public PostExplosion findById(Long id) {
        return postExplosionRepository.findById(id).orElse(null);
    }

    @Override
    public PostExplosion save(Map<String, Object> entity) {
        return null;
    }

    @Override
    public PostExplosion update(Map<String, Object> entity) {
        return null;
    }

    public PostExplosion saveOrUpdate(PostExplosion postExplosion, MultipartFile[] multipartFiles) {
        if (postExplosion.getId() != null && multipartFiles != null && multipartFiles.length > 0 && multipartFiles[0] == null) {
            var existingPe = postExplosionRepository.findById(postExplosion.getId()).orElse(null);

            if (existingPe != null) {
                List<File> filesToDelete = existingPe.getFiles();

                if (filesToDelete != null && !filesToDelete.isEmpty()) {
                    existingPe.setFiles(new ArrayList<>());
                    postExplosionRepository.save(existingPe);
                    fileRepository.deleteAll(filesToDelete);
                }
            }
        }

        List<File> existing = postExplosion.getFiles();
        if (existing == null) existing = new ArrayList<>();

        if (multipartFiles == null || multipartFiles.length == 0) {
            postExplosion.setFiles(existing);
            return postExplosionRepository.save(postExplosion);
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
        postExplosion.setFiles(existing);

        return postExplosionRepository.save(postExplosion);
    }

    @Override
    public void deleteById(Long id) {
        postExplosionRepository.deleteById(id);
    }

    @Override
    public List<GenericList> find2Select() {
        return postExplosionRepository.findBy(GenericList.class);
    }

    public List<CircleDTO> getCircles(Double vestigeDistance) {
        var formulas = formulaRepository.findDistinctByTableTableName("post_explosions");

        Map<String, String> valuesParameters = Map.of(
                "Rvest", vestigeDistance.toString()
        );

        return formulas
                .stream()
                .map(formula -> {
                    try {
                        Double result = formulaService.execute(formula, valuesParameters);
                        var dto = new CircleDTO();
                        dto.setName(formula.getName());
                        dto.setRadius(result);

                        if(formula.getCircle() != null) {
                            dto.setColor(formula.getCircle().getColor());
                        } else {
                            dto.setColor("#FF0000");
                        }

                        return dto;
                    } catch (Throwable e) {
                        var dto = new CircleDTO();
                        dto.setName(formula.getName());
                        dto.setRadius(vestigeDistance);
                        dto.setColor("#FF0000");
                        return dto;
                    }
                })
                .toList();
    }
}
