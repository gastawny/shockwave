package com.gastawny.shockwave.dto.formula;

import com.gastawny.shockwave.models.Formula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormulaResult {
    Formula formula;
    Double result;
}
