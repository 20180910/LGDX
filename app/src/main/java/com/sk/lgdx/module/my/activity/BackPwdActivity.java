package com.sk.lgdx.module.my.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.github.customview.MyTextView;
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
 * Created by Administrator on 2017/12/5.
 */

public class BackPwdActivity extends BaseActivity {
    @BindView(R.id.et_back_pwd_new1)
    EditText et_back_pwd_new1;
    @BindView(R.id.et_back_pwd_new2)
    EditText et_back_pwd_new2;
    @BindView(R.id.tv_back_pwd_finish)
    MyTextView tv_back_pwd_finish;
    String email,newPassword,newPassword2;


    @Override
    protected int getContentView() {
        setAppTitle("找回密码");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_back_pwd;
    }

    @Override
    protected void initView() {
        /**
         *  Intent intent=new Intent();
         intent.putExtra(Constant.IParam.mailbox,email);
         */
        getValue();


    }

    private void getValue() {
        email=getIntent().getStringExtra(Constant.IParam.mailbox);

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_back_pwd_finish})
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_back_pwd_finish:

                newPassword=getSStr(et_back_pwd_new1);
                newPassword2=getSStr(et_back_pwd_new2);
                if(TextUtils.isEmpty(newPassword)){
                    showMsg("密码不能为空");
                    return;
                }

                if(!newPassword.equals(newPassword2)){
                    showMsg("两次密码不一样");
                    return;
                }
                forgetPWD();



            break;
        }
    }

    private void forgetPWD() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("email",email);
        map.put("newPassword",newPassword);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.forgetPWD(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                finish();

            }
        });



    }
}
