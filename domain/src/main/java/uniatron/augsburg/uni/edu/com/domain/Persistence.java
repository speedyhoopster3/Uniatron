package uniatron.augsburg.uni.edu.com.domain;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import uniatron.augsburg.uni.edu.com.domain.dao.AppUsageDao;
import uniatron.augsburg.uni.edu.com.domain.dao.StepCountDao;
import uniatron.augsburg.uni.edu.com.domain.dao.TimeCreditDao;
import uniatron.augsburg.uni.edu.com.domain.model.StepCount;

@Database(version = 1, entities = {StepCount.class})
public abstract class Persistence extends RoomDatabase {
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
     * Create a persistence database.
     *
     * @param context The application context.
     * @return the persistence database.
     */
    public static Persistence create(@NonNull final Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                Persistence.class,
                "uniatron")
                .build();
    }
}
