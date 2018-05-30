package com.example.asus.yklx2.net;

import com.example.asus.yklx2.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/13.
 */

public interface AddCartApiService {
    @FormUrlEncoded
    @POST("product/addCart")
    Observable<BaseBean> getAddCart(@Field("uid") String uid,
                                    @Field("pid") String pid);

}
