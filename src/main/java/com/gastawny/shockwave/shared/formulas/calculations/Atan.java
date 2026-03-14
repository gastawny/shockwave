package com.gastawny.shockwave.shared.formulas.calculations;

import com.gastawny.shockwave.shared.formulas.Calculable;

public class Atan implements Calculable {

    @Override
    public String execute(String expression) {
        return expression.replaceAll("atan\\(", "T(java.lang.Math).atan(");
    }
}
