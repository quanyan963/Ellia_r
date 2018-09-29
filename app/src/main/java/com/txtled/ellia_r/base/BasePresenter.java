package com.txtled.ellia_r.base;

/**
 * Created by Mr.Quan on 2018/4/13.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
