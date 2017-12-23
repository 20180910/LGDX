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
import com.sk.lgdx.module.my.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/5.
 */

public class YijianfankuiActivity extends BaseActivity {
    @BindView(R.id.et_yijianfankui)
    EditText et_yijianfankui;
    @BindView(R.id.tv_yijianfankui_tijiao)
    MyTextView tv_yijianfankui_tijiao;
    String content;

    @Override
    protected int getContentView() {
        setAppTitle("意见反馈");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_yijianfankui;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_yijianfankui_tijiao})
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_yijianfankui_tijiao:
                content=getSStr(et_yijianfankui);
                if (TextUtils.isEmpty(content)) {
                    showMsg("请输入反馈内容！");
                    return;
                }
                getSubmitFeedback();

                
            break;
        }
        
    }

    private void getSubmitFeedback() {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("content",content);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getSubmitFeedback(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                finish();



            }
        });

    }
}
