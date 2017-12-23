package com.sk.lgdx.module.home.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/8.
 */

public class KaoqinActivity extends BaseActivity {
    @BindView(R.id.tv_kaoqin_saomiao)
    ImageView tv_kaoqin_saomiao;
    @BindView(R.id.tv_kaoqin_time)
    TextView tv_kaoqin_time;
    @BindView(R.id.tv_kechengbiao_date1)
    TextView tv_kechengbiao_date1;
    @BindView(R.id.tv_kechengbiao_date2)
    TextView tv_kechengbiao_date2;
    @BindView(R.id.tv_kechengbiao_date3)
    TextView tv_kechengbiao_date3;
    @BindView(R.id.tv_kechengbiao_date4)
    TextView tv_kechengbiao_date4;
    @BindView(R.id.tv_kechengbiao_date5)
    TextView tv_kechengbiao_date5;
    @BindView(R.id.tv_kechengbiao_date6)
    TextView tv_kechengbiao_date6;
    @BindView(R.id.textView2)
    TextView textView2;

    @Override
    protected int getContentView() {
        setAppTitle("考勤");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_kaoqin;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }

    @OnClick({R.id.tv_kaoqin_saomiao, R.id.tv_kaoqin_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_kaoqin_saomiao:
//                STActivity(NewScanActivity.class);
                break;
            case R.id.tv_kaoqin_time:
                break;
        }
    }
}
