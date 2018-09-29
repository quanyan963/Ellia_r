package com.txtled.ellia_r.di.component;



import com.txtled.ellia_r.application.MyApplication;
import com.txtled.ellia_r.di.module.AppModule;
import com.txtled.ellia_r.model.DataManagerModel;
import com.txtled.ellia_r.model.ble.BleHelper;
import com.txtled.ellia_r.model.db.DBHelper;
import com.txtled.ellia_r.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mr.Quan on 2018/4/13.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    MyApplication getContext();

    DataManagerModel getDataManagerModel();

    BleHelper getBleHelper();

    DBHelper getDbHelper();

    PreferencesHelper getPreferencesHelper();
}
