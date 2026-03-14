package com.gastawny.shockwave.shared.formulas.calculations;

import com.gastawny.shockwave.shared.formulas.Calculable;

public class Max implements Calculable {

    @Override
    public String execute(String expression) {
        return expression.replaceAll("max\\(", "T(java.lang.Math).max(");
    }
}
