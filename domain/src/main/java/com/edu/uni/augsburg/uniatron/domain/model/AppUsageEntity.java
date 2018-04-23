package com.edu.uni.augsburg.uniatron.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import com.edu.uni.augsburg.uniatron.domain.converter.DateConverter;
import com.edu.uni.augsburg.uniatron.model.AppUsage;

@Entity(indices = {@Index("app_name")})
@TypeConverters({DateConverter.class})
public class AppUsageEntity implements AppUsage {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "app_name")
    private String appName;
    @ColumnInfo(name = "timestamp")
    private Date timestamp;
    @ColumnInfo(name = "usage_time_in_seconds")
    private int usageTimeInSeconds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getUsageTimeInSeconds() {
        return usageTimeInSeconds;
    }

    public void setUsageTimeInSeconds(int usageTimeInSeconds) {
        this.usageTimeInSeconds = usageTimeInSeconds;
    }
}
