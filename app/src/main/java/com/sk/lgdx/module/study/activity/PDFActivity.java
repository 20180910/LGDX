package com.sk.lgdx.module.study.activity;

import android.graphics.Canvas;
import android.view.View;
import android.widget.LinearLayout;

import com.lidong.pdf.PDFView;
import com.lidong.pdf.listener.OnDrawListener;
import com.lidong.pdf.listener.OnLoadCompleteListener;
import com.lidong.pdf.listener.OnPageChangeListener;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.module.study.Constant;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/25.
 */

public class PDFActivity extends BaseActivity implements OnPageChangeListener,OnLoadCompleteListener, OnDrawListener {
    @BindView(R.id.pdfView)
    PDFView pdfView;
    @BindView(R.id.ll)
    LinearLayout ll;

    @Override
    protected int getContentView() {
        return R.layout.act_pdf;
    }

    @Override
    protected void initView() {
        String fileUrl = getIntent().getStringExtra(Constant.IParam.video_pdf);
        String fileName=fileUrl.substring(fileUrl.lastIndexOf("/")+1);
        pdfView.fileFromLocalStorage(this,this,this,fileUrl,fileName);
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }

    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }
}
