package com.gastawny.shockwave.dto.explosive;

import com.gastawny.shockwave.models.Explosive;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ExplosiveDataDTO {
    private Explosive explosive;
    List<Map<String, Object>> parametersValues;
}
