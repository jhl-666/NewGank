package com.ljhdemo.newgank.ui.presenter.impl;

import com.ljhdemo.newgank.bean.GankResult;
import com.ljhdemo.newgank.http.ServiceGenerator;
import com.ljhdemo.newgank.ui.base.BasePresenter;
import com.ljhdemo.newgank.ui.iView.IWelfareView;
import com.ljhdemo.newgank.ui.presenter.IWelfarePresenter;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ljh on 2018/1/4.
 */

public class WelfarePresenterImpl extends BasePresenter<IWelfareView> implements IWelfarePresenter {

    private List<GankResult.ResultsBean> beanList;
    private int pageNum = 1;

    @Override
    public void getNewDatas() {
        ServiceGenerator.getApiService()
                .getGankResult("福利", 10, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().overRefresh(false);
                    }

                    @Override
                    public void onNext(GankResult gankResult) {
                        beanList = gankResult.getResults();
                        getView().overRefresh(true);
                        getView().setWelFareList(beanList);
                    }
                });
    }

    @Override
    public void getMoreDatas() {
        pageNum++;
        ServiceGenerator.getApiService()
                .getGankResult("福利", 10, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().overRefresh(false);
                    }

                    @Override
                    public void onNext(GankResult gankResult) {
                        getView().overRefresh(true);
                        if (pageNum >= 10) {
                            getView().setLoadMoreEnabled(false);
                            getView().showToast("没有更多数据了");
                        } else {
                            beanList.addAll(gankResult.getResults());
                            getView().setLoadMoreEnabled(true);
                            getView().setWelFareList(gankResult.getResults());
                        }
                    }
                });
    }
}
