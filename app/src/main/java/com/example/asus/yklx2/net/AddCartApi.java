package com.example.asus.yklx2.net;



import com.example.asus.yklx2.bean.BaseBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/13.
 */

public class AddCartApi {
    private static AddCartApi addCartApi;
    private AddCartApiService addCartApiService;

    private AddCartApi(AddCartApiService addCartApiService) {
        this.addCartApiService = addCartApiService;
    }

    public static AddCartApi getAddCartApi(AddCartApiService addCartApiService) {
        if (addCartApi == null) {
            addCartApi = new AddCartApi(addCartApiService);
        }
        return addCartApi;
    }
    public Observable<BaseBean> getAddCartApi(String uid, String pid) {
        return addCartApiService.getAddCart(uid,pid);
    }
}
