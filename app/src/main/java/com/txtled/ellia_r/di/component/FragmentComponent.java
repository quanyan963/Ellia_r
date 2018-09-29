package com.txtled.ellia_r.di.component;

import android.app.Activity;


import com.txtled.ellia_r.aroma.AromaFragment;
import com.txtled.ellia_r.di.module.FragmentModule;
import com.txtled.ellia_r.di.scope.FragmentScope;
import com.txtled.ellia_r.light.LightFragment;
import com.txtled.ellia_r.sound.SoundFragment;

import dagger.Component;

/**
 * Created by Mr.Quan on 2018/4/14.
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(SoundFragment soundFragment);

    void inject(AromaFragment aromaFragment);

    void inject(LightFragment lightFragment);
}
