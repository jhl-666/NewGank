package com.ljhdemo.newgank.ui.iView;

import com.ljhdemo.newgank.bean.GankResult;

import java.util.List;

/**
 * Created by ljh on 2018/1/4.
 */

public interface ICategoryView {
    void setCategoryList(List<GankResult.ResultsBean> categoryList);

    void showToast(String msg);

    void overRefresh(boolean flag);

    void setLoadMoreEnabled(boolean flag);
}
