package uniatron.augsburg.uni.edu.com.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(indices = {@Index("app_name")})
public class AppUsage {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "app_name")
    private String appName;
    @ColumnInfo(name = "timestamp")
    private Date timestamp;
    @ColumnInfo(name = "usage_time_in_seconds")
    private int usageTimeInSeconds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getUsageTimeInSeconds() {
        return usageTimeInSeconds;
    }

    public void setUsageTimeInSeconds(int usageTimeInSeconds) {
        this.usageTimeInSeconds = usageTimeInSeconds;
    }
}
