package com.edu.uni.augsburg.uniatron.model;

import java.util.Date;

/**
 * @author Fabio Hellmann
 */
public interface StepCount {
    int getId();

    Date getTimestamp();

    int getStepCount();
}
