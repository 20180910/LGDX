package com.sk.lgdx.module.home.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.MySubscriber;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.home.event.SaomaEvent;
import com.sk.lgdx.module.home.network.ApiRequest;
import com.sk.lgdx.module.home.network.response.KaoqinObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @BindView(R.id.rv_kaoqin)
    RecyclerView rv_kaoqin;
    @BindView(R.id.rv_xingqiyi)
    RecyclerView rv_xingqiyi;
    @BindView(R.id.rv_xingqier)
    RecyclerView rv_xingqier;
    @BindView(R.id.rv_xingqisan)
    RecyclerView rv_xingqisan;
    @BindView(R.id.rv_xingqisi)
    RecyclerView rv_xingqisi;
    @BindView(R.id.rv_xingqiwu)
    RecyclerView rv_xingqiwu;
    @BindView(R.id.rv_xingqiliu)
    RecyclerView rv_xingqiliu;
    @BindView(R.id.rv_xingqitian)
    RecyclerView rv_xingqitian;
    BaseRecyclerAdapter dateAdapter, oneAdapter, twoAdapter, threeAdapter, fourAdapter, fiveAdapter, sixAdapter, sevenAdapter;

    @Override
    protected int getContentView() {
        setAppTitle("考勤");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_kaoqin;
    }

    @Override
    protected void initView() {
        String[] xingqi = {"周一", "周二", "周三", "周四", "周五", "周六", "周天"};
        dateAdapter=new BaseRecyclerAdapter<String>(mContext,R.layout.item_kaoqin_date) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, String bean) {
                TextView tv_item_kechengbiao_xingqi = holder.getTextView(R.id.tv_item_kechengbiao_xingqi);
                TextView tv_item_kechengbiao_date = holder.getTextView(R.id.tv_item_kechengbiao_date);
                tv_item_kechengbiao_xingqi.setText(xingqi[i]);
                tv_item_kechengbiao_date.setText(bean);
            }
        };
        rv_kaoqin.setLayoutManager(new GridLayoutManager(mContext, 7));
        rv_kaoqin.setNestedScrollingEnabled(false);
        rv_kaoqin.setAdapter(dateAdapter);

        oneAdapter = new BaseRecyclerAdapter<KaoqinObj.TimeHourBean>(mContext, R.layout.item_kaoqin_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KaoqinObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                if (bean.getCourse_id().equals("0")) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    if (bean.getIs_qiandao().equals("0")) {
                        tv_item_kecheng.setText("未到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                    }else if (bean.getIs_qiandao().equals("1")){
                        tv_item_kecheng.setText("已签到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }else {
                        tv_item_kecheng.setText(bean.getContent());
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }
                }
            }
        };
        rv_xingqiyi.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqiyi.setNestedScrollingEnabled(false);
        rv_xingqiyi.setAdapter(oneAdapter);

        twoAdapter = new BaseRecyclerAdapter<KaoqinObj.TimeHourBean>(mContext, R.layout.item_kaoqin_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KaoqinObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                if (bean.getCourse_id().equals("0")) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    if (bean.getIs_qiandao().equals("0")) {
                        tv_item_kecheng.setText("未到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                    }else if (bean.getIs_qiandao().equals("1")){
                        tv_item_kecheng.setText("已签到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }else {
                        tv_item_kecheng.setText(bean.getContent());
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }
                }
            }
        };
        rv_xingqier.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqier.setNestedScrollingEnabled(false);
        rv_xingqier.setAdapter(twoAdapter);

        threeAdapter = new BaseRecyclerAdapter<KaoqinObj.TimeHourBean>(mContext, R.layout.item_kaoqin_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KaoqinObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                if (bean.getCourse_id().equals("0")) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    if (bean.getIs_qiandao().equals("0")) {
                        tv_item_kecheng.setText("未到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                    }else if (bean.getIs_qiandao().equals("1")){
                        tv_item_kecheng.setText("已签到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }else {
                        tv_item_kecheng.setText(bean.getContent());
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }
                }
            }
        };
        rv_xingqisan.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqisan.setNestedScrollingEnabled(false);
        rv_xingqisan.setAdapter(threeAdapter);

        fourAdapter = new BaseRecyclerAdapter<KaoqinObj.TimeHourBean>(mContext, R.layout.item_kaoqin_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KaoqinObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                if (bean.getCourse_id().equals("0")) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    if (bean.getIs_qiandao().equals("0")) {
                        tv_item_kecheng.setText("未到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                    }else if (bean.getIs_qiandao().equals("1")){
                        tv_item_kecheng.setText("已签到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }else {
                        tv_item_kecheng.setText(bean.getContent());
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }
                }
            }
        };
        rv_xingqisi.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqisi.setNestedScrollingEnabled(false);
        rv_xingqisi.setAdapter(fourAdapter);

        fiveAdapter = new BaseRecyclerAdapter<KaoqinObj.TimeHourBean>(mContext, R.layout.item_kaoqin_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KaoqinObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                if (bean.getCourse_id().equals("0")) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    if (bean.getIs_qiandao().equals("0")) {
                        tv_item_kecheng.setText("未到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                    }else if (bean.getIs_qiandao().equals("1")){
                        tv_item_kecheng.setText("已签到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }else {
                        tv_item_kecheng.setText(bean.getContent());
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }
                }
            }
        };
        rv_xingqiwu.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqiwu.setNestedScrollingEnabled(false);
        rv_xingqiwu.setAdapter(fiveAdapter);

        sixAdapter = new BaseRecyclerAdapter<KaoqinObj.TimeHourBean>(mContext, R.layout.item_kaoqin_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KaoqinObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                if (bean.getCourse_id().equals("0")) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    if (bean.getIs_qiandao().equals("0")) {
                        tv_item_kecheng.setText("未到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                    }else if (bean.getIs_qiandao().equals("1")){
                        tv_item_kecheng.setText("已签到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }else {
                        tv_item_kecheng.setText(bean.getContent());
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }
                }
            }
        };
        rv_xingqiliu.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqiliu.setNestedScrollingEnabled(false);
        rv_xingqiliu.setAdapter(sixAdapter);

        sevenAdapter = new BaseRecyclerAdapter<KaoqinObj.TimeHourBean>(mContext, R.layout.item_kaoqin_xingqi) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KaoqinObj.TimeHourBean bean) {
                TextView tv_item_kecheng = holder.getTextView(R.id.tv_item_kecheng);
                View v_item_xingqi_bottom = holder.getView(R.id.v_item_xingqi_bottom);
                if (i == 1) {
                    v_item_xingqi_bottom.setVisibility(View.VISIBLE);
                } else {
                    v_item_xingqi_bottom.setVisibility(View.GONE);
                }
                if (bean.getCourse_id().equals("0")) {
                    tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                }else {
                    if (bean.getIs_qiandao().equals("0")) {
                        tv_item_kecheng.setText("未到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
                    }else if (bean.getIs_qiandao().equals("1")){
                        tv_item_kecheng.setText("已签到");
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }else {
                        tv_item_kecheng.setText(bean.getContent());
                        tv_item_kecheng.setBackgroundColor(mContext.getResources().getColor(R.color.home_text_color));
                    }
                }
            }
        };
        rv_xingqitian.setLayoutManager(new LinearLayoutManager(mContext));
        rv_xingqitian.setNestedScrollingEnabled(false);
        rv_xingqitian.setAdapter(sevenAdapter);





    }

    @Override
    protected void initData() {
        showProgress();
        getKaoqin();


    }

    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(SaomaEvent.class, new MySubscriber<SaomaEvent>() {
            @Override
            public void onMyNext(SaomaEvent event) {
                showLoading();
                getKaoqin();


            }
        });
    }

    private void getKaoqin() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getKaoqin(map, new MyCallBack<List<KaoqinObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<KaoqinObj> obj) {
                List<String>date=new ArrayList<String>();
                for (int i = 0; i < obj.size(); i++) {
                    date.add(obj.get(i).getTime());
                }
                dateAdapter.setList(date,true);
                oneAdapter.setList(obj.get(0).getTime_hour(),true);
                twoAdapter.setList(obj.get(1).getTime_hour(),true);
                threeAdapter.setList(obj.get(2).getTime_hour(), true);
                fourAdapter.setList(obj.get(3).getTime_hour(), true);
                fiveAdapter.setList(obj.get(4).getTime_hour(), true);
                sixAdapter.setList(obj.get(5).getTime_hour(), true);
                sevenAdapter.setList(obj.get(6).getTime_hour(), true);
            }
        });

    }


    @Override
    protected void onViewClick(View v) {

    }

    @OnClick({R.id.tv_kaoqin_saomiao, R.id.tv_kaoqin_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_kaoqin_saomiao:
                STActivity(NewScanActivity.class);
                break;
            case R.id.tv_kaoqin_time:
                break;
        }
    }
}
