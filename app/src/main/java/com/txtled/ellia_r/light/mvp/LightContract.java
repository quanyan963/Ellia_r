package com.txtled.ellia_r.light.mvp;

import com.txtled.ellia_r.base.BasePresenter;
import com.txtled.ellia_r.base.BaseView;
import com.txtled.ellia_r.bean.ColorList;

import java.util.List;

/**
 * Created by Mr.Quan on 2018/8/30.
 */

public interface LightContract {

    interface View extends BaseView{

        void ColorPickVisible();

        void colorSelected();
    }

    interface Presenter extends BasePresenter<View>{

        void onClick(int viewId);

        List<ColorList> getList();

        void insertColor(ColorList color);
    }
}
