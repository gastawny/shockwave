package com.gastawny.shockwave.shared.formulas.calculations;

import com.gastawny.shockwave.shared.formulas.Calculable;

public class Sinal implements Calculable {

    @Override
    public String execute(String expression) {
        return expression.replaceAll("sinal\\(", "T(java.lang.Math).signum(");
    }
}