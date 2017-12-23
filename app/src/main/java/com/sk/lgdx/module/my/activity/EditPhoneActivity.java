package com.sk.lgdx.module.my.activity;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;

import com.github.androidtools.SPUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.sk.lgdx.Config;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.base.BaseObj;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.my.Constant;
import com.sk.lgdx.module.my.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/7.
 */

public class EditPhoneActivity extends BaseActivity {
    @BindView(R.id.et_edit)
    MyEditText et_edit;
    @BindView(R.id.tv_edit_baocun)
    MyTextView tv_edit_baocun;
    String type,value;

    @Override
    protected int getContentView() {
        setAppTitle("修改手机号");
        setBackIcon(R.drawable.back_white);


        return R.layout.act_edit_phone;
    }

    @Override
    protected void initView() {

//        pwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        getValue();

    }

    private void getValue() {

        type=getIntent().getStringExtra(Constant.IParam.type);
        value=getIntent().getStringExtra(Constant.IParam.value);

        if (type.equals(Constant.IParam.phone)) {
            et_edit.setInputType(InputType.TYPE_CLASS_NUMBER);

            setAppTitle("修改手机号");
            if (TextUtils.isEmpty(value)) {
                et_edit.setHint("请输入手机号");
            }else {
                et_edit.setText(value);
            }

        }else {
            //textEmailAddress
            et_edit.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            setAppTitle("修改邮箱");
            if (TextUtils.isEmpty(value)) {
                et_edit.setHint("请输入邮箱");
            }else {
                et_edit.setText(value);
            }


        }


    }
    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }

    @OnClick(R.id.tv_edit_baocun)
    public void onClick() {
        value=getSStr(et_edit);
        if (TextUtils.isEmpty(value)) {
            showMsg("内容不能为空");
            return;
        }
        if (type.equals(Constant.IParam.phone)) {
            getEditPhone();
        }else {
            getEditEmail();



        }

    }

    private void getEditEmail() {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("userid",getUserId());
        map.put("email",value);
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getEditEmail(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                SPUtils.setPrefString(mContext, Config.email,value);
                Intent intent=new Intent();
                intent.putExtra(Constant.IParam.value,value);
                setResult(RESULT_OK,intent);
                showMsg(obj.getMsg());
                finish();

            }
        });

    }

    private void getEditPhone() {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("userid",getUserId());
        map.put("phone",value);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getEditPhone(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                SPUtils.setPrefString(mContext, Config.mobile,value);

                Intent intent=new Intent();
                    intent.putExtra(Constant.IParam.value,value);
                    setResult(RESULT_OK,intent);
                showMsg(obj.getMsg());
                finish();


            }
        });

    }



}
