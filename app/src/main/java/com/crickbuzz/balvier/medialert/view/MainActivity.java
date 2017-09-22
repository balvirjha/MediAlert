package com.crickbuzz.balvier.medialert.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crickbuzz.balvier.medialert.MediAlertApplication;
import com.crickbuzz.balvier.medialert.R;
import com.crickbuzz.balvier.medialert.components.DaggerMainActivityComponent;
import com.crickbuzz.balvier.medialert.components.MainActivityComponent;
import com.crickbuzz.balvier.medialert.modules.MainActivityModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MedicineIntakeFragment medicineIntakeFragment;
    private MainActivityComponent activityComponent;

    public MainActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerMainActivityComponent.builder()
                    .mainActivityModule(new MainActivityModule(this))
                    .mEdiAlertApplicationComponent(MediAlertApplication.get(this.getApplicationContext()).getMEdiAlertApplicationComponent())
                    .build();
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        showLeftFragment();
    }

    public void showLeftFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.leftFragment,medicineIntakeFragment,
                MedicineIntakeFragment.class.getSimpleName())
                .addToBackStack(MedicineIntakeFragment.class.getSimpleName())
                .commit();

    }

    public void showBackButton(boolean isShow) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();

    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().findFragmentByTag(MedicineIntakeFragment.class.getSimpleName()) != null
                && getSupportFragmentManager().findFragmentByTag(MedicineIntakeFragment.class.getSimpleName()).isVisible()) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
            showLeftFragment();
        }
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        super.onBackPressed();
    }
}
