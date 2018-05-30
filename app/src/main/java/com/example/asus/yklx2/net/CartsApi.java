package com.example.asus.yklx2.net;

import com.example.asus.yklx2.bean.BaseBean;
import com.example.asus.yklx2.bean.CartsBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/29.
 */

public class CartsApi {
    private static CartsApi cartsApi;
    private CartsApiService cartsApiService;

    private CartsApi(CartsApiService cartsApiService) {
        this.cartsApiService = cartsApiService;
    }

    public static CartsApi getCartsApi(CartsApiService cartsApiService) {
        if (cartsApi == null) {
            cartsApi = new CartsApi(cartsApiService);
        }
        return cartsApi;
    }
    public Observable<CartsBean> getCartsApi(String uid) {
        return cartsApiService.getCartsApi(uid);
    }
}
