package com.newpos.latt.eec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.newpos.latt.eec.R;
import com.newpos.latt.eec.R2;
import com.newpos.latte.ui.recycler.DataConverter;
import com.newpos.latte.ui.refresh.PagingBean;
import com.newpos.latte.ui.refresh.RefreshHandler;
import com.newpos.latte.app.Latte;
import com.newpos.latte.bottom.BottomItemDelegate;
import com.newpos.latte.net.RestClient;
import com.newpos.latte.net.callback.ISuccess;
import com.newpos.latte.ui.recycler.MultipleFileds;
import com.newpos.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/5 0005.
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan;
    @BindView(R2.id.et_index_search)
    AppCompatEditText mEditTextSearch;
    @BindView(R2.id.icon_index_message)
    IconTextView mIconMessage;

    private RefreshHandler mRefreshHandler ;

    private void initRefreshLayout(){
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("http://192.168.31.133/index_data.json");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    private void initRecyclerView(){
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
    }
    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
            mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter(), new PagingBean());
//        RestClient.builder()
//                .url("http://192.168.31.133/index_data.json")
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        final  IndexDataConverter converter = new IndexDataConverter();
//                        converter.setJsonData(response);
//                        final ArrayList<MultipleItemEntity> list = converter.convert();
//                        final String image = list.get(0).getField(MultipleFileds.TEXT);
//                        Toast.makeText(Latte.getApplicationContext(), image, Toast.LENGTH_SHORT).show();
//                    }
//                }).build()
//                .get();
    }
}
