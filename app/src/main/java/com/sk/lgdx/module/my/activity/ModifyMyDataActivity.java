package com.sk.lgdx.module.my.activity;

import android.view.View;

import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/5.
 */

public class ModifyMyDataActivity extends BaseActivity {
    @BindView(R.id.et_modify_my_data)
    MyEditText et_modify_my_data;
    @BindView(R.id.tv_modify_my_data_save)
    MyTextView tv_modify_my_data_save;

    @Override
    protected int getContentView() {
        setAppTitle("修改手机号");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_modify_my_data;
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

    @OnClick(R.id.tv_modify_my_data_save)
    public void onClick() {
    }
}
