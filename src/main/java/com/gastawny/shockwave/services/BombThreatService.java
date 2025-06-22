package com.gastawny.shockwave.services;

import com.gastawny.shockwave.dto.bombThreat.ReqBombThreatDTO;
import com.gastawny.shockwave.models.BombThreat;
import com.gastawny.shockwave.repositories.BombThreatRepository;
import com.gastawny.shockwave.repositories.FormThreatRepository;
import com.gastawny.shockwave.repositories.LocatedObjectRepository;
import com.gastawny.shockwave.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BombThreatService {

    private final LocatedObjectRepository locatedObjectRepository;
    private final UserRepository userRepository;
    private final FormThreatRepository formThreatRepository;
    private final BombThreatRepository bombThreatRepository;

    public BombThreatService(LocatedObjectRepository locatedObjectRepository, UserRepository userRepository, FormThreatRepository formThreatRepository, BombThreatRepository bombThreatRepository) {
        this.locatedObjectRepository = locatedObjectRepository;
        this.userRepository = userRepository;
        this.formThreatRepository = formThreatRepository;
        this.bombThreatRepository = bombThreatRepository;
    }

    public ReqBombThreatDTO save(ReqBombThreatDTO req) {
        var locatedObject = locatedObjectRepository.getReferenceById(req.getLocatedObjectId());
        var user = userRepository.getReferenceById(req.getUserId());
        var formThreat = formThreatRepository.getReferenceById(req.getFormThreatId());

        var bombThreat = new BombThreat();

        bombThreat.setLocatedObject(locatedObject);
        bombThreat.setUser(user);
        bombThreat.setFormThreat(formThreat);
        bombThreat.setName(req.getName());
        bombThreat.setFormThreatDescription(req.getFormThreatDescription());
        bombThreat.setObjectNotFoundDescription(req.getObjectNotFoundDescription());

        bombThreatRepository.save(bombThreat);

        return req;
    }
}
