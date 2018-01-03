package com.sk.lgdx.module.home.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.SPUtils;
import com.github.customview.MyImageView;
import com.sk.lgdx.Config;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseFragment;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.my.activity.AboutUsActivity;
import com.sk.lgdx.module.my.activity.MyCollectionActivity;
import com.sk.lgdx.module.my.activity.MyDataActivity;
import com.sk.lgdx.module.my.activity.MyDownloadActivity;
import com.sk.lgdx.module.my.activity.SettingActivity;
import com.sk.lgdx.module.my.activity.YijianfankuiActivity;
import com.sk.lgdx.module.my.network.ApiRequest;
import com.sk.lgdx.module.my.network.response.UserInfoObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/4.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.iv_my_icon)
    MyImageView iv_my_icon;
    @BindView(R.id.tv_my_collection)
    TextView tv_my_collection;
    @BindView(R.id.tv_my_download)
    TextView tv_my_download;
    @BindView(R.id.tv_my_yijianfankui)
    TextView tv_my_yijianfankui;
    @BindView(R.id.tv_my_about_us)
    TextView tv_my_about_us;
    @BindView(R.id.tv_my_setting)
    TextView tv_my_setting;
    @BindView(R.id.tv_my_name)
    TextView tv_my_name;
    @BindView(R.id.tv_my_xuehao)
    TextView tv_my_xuehao;
    @BindView(R.id.ll_my_geren)
    LinearLayout ll_my_geren;

    @Override
    protected int getContentView() {

        return R.layout.frag_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(mContext).load(SPUtils.getPrefString(mContext, Config.avatar,"")).error(R.drawable.my_people).into(iv_my_icon);

    }

    @Override
    protected void initData() {
        showProgress();
        getUserInfo();

    }

    private void getUserInfo() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getUserInfo(map, new MyCallBack<UserInfoObj>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(UserInfoObj obj) {
                    Glide.with(mContext).load(obj.getAvatar()).error(R.drawable.my_people).into(iv_my_icon);
                SPUtils.setPrefString(mContext,Config.avatar,obj.getAvatar());
                tv_my_name.setText(obj.getName());
                tv_my_xuehao.setText("学号：" + obj.getUser_name());


            }
        });

    }


    @OnClick({R.id.iv_my_icon,
            R.id.tv_my_collection,
            R.id.tv_my_download,
            R.id.tv_my_yijianfankui,
            R.id.tv_my_about_us,
            R.id.tv_my_setting,
            R.id.ll_my_geren})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_my_icon:

                break;
            case R.id.tv_my_collection:
                STActivity(MyCollectionActivity.class);
                break;
            case R.id.tv_my_download:
                STActivity(MyDownloadActivity.class);
                break;
            case R.id.tv_my_yijianfankui:
                STActivity(YijianfankuiActivity.class);
                break;
            case R.id.tv_my_about_us:
                STActivity(AboutUsActivity.class);
                break;
            case R.id.tv_my_setting:
                STActivity(SettingActivity.class);
                break;
            case R.id.ll_my_geren:
                STActivity(MyDataActivity.class);
                break;
        }
    }

}
