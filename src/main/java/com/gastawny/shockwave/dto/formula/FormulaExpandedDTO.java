package com.gastawny.shockwave.dto.formula;

import com.gastawny.shockwave.models.Constant;
import com.gastawny.shockwave.models.FormulaComposition;
import com.gastawny.shockwave.models.Parameter;
import lombok.Data;

import java.util.List;

@Data
public class FormulaExpandedDTO {

    private Long id;
    private String name;
    private String expression;
    private List<Constant> constants;
    private List<Parameter> parameters;
}
