package com.edu.uni.augsburg.uniatron.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.edu.uni.augsburg.uniatron.domain.converter.DateConverter;
import com.edu.uni.augsburg.uniatron.model.AppUsage;

import java.util.Date;

/**
 * @author Fabio Hellmann
 */
@Entity(indices = {@Index("app_name")})
@TypeConverters({DateConverter.class})
public class AppUsageEntity implements AppUsage {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;
    @ColumnInfo(name = "app_name")
    private String mAppName;
    @ColumnInfo(name = "timestamp")
    private Date mTimestamp;
    @ColumnInfo(name = "usage_time_in_seconds")
    private int mUsageTimeInSeconds;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getAppName() {
        return mAppName;
    }

    public void setAppName(String appName) {
        this.mAppName = appName;
    }

    public Date getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.mTimestamp = timestamp;
    }

    public int getUsageTimeInSeconds() {
        return mUsageTimeInSeconds;
    }

    public void setUsageTimeInSeconds(int usageTimeInSeconds) {
        this.mUsageTimeInSeconds = usageTimeInSeconds;
    }
}
