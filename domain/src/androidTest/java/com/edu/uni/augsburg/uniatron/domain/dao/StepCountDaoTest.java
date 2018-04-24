package com.edu.uni.augsburg.uniatron.domain.dao;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.edu.uni.augsburg.uniatron.domain.AppDatabase;
import com.edu.uni.augsburg.uniatron.domain.dao.util.TestUtils;
import com.edu.uni.augsburg.uniatron.domain.model.StepCountEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class StepCountDaoTest {
    private AppDatabase mDb;
    private StepCountDao mDao;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getTargetContext();
        mDb = AppDatabase.buildInMemory(context);
        mDao = mDb.stepCountDao();
    }

    @After
    public void tearDown() {
        mDb.close();
    }

    @Test
    public void add() {
        final StepCountEntity stepCountEntity = create(1, new Date());
        mDao.add(stepCountEntity);

        assertThat(stepCountEntity.getId(), is(not(-1)));
    }

    @Test
    public void loadStepCounts() throws Exception {
        final int count = 10;
        mDao.add(create(count, TestUtils.getDate(1, 1, 2018)));
        mDao.add(create(count, TestUtils.getDate(1, 1, 2018)));
        mDao.add(create(count, TestUtils.getDate(1, 1, 2018)));
        mDao.add(create(count, TestUtils.getDate(1, 1, 2018)));
        mDao.add(create(count, TestUtils.getDate(1, 1, 2018)));

        final LiveData<Integer> data = mDao.loadStepCounts(TestUtils.getDate(1, 1, 2018));

        assertThat(data, is(notNullValue()));
        assertThat(TestUtils.getValue(data), is(count * 5));
    }

    private StepCountEntity create(int count, Date date) {
        final StepCountEntity stepCountEntity = new StepCountEntity();
        stepCountEntity.setId(-1);
        stepCountEntity.setStepCount(count);
        stepCountEntity.setTimestamp(date);
        return stepCountEntity;
    }
}