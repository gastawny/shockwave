package com.gastawny.shockwave.shared.formulas.calculations;

import com.gastawny.shockwave.shared.formulas.Calculable;

public class Min implements Calculable {

    @Override
    public String execute(String expression) {
        return expression.replaceAll("min\\(", "T(java.lang.Math).min(");
    }
}
