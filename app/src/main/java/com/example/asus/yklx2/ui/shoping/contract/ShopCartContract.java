package com.example.asus.yklx2.ui.shoping.contract;

import com.example.asus.yklx2.bean.CartsBean;
import com.example.asus.yklx2.bean.SellerBean;
import com.example.asus.yklx2.ui.base.BaseContract;

import java.util.List;

/**
 * Created by asus on 2018/5/29.
 */

public interface ShopCartContract {
    interface View extends BaseContract.BaseView{
        void getCartssuess(List<SellerBean> groupList , List<List<CartsBean.DataBean.ListBean>> childList);
        void getupdateCartssuess();
        void getDeleteCartsSuccess();
    }
    interface Presnter extends BaseContract.BasePresenter<View>{
        void getCartssuess(String uid);
        void getupdateCartssuess(String uid,String sellerid,String pid,String num,String selected);
        void getDeleteCartsSuccess(String uid,String pid);
    }
}
