package com.sk.lgdx.module.home.activity;

import android.view.View;
import android.widget.TextView;

import com.github.baseclass.rx.RxBus;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.home.Constant;
import com.sk.lgdx.module.home.event.NewsEvent;
import com.sk.lgdx.module.home.network.ApiRequest;
import com.sk.lgdx.module.home.network.response.NewsDetailObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/8.
 */

public class NewsDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_news_details_title)
    TextView tv_news_details_title;
    @BindView(R.id.tv_news_details_time)
    TextView tv_news_details_time;
    @BindView(R.id.tv_news_details_content)
    TextView tv_news_details_content;
    String news_id,is_check;

    @Override
    protected int getContentView() {
        setAppTitle("详细消息");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_news_details;
    }

    @Override
    protected void initView() {
        getValue();

    }

    private void getValue() {
        news_id=getIntent().getStringExtra(Constant.IParam.news_id);
        is_check=getIntent().getStringExtra(Constant.IParam.is_check);

    }

    @Override
    protected void initData() {
        showProgress();
        getNewsDetail();


    }

    private void getNewsDetail() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("news_id",news_id);
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getNewsDetail(map, new MyCallBack<NewsDetailObj>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(NewsDetailObj obj) {
                tv_news_details_title.setText(obj.getTitle());
                tv_news_details_content.setText(obj.getContent());
                tv_news_details_time.setText(obj.getAdd_time());
                if (is_check.equals("1")) {
                    RxBus.getInstance().post(new NewsEvent(is_check));

                }


            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}
