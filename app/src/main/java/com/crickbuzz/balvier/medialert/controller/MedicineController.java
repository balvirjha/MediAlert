package com.crickbuzz.balvier.medialert.controller;

import android.content.Context;
import android.util.Log;

import com.crickbuzz.balvier.medialert.scopes.ApplicationContext;
import com.crickbuzz.balvier.medialert.modal.MedicinePOJO;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Balvier on 9/16/2017.
 */

@Singleton
public class MedicineController {

    @Inject
    DatabaseHandler db;
    static Context contextActivity;


    @Inject
    public MedicineController(@ApplicationContext Context context) {
        contextActivity = context;
    }

    public long addMedicine(MedicinePOJO medicinePOJO) {
        Log.e("bvc", "addMedicine called");
        return getDatabaseHandler().addMedicine(medicinePOJO);
    }

    public long updateMedicine(MedicinePOJO medicinePOJO) {
        Log.e("bvc", "addMedicine called");
        return getDatabaseHandler().updateMedicine(medicinePOJO);
    }

    public List<MedicinePOJO> getAllMedicine() {
        Log.e("bvc", "getAllMedicine called");
        return getDatabaseHandler().getAllMedicines();
    }

    public List<MedicinePOJO> getAllMissedMedicine() {
        Log.e("bvc", "getAllMedicine called");
        return getDatabaseHandler().getAllMissedMedicines();
    }

    public List<MedicinePOJO> getAllTakenMedicine() {
        Log.e("bvc", "getAllMedicine called");
        return getDatabaseHandler().getAlltakenMedicines();
    }

    public MedicinePOJO getMedicine(int rowId) {
        Log.e("bvc", "getMedicine called");
        return getDatabaseHandler().getMedicine(rowId);
    }

    public MedicinePOJO getLatestEnteredMedicines() {
        Log.e("bvc", "getMedicine called");
        return getDatabaseHandler().getLatestEnteredMedicines();
    }

    private DatabaseHandler getDatabaseHandler() {
        Log.e("bvc", "getDatabaseHandler called");

        return db;
    }

}
