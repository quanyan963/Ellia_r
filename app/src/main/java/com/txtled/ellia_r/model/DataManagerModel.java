package com.txtled.ellia_r.model;

import android.app.Activity;
import android.content.Context;


import com.txtled.ellia_r.bean.ColorList;
import com.txtled.ellia_r.model.ble.BleHelper;
import com.txtled.ellia_r.model.db.DBHelper;
import com.txtled.ellia_r.model.operate.OperateHelper;
import com.txtled.ellia_r.model.prefs.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Mr.Quan on 2018/4/17.
 */

public class DataManagerModel implements DBHelper,BleHelper,OperateHelper,PreferencesHelper {
    private BleHelper mBleHelper;
    private DBHelper mDBDbHelper;
    private PreferencesHelper mPreferencesHelper;
    private OperateHelper mOperateHelper;

    public DataManagerModel(BleHelper mBleHelper, DBHelper mDBDbHelper, PreferencesHelper
            mPreferencesHelper, OperateHelper mOperateHelper) {
        this.mBleHelper = mBleHelper;
        this.mDBDbHelper = mDBDbHelper;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mOperateHelper = mOperateHelper;
    }

    @Override
    public void scanBle(Activity activity, boolean isSpecified, OnScanBleListener onScanBleListener, OnConnBleListener onConnBleListener) {
        mBleHelper.scanBle(activity,isSpecified,onScanBleListener,onConnBleListener);
    }

    @Override
    public void connBle(OnConnBleListener onConnBleListener) {
        mBleHelper.connBle(onConnBleListener);
    }

    @Override
    public void writeCommand(String command) {
        mBleHelper.writeCommand(command);
    }

    @Override
    public void notifyBle(final OnReadListener readListener) {
        mBleHelper.notifyBle(readListener);
    }

    @Override
    public void readCommand(OnReadListener readListener) {
        mBleHelper.readCommand(readListener);
    }

    @Override
    public void unRegisterConn() {
        mBleHelper.unRegisterConn();
    }

    @Override
    public void requestPermissions(Activity activity, String[] permissions, OnPermissionsListener permissionsListener) {
        mOperateHelper.requestPermissions(activity,permissions,permissionsListener);
    }

//    @Override
//    public void insertMusic(Song song) {
//        mDBDbHelper.insertMusic(song);
//    }
//
//    @Override
//    public void deleteAllSong() {
//        mDBDbHelper.deleteAllSong();
//    }
//
//    @Override
//    public List<Song> getMusicInfoList() {
//        return mDBDbHelper.getMusicInfoList();
//    }
//
//    @Override
//    public void insertFlame(Flame flame) {
//        mDBDbHelper.insertFlame(flame);
//    }
//
//    @Override
//    public void updateFlame(String type, int value) {
//        mDBDbHelper.updateFlame(type,value);
//    }
//
//    @Override
//    public Flame getFlame() {
//        return mDBDbHelper.getFlame();
//    }

    @Override
    public int getPlayPosition() {
        return mPreferencesHelper.getPlayPosition();
    }

    @Override
    public void setPlayPosition(int position) {
        mPreferencesHelper.setPlayPosition(position);
    }

    @Override
    public void setIsFirstApp(boolean b) {
        mPreferencesHelper.setIsFirstApp(b);
    }

    @Override
    public boolean getIsFirstApp() {
        return mPreferencesHelper.getIsFirstApp();
    }

    @Override
    public void setMainVolume(int progress) {
        mPreferencesHelper.setMainVolume(progress);
    }

    @Override
    public int getMainVolume() {
        return mPreferencesHelper.getMainVolume();
    }

    @Override
    public void setInitDialog(boolean b) {
        mPreferencesHelper.setInitDialog(b);
    }

    @Override
    public boolean getInitDialog() {
        return mPreferencesHelper.getInitDialog();
    }

    @Override
    public boolean getIsCycle() {
        return mPreferencesHelper.getIsCycle();
    }

    @Override
    public void setIsCycle(boolean b) {
        mPreferencesHelper.setIsCycle(b);
    }

    @Override
    public boolean getIsRandom() {
        return mPreferencesHelper.getIsRandom();
    }

    @Override
    public void setIsRandom(boolean b) {
        mPreferencesHelper.setIsRandom(b);
    }

    @Override
    public void setPlayStatue(int statue) {
        mPreferencesHelper.setPlayStatue(statue);
    }

    @Override
    public int getPlayStatue() {
        return mPreferencesHelper.getPlayStatue();
    }

    public void updatePlayState(int flag) {
//        switch (flag) {
//            case Constants.FLAG_SPA:
//                if (mPreferencesHelper.getRadioPlayState()) {
//                    mPreferencesHelper.setRadioPlayState(false);
//                }
//                break;
//            case Constants.FLAG_MUSIC:
//                if (mPreferencesHelper.getRadioPlayState()) {
//                    mPreferencesHelper.setRadioPlayState(false);
//                }
//                if (mDBDbHelper.getSpa().getState()) {
//                    mDBDbHelper.updateSpa(Spa.FLAG_STATE,"0");
//                }
//                break;
//            case Constants.FLAG_RADIO:
//                if (mDBDbHelper.getSpa().getState()) {
//                    mDBDbHelper.updateSpa(Spa.FLAG_STATE,"0");
//                }
//                break;
//        }
    }

    @Override
    public void insertColor(ColorList color) {
        mDBDbHelper.insertColor(color);
    }

    @Override
    public void deleteColor(ColorList color) {
        mDBDbHelper.deleteColor(color);
    }

    @Override
    public List<ColorList> getColorList() {
        return mDBDbHelper.getColorList();
    }
}
