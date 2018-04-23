package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.edu.uni.augsburg.uniatron.domain.AppDatabase;
import com.edu.uni.augsburg.uniatron.domain.model.AppUsageEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AppUsageDaoTest {
    private AppDatabase mDb;
    private AppUsageDao mDao;

    @Before
    public void setUp() throws Exception {
        final Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.appUsageDao();
    }

    @After
    public void tearDown() throws Exception {
        mDb.close();
    }

    @Test
    public void add() {
        final AppUsageEntity appUsageEntity = create("Test", new Date());
        mDao.add(appUsageEntity);

        assertThat(appUsageEntity.getId(), is(not(equalTo(0))));
    }

    @Test
    public void loadAll() {
        mDao.add(create("Test", createDate(-100)));
        mDao.add(create("Test1", createDate(-89)));
        mDao.add(create("Test2", createDate(-84)));
        mDao.add(create("Test3", createDate(-80)));
        mDao.add(create("Test4", createDate(-10)));
        mDao.add(create("Test5", createDate(0)));
        mDao.add(create("Test6", createDate(0)));

        final DataSource.Factory<Integer, AppUsageEntity> data = mDao.loadAll();
    }

    private Date createDate(int addDays) {
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DATE, addDays);
        return calendar.getTime();
    }

    private AppUsageEntity create(String name, Date date) {
        final AppUsageEntity appUsageEntity = new AppUsageEntity();
        appUsageEntity.setAppName(name);
        appUsageEntity.setTimestamp(date);
        appUsageEntity.setUsageTimeInSeconds((int) (Math.random() * 100));
        return appUsageEntity;
    }
}