package com.edu.uni.augsburg.uniatron.domain.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * @author Fabio Hellmann
 */
public final class DateConverter {
    private DateConverter() {
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
