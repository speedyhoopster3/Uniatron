package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import com.edu.uni.augsburg.uniatron.domain.AppDatabase;
import com.edu.uni.augsburg.uniatron.domain.model.TimeCreditEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TimeCreditDaoTest {
    private AppDatabase mDb;
    private TimeCreditDao mDao;

    @Before
    public void setUp() throws Exception {
        final Context context = InstrumentationRegistry.getTargetContext();
        mDb = AppDatabase.buildInMemory(context);
        mDao = mDb.timeCreditDao();
    }

    @After
    public void tearDown() throws Exception {
        mDb.close();
    }

    @Test
    public void add() {
        final int value = -1;
        final TimeCreditEntity timeCreditEntity1 = createTestData(1, 1, 2018);
        timeCreditEntity1.setId(value);
        mDao.add(timeCreditEntity1);
        assertThat(timeCreditEntity1, is(not(value)));
    }

    @Test
    public void loadTimeCredits() {
        final Observer<Integer> observer = mock(Observer.class);

        mDao.add(createTestData(1, 1, 2018));
        mDao.add(createTestData(1, 1, 2018));
        mDao.add(createTestData(1, 2, 2018));
        mDao.add(createTestData(1, 3, 2018));
        mDao.add(createTestData(1, 2, 2018));

        final LiveData<Integer> data = mDao.loadTimeCredits(getDate(1, 1, 2018));
        data.observeForever(observer);
        verify(observer).onChanged(eq(10));
    }

    private TimeCreditEntity createTestData(int date, int month, int year) {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setTimeInMinutes(5);
        timeCreditEntity.setTimestamp(getDate(date, month, year));
        return timeCreditEntity;
    }

    @NonNull
    private Date getDate(int date, int month, int year) {
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(year, month - 1, date);
        return calendar.getTime();
    }
}