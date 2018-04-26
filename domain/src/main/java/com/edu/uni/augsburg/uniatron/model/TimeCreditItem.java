package com.edu.uni.augsburg.uniatron.model;

/**
 * The declared credits for {@link TimeCredit}.
 *
 * @author Fabio Hellmann
 */
public enum TimeCreditItem {
    /** The time credit for 10000 steps. */
    CREDIT_10000(10_000, 30),
    /** The time credit for 7500 steps. */
    CREDIT_7500(7_500, 20),
    /** The time credit for 5000 steps. */
    CREDIT_5000(5_000, 10),
    /** The time credit for 4000 steps. */
    CREDIT_4000(4_000, 8),
    /** The time credit for 3000 steps. */
    CREDIT_3000(3_000, 6),
    /** The time credit for 2000 steps. */
    CREDIT_2000(2_000, 4),
    /** The time credit for 1000 steps. */
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
     * Checks whether this time credit is usable with the
     * specified amount of remaining steps.
     *
     * @param remainingStepCount The remaining step count.
     * @return <code>true</code> if this time credit is usable,
     * <code>false</code> otherwise.
     */
    public boolean isUsable(final int remainingStepCount) {
        return remainingStepCount >= stepCount;
    }
}
