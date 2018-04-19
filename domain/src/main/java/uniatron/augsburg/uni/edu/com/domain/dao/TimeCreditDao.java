package uniatron.augsburg.uni.edu.com.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import uniatron.augsburg.uni.edu.com.domain.model.TimeCredit;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TimeCreditDao {
    /**
     * Persist a time credit.
     *
     * @param timeCredit The time credit to persist.
     */
    @Insert(onConflict = REPLACE)
    void add(@NonNull TimeCredit timeCredit);

    /**
     * Query the sum of remaining time credits for the current date.
     *
     * @return the remaining time credits.
     */
    @Query("SELECT SUM(TimeCredit.time_in_minutes) FROM TimeCredit WHERE date(TimeCredit.timestamp) >= date(now())")
    LiveData<Integer> loadTodayRemainingTimeCredit();

    /**
     * Query all the time credits over time since the date.
     *
     * @param date The date to query since.
     * @return the time credits.
     */
    @Query("SELECT * FROM TimeCredit WHERE TimeCredit.timestamp >= :date")
    LiveData<List<TimeCredit>> loadSince(@NonNull Date date);
}
