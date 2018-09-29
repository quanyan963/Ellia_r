package com.txtled.ellia_r.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.txtled.ellia_r.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Quan on 2018/4/14.
 */

@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Activity provideActivity() {
        return fragment.getActivity();
    }
}
