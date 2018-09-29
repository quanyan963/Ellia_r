package com.txtled.ellia_r.light;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.txtled.ellia_r.R;
import com.txtled.ellia_r.base.MvpBaseFragment;
import com.txtled.ellia_r.bean.ColorList;
import com.txtled.ellia_r.light.mvp.LightContract;
import com.txtled.ellia_r.light.mvp.LightPresenter;
import com.txtled.ellia_r.main.MainActivity;
import com.txtled.ellia_r.utils.Utils;
import com.txtled.ellia_r.widget.ColorPicker;
import com.txtled.ellia_r.widget.CustomTextView;
import com.txtled.ellia_r.widget.ItemLayout;
import com.txtled.ellia_r.widget.RoundTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mr.Quan on 2018/8/30.
 */

public class LightFragment extends MvpBaseFragment<LightPresenter> implements LightContract.View,
        View.OnClickListener,ColorPicker.OnColorChangedListener,ItemLayout.OnSeekBarListener,
        LightAdapter.OnGetMeasure {
    @BindView(R.id.rlv_light)
    RecyclerView rlvLight;
    @BindView(R.id.tv_hide)
    CustomTextView tvHide;
    @BindView(R.id.color_picker)
    ColorPicker colorPicker;
    @BindView(R.id.il_lightness)
    ItemLayout ilLightness;
    @BindView(R.id.rl_color_view)
    RelativeLayout rlColorView;
    @BindView(R.id.iv_plus)
    ImageView ivPlus;
    @BindView(R.id.rtv_select)
    RoundTextView rtvSelect;

    private View view;
    private LightAdapter mLightAdapter;
    private MainActivity.MyTouchListener mMyTouchListener;
    private List<ColorList> colorLists;
    private int mColor;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_light, null, false);
        return view;
    }

    public View getColorPicker(){
        return colorPicker;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            ((MainActivity) getActivity()).registerMyTouchListener(mMyTouchListener);
        } else {
            ((MainActivity) getActivity()).unRegisterMyTouchListener(mMyTouchListener);
        }
    }

    @Override
    public void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };

        mMyTouchListener = new MainActivity.MyTouchListener() {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                //不在colorPicker touch范围类
                return !Utils.inRangeOfView(colorPicker, event) && !Utils.inRangeOfView(
                        ilLightness, event) && !Utils.inRangeOfView(tvHide,event) ||
                        rlColorView.getVisibility() == View.GONE;
            }
        };

        colorLists = presenter.getList();
        rlvLight.setHasFixedSize(true);
        rlvLight.setLayoutManager(layoutManager);
        mLightAdapter = new LightAdapter(getContext());
        rlvLight.setNestedScrollingEnabled(false);
        rlvLight.setAdapter(mLightAdapter);
        mLightAdapter.initList(colorLists);
        ilLightness.setSeekBarProgress(100);
        initListener();

    }

    private void initListener() {
        ivPlus.setOnClickListener(this);
        tvHide.setOnClickListener(this);
        rtvSelect.setOnClickListener(this);
        colorPicker.setOnColorSelectListener(this);
        ilLightness.setOnSeekBarListener(this);
    }

    @Override
    public void onClick(View view) {
        presenter.onClick(view.getId());
    }

    @Override
    public void ColorPickVisible() {
        if (ivPlus.getVisibility() == View.VISIBLE){
            ivPlus.setVisibility(View.GONE);
            rtvSelect.setVisibility(View.VISIBLE);
            rlColorView.setVisibility(View.VISIBLE);
        }else {
            rlColorView.setVisibility(View.GONE);
            ivPlus.setVisibility(View.VISIBLE);
            rtvSelect.setVisibility(View.GONE);
        }
    }

    @Override
    public void colorSelected() {
        ColorList color = new ColorList(mColor);
        presenter.insertColor(color);
        mLightAdapter.insertColor(color);
    }

    @Override
    public void onColorSelect(int color) {
        rtvSelect.setRoundBackground(color);
        mColor = color;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onProgressChange(SeekBar seekBar) {
        colorPicker.setBrightness((float) seekBar.getProgress() / 100);
    }

    @Override
    public void getMeasure(int height) {
        
    }
}
