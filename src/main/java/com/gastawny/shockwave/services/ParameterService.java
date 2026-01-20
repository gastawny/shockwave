package com.gastawny.shockwave.services;

import com.gastawny.shockwave.models.ParameterDependency;
import com.gastawny.shockwave.repositories.MetadataRepository;
import org.springframework.stereotype.Service;

@Service
public class ParameterService {

    private final MetadataRepository metadataRepository;

    public ParameterService(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
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
}