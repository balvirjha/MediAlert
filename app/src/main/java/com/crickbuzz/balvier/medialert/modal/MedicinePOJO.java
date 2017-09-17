package com.crickbuzz.balvier.medialert.modal;

import java.io.Serializable;

/**
 * Created by Balvier on 9/16/2017.
 */

public class MedicinePOJO implements Serializable {

    private String medicineName;
    private int dosage, id;
    private String date, time;
    private boolean isNotificationEnabled;
    private boolean isMedicineTaken;

    public MedicinePOJO(int id, String medicineName, int dosage, String date, String time, boolean isNotificationEnabled, boolean isMedicineTaken) {
        this.id = id;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.date = date;
        this.time = time;
        this.isNotificationEnabled = isNotificationEnabled;
        this.isMedicineTaken = isMedicineTaken;
    }

    public MedicinePOJO(String medicineName, int dosage, String date, String time, boolean isNotificationEnabled, boolean isMedicineTaken) {
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.date = date;
        this.time = time;
        this.isNotificationEnabled = isNotificationEnabled;
        this.isMedicineTaken = isMedicineTaken;
    }

    public boolean isMedicineTaken() {
        return isMedicineTaken;
    }

    public void setMedicineTaken(boolean medicineTaken) {
        isMedicineTaken = medicineTaken;
    }

    public boolean isNotificationEnabled() {
        return isNotificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        isNotificationEnabled = notificationEnabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
