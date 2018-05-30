package com.example.asus.yklx2.ui.search.contract;

import com.example.asus.yklx2.bean.SearchBean;
import com.example.asus.yklx2.ui.base.BaseContract;

/**
 * Created by asus on 2018/5/14.
 */

public interface SearchContract {
    interface View extends BaseContract.BaseView{
        void getProductsSuccess(SearchBean searchBean);

    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getPresenter(String keywords, String pscid);
    }
}
