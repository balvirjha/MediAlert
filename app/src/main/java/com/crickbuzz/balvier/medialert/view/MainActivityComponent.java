package com.crickbuzz.balvier.medialert.view;

import com.crickbuzz.balvier.medialert.MEdiAlertApplicationComponent;
import com.crickbuzz.balvier.medialert.PerActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Balvier on 9/22/2017.
 */

@PerActivity
@Component(dependencies = MEdiAlertApplicationComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
