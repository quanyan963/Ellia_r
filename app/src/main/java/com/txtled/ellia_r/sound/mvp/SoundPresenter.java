package com.txtled.ellia_r.sound.mvp;

import com.txtled.ellia_r.base.RxPresenter;

import javax.inject.Inject;

/**
 * Created by Mr.Quan on 2018/8/30.
 */

public class SoundPresenter extends RxPresenter<SoundContract.View> implements SoundContract.Presenter {

    @Inject
    public SoundPresenter() {
    }
}
