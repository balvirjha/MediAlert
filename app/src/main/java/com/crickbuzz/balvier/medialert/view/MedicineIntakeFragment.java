package com.crickbuzz.balvier.medialert.view;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.crickbuzz.balvier.medialert.R;
import com.crickbuzz.balvier.medialert.controller.AlarmBootReciever;
import com.crickbuzz.balvier.medialert.controller.MedicineController;
import com.crickbuzz.balvier.medialert.controller.NotificationReciever;
import com.crickbuzz.balvier.medialert.controller.Util;
import com.crickbuzz.balvier.medialert.modal.MedicinePOJO;
import com.crickbuzz.balvier.medialert.modal.MedicineSerializable;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Balvier on 9/16/2017.
 */

public class MedicineIntakeFragment extends Fragment implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,
        CompoundButton.OnCheckedChangeListener {

    @Inject
    MedicineController medicineController;

    private View mRoot;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Button medicineDate, medicineTime, buttomSave, buttomShowAllMissedReport, buttomShowAllTakenReport;
    private EditText medicineName, medicineDosage;
    private CheckBox checkTaken, checkNotTaken, cancelAlarm;
    private LinearLayout intekeLayout;
    private boolean isOpenFromNotification;
    private ReportFragment reportFragment;
    private Bundle bundle;
    private MedicineSerializable medicineParcelable;
    private List<MedicinePOJO> medicinePOJOList;

    @Inject
    public MedicineIntakeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.medicine_intake_fragment, container, false);
        return mRoot;
    }

    MedicinePOJO medicinePOJO;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        medicineDate = (Button) mRoot.findViewById(R.id.madicineDate);
        medicineDate.setOnClickListener(this);
        medicineTime = (Button) mRoot.findViewById(R.id.madicineTime);
        medicineTime.setOnClickListener(this);
        buttomSave = (Button) mRoot.findViewById(R.id.buttomSave);
        buttomShowAllMissedReport = (Button) mRoot.findViewById(R.id.buttomShowAllMissedReport);
        buttomShowAllTakenReport = (Button) mRoot.findViewById(R.id.buttomShowAllTakenReport);
        buttomSave.setOnClickListener(this);
        buttomShowAllMissedReport.setOnClickListener(this);
        buttomShowAllTakenReport.setOnClickListener(this);
        medicineName = (EditText) mRoot.findViewById(R.id.madicineName);
        medicineDosage = (EditText) mRoot.findViewById(R.id.madicineDasages);

        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        if (getActivity().getIntent().getSerializableExtra("medicinepojo") != null) {
            NotificationManager nMgr = (NotificationManager) getActivity().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            nMgr.cancel(0);

            isOpenFromNotification = true;
            Log.e("bvc", "getActivity().getIntent().getSerializableExtra(\"medicinepojo\") != null");
            medicinePOJO = (MedicinePOJO) getActivity().getIntent().getSerializableExtra("medicinepojo");
            Log.e("bvc", "medicinePOJO on notification calleed : " + medicinePOJO.getMedicineName());
            NotificationManager notificationManager = (NotificationManager) getActivity().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(1);
            displayData(medicinePOJO);
        }

    }

    private void displayData(MedicinePOJO medicinePOJO) {

        setViewsVisibilty(View.VISIBLE);
        medicineName.setText(medicinePOJO.getMedicineName());
        medicineDosage.setText(String.valueOf(medicinePOJO.getDosage()));
        medicineDate.setText(medicinePOJO.getDate());
        isDateSet = true;
        medicineTime.setText(medicinePOJO.getTime());
        isTimeSet = true;
        checkTaken.setChecked(medicinePOJO.isMedicineTaken());
        checkNotTaken.setChecked(medicinePOJO.isMedicineTaken());
    }

    void setViewsVisibilty(int visibilty) {
        intekeLayout = mRoot.findViewById(R.id.intekeLayout);
        intekeLayout.setVisibility(visibilty);
        checkTaken = (CheckBox) mRoot.findViewById(R.id.checkTaken);

        checkTaken.setVisibility(visibilty);
        checkTaken.setOnCheckedChangeListener(this);
        checkNotTaken = (CheckBox) mRoot.findViewById(R.id.checkNotTaken);
        checkNotTaken.setVisibility(visibilty);
        checkNotTaken.setOnCheckedChangeListener(this);
        cancelAlarm = (CheckBox) mRoot.findViewById(R.id.cancelAlarm);
        cancelAlarm.setVisibility(visibilty);
        cancelAlarm.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.madicineDate:
                showDatePickerFragemnt();
                break;

            case R.id.madicineTime:
                showTimePickerFragemnt();
                break;

            case R.id.buttomSave:
                validateDataAndSave();
                break;

            case R.id.buttomShowAllMissedReport:
                medicinePOJOList = medicineController.getAllMissedMedicine();
                if (medicinePOJOList.size() > 0) {
                    reportFragment = new ReportFragment();
                    medicineParcelable = new MedicineSerializable(medicinePOJOList);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("medicineData", medicineParcelable);
                    reportFragment.setArguments(bundle);
                    launchReportFragment();
                    ((MainActivity) getActivity()).showBackButton(true);
                    isOpenFromNotification = false;
                    medicinePOJO = null;

                } else {
                    Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.buttomShowAllTakenReport:
                medicinePOJOList = medicineController.getAllTakenMedicine();
                if (medicinePOJOList.size() > 0) {
                    reportFragment = new ReportFragment();
                    medicineParcelable = new MedicineSerializable(medicinePOJOList);
                    bundle = new Bundle();
                    bundle.putSerializable("medicineData", medicineParcelable);
                    reportFragment.setArguments(bundle);
                    launchReportFragment();
                    ((MainActivity) getActivity()).showBackButton(true);
                    isOpenFromNotification = false;
                    medicinePOJO = null;
                } else {
                    Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    void launchReportFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.leftFragment, reportFragment, MedicineSerializable.class.getSimpleName())
                .addToBackStack(MedicineSerializable.class.getSimpleName())
                .commit();
    }

    void validateDataAndSave() {
        if (!medicineName.getText().toString().isEmpty()
                && !medicineDosage.getText().toString().isEmpty()
                && isDateSet && isTimeSet) {
            try {
                Integer.parseInt(medicineDosage.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Medicine dosage must be integer only", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return;
            }
            MedicinePOJO medicinePOJONew = null;
            long rowsAdded = -1;
            if (!isOpenFromNotification) {
                medicinePOJO = new MedicinePOJO(medicineName.getText().toString(),
                        Integer.parseInt(medicineDosage.getText().toString()),
                        medicineDate.getText().toString(), medicineTime.getText().toString(), true, false);
                rowsAdded = medicineController.addMedicine(medicinePOJO);
                Log.e("bvc", "rowsAdded : " + rowsAdded);
                if (rowsAdded > 0) {

                    setAlarm(Util.convertStringToDate(new StringBuilder()
                            .append(medicineDate.getText().toString())
                            .append(" ")
                            .append(medicineTime.getText())
                            .toString()), medicinePOJO);
                }
            } else {

                medicinePOJONew = medicineController.getLatestEnteredMedicines();
                medicinePOJONew.setMedicineName(medicineName.getText().toString());
                medicinePOJONew.setDosage(Integer.parseInt(medicineDosage.getText().toString()));
                medicinePOJONew.setDate(medicineDate.getText().toString());
                medicinePOJONew.setTime(medicineTime.getText().toString());
                medicinePOJONew.setNotificationEnabled(cancelAlarm.isChecked());
                medicinePOJONew.setMedicineTaken(checkTaken.isChecked());

                medicinePOJO = new MedicinePOJO(medicineName.getText().toString(),
                        Integer.parseInt(medicineDosage.getText().toString()),
                        medicineDate.getText().toString(), medicineTime.getText().toString(), cancelAlarm.isChecked(), checkTaken.isChecked());
                medicinePOJO.setId(medicinePOJONew.getId());
                rowsAdded = medicineController.updateMedicine(medicinePOJONew);
                Log.e("bvc", "rowsUpdated : " + rowsAdded);
                if (rowsAdded > 0 && cancelAlarm.isChecked()) {
                    Intent alarmintent = new Intent(getActivity().getApplicationContext(), NotificationReciever.class);
                    pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, alarmintent, 0);
                    alarmManager.cancel(pendingIntent);
                }
                setViewsVisibilty(View.GONE);
            }
            isDateSet = false;
            isTimeSet = false;
            medicineName.setText("");
            medicineDosage.setText("");
            medicineDate.setText(getActivity().getResources().getString(R.string.medicineDate));
            medicineTime.setText(getActivity().getResources().getString(R.string.medicineTime));
        } else {
            if (!isDateSet) {
                Toast.makeText(getActivity(), "Medicine date is missing", Toast.LENGTH_SHORT).show();
            }
            if (!isTimeSet) {
                Toast.makeText(getActivity(), "Medicine time is missing", Toast.LENGTH_SHORT).show();
            }
            if (medicineName.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Medicine name is missing", Toast.LENGTH_SHORT).show();
            }
            if (medicineDosage.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Medicine dasages is missing", Toast.LENGTH_SHORT).show();
            }

        }

    }


    boolean isDateSet = false, isTimeSet = false;

    private void setAlarm(long timeInMilles, MedicinePOJO medicinePOJO) {
        Intent alarmintent = new Intent(getActivity().getApplicationContext(), NotificationReciever.class);
        alarmintent.putExtra("medicinepojo", medicinePOJO);
        pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, alarmintent, 0);
        Log.d("bvc", "AlarmReceiver set");

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMilles, 86400000, pendingIntent);

        setBootReceiverEnabled(PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
    }

    private void cancelAlarm() {
        Log.d("bvc", "AlarmReceiver cancelled");
        alarmManager.cancel(pendingIntent);

        setBootReceiverEnabled(PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
    }

    private void setBootReceiverEnabled(int componentEnabledState) {
        ComponentName componentName = new ComponentName(getActivity(), AlarmBootReciever.class);
        PackageManager packageManager = getActivity().getPackageManager();
        packageManager.setComponentEnabledSetting(componentName,
                componentEnabledState,
                PackageManager.DONT_KILL_APP);
    }


    void showDatePickerFragemnt() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(), this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    void showTimePickerFragemnt() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), this,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear,
                          int dayOfMonth) {
        ((Button) mRoot.findViewById(R.id.madicineDate)).setText(new StringBuilder().append(year)
                .append("-")
                .append(monthOfYear + 1)
                .append("-")
                .append(dayOfMonth).toString());
        isDateSet = true;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

        ((Button) mRoot.findViewById(R.id.madicineTime)).setText(new StringBuilder().append(hourOfDay)
                .append(":")
                .append(minute)
                .toString());
        isTimeSet = true;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.checkTaken:
                checkTaken.setChecked(b);
                checkNotTaken.setChecked(!b);
                break;
            case R.id.checkNotTaken:
                checkNotTaken.setChecked(b);
                checkTaken.setChecked(!b);
                break;
            case R.id.cancelAlarm:
                cancelAlarm.setChecked(b);
                break;
        }
    }
}
