package com.sk.lgdx.module.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.RxBus;
import com.sk.lgdx.R;
import com.sk.lgdx.module.home.event.TijiaozuoyeEvent;
import com.sk.lgdx.module.home.network.response.StudentOperationListObj;

/**
 * Created by Administrator on 2017/7/21.
 */

public class MyHomeWorkAdapter extends BaseRecyclerAdapter<StudentOperationListObj.OperationListBean>{
    public MyHomeWorkAdapter(Context ctx, int layoutId) {
        super(ctx, layoutId);
    }


    @Override
    public void bindData(RecyclerViewHolder holder, int i, StudentOperationListObj.OperationListBean bean) {
        TextView tv_time_my_home_work_title = holder.getTextView(R.id.tv_time_my_home_work_title);
        TextView tv_time_my_home_work_time = holder.getTextView(R.id.tv_time_my_home_work_time);
        TextView tv_time_my_home_work_tijiao = holder.getTextView(R.id.tv_time_my_home_work_tijiao);
        TextView tv_time_my_home_work_yitijiao = holder.getTextView(R.id.tv_time_my_home_work_yitijiao);
        TextView tv_item_my_home_work_summary = holder.getTextView(R.id.tv_item_my_home_work_summary);
        LinearLayout ll_item_my_home_work_img = (LinearLayout) holder.getView(R.id.ll_item_my_home_work_img);
        ImageView iv_item_my_home_work_img1 = holder.getImageView(R.id.iv_item_my_home_work_img1);
        ImageView iv_item_my_home_work_img2 = holder.getImageView(R.id.iv_item_my_home_work_img2);
        ImageView iv_item_my_home_work_img3 = holder.getImageView(R.id.iv_item_my_home_work_img3);
        int   screenW = mContext.getResources().getDisplayMetrics().widthPixels;
        int wh=(screenW-PhoneUtils.dip2px(mContext,10))/3;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv_item_my_home_work_img1.getLayoutParams();
        params.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
        params.width = wh;
        params.height = wh;
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) iv_item_my_home_work_img2.getLayoutParams();
        params2.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
        params2.width = wh;
        params2.height = wh;
        LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) iv_item_my_home_work_img3.getLayoutParams();
        params3.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
        params3.width = wh;
        params3.height = wh;
        iv_item_my_home_work_img1.setLayoutParams(params);
        iv_item_my_home_work_img2.setLayoutParams(params2);
        iv_item_my_home_work_img3.setLayoutParams(params3);





        tv_time_my_home_work_title.setText(bean.getTitle());
        tv_time_my_home_work_time.setText(bean.getEnd_time()+"截止");
        if (bean.getIs_submit()==0) {
            tv_time_my_home_work_tijiao.setVisibility(View.VISIBLE);
            tv_time_my_home_work_yitijiao.setVisibility(View.GONE);
        }else {
            tv_time_my_home_work_tijiao.setVisibility(View.GONE);
            tv_time_my_home_work_yitijiao.setVisibility(View.VISIBLE);
        }
        if (bean.getOperation_submit().size()==0) {
            tv_item_my_home_work_summary.setVisibility(View.GONE);
            ll_item_my_home_work_img.setVisibility(View.GONE);
        }else {
            tv_item_my_home_work_summary.setVisibility(View.VISIBLE);
            ll_item_my_home_work_img.setVisibility(View.VISIBLE);
            if (bean.getOperation_submit().get(0).getContent().equals("")) {
                tv_item_my_home_work_summary.setVisibility(View.GONE);
            }else {
                tv_item_my_home_work_summary.setVisibility(View.VISIBLE);
                tv_item_my_home_work_summary.setText(bean.getOperation_submit().get(0).getContent());
            }
            if (bean.getOperation_submit().get(0).getOperation_image().size()==0) {
                ll_item_my_home_work_img.setVisibility(View.GONE);
            }else {
                ll_item_my_home_work_img.setVisibility(View.VISIBLE);
                if (bean.getOperation_submit().get(0).getOperation_image().size()==1) {
                    iv_item_my_home_work_img1.setVisibility(View.VISIBLE);
                    iv_item_my_home_work_img2.setVisibility(View.INVISIBLE);
                    iv_item_my_home_work_img3.setVisibility(View.INVISIBLE);
                    Glide.with(mContext).load(bean.getOperation_submit().get(0).getOperation_image().get(0)).error(R.color.c_press).into(iv_item_my_home_work_img1);
                }else if (bean.getOperation_submit().get(0).getOperation_image().size()==2){
                    iv_item_my_home_work_img1.setVisibility(View.VISIBLE);
                    iv_item_my_home_work_img2.setVisibility(View.VISIBLE);
                    iv_item_my_home_work_img3.setVisibility(View.INVISIBLE);
                    Glide.with(mContext).load(bean.getOperation_submit().get(0).getOperation_image().get(0)).error(R.color.c_press).into(iv_item_my_home_work_img1);
                    Glide.with(mContext).load(bean.getOperation_submit().get(0).getOperation_image().get(1)).error(R.color.c_press).into(iv_item_my_home_work_img2);
                }else if (bean.getOperation_submit().get(0).getOperation_image().size()==3){
                    iv_item_my_home_work_img1.setVisibility(View.VISIBLE);
                    iv_item_my_home_work_img2.setVisibility(View.VISIBLE);
                    iv_item_my_home_work_img3.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(bean.getOperation_submit().get(0).getOperation_image().get(0)).error(R.color.c_press).into(iv_item_my_home_work_img1);
                    Glide.with(mContext).load(bean.getOperation_submit().get(0).getOperation_image().get(1)).error(R.color.c_press).into(iv_item_my_home_work_img2);
                    Glide.with(mContext).load(bean.getOperation_submit().get(0).getOperation_image().get(2)).error(R.color.c_press).into(iv_item_my_home_work_img3);
                }


            }



        }
        tv_time_my_home_work_tijiao.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                RxBus.getInstance().post(new TijiaozuoyeEvent(bean.getOperation_id()+""));



            }
        });



    }


}
