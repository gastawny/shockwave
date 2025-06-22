package com.gastawny.shockwave.dto.locatedObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqLocatedObjectDTO {
    private Long explosiveId;
    private Long groundId;
}
