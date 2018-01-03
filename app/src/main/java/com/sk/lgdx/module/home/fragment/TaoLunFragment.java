package com.sk.lgdx.module.home.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.LoadMoreAdapter;
import com.github.baseclass.adapter.LoadMoreViewHolder;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseFragment;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.taolun.Constant;
import com.sk.lgdx.module.taolun.activity.TaolunDetailsActivity;
import com.sk.lgdx.module.taolun.adapter.TaolunImgAdapter;
import com.sk.lgdx.module.taolun.network.ApiRequest;
import com.sk.lgdx.module.taolun.network.response.TaolunquObj;
import com.sk.lgdx.tools.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/4.
 */

public class TaoLunFragment extends BaseFragment {
    @BindView(R.id.rv_taolun)
    RecyclerView rv_taolun;
    LoadMoreAdapter adapter;


    @Override
    protected int getContentView() {
        return R.layout.frag_taolun;
    }

    @Override
    protected void initView() {
        adapter = new LoadMoreAdapter<TaolunquObj.DiscussionForumListBean>(mContext, R.layout.item_taolun, pageSize) {
            @Override
            public void bindData(LoadMoreViewHolder holder, int i, TaolunquObj.DiscussionForumListBean bean) {
                ImageView iv_item_taolun_icon = holder.getImageView(R.id.iv_item_taolun_icon);
                TextView tv_item_taolun_name = holder.getTextView(R.id.tv_item_taolun_name);
                TextView tv_item_taolun_title = holder.getTextView(R.id.tv_item_taolun_title);
                TextView tv_item_taolun_content = holder.getTextView(R.id.tv_item_taolun_content);
                TextView tv_item_taolun_time = holder.getTextView(R.id.tv_item_taolun_time);
                TextView tv_item_taolun_message_num = holder.getTextView(R.id.tv_item_taolun_message_num);
                TextView tv_item_taolun_zan_num = holder.getTextView(R.id.tv_item_taolun_zan_num);
                RecyclerView rv_item_taolun_img = (RecyclerView) holder.getView(R.id.rv_item_taolun_img);

                if (TextUtils.isEmpty(bean.getHead_portrait())) {
                    iv_item_taolun_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.my_people));

                } else {
                    Glide.with(mContext).load(bean.getHead_portrait()).error(R.drawable.my_people).into(iv_item_taolun_icon);

                }


                tv_item_taolun_name.setText(bean.getName());
                tv_item_taolun_title.setText(bean.getTitle());
                tv_item_taolun_content.setText(bean.getContent());
                tv_item_taolun_time.setText(com.github.androidtools.DateUtils.dateToString(new Date(bean.getAdd_time()*1000 ),"yyyy-MM-dd HH:mm"));
                tv_item_taolun_zan_num.setText(bean.getThumbup_count());
                tv_item_taolun_message_num.setText(bean.getComment_count());
                if (bean.getIs_thumbup().equals("0")) {
                    tv_item_taolun_zan_num.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.my_collection_zan),
                            null,
                            null,
                            null);
                } else {
                    tv_item_taolun_zan_num.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.my_collection_zan_selset),
                            null,
                            null,
                            null);


                }


                List<String> imgList = new ArrayList<>();
                for (int i1 = 0; i1 < bean.getImage_list().size(); i1++) {
                    imgList.add(bean.getImage_list().get(i1));
                }

                TaolunImgAdapter imgAdapter = new TaolunImgAdapter(mContext, R.layout.item_taolun_img);
                imgAdapter.setList(imgList,true);

                rv_item_taolun_img.setLayoutManager(new GridLayoutManager(mContext, 3));
                rv_item_taolun_img.addItemDecoration(new DividerGridItemDecoration(mContext, PhoneUtils.dip2px(mContext,5),R.color.white));
                rv_item_taolun_img.setNestedScrollingEnabled(false);
                rv_item_taolun_img.setAdapter(imgAdapter);


              holder.itemView.setOnClickListener(new MyOnClickListener() {
                  @Override
                  protected void onNoDoubleClick(View view) {
                      Intent intent=new Intent();
                      intent.putExtra(Constant.IParam.discussion_forum_id,bean.getDiscussion_forum_id());
                      STActivity(intent,TaolunDetailsActivity.class);
                  }
              });

            }
        };

        adapter.setOnLoadMoreListener(this);
        rv_taolun.setLayoutManager(new LinearLayoutManager(mContext));
        rv_taolun.setNestedScrollingEnabled(false);
        rv_taolun.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("pagesize", pageSize + "");
        map.put("page", page + "");
        map.put("sign", GetSign.getSign(map));
        ApiRequest.getDiscussionForum(map, new MyCallBack<TaolunquObj>(mContext, pcfl, pl_load) {
            @Override
            public void onSuccess(TaolunquObj obj) {
                if (isLoad) {
                    pageNum++;
                    adapter.addList(obj.getDiscussion_forum_list(), true);
                } else {
                    pageNum = 2;
                    adapter.setList(obj.getDiscussion_forum_list(), true);
                }


            }
        });


    }

    @Override
    protected void onViewClick(View v) {

    }
}
