package com.crickbuzz.balvier.medialert;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balvier on 9/22/2017.
 */

@Module
public class MediAlertApplicationModule {
    private MediAlertApplication mediAlertApplication;

    public MediAlertApplicationModule(MediAlertApplication app) {
        mediAlertApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mediAlertApplication;
    }

    @Provides
    Context providesApplication() {
        return mediAlertApplication;
    }
}
