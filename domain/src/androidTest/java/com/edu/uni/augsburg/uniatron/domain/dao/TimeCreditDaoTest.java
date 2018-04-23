package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.edu.uni.augsburg.uniatron.domain.AppDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimeCreditDaoTest {
    private AppDatabase mDb;
    private TimeCreditDao mDao;

    @Before
    public void setUp() throws Exception {
        final Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.timeCreditDao();
    }

    @After
    public void tearDown() throws Exception {
        mDb.close();
    }

    @Test
    public void add() {
    }

    @Test
    public void loadTodayRemainingTimeCredit() {
    }

    @Test
    public void loadSince() {
    }
}