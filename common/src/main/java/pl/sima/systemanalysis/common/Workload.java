package pl.sima.systemanalysis.common;

import java.security.SecureRandom;

public class Workload {
    private static final int TICK = 1_000_000;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static double useCpu(double ticks) {
        double r = 0;
        for (int i = 0; i < TICK * ticks; i++) {
            r += Math.cos(RANDOM.nextDouble() * 2.0 - 1.0);
        }
        return r;
    }
}
