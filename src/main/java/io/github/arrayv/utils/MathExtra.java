package io.github.arrayv.utils;

import java.text.DecimalFormat

public final class MathExtra {
    public int logBase(n, base) {
        int val = Math.log(n) / Math.log(base); // Taken from https://stackoverflow.com/a/21305821, thanks!
        return val;
    };
    public int nlogn(n) {
        int val = n * (Math.log(n));
        return val
    };
};
