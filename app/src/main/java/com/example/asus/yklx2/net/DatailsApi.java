package com.example.asus.yklx2.net;



import com.example.asus.yklx2.bean.DatailsBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/12.
 */

public class DatailsApi {
    private static DatailsApi datailsApi;
    private DatailsApiService datailsApiService;

    private DatailsApi(DatailsApiService datailsApiService) {
        this.datailsApiService = datailsApiService;
    }
    public static DatailsApi getDatailsApi(DatailsApiService datailsApiService) {
        if (datailsApi == null) {
            datailsApi = new DatailsApi(datailsApiService);
        }
        return datailsApi;
    }

    public Observable<DatailsBean> getDatailsApi(String pid) {
        return datailsApiService.getDatails(pid);
    }
}
