package com.sk.lgdx.module.home.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.MySubscriber;
import com.github.baseclass.rx.RxBus;
import com.github.customview.MyTextView;
import com.sk.lgdx.Config;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseFragment;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.home.Constant;
import com.sk.lgdx.module.home.activity.KaoqinActivity;
import com.sk.lgdx.module.home.activity.KechengbiaoActivity;
import com.sk.lgdx.module.home.activity.MyHomeWorkActivity;
import com.sk.lgdx.module.home.activity.NewsActvity;
import com.sk.lgdx.module.home.activity.ZiXunDetailActivity;
import com.sk.lgdx.module.home.event.MyHomeWorkEvent;
import com.sk.lgdx.module.home.event.NewsEvent;
import com.sk.lgdx.module.home.event.StudyEvent;
import com.sk.lgdx.module.home.network.ApiRequest;
import com.sk.lgdx.module.home.network.response.HomeRoastingChartObj;
import com.sk.lgdx.module.home.network.response.InformationListObj;
import com.sk.lgdx.module.home.network.response.TypeAssemBlageObj;
import com.sk.lgdx.module.home.network.response.UnreadNewsObj;
import com.sk.lgdx.tools.GlideLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.github.baseclass.rx.RxBusHelper.getRxBusEvent;

/**
 * Created by Administrator on 2017/12/4.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.tv_home_xiaoxi_num)
    MyTextView tv_home_xiaoxi_num;
    @BindView(R.id.bn_home)
    Banner bn_home;
    @BindView(R.id.ll_home_xuexi)
    LinearLayout ll_home_xuexi;
    @BindView(R.id.ll_home_kechengbiao)
    LinearLayout ll_home_kechengbiao;
    @BindView(R.id.ll_home_kaoqin)
    LinearLayout ll_home_kaoqin;
    @BindView(R.id.tv_home_zuye_num)
    MyTextView tv_home_zuye_num;
    @BindView(R.id.rl_home_zuoye)
    RelativeLayout rl_home_zuoye;
    @BindView(R.id.rv_home_xueyuan)
    RecyclerView rv_home_xueyuan;
    @BindView(R.id.iv_home_news)
    ImageView iv_home_news;
    LoadMoreAdapter adapter;
    @BindView(R.id.ll_home_xiayijie)
    LinearLayout ll_home_xiayijie;
    @BindView(R.id.rv_home_type)
    RecyclerView rv_home_type;
    BaseRecyclerAdapter typeAdapter;
    private List<String> bannerList;
    String news_num="0",homework_num="0";
    ;

    @Override
    protected int getContentView() {
        return R.layout.frag_home;
    }

    @Override
    protected void initView() {
        rv_home_xueyuan.setFocusable(false);


       typeAdapter=new BaseRecyclerAdapter<TypeAssemBlageObj>(mContext,R.layout.item_home_type) {
           @Override
           public void bindData(RecyclerViewHolder holder, int i, TypeAssemBlageObj obj) {
               ImageView iv_item_home_type_icon = holder.getImageView(R.id.iv_item_home_type_icon);
               TextView tv_item_home_type_name = holder.getTextView(R.id.tv_item_home_type_name);
               TextView tv_item_home_type_num = holder.getTextView(R.id.tv_item_home_type_num);
               Glide.with(mContext).load(obj.getImg_url()).error(R.color.c_press).into(iv_item_home_type_icon);
               tv_item_home_type_name.setText(obj.getTitle());
               if (i==3) {
                   if (homework_num.equals("0")) {
                       tv_item_home_type_num.setVisibility(View.GONE);
                   }else {
                       tv_item_home_type_num.setVisibility(View.VISIBLE);
                       tv_item_home_type_num.setText(homework_num);
                   }

               }else {
                   tv_item_home_type_num.setVisibility(View.GONE);
               }
               holder.itemView.setOnClickListener(new MyOnClickListener() {
                   @Override
                   protected void onNoDoubleClick(View view) {
                       switch (i){
                           case 0:
                               RxBus.getInstance().post(new StudyEvent(Config.study));
                           break;
                           case 1:
                               STActivity(KechengbiaoActivity.class);
                           break;
                           case 2:
                               STActivity(KaoqinActivity.class);
                           break;

                           case 3:
                               Log.d("======","=======");
//                               STActivity(KaoqinActivity.class);
                               STActivity(MyHomeWorkActivity.class);
                           break;
                       }

                   }
               });



           }
       };
       rv_home_type.setLayoutManager(new GridLayoutManager(mContext,4));
        rv_home_type.setNestedScrollingEnabled(false);
        rv_home_type.setAdapter(typeAdapter);


        adapter = new LoadMoreAdapter<InformationListObj>(mContext, R.layout.item_xueyuan_zixun, pageSize,nsv) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, InformationListObj bean) {
                ImageView iv_zixun = holder.getImageView(R.id.iv_zixun);
                TextView tv_zixun_content = holder.getTextView(R.id.tv_zixun_content);
                TextView tv_zixun_time = holder.getTextView(R.id.tv_zixun_time);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(iv_zixun);
                tv_zixun_content.setText(bean.getTitle());
                tv_zixun_time.setText(bean.getAdd_time());


                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {

                        Intent intent=new Intent();
                        intent.putExtra(Constant.IParam.information_id,bean.getInformation_id());
                        STActivity(intent,ZiXunDetailActivity.class);
                    }
                });

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_home_xueyuan.setLayoutManager(new LinearLayoutManager(mContext));
        rv_home_xueyuan.setNestedScrollingEnabled(false);
        rv_home_xueyuan.addItemDecoration(getItemDivider());
        rv_home_xueyuan.setAdapter(adapter);
        nsv.scrollTo(0, 0);

    }

    @Override
    protected void initData() {
        showProgress();
        getRoastingChart();
        getTypeAssemBlage();
        getData(1,false);
        getUnreadNews();
    }
    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(NewsEvent.class, new MySubscriber<NewsEvent>() {
            @Override
            public void onMyNext(NewsEvent event) {
                if (event.is_check.equals("1")) {
                    showLoading();
                    getUnreadNews();
                }

            }
        });
        getRxBusEvent(MyHomeWorkEvent.class, new MySubscriber<MyHomeWorkEvent>() {
            @Override
            public void onMyNext(MyHomeWorkEvent event) {
                showLoading();
                getUnreadNews();
            }
        });
    }



    private void getUnreadNews() {
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getUnreadNews(map, new MyCallBack<UnreadNewsObj>(mContext) {
            @Override
            public void onSuccess(UnreadNewsObj obj) {
                news_num=obj.getNews_num();
                homework_num=obj.getHomework_num();
                if (news_num.equals("0")) {
                    tv_home_xiaoxi_num.setVisibility(View.GONE);
                }else {
                    tv_home_xiaoxi_num.setVisibility(View.VISIBLE);
                    tv_home_xiaoxi_num.setText(news_num);

                }
                if (typeAdapter!=null) {
                    typeAdapter.notifyDataSetChanged();
                }




            }
        });

    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        //getInformationList
        Map<String,String>map=new HashMap<String,String>();
        map.put("pagesize",pageSize+"");
        map.put("page",page+"");
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getInformationList(map, new MyCallBack<List<InformationListObj>>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(List<InformationListObj> obj) {
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

    private void getTypeAssemBlage() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getTypeAssemBlage(map, new MyCallBack<List<TypeAssemBlageObj>>(mContext) {
            @Override
            public void onSuccess(List<TypeAssemBlageObj> obj) {
                typeAdapter.setList(obj,true);


            }
        });

    }

    private void getRoastingChart() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getRoastingChart(map, new MyCallBack<HomeRoastingChartObj>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(HomeRoastingChartObj obj) {
                bannerList = new ArrayList<String>();
                ;
                for (int i = 0; i < obj.getRoasting_list().size(); i++) {
                    bannerList.add(obj.getRoasting_list().get(i).getImg_url());

                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.height = (int) (PhoneUtils.getScreenWidth(mContext) / 2.25);
                layoutParams.width = PhoneUtils.getScreenWidth(mContext);

                bn_home.setLayoutParams(layoutParams);


                bn_home.setImages(bannerList);
                bn_home.setImageLoader(new GlideLoader());
                bn_home.start();
                bn_home.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                });
            }
        });

    }


    @Override
    public void onStop() {
        super.onStop();
        if (bn_home != null && bannerList != null) {
            bn_home.stopAutoPlay();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bn_home != null && bannerList != null) {
            bn_home.startAutoPlay();
        }
    }


    @OnClick({R.id.ll_home_xuexi, R.id.ll_home_kechengbiao, R.id.ll_home_kaoqin, R.id.rl_home_zuoye, R.id.iv_home_news, R.id.ll_home_xiayijie})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home_xuexi:
                RxBus.getInstance().post(new StudyEvent(Config.study));
                break;
            case R.id.ll_home_kechengbiao:
                STActivity(KechengbiaoActivity.class);
                break;
            case R.id.ll_home_kaoqin:
                STActivity(KaoqinActivity.class);
                break;
            case R.id.rl_home_zuoye:
                STActivity(MyHomeWorkActivity.class);
                break;
            case R.id.iv_home_news:
                STActivity(NewsActvity.class);
                break;
            case R.id.ll_home_xiayijie:
                STActivity(KechengbiaoActivity.class);
                break;
        }
    }
}
