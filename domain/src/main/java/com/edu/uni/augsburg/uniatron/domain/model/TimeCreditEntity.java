package com.edu.uni.augsburg.uniatron.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.edu.uni.augsburg.uniatron.domain.converter.DateConverter;
import com.edu.uni.augsburg.uniatron.model.TimeCredit;

import java.util.Date;

@Entity
@TypeConverters({DateConverter.class})
public class TimeCreditEntity implements TimeCredit {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "timestamp")
    private Date timestamp;
    @ColumnInfo(name = "time_in_minutes")
    private int timeInMinutes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }
}
