package com.example.asus.yklx2.ui.shoping.presenter;

import com.example.asus.yklx2.bean.BaseBean;
import com.example.asus.yklx2.bean.CartsBean;
import com.example.asus.yklx2.bean.SellerBean;
import com.example.asus.yklx2.net.CartsApi;
import com.example.asus.yklx2.net.DeleteCartApi;
import com.example.asus.yklx2.net.UpdateCartsApi;
import com.example.asus.yklx2.ui.base.BasePresenter;
import com.example.asus.yklx2.ui.shoping.contract.ShopCartContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/29.
 */

public class ShopCartPresenter extends BasePresenter<ShopCartContract.View> implements ShopCartContract.Presnter {
    private CartsApi cartsApi;
    private UpdateCartsApi updateCartsApi;
    private DeleteCartApi deleteCartApi;
    @Inject
    public ShopCartPresenter(CartsApi cartsApi, UpdateCartsApi updateCartsApi, DeleteCartApi deleteCartApi) {
        this.cartsApi = cartsApi;
        this.updateCartsApi = updateCartsApi;
        this.deleteCartApi = deleteCartApi;
    }

    @Override
    public void getCartssuess(String uid) {
        cartsApi.getCartsApi(uid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<CartsBean, List<CartsBean.DataBean>>() {
                    @Override
                    public  List<CartsBean.DataBean> apply(CartsBean cartsBean) throws Exception {
                        return cartsBean.getData();
                    }
                }).subscribe(new Consumer<List<CartsBean.DataBean>>() {
            @Override
            public void accept(List<CartsBean.DataBean> dataBeans) throws Exception {
                List<SellerBean> groupList =new ArrayList<>();
                List<List<CartsBean.DataBean.ListBean>> childList =new ArrayList<>();
                for (int i = 0; i < dataBeans.size() ; i++) {
                    CartsBean.DataBean dataBean = dataBeans.get(i);
                    SellerBean sellerBean = new SellerBean();
                    sellerBean.setSellerid(dataBean.getSellerid());
                    sellerBean.setSellerName(dataBean.getSellerName());
                    sellerBean.setSelected(isSellerProductAllSelect(dataBean));
                    groupList.add(sellerBean);

                    List<CartsBean.DataBean.ListBean> list = dataBean.getList();
                    childList.add(list);
                }
                if (mView!=null){
                    mView.getCartssuess(groupList,childList);
                }
            }
        });
    }

    private boolean isSellerProductAllSelect(CartsBean.DataBean dataBean) {
        List<CartsBean.DataBean.ListBean> list = dataBean.getList();
        for (int i = 0; i < list.size(); i++) {
            CartsBean.DataBean.ListBean listBean = list.get(i);
            if (0==listBean.getSelected()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void getupdateCartssuess(String uid, String sellerid, String pid, String num, String selected) {
        updateCartsApi.getupdateCartsApi(uid,sellerid,pid,num,selected)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView!=null){
                    mView.getupdateCartssuess();
                }
            }
        });

    }

    @Override
    public void getDeleteCartsSuccess(String uid, String pid) {
        deleteCartApi.getDeleteCartApi(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {
                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView != null) {
                    mView.getDeleteCartsSuccess();
                }
            }
        });
    }
}
