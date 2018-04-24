package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.edu.uni.augsburg.uniatron.domain.AppDatabase;
import com.edu.uni.augsburg.uniatron.domain.util.TestUtils;
import com.edu.uni.augsburg.uniatron.domain.model.TimeCreditEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.edu.uni.augsburg.uniatron.domain.util.TestUtils.getDate;
import static com.edu.uni.augsburg.uniatron.domain.util.TestUtils.getLiveDataValue;
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
    public void setUp() {
        final Context context = InstrumentationRegistry.getTargetContext();
        mDb = AppDatabase.buildInMemory(context);
        mDao = mDb.timeCreditDao();
    }

    @After
    public void tearDown() {
        mDb.close();
    }

    @Test
    public void add() {
        final TimeCreditEntity timeCreditEntity1 = createTestData(1);
        mDao.add(timeCreditEntity1);
        assertThat(timeCreditEntity1.getId(), is(not(-1)));
    }

    @Test
    public void loadTimeCredits() throws Exception {
        mDao.add(createTestData(1));
        mDao.add(createTestData(1));
        mDao.add(createTestData(2));
        mDao.add(createTestData(3));
        mDao.add(createTestData(2));

        final LiveData<Integer> data1 = mDao.loadTimeCredits(getDate(1, 1, 2018));
        final LiveData<Integer> data2 = mDao.loadTimeCredits(getDate(1, 2, 2018));
        final LiveData<Integer> data3 = mDao.loadTimeCredits(getDate(1, 3, 2018));

        assertThat(getLiveDataValue(data1), is(10));
        assertThat(getLiveDataValue(data2), is(10));
        assertThat(getLiveDataValue(data3), is(5));
    }

    private TimeCreditEntity createTestData(int month) {
        final TimeCreditEntity timeCreditEntity = new TimeCreditEntity();
        timeCreditEntity.setId(-1);
        timeCreditEntity.setTimeInMinutes(5);
        timeCreditEntity.setTimestamp(getDate(1, month, 2018));
        return timeCreditEntity;
    }
}