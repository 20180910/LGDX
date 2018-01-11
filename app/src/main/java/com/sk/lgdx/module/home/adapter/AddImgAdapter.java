package com.sk.lgdx.module.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.rx.RxBus;
import com.sk.lgdx.R;
import com.sk.lgdx.module.home.event.AddImgEvent;

/**
 * Created by Administrator on 2017/11/25.
 */

public class AddImgAdapter extends BaseRecyclerAdapter<String> {

    private int selectImgIndex;

    public AddImgAdapter(Context ctx, int layoutId) {
        super(ctx, layoutId);
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder itemHolder;
        if(viewType==1){
            itemHolder = new RecyclerViewHolder(this.mContext, this.mInflater.inflate(R.layout.item_fatie_addimg, parent, false));
        }else{
            itemHolder = new RecyclerViewHolder(this.mContext, this.mInflater.inflate(R.layout.item_fatie_img, parent, false));
        }
        return itemHolder;
    }
    @Override
    public void bindData(RecyclerViewHolder itemHolder, int itemPosition, String bean) {
        itemHolder.itemView.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if(itemPosition==mList.size()){
                    selectImgIndex =-1;
                }else{
                    selectImgIndex =itemPosition;
                }
                addPhoto();
            }
            private void addPhoto() {
                RxBus.getInstance().post(new AddImgEvent(selectImgIndex));
            }
        });
        if(getItemViewType(itemPosition)==1){

        }else{
            ImageView imageView = itemHolder.getImageView(R.id.iv_add_img);
            TextView  tv_add_name = itemHolder.getTextView(R.id.tv_add_name);

            int   screenW = mContext.getResources().getDisplayMetrics().widthPixels;
            int wh=(screenW- PhoneUtils.dip2px(mContext,40))/3;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) tv_add_name.getLayoutParams();
            params.width = wh;
            params.height =  wh;
            params2.width = wh;
            params2.height =  wh;
            params.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
            params2.setMargins(PhoneUtils.dip2px(mContext,5),0,PhoneUtils.dip2px(mContext,5),0);
            imageView.setLayoutParams(params);
            tv_add_name.setLayoutParams(params2);
            //gif,jpg,png,bmp,jpeg

           String houzhui= bean.substring( bean.lastIndexOf("."),bean.length());
            String name=bean.substring(bean.lastIndexOf("/")+1,bean.length());

            Log.i("===","===houzhui="+houzhui);
            if (houzhui.equals(".gif")||houzhui.equals(".jpg")
                    ||houzhui.equals(".png")||houzhui.equals(".bmp")||houzhui.equals(".jpeg")) {
                tv_add_name.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(bean).error(R.color.c_press).into(imageView);

            }else {
                tv_add_name.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                tv_add_name.setText(name);

            }


//            bean.






        }
    }
    @Override
    public int getItemViewType(int itemPosition) {
        if(itemPosition==mList.size()&&mList.size()<3){
            return 1;
        }else{
            return 0;
        }
    }
    @Override
    public int getItemCount() {
        if(mList==null){
            return 0;
        }else if(mList.size()>=3){
            return mList.size();
        }else{
            return mList==null?0:mList.size()+1;
        }
    }
}

