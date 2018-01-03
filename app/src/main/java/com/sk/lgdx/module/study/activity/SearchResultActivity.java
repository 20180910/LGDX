package com.sk.lgdx.module.study.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.study.Constant;
import com.sk.lgdx.module.study.network.ApiRequest;
import com.sk.lgdx.module.study.network.response.SearchResultObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/3.
 */

public class SearchResultActivity extends BaseActivity {
    @BindView(R.id.rv_search_result)
    RecyclerView rv_search_result;
    LoadMoreAdapter adapter;
    String search_term;


    @Override
    protected int getContentView() {
        setAppTitle("搜索列表");
        setBackIcon(R.drawable.back_white);
        return R.layout.act_search_result;
    }

    @Override
    protected void initView() {
        getValue();
        adapter=new LoadMoreAdapter<SearchResultObj.CourseWareBean>(mContext,R.layout.item_study_history,pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int position, SearchResultObj.CourseWareBean bean) {
                ImageView iv_item_study_history_icon = holder.getImageView(R.id.iv_item_study_history_icon);
                TextView tv_item_study_history_title = holder.getTextView(R.id.tv_item_study_history_title);
                TextView tv_item_study_history_downlad_num = holder.getTextView(R.id.tv_item_study_history_downlad_num);
                TextView tv_item_study_history_zan_num = holder.getTextView(R.id.tv_item_study_history_zan_num);
                TextView tv_item_study_history_look_num = holder.getTextView(R.id.tv_item_study_history_look_num);
                TextView tv_item_study_history_time = holder.getTextView(R.id.tv_item_study_history_time);
                Glide.with(mContext).load(bean.getImage_url()).error(R.color.c_press).into(iv_item_study_history_icon);
                tv_item_study_history_title.setText(bean.getTitle());
                tv_item_study_history_downlad_num.setText(bean.getSales());
                tv_item_study_history_zan_num.setText(bean.getThumbup_count());
                tv_item_study_history_look_num.setText(bean.getCourseware_record_count());
                tv_item_study_history_time.setText(bean.getAdd_time());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        String video_pdf=bean.getVideo_pdf().substring(bean.getVideo_pdf().length()-3,bean.getVideo_pdf().length());
                        Log.d("=========","video_pdf===="+video_pdf);
                        Intent intent=new Intent();
                        if (video_pdf.equals("mp4")) {
                            intent.putExtra(Constant.IParam.type,"0");
                            intent.putExtra(Constant.IParam.courseware_id,bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf,bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url,bean.getImage_url());
                            STActivity(intent,KeChengDetailActivity.class);
                        }else {
                            intent.putExtra(Constant.IParam.type,"1");
                            intent.putExtra(Constant.IParam.courseware_id,bean.getCourseware_id());
                            intent.putExtra(Constant.IParam.video_pdf,bean.getVideo_pdf());
                            intent.putExtra(Constant.IParam.image_url,bean.getImage_url());
                            STActivity(intent,KeChengDetailActivity.class);
                        }

                    }
                });



            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_search_result.setLayoutManager(new LinearLayoutManager(mContext));
        rv_search_result.setNestedScrollingEnabled(false);
        rv_search_result.addItemDecoration(getItemDivider());
        rv_search_result.setAdapter(adapter);



    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);

    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("search_term",search_term);
        map.put("user_id",getUserId());
        map.put("pagesize",pageSize+"");
        map.put("page",page+"");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getSearchCourseWare(map, new MyCallBack<SearchResultObj>(mContext,pcfl,pl_load) {
            @Override
            public void onSuccess(SearchResultObj obj) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(obj.getCourseWare(),true);
                }else{
                    pageNum=2;
                    adapter.setList(obj.getCourseWare(),true);
                }

            }
        });

    }

    private void getValue() {
        search_term=getIntent().getStringExtra(Constant.IParam.searchTerm);
    }

    @Override
    protected void onViewClick(View v) {

    }
}
