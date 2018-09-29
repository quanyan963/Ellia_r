package com.txtled.ellia_r.di.component;

import android.app.Activity;


import com.txtled.ellia_r.main.MainActivity;
import com.txtled.ellia_r.di.module.ActivityModule;
import com.txtled.ellia_r.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Mr.Quan on 2018/4/13.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);

    //void inject(MainMenuActivity mainActivity);

    //void inject(StartActivity startActivity);
}
