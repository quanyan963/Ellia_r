package com.txtled.ellia_r.base;



import com.txtled.ellia_r.application.MyApplication;
import com.txtled.ellia_r.di.component.ActivityComponent;
import com.txtled.ellia_r.di.component.DaggerActivityComponent;
import com.txtled.ellia_r.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by Mr.Quan on 2018/4/13.
 */

public abstract class MvpBaseActivity<T extends BasePresenter> extends BaseActivity implements
        BaseView {
    @Inject
    public T presenter;

    public abstract void setInject();

    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
        setInject();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.detachView();
    }
}
