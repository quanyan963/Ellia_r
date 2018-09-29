package com.txtled.ellia_r.main.mvp;

import android.support.constraint.ConstraintLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.txtled.ellia_r.R;
import com.txtled.ellia_r.base.RxPresenter;
import com.txtled.ellia_r.model.DataManagerModel;
import com.txtled.ellia_r.utils.BleUtils;
import com.txtled.ellia_r.widget.SlideViewPager;

import javax.inject.Inject;

import static com.txtled.ellia_r.constant.Constants.PAGE_AROMA;
import static com.txtled.ellia_r.constant.Constants.PAGE_LIGHT;
import static com.txtled.ellia_r.constant.Constants.PAGE_SOUND;

/**
 * Created by Mr.Quan on 2018/8/29.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    DataManagerModel mDataManagerModel;
    private int playStatue;

    @Inject
    public MainPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }

    @Override
    public void switchNavigationByPage(int position, RadioGroup radioGroup, ConstraintLayout parent) {
        switch (position) {
            case 0:
                radioGroup.check(R.id.rb_sound);
                break;
            case 1:
                radioGroup.check(R.id.rb_aroma);
                break;
            case 2:
                radioGroup.check(R.id.rb_light);
                break;
            default:
                //rlMainBg.setBackgroundResource(R.color.white);
                break;
        }
    }

    @Override
    public void switchNavigationByRb(int id, SlideViewPager vPager) {
        int position = 0;
        switch (id) {
            case R.id.rb_aroma:
                position = 1;
                break;
            case R.id.rb_sound:
                position = 0;
                break;
            case R.id.rb_light:
                position = 2;
                break;

        }
        //view.updateTitle(position);
        //view.updateRightView(position);
        vPager.setCurrentItem(position);
    }

    @Override
    public void onPlayButtonClicked() {
        playStatue = (mDataManagerModel.getPlayStatue() + 1) % 2;
        mDataManagerModel.setPlayStatue(playStatue);
//        mDataManagerModel.writeCommand(BleUtils.getLight(playStatue));
        view.changePlayStatue(playStatue);
    }

    @Override
    public void changeSong(int songNum) {
        mDataManagerModel.setPlayStatue(1);
        //mDataManagerModel.writeCommand(BleUtils.getLight(songNum));
    }

    @Override
    public void onScroll(SlideViewPager vPager, int position) {
        switch (position) {
            case 0:
                vPager.resetHeight(PAGE_SOUND);
                break;
            case 1:
                vPager.resetHeight(PAGE_AROMA);
                break;
            case 2:
                vPager.resetHeight(PAGE_LIGHT);
                break;
        }
    }

    @Override
    public void prepareOptionsMenu(SlideViewPager vPager, Menu menu) {
        switch (vPager.getCurrentItem()) {
            case 0:
            case 1:
                menu.findItem(R.id.camera).setVisible(false);
                menu.findItem(R.id.alarm).setVisible(true);
                menu.findItem(R.id.setting).setVisible(true);
                break;
            case 2:
                menu.findItem(R.id.camera).setVisible(true);
                menu.findItem(R.id.alarm).setVisible(true);
                menu.findItem(R.id.setting).setVisible(true);
                break;
        }
    }

    @Override
    public void optionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera:

                break;
            case R.id.alarm:
                view.onTimerClick();
                break;
            case R.id.setting:

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fl_player:
                view.popUpMusicPlayer();
                break;
        }
    }
}
