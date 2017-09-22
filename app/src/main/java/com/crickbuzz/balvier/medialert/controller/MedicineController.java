package com.crickbuzz.balvier.medialert.controller;

import android.content.Context;
import android.util.Log;

import com.crickbuzz.balvier.medialert.ActivityContext;
import com.crickbuzz.balvier.medialert.ApplicationContext;
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

    private static MedicineController instance;

    @Inject
    public MedicineController(@ApplicationContext Context context) {
        contextActivity = context;
    }

   /* public static MedicineController getInstance(Context context) {
        contextActivity = context;
        if (instance == null) {
            synchronized (MedicineController.class) {
                if (instance == null) {
                    instance = new MedicineController();
                }
            }
        }
        return instance;
    }*/

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
       /* if (db == null) {
            return new DatabaseHandler(contextActivity);
        } else {*/
        return db;
        // }
    }

}
