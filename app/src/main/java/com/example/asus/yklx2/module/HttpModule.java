package com.example.asus.yklx2.module;

import com.example.asus.yklx2.net.AddCartApi;
import com.example.asus.yklx2.net.AddCartApiService;
import com.example.asus.yklx2.net.CartsApi;
import com.example.asus.yklx2.net.CartsApiService;
import com.example.asus.yklx2.net.DatailsApi;
import com.example.asus.yklx2.net.DatailsApiService;
import com.example.asus.yklx2.net.DeleteCartApi;
import com.example.asus.yklx2.net.DeleteCartApiServlce;
import com.example.asus.yklx2.net.SearchApi;
import com.example.asus.yklx2.net.SearchApiService;
import com.example.asus.yklx2.net.UpdateCartsApi;
import com.example.asus.yklx2.net.UpdateCartsApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asus on 2018/5/29.
 */
@Module
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder(){
        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS);
    }
    @Provides
    SearchApi provideSearchApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        SearchApiService searchApiService = retrofit.create(SearchApiService.class);
        return SearchApi.getSearchApi(searchApiService);
    }
    @Provides
    DatailsApi provideDatailsApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        DatailsApiService datailsApiService = retrofit.create(DatailsApiService.class);
        return DatailsApi.getDatailsApi(datailsApiService);
    }
    @Provides
    AddCartApi provideAddCartApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        AddCartApiService addCartApiService = retrofit.create(AddCartApiService.class);
        return AddCartApi.getAddCartApi(addCartApiService);
    }
    @Provides
    CartsApi provideCartsApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        CartsApiService cartsApiService = retrofit.create(CartsApiService.class);
        return CartsApi.getCartsApi(cartsApiService);
    }
    @Provides
    UpdateCartsApi provideUpdateCartsApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        UpdateCartsApiService updateCartsApiService = retrofit.create(UpdateCartsApiService.class);
        return UpdateCartsApi.getupdateCartsApi(updateCartsApiService);
    }
    @Provides
    DeleteCartApi provideDeleteCartApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.23.105/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        DeleteCartApiServlce deleteCartApiServlce = retrofit.create(DeleteCartApiServlce.class);
        return DeleteCartApi.getDeleteCartApi(deleteCartApiServlce);
    }
}
