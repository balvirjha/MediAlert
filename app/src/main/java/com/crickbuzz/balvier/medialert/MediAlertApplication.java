package com.crickbuzz.balvier.medialert;

import android.app.Application;
import android.content.Context;

import com.crickbuzz.balvier.medialert.components.DaggerMEdiAlertApplicationComponent;
import com.crickbuzz.balvier.medialert.components.MEdiAlertApplicationComponent;
import com.crickbuzz.balvier.medialert.controller.MedicineController;
import com.crickbuzz.balvier.medialert.modules.MediAlertApplicationModule;

import javax.inject.Inject;

/**
 * Created by Balvier on 9/22/2017.
 */

public class MediAlertApplication extends Application {

    @Inject
    MedicineController medicineController;

    protected MEdiAlertApplicationComponent mEdiAlertApplicationComponent;

    public static MediAlertApplication get(Context context) {
        return (MediAlertApplication) context.getApplicationContext();
    }

    public MEdiAlertApplicationComponent getMEdiAlertApplicationComponent() {
        return mEdiAlertApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mEdiAlertApplicationComponent = DaggerMEdiAlertApplicationComponent
                .builder()
                .mediAlertApplicationModule(new MediAlertApplicationModule(this))
                .build();
    }
}
