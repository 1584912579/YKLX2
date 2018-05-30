package com.example.asus.yklx2.ui.Details.presenter;

import com.example.asus.yklx2.bean.BaseBean;
import com.example.asus.yklx2.bean.DatailsBean;
import com.example.asus.yklx2.net.AddCartApi;
import com.example.asus.yklx2.net.DatailsApi;
import com.example.asus.yklx2.ui.Details.contract.DetailsContract;
import com.example.asus.yklx2.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/12.
 */

public class DetailsPresenter extends BasePresenter<DetailsContract.View> implements DetailsContract.Presenter {
    private DatailsApi datailsApi;
    private AddCartApi addCartApi;
    @Inject
    public DetailsPresenter(DatailsApi datailsApi,AddCartApi addCartApi) {
        this.datailsApi = datailsApi;
        this.addCartApi = addCartApi;
    }

    @Override
    public void getPresenter(String pid) {
        datailsApi.getDatailsApi(pid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DatailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DatailsBean datailsBean) {
                        mView.getDatailsSuccess(datailsBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getAddCartPresenter(String uid, String pid ) {
        addCartApi.getAddCartApi(uid, pid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        mView.getAddCartSuccess(baseBean);
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
