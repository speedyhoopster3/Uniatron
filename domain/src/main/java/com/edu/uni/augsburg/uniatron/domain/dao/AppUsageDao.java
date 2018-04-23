package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.edu.uni.augsburg.uniatron.domain.converter.DateConverter;
import com.edu.uni.augsburg.uniatron.domain.model.AppUsageEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters({DateConverter.class})
public interface AppUsageDao {
    /**
     * Persist an app usage.
     *
     * @param appUsageEntity The app usage to persist.
     */
    @Insert(onConflict = REPLACE)
    void add(@NonNull AppUsageEntity appUsageEntity);

    /**
     * Query all app usages.
     *
     * @return the app usages.
     */
    @Query("SELECT * FROM AppUsageEntity ORDER BY timestamp DESC")
    DataSource.Factory<Integer, AppUsageEntity> loadAll();
}
