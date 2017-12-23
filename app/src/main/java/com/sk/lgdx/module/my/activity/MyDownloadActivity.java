package com.sk.lgdx.module.my.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.IOCallBack;
import com.github.customview.MyTextView;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.tools.AndroidUtils;
import com.sk.lgdx.tools.IntentUtils;
import com.sk.lgdx.tools.download.entity.AppInfo;
import com.sk.lgdx.tools.download.util.DownloadUtils;
import com.sk.lgdx.tools.download.util.FileUtils;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/12/5.
 */

public class MyDownloadActivity extends BaseActivity {
    @BindView(R.id.rv_my_download)
    RecyclerView rv_my_download;
    @BindView(R.id.tv_my_download_quanxuan)
    TextView tv_my_download_quanxuan;
    @BindView(R.id.tv_my_download_detele)
    MyTextView tv_my_download_detele;
    @BindView(R.id.ll_my_download_delete)
    LinearLayout ll_my_download_delete;
    @BindView(R.id.ll_my_download_bianji)
    LinearLayout ll_my_download_bianji;
    private boolean isEdit;
    BaseRecyclerAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的下载");
        setAppRightTitle("管理");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_my_download;
    }

    @Override
    protected void initView() {
        adapter=new BaseRecyclerAdapter<AppInfo>(mContext, R.layout.item_my_download) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, AppInfo bean) {
                CheckBox ch_item_my_download = (CheckBox) holder.getView(R.id.ch_item_my_download);
                ImageView iv_item_my_download_icon = holder.getImageView(R.id.iv_item_my_download_icon);
                TextView tv_item_my_download_name = holder.getTextView(R.id.tv_item_my_download_name);
                TextView tv_item_my_download_type = holder.getTextView(R.id.tv_item_my_download_type);
                TextView tv_item_my_download_size = holder.getTextView(R.id.tv_item_my_download_size);
                Log.d("=======","=====bean.getImage()="+bean.getImage());
                Glide.with(mContext).load(bean.getImage()).error(R.color.c_press).into(iv_item_my_download_icon);
                tv_item_my_download_name.setText(bean.getTitle());
                if (bean.getHouZhui().equals("mp4")) {
                    tv_item_my_download_type.setText("视频");
                }else {
                    tv_item_my_download_type.setText("PDF");
                }

                tv_item_my_download_size.setText(formetFileSize(Double.parseDouble(bean.getFileSize())));
//
                if (isEdit) {
                    ch_item_my_download.setVisibility(View.VISIBLE);
                }else {
                    ch_item_my_download.setVisibility(View.GONE);
                }
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        String path=FileUtils.getDownloadDir()+"/"+bean.getFileName();
                        IntentUtils.openFileIntent(mContext,path);
                    }
                });



            }
        };
        rv_my_download.setLayoutManager(new LinearLayoutManager(mContext));
        rv_my_download.setNestedScrollingEnabled(false);
        rv_my_download.setAdapter(adapter);



    }

    @Override
    protected void initData() {
        showProgress();
//        adapter.setList(downloadCompleteFile,true);
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        RXStart(new IOCallBack<List<AppInfo>>() {
            @Override
            public void call(Subscriber<? super List<AppInfo>> subscriber) {
                subscriber.onNext(DownloadUtils.getDownloadCompleteFile(mContext));
                subscriber.onCompleted();
            }
            @Override
            public void onMyNext(List<AppInfo> appInfos) {
                adapter.setList(appInfos,true);
                pl_load.showContent();
            }

            @Override
            public void onMyError(Throwable e) {
                super.onMyError(e);
                pl_load.showErrorText();
            }
        });

    }

    @OnClick({R.id.app_right_tv, R.id.tv_my_download_quanxuan})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.app_right_tv:
                isEdit = !isEdit;
                if (isEdit) {
                    setAppRightTitle("完成");
                    ll_my_download_bianji.setVisibility(View.VISIBLE);
                } else {
                    setAppRightTitle("编辑");
                    ll_my_download_bianji.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
            case R.id.tv_my_download_quanxuan:

                break;
        }


    }
    public  String formetFileSize(Double fileS) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format(AndroidUtils.chuFa(fileS, 1024)) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format(AndroidUtils.chuFa(fileS, 1048576)) + "M";
        } else {
            fileSizeString = df.format(AndroidUtils.chuFa(fileS, 1073741824)) + "G";
        }
        return fileSizeString;
    }
/*update table set name=? age =? where id=?*/

    @OnClick(R.id.tv_my_download_quanxuan)
    public void onClick() {
    }
}
