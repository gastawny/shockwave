package com.gastawny.shockwave.services;

import com.gastawny.shockwave.dto.locatedObject.ReqLocatedObjectDTO;
import com.gastawny.shockwave.models.LocatedObject;
import com.gastawny.shockwave.repositories.ExplosiveRepository;
import com.gastawny.shockwave.repositories.GroundRepository;
import com.gastawny.shockwave.repositories.LocatedObjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocatedObjectService {

    private final ExplosiveRepository explosiveRepository;
    private final GroundRepository groundRepository;
    private final LocatedObjectRepository locatedObjectRepository;

    public LocatedObjectService(LocatedObjectRepository locatedObjectRepository, ExplosiveRepository explosiveRepository, GroundRepository groundRepository) {
        this.locatedObjectRepository = locatedObjectRepository;
        this.explosiveRepository = explosiveRepository;
        this.groundRepository = groundRepository;
    }

    public Optional<LocatedObject> findById(Long id) {
        return locatedObjectRepository.findById(id);
    }

    public ReqLocatedObjectDTO save(ReqLocatedObjectDTO req) {
        var explosive = explosiveRepository.getReferenceById(req.getExplosiveId());
        var ground = groundRepository.getReferenceById(req.getGroundId());

        var locatedObject = new LocatedObject();

        locatedObject.setExplosive(explosive);
        locatedObject.setGround(ground);

        locatedObjectRepository.save(locatedObject);

        return req;
    }
}
