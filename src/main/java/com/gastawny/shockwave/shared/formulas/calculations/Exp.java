package com.gastawny.shockwave.shared.formulas.calculations;

import com.gastawny.shockwave.shared.formulas.Calculable;

public class Exp implements Calculable {

    @Override
    public String execute(String expression) {
        return expression.replaceAll("exp\\(", "T(java.lang.Math).exp(");
    }
}
