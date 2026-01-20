package com.gastawny.shockwave.dto.bombThreat;

import java.time.LocalDateTime;

public interface BombThreatReportDTO {
    Long getId();
    String getName();
    LocalDateTime getCreatedAt();
}
