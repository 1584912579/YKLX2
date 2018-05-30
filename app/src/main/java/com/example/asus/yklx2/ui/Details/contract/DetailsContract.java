package com.example.asus.yklx2.ui.Details.contract;


import com.example.asus.yklx2.bean.BaseBean;
import com.example.asus.yklx2.bean.DatailsBean;
import com.example.asus.yklx2.ui.base.BaseContract;

/**
 * Created by asus on 2018/5/12.
 */

public interface DetailsContract {
    interface View extends BaseContract.BaseView{
        void getDatailsSuccess(DatailsBean datailsBean);
        void getAddCartSuccess(BaseBean baseBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getPresenter(String pid);
        void getAddCartPresenter(String uid, String pid);
    }
}
