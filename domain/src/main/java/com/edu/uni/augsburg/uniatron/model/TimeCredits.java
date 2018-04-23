package com.edu.uni.augsburg.uniatron.model;

import com.edu.uni.augsburg.uniatron.domain.model.TimeCreditEntity;

import java.util.Date;

public enum TimeCredits {
    CREDIT_10000(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(30);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    }),
    CREDIT_7500(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(20);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    }),
    CREDIT_5000(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(10);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    }),
    CREDIT_3000(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(6);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    }),
    CREDIT_2000(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(4);
        timeCreditEntity.setTimestamp(new Date());
        return timeCreditEntity;
    }),
    CREDIT_1000(() -> {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(2);
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
}
