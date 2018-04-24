package com.edu.uni.augsburg.uniatron.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.edu.uni.augsburg.uniatron.domain.converter.DateConverter;
import com.edu.uni.augsburg.uniatron.model.TimeCredit;

import java.util.Date;

/**
 * @author Fabio Hellmann
 */
@Entity
@TypeConverters({DateConverter.class})
public class TimeCreditEntity implements TimeCredit {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;
    @ColumnInfo(name = "timestamp")
    private Date mTimestamp;
    @ColumnInfo(name = "time_in_minutes")
    private int mTimeInMinutes;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public Date getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.mTimestamp = timestamp;
    }

    public int getTimeInMinutes() {
        return mTimeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.mTimeInMinutes = timeInMinutes;
    }
}
