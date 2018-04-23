package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.edu.uni.augsburg.uniatron.domain.converter.DateConverter;
import com.edu.uni.augsburg.uniatron.domain.model.TimeCreditEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters({DateConverter.class})
public interface TimeCreditDao {
    /**
     * Persist a time credit.
     *
     * @param timeCreditEntity The time credit to persist.
     */
    @Insert(onConflict = REPLACE)
    void add(@NonNull TimeCreditEntity timeCreditEntity);

    /**
     * Query the sum of remaining time credits for the current date.
     *
     * @return the remaining time credits.
     */
    @Query("SELECT SUM(TimeCreditEntity.time_in_minutes) FROM TimeCreditEntity " +
            "WHERE date(TimeCreditEntity.timestamp) >= date('now')")
    LiveData<Integer> loadTodayRemainingTimeCredit();

    /**
     * Query all the time credits.
     *
     * @return the time credits.
     */
    @Query("SELECT * FROM TimeCreditEntity ORDER BY timestamp DESC")
    DataSource.Factory<Integer, TimeCreditEntity> loadAll();
}
