package com.crickbuzz.balvier.medialert.components;

import com.crickbuzz.balvier.medialert.modules.MainActivityModule;
import com.crickbuzz.balvier.medialert.scopes.PerActivity;
import com.crickbuzz.balvier.medialert.view.MainActivity;

import dagger.Component;

/**
 * Created by Balvier on 9/22/2017.
 */

@PerActivity
@Component(dependencies = MEdiAlertApplicationComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
