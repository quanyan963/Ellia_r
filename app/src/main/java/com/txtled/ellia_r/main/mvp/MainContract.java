package com.txtled.ellia_r.main.mvp;

import android.support.constraint.ConstraintLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.txtled.ellia_r.base.BasePresenter;
import com.txtled.ellia_r.base.BaseView;
import com.txtled.ellia_r.widget.SlideViewPager;

/**
 * Created by Mr.Quan on 2018/8/29.
 */

public interface MainContract {
    interface View extends BaseView{
        void changePlayStatue(int statue);

        void onTimerClick();

        void popUpMusicPlayer();
    }

    interface Presenter extends BasePresenter<View>{
        void switchNavigationByPage(int position, RadioGroup radioGroup, ConstraintLayout parent);
        void switchNavigationByRb(int id, SlideViewPager vPager);
        void onPlayButtonClicked();
        void changeSong(int songNum);

        void onScroll(SlideViewPager vPager, int position);

        void prepareOptionsMenu(SlideViewPager vPager, Menu menu);

        void optionsItemSelected(MenuItem item);

        void onClick(android.view.View view);
    }
}
