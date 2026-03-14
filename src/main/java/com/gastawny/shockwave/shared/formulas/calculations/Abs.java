package com.gastawny.shockwave.shared.formulas.calculations;

import com.gastawny.shockwave.shared.formulas.Calculable;

public class Abs implements Calculable {

    @Override
    public String execute(String expression) {
        return expression.replaceAll("abs\\(", "T(java.lang.Math).abs(");
    }
}