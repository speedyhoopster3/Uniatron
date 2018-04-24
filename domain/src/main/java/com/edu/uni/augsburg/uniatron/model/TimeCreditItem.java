package com.edu.uni.augsburg.uniatron.model;

/**
 * @author Fabio Hellmann
 */
public enum TimeCreditItem {
    CREDIT_10000(10_000, 30),
    CREDIT_7500(7_500, 20),
    CREDIT_5000(5_000, 10),
    CREDIT_4000(4_000, 8),
    CREDIT_3000(3_000, 6),
    CREDIT_2000(2_000, 4),
    CREDIT_1000(1_000, 2);

    private final int stepCount;
    private final int timeInMinutes;

    TimeCreditItem(int stepCount, int timeInMinutes) {
        this.stepCount = stepCount;
        this.timeInMinutes = timeInMinutes;
    }

    /**
     * Get the step count.
     *
     * @return The step count.
     */
    public int getStepCount() {
        return stepCount;
    }

    /**
     * Get the time in minutes.
     *
     * @return The time.
     */
    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    /**
     * Checks whether this time credit is usable with the specified amount of remaining steps.
     *
     * @param remainingStepCount The remaining step count.
     * @return <code>true</code> if this time credit is usable, <code>false</code> otherwise.
     */
    public boolean isUsable(final int remainingStepCount) {
        return remainingStepCount >= stepCount;
    }
}
