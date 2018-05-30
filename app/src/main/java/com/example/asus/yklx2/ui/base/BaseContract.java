package com.example.asus.yklx2.ui.base;

/**
 * Created by asus on 2018/5/29.
 */

public interface BaseContract {
    interface BaseView{
        void showLoading();
        void dismissLoading();
    }

    interface BasePresenter<T extends BaseView>{
        void attchView(T view);
        void detachView();
    }
}
