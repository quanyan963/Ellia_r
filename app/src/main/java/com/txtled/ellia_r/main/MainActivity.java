package com.txtled.ellia_r.main;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.txtled.ellia_r.R;
import com.txtled.ellia_r.aroma.AromaFragment;
import com.txtled.ellia_r.base.MvpBaseActivity;
import com.txtled.ellia_r.constant.Constants;
import com.txtled.ellia_r.light.LightAdapter;
import com.txtled.ellia_r.light.LightFragment;
import com.txtled.ellia_r.main.mvp.MainContract;
import com.txtled.ellia_r.main.mvp.MainPresenter;
import com.txtled.ellia_r.sound.SoundAdapter;
import com.txtled.ellia_r.sound.SoundFragment;
import com.txtled.ellia_r.utils.Utils;
import com.txtled.ellia_r.widget.ColorPicker;
import com.txtled.ellia_r.widget.CustomScrollView;
import com.txtled.ellia_r.widget.CustomTextView;
import com.txtled.ellia_r.widget.SlideViewPager;
import com.txtled.ellia_r.widget.TintRadioButton;
import com.txtled.ellia_r.widget.listener.MusicPlayerListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.txtled.ellia_r.constant.Constants.PAGE_LIGHT;

public class MainActivity extends MvpBaseActivity<MainPresenter> implements MainContract.View,
        RadioGroup.OnCheckedChangeListener, TimerAdapter.OnCheckedListener,
        TimerAdapter.OnImgListener, SoundAdapter.PlayerStatue, View.OnClickListener,
        MusicPlayerListener, LightAdapter.OnGetMeasure {

    @BindView(R.id.v_pager)
    SlideViewPager vPager;
    @BindView(R.id.rb_sound)
    TintRadioButton rbSound;
    @BindView(R.id.rb_aroma)
    TintRadioButton rbAroma;
    @BindView(R.id.rb_light)
    TintRadioButton rbLight;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.cl_main)
    ConstraintLayout clMain;
    @BindView(R.id.rl_timer)
    RecyclerView rlTimer;
    //    @BindView(R.id.rl_main)
//    RelativeLayout rlMain;
//    @BindView(R.id.sv_main)
//    CustomScrollView svMain;
    @BindView(R.id.iv_up)
    ImageView ivUp;
    @BindView(R.id.tv_song)
    CustomTextView tvSong;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.fl_player)
    FrameLayout flPlayer;
    @BindView(R.id.sv_main)
    CustomScrollView svMain;

    private Fragment mAromaFragment;
    private PagerAdapter mPagerAdapter;
    private boolean stop;
    private TimerAdapter mTimerAdapter;
    // 保存MyTouchListener接口的列表
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<>();
    private ColorPicker colorPicker;
    private int pageIndex;

    @Override
    public void getMeasure(int height) {
        //vPager.removeViewAt(PAGE_LIGHT);
        vPager.addHeight(PAGE_LIGHT, height);
    }


    public interface MyTouchListener {
        boolean onTouchEvent(MotionEvent event);
    }

    @Override
    public void setInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void init() {

        initToolbar();
        initViewPager();
        initTimer();
        flPlayer.setOnClickListener(this);
        svMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mPagerAdapter.getItem(pageIndex) instanceof LightFragment){
                    if (Utils.inRangeOfView(((LightFragment)mPagerAdapter.getItem(pageIndex)).
                            getColorPicker(),motionEvent)){
                        return true;
                    }else {
                        return false;
                    }
                }else {
                    return false;
                }
            }
        });
    }

    private void initTimer() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        rlTimer.setHasFixedSize(true);
        rlTimer.setLayoutManager(layoutManager);
        mTimerAdapter = new TimerAdapter(this, this, this);
        rlTimer.setNestedScrollingEnabled(false);
        rlTimer.setAdapter(mTimerAdapter);
        mTimerAdapter.initList();
    }

    private void initViewPager() {
        mAromaFragment = new AromaFragment();
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new SoundFragment(), getString(R.string.sound));
        mPagerAdapter.addFragment(mAromaFragment, getString(R.string.aroma));
        mPagerAdapter.addFragment(new LightFragment(), getString(R.string.light));
        vPager.setOnPageChangeListener(new myOnPageChangeListener());
        radioGroup.setOnCheckedChangeListener(this);
        vPager.setAdapter(mPagerAdapter);
        vPager.setCurrentItem(getIntent().getIntExtra(Constants.RB_ID, 1));
    }

    /**
     * timer子项点击事件
     *
     * @param position
     */
    @Override
    public void onClick(int position) {
        Utils.Logger(TAG, "clicked:", position + "");
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        stop = true;
        presenter.switchNavigationByRb(i, vPager);
        stop = false;
    }

    /**
     * timer点击事件
     *
     * @param position
     */
    @Override
    public void onImgClick(int position) {
        //mTimerAdapter.removeAll();
        rlTimer.setVisibility(View.GONE);
        Utils.Logger(TAG, "clicked:", position + "cancel");
    }

    /**
     * sound item点击事件
     */
    @Override
    public void onPlayerClick(int position) {
        presenter.changeSong(position);
        if (flPlayer.getVisibility() == View.GONE) {
            flPlayer.setVisibility(View.VISIBLE);
        }

    }

    /**
     * sound界面播放按钮点击事件
     */
    @Override
    public void onPlayButtonClick() {
        presenter.onPlayButtonClicked();
//        if (flPlayer.getVisibility() == View.GONE) {
//            flPlayer.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void changePlayStatue(int statue) {
        if (statue == 0) {
            flPlayer.setVisibility(View.GONE);
            //停止播放
        } else {
            flPlayer.setVisibility(View.VISIBLE);
            //开始播放
        }
    }

    @Override
    public void onTimerClick() {
        if (rlTimer.getVisibility() == View.GONE) {
            rlTimer.setVisibility(View.VISIBLE);
        } else {
            rlTimer.setVisibility(View.GONE);
        }
    }

    @Override
    public void popUpMusicPlayer() {
        Utils.showBottomDialog(this, this);
    }

    @Override
    public void onClick(View view) {
        presenter.onClick(view);
    }

    @Override
    public void onRewindClick() {
        Utils.Logger(TAG, "onRewindClick", "ok");
    }

    @Override
    public void onForwardClick() {
        Utils.Logger(TAG, "onForwardClick", "ok");
    }

    @Override
    public void onPlayClick() {
        Utils.Logger(TAG, "onPlayClick", "ok");
    }

    @Override
    public void onVolumeClick(int progress) {

    }

//    @Override
//    public void changeData(int position, int height) {
//        vPager.addHeight(position,height);
//    }

    private class myOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
            presenter.onScroll(vPager, position);
        }

        @Override
        public void onPageSelected(int position) {
            pageIndex = position;
            if (!stop)
                presenter.switchNavigationByPage(position, radioGroup, clMain);
            invalidateOptionsMenu();

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    /**
     * 根据不同fragment动态显示menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        presenter.prepareOptionsMenu(vPager, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * menu点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.optionsItemSelected(item);

        return super.onOptionsItemSelected(item);
    }

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove(listener);
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            if (listener != null) {

                vPager.setIsScanScroll(listener.onTouchEvent(ev));
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
