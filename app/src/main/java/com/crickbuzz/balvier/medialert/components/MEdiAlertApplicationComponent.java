package com.crickbuzz.balvier.medialert.components;

import android.content.Context;

import com.crickbuzz.balvier.medialert.MediAlertApplication;
import com.crickbuzz.balvier.medialert.controller.DatabaseHandler;
import com.crickbuzz.balvier.medialert.controller.MedicineController;
import com.crickbuzz.balvier.medialert.modules.MediAlertApplicationModule;
import com.crickbuzz.balvier.medialert.scopes.ApplicationContext;

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
