package com.edu.uni.augsburg.uniatron.domain;

import android.support.annotation.NonNull;

import com.edu.uni.augsburg.uniatron.model.TimeCreditBuilder;

public final class DataRepository {

    private static DataRepository sInstance;
    private final AppDatabase mDatabase;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
    }

    public static DataRepository getInstance(final AppDatabase database) {
        synchronized (DataRepository.class) {
            if (sInstance == null) {
                sInstance = new DataRepository(database);
            }
        }
        return sInstance;
    }

    public void addTimeCredit(@NonNull final TimeCreditBuilder timeCreditBuilder) {
        mDatabase.timeCreditDao().add(timeCreditBuilder.build());
    }
}
