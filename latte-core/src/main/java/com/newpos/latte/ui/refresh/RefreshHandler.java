package com.newpos.latte.ui.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.newpos.latte.app.Latte;
import com.newpos.latte.net.RestClient;
import com.newpos.latte.net.callback.ISuccess;
import com.newpos.latte.ui.recycler.DataConverter;
import com.newpos.latte.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by Administrator on 2018/4/5 0005.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout mRefreshLayout;

    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private  MultipleRecyclerAdapter  mAdapter;
    private final DataConverter CONVERTER;


    public RefreshHandler(SwipeRefreshLayout mRefreshLayout, RecyclerView recyclerView, DataConverter converter, PagingBean bean) {
        this.mRefreshLayout = mRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        mRefreshLayout.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout mRefreshLayout, RecyclerView recyclerView, DataConverter converter, PagingBean bean){
        return new RefreshHandler(mRefreshLayout, recyclerView, converter, bean);
    }

    private void refresh(){
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url){
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setPageTotal(object.getInteger("total"))
                        .setPageSize(object.getInteger("page_size"));

                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response).convert());
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                }).build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
