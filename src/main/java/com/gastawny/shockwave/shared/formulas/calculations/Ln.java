package com.gastawny.shockwave.shared.formulas.calculations;

import com.gastawny.shockwave.shared.formulas.Calculable;

public class Ln implements Calculable {

    @Override
    public String execute(String expression) {
        return expression.replaceAll("ln\\(", "T(java.lang.Math).log(");
    }
}
