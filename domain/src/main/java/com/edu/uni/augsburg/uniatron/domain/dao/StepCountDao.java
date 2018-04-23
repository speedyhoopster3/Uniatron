package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.edu.uni.augsburg.uniatron.domain.converter.DateConverter;
import com.edu.uni.augsburg.uniatron.domain.model.StepCounteEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters({DateConverter.class})
public interface StepCountDao {
    /**
     * Persist a step count.
     *
     * @param stepCount The step count to persist.
     */
    @Insert(onConflict = REPLACE)
    void add(@NonNull StepCounteEntity stepCount);

    /**
     * Load the remaining steps for today.
     *
     * @return The remaining steps.
     */
    @Query("SELECT SUM(step_count) FROM StepCounteEntity WHERE date(timestamp) = date('now')")
    LiveData<Integer> loadTodayRemainingSteps();

    /**
     * Query all the step counts.
     *
     * @return the step counts.
     */
    @Query("SELECT * FROM StepCounteEntity ORDER BY timestamp DESC")
    DataSource.Factory<Integer, StepCounteEntity> loadAll();
}
