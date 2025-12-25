package com.gastawny.shockwave.dto.locatedObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqLocatedObjectDTO {
    private Long explosiveId;
    private Long groundId;
    private Long objectFormatId;
    private List<ObjectFormatParametersValuesDTO> parametersValues;
}
