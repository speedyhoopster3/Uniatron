package com.edu.uni.augsburg.uniatron.domain;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.edu.uni.augsburg.uniatron.domain.dao.AppUsageDao;
import com.edu.uni.augsburg.uniatron.domain.dao.StepCountDao;
import com.edu.uni.augsburg.uniatron.domain.dao.TimeCreditDao;
import com.edu.uni.augsburg.uniatron.model.StepCount;
import com.edu.uni.augsburg.uniatron.model.TimeCredit;
import com.edu.uni.augsburg.uniatron.model.TimeCreditItem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DataRepositoryTest {
    private DataRepository mRepository;
    private AppUsageDao appUsageDao;
    private StepCountDao stepCountDao;
    private TimeCreditDao timeCreditDao;

    @Before
    public void setUp() throws Exception {
        appUsageDao = mock(AppUsageDao.class);
        stepCountDao = mock(StepCountDao.class);
        timeCreditDao = mock(TimeCreditDao.class);

        final AppDatabase database = mock(AppDatabase.class);
        Mockito.when(database.appUsageDao()).thenReturn(appUsageDao);
        Mockito.when(database.stepCountDao()).thenReturn(stepCountDao);
        Mockito.when(database.timeCreditDao()).thenReturn(timeCreditDao);

        mRepository = new DataRepository(database);
    }

    @Test
    public void addTimeCredit() {
        final TimeCredit timeCredit = mRepository.addTimeCredit(TimeCreditItem.CREDIT_1000);
        assertThat(timeCredit, is(notNullValue()));
    }

    @Test(expected = Exception.class)
    public void addTimeCreditFailed() {
        Mockito.doThrow(Exception.class).when(timeCreditDao).add(any());

        mRepository.addTimeCredit(TimeCreditItem.CREDIT_1000);
        assertFalse("Should has thrown an exception!", true);
    }

    @Test
    public void getTimeCreditsToday() {
        final int value = 10;
        final LiveData<Integer> liveData = spy(new MutableLiveData<>());
        when(liveData.getValue()).thenReturn(value);
        when(timeCreditDao.loadTimeCredits(any())).thenReturn(liveData);

        final LiveData<Integer> timeCreditsToday = mRepository.getTimeCreditsToday();
        assertThat(timeCreditsToday, is(notNullValue()));
        assertThat(timeCreditsToday.getValue(), is(value));
        verify(timeCreditDao, atLeastOnce()).loadTimeCredits(any());
    }

    @Test
    public void getTimeCreditsByDate() {
        final int value = 10;
        final LiveData<Integer> liveData = spy(new MutableLiveData<>());
        when(liveData.getValue()).thenReturn(value);
        when(timeCreditDao.loadTimeCredits(any())).thenReturn(liveData);

        final LiveData<Integer> timeCreditsToday = mRepository.getTimeCreditsByDate(new Date());
        assertThat(timeCreditsToday, is(notNullValue()));
        assertThat(timeCreditsToday.getValue(), is(value));
        verify(timeCreditDao, atLeastOnce()).loadTimeCredits(any());
    }

    @Test
    public void addStepCount() {
        final int value = 10;
        final StepCount stepCount = mRepository.addStepCount(value);
        assertThat(stepCount, is(notNullValue()));
        assertThat(stepCount.getStepCount(), is(value));
    }

    @Test(expected = Exception.class)
    public void addStepCountFailed() {
        Mockito.doThrow(Exception.class).when(stepCountDao).add(any());

        mRepository.addStepCount(10);
        assertFalse("Should has thrown an exception!", true);
    }

    @Test
    public void getStepCountsToday() {
        final int value = 10;
        final LiveData<Integer> liveData = spy(new MutableLiveData<>());
        when(liveData.getValue()).thenReturn(value);
        when(stepCountDao.loadStepCounts(any())).thenReturn(liveData);

        final LiveData<Integer> stepCountsToday = mRepository.getStepCountsToday();
        assertThat(stepCountsToday, is(notNullValue()));
        assertThat(stepCountsToday.getValue(), is(value));
        verify(stepCountDao, atLeastOnce()).loadStepCounts(any());
    }

    @Test
    public void getStepCountsByDate() {
        final int value = 10;
        final LiveData<Integer> liveData = spy(new MutableLiveData<>());
        when(liveData.getValue()).thenReturn(value);
        when(stepCountDao.loadStepCounts(any())).thenReturn(liveData);

        final LiveData<Integer> stepCountsToday = mRepository.getStepCountsByDate(new Date());
        assertThat(stepCountsToday, is(notNullValue()));
        assertThat(stepCountsToday.getValue(), is(value));
        verify(stepCountDao, atLeastOnce()).loadStepCounts(any());
    }

    @Test
    public void addAppUsage() {
    }

    @Test
    public void getAppUsageTimeToday() {
    }

    @Test
    public void getAppUsageTimeByDate() {
    }

    @Test
    public void getAppUsagePercentToday() {
    }

    @Test
    public void getAppUsagePercentByDate() {
    }
}