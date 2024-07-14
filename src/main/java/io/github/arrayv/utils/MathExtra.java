package io.github.arrayv.utils;

import java.text.DecimalFormat;

public final class MathExtra {
    public double logBase(double n, double base) {
        double val = Math.log(n) / Math.log(base); // Taken from https://stackoverflow.com/a/21305821, thanks!
        return val;
    };
    public double nlogn(double n) {
        double val = n * (Math.log(n));
        return val;
    };
};
