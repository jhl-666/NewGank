package com.ljhdemo.newgank.ui.presenter.impl;

import com.ljhdemo.newgank.bean.GankResult;
import com.ljhdemo.newgank.http.ServiceGenerator;
import com.ljhdemo.newgank.ui.base.BasePresenter;
import com.ljhdemo.newgank.ui.iView.ICategoryView;
import com.ljhdemo.newgank.ui.presenter.ICategoryPresenter;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ljh on 2018/1/4.
 * 分类
 */

public class CategoryPresenterImpl extends BasePresenter<ICategoryView> implements ICategoryPresenter {

    private List<GankResult.ResultsBean> beanList;
    private int pageNum = 1;
    private LifecycleProvider<FragmentEvent> mProvider;
    private String mCategory;

    public CategoryPresenterImpl(LifecycleProvider<FragmentEvent> provider, String category) {
        mProvider = provider;
        mCategory = category;
    }

    @Override
    public void getNewDatas() {
        ServiceGenerator.getApiService()
                .getGankResult(mCategory, 10, pageNum)
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
                        getView().setCategoryList(beanList);
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
        ServiceGenerator.getApiService()
                .getGankResult(mCategory, 10, pageNum)
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
                            getView().setCategoryList(gankResult.getResults());
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
