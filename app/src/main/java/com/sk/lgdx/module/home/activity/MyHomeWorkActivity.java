package com.sk.lgdx.module.home.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.rx.IOCallBack;
import com.github.baseclass.rx.MySubscriber;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.home.adapter.MyHomeWorkAdapter;
import com.sk.lgdx.module.home.event.DownLoadSuccessEvent;
import com.sk.lgdx.module.home.event.MyHomeWorkEvent;
import com.sk.lgdx.module.home.event.TijiaozuoyeEvent;
import com.sk.lgdx.module.home.event.WenjianEvent;
import com.sk.lgdx.module.home.network.ApiRequest;
import com.sk.lgdx.module.home.network.response.StudentOperationListObj;
import com.sk.lgdx.module.study.Constant;
import com.sk.lgdx.tools.download.entity.AppInfo;
import com.sk.lgdx.tools.download.util.DownloadUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/12/5.
 */

public class MyHomeWorkActivity extends BaseActivity {
    @BindView(R.id.rv_my_home_work)
    RecyclerView rv_my_home_work;

    LoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        setAppTitle("我的作业");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_my_home_work;
    }

    @Override
    protected void initView() {
//
//
//
        adapter=new LoadMoreAdapter<StudentOperationListObj>(mContext,R.layout.item_my_home_work_title,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, StudentOperationListObj bean) {
                TextView tv_my_home_work_title = holder.getTextView(R.id.tv_my_home_work_title);
                RecyclerView rv_item_my_home_work = (RecyclerView) holder.getView(R.id.rv_item_my_home_work);
                tv_my_home_work_title.setText(bean.getAdd_time());

                if (bean.getOperation_list().size()==0) {
                    rv_item_my_home_work.setVisibility(View.GONE);
                }else {
                    rv_item_my_home_work.setVisibility(View.VISIBLE);
                }
                MyHomeWorkAdapter mAdapter=new MyHomeWorkAdapter(mContext,R.layout.item_my_home_work);
                mAdapter.setList(bean.getOperation_list(),true);

                rv_item_my_home_work.setLayoutManager(new LinearLayoutManager(mContext));
                rv_item_my_home_work.setNestedScrollingEnabled(false);
                rv_item_my_home_work.setAdapter(mAdapter);
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_my_home_work.setLayoutManager(new LinearLayoutManager(mContext));
        rv_my_home_work.setNestedScrollingEnabled(false);
        rv_my_home_work.setAdapter(adapter);




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
        ApiRequest.getStudentOperationList(map, new MyCallBack<List<StudentOperationListObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<StudentOperationListObj> obj) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(obj,true);
                }else{
                    pageNum=2;
                    adapter.setList(obj,true);
                }
            }
        });
    }

    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(TijiaozuoyeEvent.class, new MySubscriber<TijiaozuoyeEvent>() {
            @Override
            public void onMyNext(TijiaozuoyeEvent event) {
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.operation_id,event.operation_id);
                STActivity(intent,QuerentijiaoActivity.class);

            }
        });
        getRxBusEvent(MyHomeWorkEvent.class, new MySubscriber<MyHomeWorkEvent>() {
            @Override
            public void onMyNext(MyHomeWorkEvent event) {
                showLoading();
                getData(1,false);


            }
        });
        getRxBusEvent(WenjianEvent.class, new MySubscriber<WenjianEvent>() {
            @Override
            public void onMyNext(WenjianEvent event) {
                startDownload(event.id,event.name,event.attachment);




            }
        });


        getRxBusEvent(DownLoadSuccessEvent.class, new MySubscriber<DownLoadSuccessEvent>() {
            @Override
            public void onMyNext(DownLoadSuccessEvent event) {
                if (event.type.equals("success")) {
                    showMsg("下载已完成。请到个人中心->我的下载中查看。");

                }else if (event.type.equals("yes")){
                    showMsg("已下载。请到个人中心->我的下载中查看。");

                }else if (event.type.equals("no")){
                    showMsg("下载中...");

                }



            }
        });

    }
    private void startDownload(String id,String name,String attachment ) {
        if (TextUtils.isEmpty(attachment)) {
            showMsg("暂无下载");
            return;
        }


        AppInfo info = new AppInfo(id, name, name, "",attachment);
        showLoading();
        RXStart(new IOCallBack<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(DownloadUtils.isExistFile(info));
                subscriber.onCompleted();
            }

            @Override
            public void onMyNext(Boolean isExist) {
                dismissLoading();
                DownloadUtils.startDownload(isExist, mContext, info);
            }
        });
    }

    @Override
    protected void onViewClick(View v) {

    }
}
