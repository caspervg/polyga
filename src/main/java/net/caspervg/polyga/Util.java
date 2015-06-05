package net.caspervg.polyga;

import java.util.Random;

public class Util {
    public static double doubleBetween(double start, double end) {
        Random random = new Random();

        // We need 64 bits because double have 53 bits precision, so int is too short
        // We have now a value between 0 and Long.MAX_VALUE.
        long value = -1L;
        while (value < 0)
            value = Math.abs(random.nextLong()); // Caution, Long.MIN_VALUE returns negative !

        // Cast to double
        double valueAsDouble = (double) value;

        // Scale so that Long.MAX_VALUE is exactly 1 !
        double diff = (end-start)/(double) Long.MAX_VALUE;

        return start + valueAsDouble*diff;
    }
}
