package uniatron.augsburg.uni.edu.com.domain;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import uniatron.augsburg.uni.edu.com.domain.dao.AppUsageDao;
import uniatron.augsburg.uni.edu.com.domain.dao.StepCountDao;
import uniatron.augsburg.uni.edu.com.domain.model.StepCount;

@Database(version = 1, entities = {StepCount.class})
public abstract class Persistence extends RoomDatabase {
    public abstract StepCountDao stepCountDao();

    public abstract AppUsageDao appUsageDao();
}
