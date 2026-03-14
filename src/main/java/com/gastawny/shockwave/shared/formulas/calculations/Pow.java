package com.gastawny.shockwave.shared.formulas.calculations;

import com.gastawny.shockwave.shared.formulas.Calculable;

public class Pow implements Calculable {

    @Override
    public String execute(String expression) {
        return expression.replaceAll("pow\\(", "T(java.lang.Math).pow(");
    }
}
