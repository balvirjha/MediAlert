package com.crickbuzz.balvier.medialert.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.crickbuzz.balvier.medialert.modal.MedicinePOJO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balvier on 9/16/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "medicineDatabase";

    private static final String TABLE_MEDICINE = "medicine";

    private static final String KEY_ID = "id";
    private static final String KEY_MEDICINE_NAME = "medicineName";
    private static final String KEY_DOSAGES = "dosages";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_NOTIFICATION_ENABLED = "noti_enabled";
    private static final String KEY_MEDICINE_TAKEN = "medicine_taken";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MEDICINE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_MEDICINE_NAME + " TEXT,"
                + KEY_DOSAGES + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT," + KEY_NOTIFICATION_ENABLED + " TEXT DEFAULT 'false',"
                + KEY_MEDICINE_TAKEN + " TEXT DEFAULT 'false'" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
        onCreate(db);

    }

    public long addMedicine(MedicinePOJO medicinePOJO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MEDICINE_NAME, medicinePOJO.getMedicineName());
        values.put(KEY_DOSAGES, medicinePOJO.getDosage());
        values.put(KEY_DATE, medicinePOJO.getDate());
        values.put(KEY_TIME, medicinePOJO.getTime());
        values.put(KEY_NOTIFICATION_ENABLED, String.valueOf(medicinePOJO.isNotificationEnabled()));
        values.put(KEY_MEDICINE_TAKEN, String.valueOf(medicinePOJO.isMedicineTaken()));
        Log.e("bvc", "addMedicine called");
        long rows = db.insert(TABLE_MEDICINE, null, values);
        Log.e("bvc", "rows : " + rows);
        db.close();
        return rows;
    }

    public MedicinePOJO getMedicine(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEDICINE, new String[]{KEY_ID,
                        KEY_MEDICINE_NAME, KEY_DOSAGES, KEY_DATE, KEY_TIME, KEY_NOTIFICATION_ENABLED, KEY_MEDICINE_TAKEN}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        MedicinePOJO medicinePOJO = new MedicinePOJO(Integer.parseInt(cursor.getString(0))
                , cursor.getString(1),
                Integer.parseInt(cursor.getString(2))
                , cursor.getString(3)
                , cursor.getString(4)
                , Boolean.parseBoolean(cursor.getString(5))
                , Boolean.parseBoolean(cursor.getString(6)));
        db.close();
        return medicinePOJO;
    }

    public List<MedicinePOJO> getAllMedicines() {
        List<MedicinePOJO> medicinePOJOList = new ArrayList<MedicinePOJO>();
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICINE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                medicinePOJOList.add(new MedicinePOJO(Integer.parseInt(cursor.getString(0))
                        , cursor.getString(1),
                        Integer.parseInt(cursor.getString(2))
                        , cursor.getString(3)
                        , cursor.getString(4)
                        , Boolean.parseBoolean(cursor.getString(5))
                        , Boolean.parseBoolean(cursor.getString(6))));
            } while (cursor.moveToNext());
        }
        db.close();
        return medicinePOJOList;
    }

    public List<MedicinePOJO> getAllMissedMedicines() {
        List<MedicinePOJO> medicinePOJOList = new ArrayList<MedicinePOJO>();
        String selectQuery = "SELECT * FROM " + TABLE_MEDICINE + " where medicine_taken ='false'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                medicinePOJOList.add(new MedicinePOJO(Integer.parseInt(cursor.getString(0))
                        , cursor.getString(1),
                        Integer.parseInt(cursor.getString(2))
                        , cursor.getString(3)
                        , cursor.getString(4)
                        , Boolean.parseBoolean(cursor.getString(5))
                        , Boolean.parseBoolean(cursor.getString(6))));
            } while (cursor.moveToNext());
        }
        db.close();
        return medicinePOJOList;
    }

    public List<MedicinePOJO> getAlltakenMedicines() {
        List<MedicinePOJO> medicinePOJOList = new ArrayList<MedicinePOJO>();
        String selectQuery = "SELECT * FROM " + TABLE_MEDICINE + " where medicine_taken ='true'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                medicinePOJOList.add(new MedicinePOJO(Integer.parseInt(cursor.getString(0))
                        , cursor.getString(1),
                        Integer.parseInt(cursor.getString(2))
                        , cursor.getString(3)
                        , cursor.getString(4)
                        , Boolean.parseBoolean(cursor.getString(5))
                        , Boolean.parseBoolean(cursor.getString(6))));
            } while (cursor.moveToNext());
        }
        db.close();
        return medicinePOJOList;
    }

    public MedicinePOJO getLatestEnteredMedicines() {

        String selectQuery = "SELECT * FROM  " + TABLE_MEDICINE + " WHERE   ID = (SELECT MAX(ID)  FROM " + TABLE_MEDICINE + ");";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            return new MedicinePOJO(Integer.parseInt(cursor.getString(0))
                    , cursor.getString(1),
                    Integer.parseInt(cursor.getString(2))
                    , cursor.getString(3)
                    , cursor.getString(4)
                    , Boolean.parseBoolean(cursor.getString(5))
                    , Boolean.parseBoolean(cursor.getString(6)));
        }
        return null;
    }

    public long updateMedicine(MedicinePOJO medicinePOJO) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MEDICINE_NAME, medicinePOJO.getMedicineName());
        values.put(KEY_DOSAGES, medicinePOJO.getDosage());
        values.put(KEY_DATE, medicinePOJO.getDate());
        values.put(KEY_TIME, medicinePOJO.getTime());
        values.put(KEY_NOTIFICATION_ENABLED, medicinePOJO.isNotificationEnabled());
        values.put(KEY_MEDICINE_TAKEN, String.valueOf(medicinePOJO.isMedicineTaken()));
        long updatedRows = -1;
        updatedRows = db.update(TABLE_MEDICINE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(medicinePOJO.getId())});
        if (updatedRows <= 0) {
            updatedRows = addMedicine(medicinePOJO);
        }
        return updatedRows;
    }

    public void deleteMedicine(MedicinePOJO medicinePOJO) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICINE, KEY_ID + " = ?",
                new String[]{String.valueOf(medicinePOJO.getId())});
        db.close();
    }

    public int getMedicinesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MEDICINE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        db.close();
        return cursor.getCount();
    }
}
