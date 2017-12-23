package com.sk.lgdx.module.my.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.my.network.ApiRequest;
import com.sk.lgdx.module.my.network.response.CollectObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/5.
 */

public class MyCollectionActivity extends BaseActivity {
    @BindView(R.id.rv_my_collection)
    RecyclerView rv_my_collection;
    LoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的收藏");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_my_collection;
    }

    @Override
    protected void initView() {
        adapter = new LoadMoreAdapter<CollectObj.CourseWareBean>(mContext, R.layout.item_my_collection, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int i, CollectObj.CourseWareBean bean) {
                ImageView iv_item_my_collection_icon = holder.getImageView(R.id.iv_item_my_collection_icon);
                TextView tv_item_my_collection_name = holder.getTextView(R.id.tv_item_my_collection_name);
                TextView tv_item_my_collection_download = holder.getTextView(R.id.tv_item_my_collection_download);
                TextView tv_item_my_collection_zan = holder.getTextView(R.id.tv_item_my_collection_zan);
                TextView tv_item_my_collection_time = holder.getTextView(R.id.tv_item_my_collection_time);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(iv_item_my_collection_icon);
                tv_item_my_collection_name.setText(bean.getTitle());
                tv_item_my_collection_zan.setText(bean.getThumbup_count());
                tv_item_my_collection_download.setText(bean.getSales());
                tv_item_my_collection_time.setText(bean.getAdd_time());

            }


        };

        adapter.setOnLoadMoreListener(this);
        rv_my_collection.setLayoutManager(new LinearLayoutManager(mContext));
        rv_my_collection.setNestedScrollingEnabled(false);
        rv_my_collection.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);

    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("pagesize",pageSize+"");
        map.put("page",page+"");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getMyCollection(map, new MyCallBack<CollectObj>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(CollectObj obj) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(obj.getCourseWare(),true);
                }else{
                    pageNum=2;
                    adapter.setList(obj.getCourseWare(),true);
                }


            }
        });


    }

    @Override
    protected void onViewClick(View v) {

    }
}
