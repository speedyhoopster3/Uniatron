package com.edu.uni.augsburg.uniatron.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.edu.uni.augsburg.uniatron.domain.dao.AppUsageDao;
import com.edu.uni.augsburg.uniatron.domain.dao.StepCountDao;
import com.edu.uni.augsburg.uniatron.domain.dao.TimeCreditDao;
import com.edu.uni.augsburg.uniatron.domain.model.AppUsageEntity;
import com.edu.uni.augsburg.uniatron.domain.model.StepCounteEntity;
import com.edu.uni.augsburg.uniatron.domain.model.TimeCreditEntity;

public class DashboardViewModel extends ViewModel {
    //public final LiveData<StepCounteEntity> stepCount;
    //public final LiveData<AppUsageEntity> topAppUsage;
    //public final LiveData<TimeCreditEntity> timeCredit;

    public DashboardViewModel(@NonNull AppUsageDao appUsageDao,
                              @NonNull StepCountDao stepCountDao,
                              @NonNull TimeCreditDao timeCreditDao) {
        //stepCount = stepCountDao.
    }
}
