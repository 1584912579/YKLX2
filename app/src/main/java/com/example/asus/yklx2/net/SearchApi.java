package com.example.asus.yklx2.net;

import com.example.asus.yklx2.bean.SearchBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/29.
 */

public class SearchApi {
    private static SearchApi searchApi;
    private SearchApiService searchApiService;
    private SearchApi (SearchApiService searchApiService){
        this.searchApiService=searchApiService;
    }

    public static SearchApi getSearchApi(SearchApiService searchApiService){
        if (searchApi==null){
            searchApi=new SearchApi(searchApiService);
        }
        return searchApi;
    }
    public Observable<SearchBean> getSearchApi(String keywords,String page){
        return searchApiService.getSearchApi(keywords,page);
    }
}
