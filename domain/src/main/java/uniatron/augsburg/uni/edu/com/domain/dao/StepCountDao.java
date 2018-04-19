package uniatron.augsburg.uni.edu.com.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import uniatron.augsburg.uni.edu.com.domain.model.StepCount;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface StepCountDao {
    @Insert(onConflict = REPLACE)
    void add(@NonNull StepCount stepCount);

    @Query("SELECT * FROM StepCount WHERE StepCount.timestamp >= :date")
    LiveData<List<StepCount>> loadSince(@NonNull Date date);
}
