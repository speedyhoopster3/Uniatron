package com.edu.uni.augsburg.uniatron.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import com.edu.uni.augsburg.uniatron.domain.converter.DateConverter;
import com.edu.uni.augsburg.uniatron.model.StepCount;

/**
 * @author Fabio Hellmann
 */
@Entity
@TypeConverters({DateConverter.class})
public class StepCountEntity implements StepCount {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;
    @ColumnInfo(name = "timestamp")
    private Date mTimestamp;
    @ColumnInfo(name = "step_count")
    private int mStepCount;

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

    public int getStepCount() {
        return mStepCount;
    }

    public void setStepCount(int stepCount) {
        this.mStepCount = stepCount;
    }
}
