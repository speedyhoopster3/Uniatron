package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.edu.uni.augsburg.uniatron.domain.AppDatabase;
import com.edu.uni.augsburg.uniatron.domain.dao.util.TestUtils;
import com.edu.uni.augsburg.uniatron.domain.model.AppUsageEntity;

import org.hamcrest.CoreMatchers;
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
import java.util.List;
import java.util.Map;

import static com.edu.uni.augsburg.uniatron.domain.dao.util.TestUtils.getDate;
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

        assertThat(TestUtils.getValue(data), is(notNullValue()));
        assertThat(TestUtils.getValue(data).get(0).getAppName(), is(equalTo(appName0)));
        assertThat(TestUtils.getValue(data).get(1).getAppName(), is(equalTo(appName2)));
        assertThat(TestUtils.getValue(data).get(2).getAppName(), is(equalTo(appName1)));
        assertThat(TestUtils.getValue(data).get(0).getUsageTimeInSeconds(), is(30));
        assertThat(TestUtils.getValue(data).get(1).getUsageTimeInSeconds(), is(20));
        assertThat(TestUtils.getValue(data).get(2).getUsageTimeInSeconds(), is(10));
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

        assertThat(TestUtils.getValue(data), is(notNullValue()));
        assertThat(TestUtils.getValue(data).get(0).getAppName(), is(equalTo(appName0)));
        assertThat(TestUtils.getValue(data).get(1).getAppName(), is(equalTo(appName1)));
        assertThat(TestUtils.getValue(data).get(0).getUsageTimeInSeconds(), is(75));
        assertThat(TestUtils.getValue(data).get(1).getUsageTimeInSeconds(), is(25));
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