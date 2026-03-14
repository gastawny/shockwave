package com.gastawny.shockwave.shared.formulas.calculations;

import com.gastawny.shockwave.shared.formulas.Calculable;

public class Erf implements Calculable {

    @Override
    public String execute(String expression) {
        return expression.replaceAll("\\berf\\b", "#erf");
    }

    public static double erf(double x) {
        return org.apache.commons.math3.special.Erf.erf(x);
    }
}
