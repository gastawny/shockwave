package com.gastawny.shockwave.repositories;

import com.gastawny.shockwave.dto.bombThreat.BombThreatReportDTO;
import com.gastawny.shockwave.models.BombThreat;

import java.util.List;

public interface BombThreatRepository extends BaseRepository<BombThreat, Long> {
    List<BombThreatReportDTO> findBy();
}
