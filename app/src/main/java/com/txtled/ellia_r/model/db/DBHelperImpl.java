package com.txtled.ellia_r.model.db;


import com.txtled.ellia_r.application.MyApplication;
import com.txtled.ellia_r.bean.ColorList;
import com.txtled.ellia_r.bean.dao.DaoMaster;
import com.txtled.ellia_r.bean.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by Mr.Quan on 2018/4/17.
 */

public class DBHelperImpl implements DBHelper {
    private static final String DB_NAME = "ellia.db";
    private DaoSession mDaoSession;

    @Inject
    public DBHelperImpl() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApplication.
                getInstance(), DB_NAME);
        Database db = openHelper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    @Override
    public void insertColor(ColorList color) {
        mDaoSession.getColorListDao().insert(color);
    }

    @Override
    public void deleteColor(ColorList color) {
        mDaoSession.getColorListDao().delete(color);
    }

    @Override
    public List<ColorList> getColorList() {
        return mDaoSession.getColorListDao().loadAll();
    }
//
//    @Override
//    public void updateFlame(String type, int value) {
//        Flame flame = getFlame();
//        switch (type){
//            case LIGHT_STATUE:
//                flame.setLightStatue(value);
//                mDaoSession.getFlameDao().update(flame);
//                break;
//            case LIGHT:
//                flame.setLight(value);
//                mDaoSession.getFlameDao().update(flame);
//                break;
//            case POWER:
//                flame.setPower(value);
//                mDaoSession.getFlameDao().update(flame);
//                break;
//            case SPEED:
//                flame.setSpeed(value);
//                mDaoSession.getFlameDao().update(flame);
//                break;
//            case TO_MUSIC:
//                flame.setToMusic(value);
//                mDaoSession.getFlameDao().update(flame);
//                break;
//        }
//    }
}
