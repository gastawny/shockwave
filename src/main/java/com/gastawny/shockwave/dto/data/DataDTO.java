package com.gastawny.shockwave.dto.data;

import com.gastawny.shockwave.shared.enums.ValueType;
import com.gastawny.shockwave.models.Value;
import lombok.Data;

@Data
public class DataDTO {
    private Long data_id;
    private Long data_type_id;
    private String name;
    private ValueType value_type;
    private Value value;
}
