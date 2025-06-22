package com.gastawny.shockwave.dto.bombThreat;

import lombok.Data;

@Data
public class ReqBombThreatDTO {

    private Long userId;
    private Long formThreatId;
    private Long locatedObjectId;
    private String name;
    private String formThreatDescription;
    private String objectNotFoundDescription;
}
