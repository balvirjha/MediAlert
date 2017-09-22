package com.crickbuzz.balvier.medialert.modules;

import android.app.Activity;
import android.content.Context;

import com.crickbuzz.balvier.medialert.scopes.ActivityContext;
import com.crickbuzz.balvier.medialert.controller.MedicineController;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balvier on 9/22/2017.
 */

@Module
public class MainActivityModule {
    private Activity mActivity;

    @Inject
    MedicineController medicineController;

    public MainActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }


    @Provides
    Activity provideActivity() {
        return mActivity;
    }

}
