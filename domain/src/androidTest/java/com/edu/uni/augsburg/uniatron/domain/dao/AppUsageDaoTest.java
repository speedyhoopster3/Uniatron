package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.edu.uni.augsburg.uniatron.domain.AppDatabase;
import com.edu.uni.augsburg.uniatron.domain.util.TestUtils;
import com.edu.uni.augsburg.uniatron.domain.model.AppUsageEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import static com.edu.uni.augsburg.uniatron.domain.util.TestUtils.getDate;
import static com.edu.uni.augsburg.uniatron.domain.util.TestUtils.getLiveDataValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class AppUsageDaoTest {
    private AppDatabase mDb;
    private AppUsageDao mDao;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getTargetContext();
        mDb = AppDatabase.buildInMemory(context);
        mDao = mDb.appUsageDao();
    }

    @After
    public void tearDown() {
        mDb.close();
    }

    @Test
    public void add() {
        final AppUsageEntity appUsageEntity = create("Test", new Date());
        mDao.add(appUsageEntity);

        assertThat(appUsageEntity.getId(), is(not(-1)));
    }

    @Test
    public void loadAppUsageTime() throws Exception {
        final String appName0 = "Test";
        mDao.add(create(appName0, getDate(1, 1, 2018)));
        mDao.add(create(appName0, getDate(1, 1, 2018)));
        mDao.add(create(appName0, getDate(1, 1, 2018)));

        final String appName1 = "Test1";
        mDao.add(create(appName1, getDate(1, 1, 2018)));

        final String appName2 = "Test2";
        mDao.add(create(appName2, getDate(1, 1, 2018)));
        mDao.add(create(appName2, getDate(1, 1, 2018)));

        final LiveData<List<AppUsageEntity>> data = mDao.loadAppUsageTime(getDate(1, 1, 2018));

        assertThat(getLiveDataValue(data), is(notNullValue()));
        assertThat(getLiveDataValue(data).get(0).getAppName(), is(equalTo(appName0)));
        assertThat(getLiveDataValue(data).get(1).getAppName(), is(equalTo(appName2)));
        assertThat(getLiveDataValue(data).get(2).getAppName(), is(equalTo(appName1)));
        assertThat(getLiveDataValue(data).get(0).getUsageTimeInSeconds(), is(30));
        assertThat(getLiveDataValue(data).get(1).getUsageTimeInSeconds(), is(20));
        assertThat(getLiveDataValue(data).get(2).getUsageTimeInSeconds(), is(10));
    }

    @Test
    public void loadAppUsagePercent() throws Exception {
        final String appName0 = "Test";
        mDao.add(create(appName0, getDate(1, 1, 2018)));
        mDao.add(create(appName0, getDate(1, 1, 2018)));
        mDao.add(create(appName0, getDate(1, 1, 2018)));

        final String appName1 = "Test1";
        mDao.add(create(appName1, getDate(1, 1, 2018)));

        final LiveData<List<AppUsageEntity>> data = mDao.loadAppUsagePercent(getDate(1, 1, 2018));

        assertThat(getLiveDataValue(data), is(notNullValue()));
        assertThat(getLiveDataValue(data).get(0).getAppName(), is(equalTo(appName0)));
        assertThat(getLiveDataValue(data).get(1).getAppName(), is(equalTo(appName1)));
        assertThat(getLiveDataValue(data).get(0).getUsageTimeInSeconds(), is(75));
        assertThat(getLiveDataValue(data).get(1).getUsageTimeInSeconds(), is(25));
    }

    private AppUsageEntity create(String name, Date date) {
        final AppUsageEntity appUsageEntity = new AppUsageEntity();
        appUsageEntity.setId(-1);
        appUsageEntity.setAppName(name);
        appUsageEntity.setTimestamp(date);
        appUsageEntity.setUsageTimeInSeconds(10);
        return appUsageEntity;
    }
}