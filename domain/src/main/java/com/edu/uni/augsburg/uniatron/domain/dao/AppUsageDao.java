package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.edu.uni.augsburg.uniatron.domain.converter.DateConverter;
import com.edu.uni.augsburg.uniatron.domain.model.AppUsageEntity;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * @author Fabio Hellmann
 */
@Dao
@TypeConverters({DateConverter.class})
public interface AppUsageDao {
    /**
     * Persist an app usage.
     *
     * @param appUsageEntity The app usage to persist.
     */
    @Insert(onConflict = REPLACE)
    void add(AppUsageEntity appUsageEntity);

    /**
     * Load the app usage time of each app summed by the app name.
     *
     * @param dateFrom The date to start searching.
     * @param dateTo The date to end searching.
     * @return The app usage time by app.
     */
    @Query("SELECT 0 id, app_name, date('now') timestamp, "
            + "SUM(usage_time_in_seconds) usage_time_in_seconds "
            + "FROM AppUsageEntity "
            + "WHERE timestamp BETWEEN :dateFrom AND :dateTo "
            + "GROUP BY app_name "
            + "ORDER BY SUM(usage_time_in_seconds) DESC")
    LiveData<List<AppUsageEntity>> loadAppUsageTime(Date dateFrom, Date dateTo);

    /**
     * Load the app usage in percent of each app summed by the app name.
     *
     * @param dateFrom The date to start searching.
     * @param dateTo The date to end searching.
     * @return The app usage percent by app.
     */
    @Query("SELECT 0 id, app_name, date('now') timestamp, "
            + "(SUM(usage_time_in_seconds) * 100 / aue1.time) usage_time_in_seconds "
            + "FROM AppUsageEntity, "
            + "(SELECT SUM(usage_time_in_seconds) time FROM AppUsageEntity "
            + "WHERE timestamp BETWEEN :dateFrom AND :dateTo) aue1 "
            + "WHERE timestamp BETWEEN :dateFrom AND :dateTo "
            + "GROUP BY app_name "
            + "ORDER BY SUM(usage_time_in_seconds) DESC")
    LiveData<List<AppUsageEntity>> loadAppUsagePercent(Date dateFrom, Date dateTo);
}
