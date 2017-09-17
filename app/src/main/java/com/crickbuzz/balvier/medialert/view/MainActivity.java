package com.crickbuzz.balvier.medialert.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crickbuzz.balvier.medialert.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showLeftFragment();
    }

    public void showLeftFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.leftFragment, new MedicineIntakeFragment(),
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
