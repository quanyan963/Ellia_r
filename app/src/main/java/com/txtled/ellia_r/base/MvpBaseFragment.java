package com.txtled.ellia_r.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.txtled.ellia_r.application.MyApplication;
import com.txtled.ellia_r.di.component.DaggerFragmentComponent;
import com.txtled.ellia_r.di.component.FragmentComponent;
import com.txtled.ellia_r.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Created by KomoriWu
 * on 2017/9/18.
 */

public abstract class MvpBaseFragment<T extends BasePresenter> extends LazyLoadBaseFragment
        implements BaseView {
    @Inject
    public T presenter;

    protected abstract void initInject();

    public FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    private FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if (presenter != null) {
            presenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }

}
