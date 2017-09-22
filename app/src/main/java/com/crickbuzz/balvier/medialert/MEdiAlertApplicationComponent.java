package com.crickbuzz.balvier.medialert;

import android.app.Application;
import android.content.Context;

import com.crickbuzz.balvier.medialert.controller.DatabaseHandler;
import com.crickbuzz.balvier.medialert.controller.MedicineController;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Balvier on 9/22/2017.
 */

@Singleton
@Component(modules = MediAlertApplicationModule.class)
public interface MEdiAlertApplicationComponent {

    void inject(MediAlertApplication mediAlertApplication);

    @ApplicationContext
    Context getContext();
    MedicineController getMedicineController();
    DatabaseHandler getDatabaseHandler();
}
