package com.txtled.ellia_r.light.mvp;

import com.txtled.ellia_r.R;
import com.txtled.ellia_r.base.RxPresenter;
import com.txtled.ellia_r.bean.ColorList;
import com.txtled.ellia_r.model.DataManagerModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Mr.Quan on 2018/8/30.
 */

public class LightPresenter extends RxPresenter<LightContract.View> implements LightContract.Presenter {

    private DataManagerModel mDataManagerModel;
    @Inject
    public LightPresenter(DataManagerModel mDataManagerModel) {
        this.mDataManagerModel = mDataManagerModel;
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId){
            case R.id.tv_hide:
                view.ColorPickVisible();
                break;
            case R.id.iv_plus:
                view.ColorPickVisible();
                break;
            case R.id.rtv_select:
                view.colorSelected();
                break;
        }
    }

    @Override
    public List<ColorList> getList() {
        return mDataManagerModel.getColorList();
    }

    @Override
    public void insertColor(ColorList color) {
        mDataManagerModel.insertColor(color);
        view.ColorPickVisible();
    }
}
