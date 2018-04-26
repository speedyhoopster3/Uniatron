package com.edu.uni.augsburg.uniatron.model;

import java.util.Date;

/**
 * @author Fabio Hellmann
 */
public interface AppUsage {
    long getId();

    String getAppName();

    Date getTimestamp();

    int getUsageTimeInSeconds();
}
