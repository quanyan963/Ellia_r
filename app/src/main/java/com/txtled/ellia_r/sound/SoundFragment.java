package com.txtled.ellia_r.sound;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.txtled.ellia_r.R;
import com.txtled.ellia_r.base.MvpBaseFragment;
import com.txtled.ellia_r.sound.mvp.SoundContract;
import com.txtled.ellia_r.sound.mvp.SoundPresenter;

import butterknife.BindView;

import static com.txtled.ellia_r.constant.Constants.PAGE_SOUND;

/**
 * Created by Mr.Quan on 2018/8/30.
 */

public class SoundFragment extends MvpBaseFragment<SoundPresenter> implements SoundContract.View {
    @BindView(R.id.rlv_sound)
    RecyclerView rlvSound;

    private SoundAdapter mSoundAdapter;

    private View view;

    private int height;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sound, container, false);
        return view;
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
        rlvSound.setHasFixedSize(true);
        rlvSound.setLayoutManager(layoutManager);
        mSoundAdapter = new SoundAdapter(getContext());
        rlvSound.setNestedScrollingEnabled(false);
        rlvSound.setAdapter(mSoundAdapter);
        mSoundAdapter.initList();

    }
}
