package com.example.asus.yklx2.net;

import com.example.asus.yklx2.bean.CartsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/29.
 */

public interface CartsApiService {
    @FormUrlEncoded
    @POST("product/getCarts")
    Observable<CartsBean> getCartsApi(@Field("uid") String uid);
}
