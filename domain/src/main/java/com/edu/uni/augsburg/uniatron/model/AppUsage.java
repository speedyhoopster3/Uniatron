package com.edu.uni.augsburg.uniatron.model;

import java.util.Date;

public interface AppUsage {

    int getId();

    String getAppName();

    Date getTimestamp();

    int getUsageTimeInSeconds();
}
