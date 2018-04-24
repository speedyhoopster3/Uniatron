package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class AppUsageDaoTest {
    private AppDatabase mDb;
    private AppUsageDao mDao;

    @Mock
    private Observer<Map<String, Integer>> observer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        final Context context = InstrumentationRegistry.getTargetContext();
        mDb = AppDatabase.buildInMemory(context);
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
        final AppUsageEntity appUsageEntity1 = create("Test1", new Date());
        mDao.add(appUsageEntity1);

        assertThat(appUsageEntity1.getId(), is(not(equalTo(0))));
    }

    @Test
    public void loadAppUsageTime() {
        mDao.add(create("Test", new Date()));
        mDao.add(create("Test1", new Date()));
        mDao.add(create("Test2", new Date()));
        mDao.add(create("Test3", new Date()));
        mDao.add(create("Test", new Date()));
        mDao.add(create("Test2", new Date()));
        mDao.add(create("Test3", new Date()));
        mDao.add(create("Test", new Date()));

        //final LiveData<Map<String, Integer>> data = mDao.loadAppUsageTime(new Date());
        //final Observer<Map<String, Integer>> observer = (Observer<Map<String, Integer>>) Mockito.mock(Observer.class);
        //data.observeForever(observer);

        //Mockito.verify(observer).onChanged(Mockito.anyMap());
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
        appUsageEntity.setUsageTimeInSeconds(10);
        return appUsageEntity;
    }
}