package com.example.asus.yklx2.ui.search.presenter;

import com.example.asus.yklx2.bean.SearchBean;
import com.example.asus.yklx2.net.SearchApi;
import com.example.asus.yklx2.ui.base.BasePresenter;
import com.example.asus.yklx2.ui.search.contract.SearchContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/14.
 */

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    private SearchApi searchApi;
    @Inject
    public SearchPresenter(SearchApi searchApi) {
        this.searchApi = searchApi;
    }

    @Override
    public void getPresenter(String keywords,String pscid) {
        searchApi.getSearchApi(keywords,pscid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean productsBean) {
                        if (mView!=null){
                            mView.getProductsSuccess(productsBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
