package com.ljhdemo.newgank.molule_main.ui.presenter.impl;

import com.ljhdemo.newgank.common.base.BasePresenter;
import com.ljhdemo.newgank.molule_main.bean.GankResult;
import com.ljhdemo.newgank.molule_main.constant.Constants;
import com.ljhdemo.newgank.molule_main.http.ApiFactory;
import com.ljhdemo.newgank.molule_main.ui.iView.IWelfareView;
import com.ljhdemo.newgank.molule_main.ui.presenter.IWelfarePresenter;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ljh on 2018/1/4.
 */

public class WelfarePresenterImpl extends BasePresenter<IWelfareView> implements IWelfarePresenter {

    private List<GankResult.ResultsBean> beanList;
    private int pageNum = 1;
    private LifecycleProvider<FragmentEvent> mProvider;

    public WelfarePresenterImpl(LifecycleProvider<FragmentEvent> provider) {
        mProvider = provider;
    }

    @Override
    public void getNewDatas() {
        ApiFactory.gankApi()
                .getGankResult(Constants.FlagWelFare, 10, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mProvider.<GankResult>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribe(new Observer<GankResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankResult gankResult) {
                        beanList = gankResult.getResults();
                        getView().overRefresh(true);
                        getView().setWelFareList(beanList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getView().overRefresh(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getMoreDatas() {
        pageNum++;
        ApiFactory.gankApi()
                .getGankResult("福利", 10, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mProvider.<GankResult>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribe(new Observer<GankResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getView().overRefresh(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
