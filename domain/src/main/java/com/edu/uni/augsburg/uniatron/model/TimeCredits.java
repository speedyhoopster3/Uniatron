package com.edu.uni.augsburg.uniatron.model;

import com.edu.uni.augsburg.uniatron.domain.model.TimeCreditEntity;

import java.util.Date;

public enum TimeCredits {
    CREDIT_10000(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(Constants.STEPS_10000_TIME_IN_MINUTES);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    }),
    CREDIT_7500(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(Constants.STEPS_7500_TIME_IN_MINUTES);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    }),
    CREDIT_5000(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(Constants.STEPS_5000_TIME_IN_MINUTES);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    }),
    CREDIT_3000(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(Constants.STEPS_3000_TIME_IN_MINUTES);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    }),
    CREDIT_2000(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(Constants.SETPS_2000_TIME_IN_MINUTES);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    }),
    CREDIT_1000(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(Constants.STEPS_1000_TIME_IN_MINUTES);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    });

    private final TimeCreditSupplier supplier;

    TimeCredits(TimeCreditSupplier supplier) {
        this.supplier = supplier;
    }

    /**
     * Build the time credit.
     *
     * @return The time credit.
     */
    public TimeCredit build() {
        return supplier.get();
    }

    @FunctionalInterface
    private interface TimeCreditSupplier {
        TimeCredit get();
    }

    private static final class Constants {
        public static final int STEPS_1000_TIME_IN_MINUTES = 2;
        public static final int SETPS_2000_TIME_IN_MINUTES = 4;
        public static final int STEPS_3000_TIME_IN_MINUTES = 6;
        public static final int STEPS_5000_TIME_IN_MINUTES = 10;
        public static final int STEPS_7500_TIME_IN_MINUTES = 20;
        public static final int STEPS_10000_TIME_IN_MINUTES = 30;
    }
}
