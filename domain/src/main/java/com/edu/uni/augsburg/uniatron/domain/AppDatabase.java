package com.edu.uni.augsburg.uniatron.domain;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.edu.uni.augsburg.uniatron.domain.converter.DateConverter;
import com.edu.uni.augsburg.uniatron.domain.dao.AppUsageDao;
import com.edu.uni.augsburg.uniatron.domain.dao.StepCountDao;
import com.edu.uni.augsburg.uniatron.domain.dao.TimeCreditDao;
import com.edu.uni.augsburg.uniatron.domain.model.AppUsageEntity;
import com.edu.uni.augsburg.uniatron.domain.model.StepCounteEntity;
import com.edu.uni.augsburg.uniatron.domain.model.TimeCreditEntity;

@Database(version = 1, entities = {StepCounteEntity.class, AppUsageEntity.class, TimeCreditEntity.class})
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;

    /**
     * Get the dao to access the step counts.
     *
     * @return the step count dao.
     */
    public abstract StepCountDao stepCountDao();

    /**
     * Get the app usage dao.
     *
     * @return the app usage dao.
     */
    public abstract AppUsageDao appUsageDao();

    /**
     * Get the time credit dao.
     *
     * @return the time credit dao.
     */
    public abstract TimeCreditDao timeCreditDao();

    /**
     * Create the app database.
     *
     * @param context The application context.
     * @return the app database.
     */
    public static AppDatabase getInstance(@NonNull final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "uniatron")
                            .build();
                }
            }
        }
        return sInstance;
    }
}
