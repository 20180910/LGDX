package com.sk.lgdx.module.home.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.github.androidtools.StatusBarUtils;
import com.github.baseclass.rx.RxBus;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.base.BaseObj;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.home.event.SaomaEvent;
import com.sk.lgdx.module.home.network.ApiRequest;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by administartor on 2017/10/24.
 */

public class NewScanActivity extends BaseActivity {
    @BindView(R.id.ll_scan_top)
    LinearLayout ll_scan_top;
    private boolean isLight;
    private CaptureFragment captureFragment;

    @Override
    protected int getContentView() {
        return R.layout.act_scan_new;
    }

    @Override
    protected void initView() {
        ll_scan_top.setPadding(0, StatusBarUtils.getStatusBarHeight(mContext), 0, 0);
        CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Log.i("===", "===" + result);
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id", getUserId());
                map.put("code", result);
                map.put("sign", GetSign.getSign(map));
                ApiRequest.getSignIn(map, new MyCallBack<BaseObj>(mContext) {
                    @Override
                    public void onSuccess(BaseObj obj) {
                        RxBus.getInstance().post(new SaomaEvent());
                        showMsg("签到成功");
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Message restartMessage = new Message();
                        restartMessage.what = com.uuzuche.lib_zxing.R.id.restart_preview;
                        captureFragment.getHandler().sendMessageDelayed(restartMessage, 5000);
                    }
                });
            }

            @Override
            public void onAnalyzeFailed() {
                /*Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
                bundle.putString(CodeUtils.RESULT_STRING, "");
                resultIntent.putExtras(bundle);
                setResult(RESULT_OK, resultIntent);
                finish();*/
            }
        };
        /**
         * 执行扫面Fragment的初始化操作
         */
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.scan);

        captureFragment.setAnalyzeCallback(analyzeCallback);
        /**
         * 替换我们的扫描控件
         */getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commitAllowingStateLoss();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_scan_back, R.id.ll_scan_shou_dian})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.iv_scan_back:
                finish();
                break;
            case R.id.ll_scan_shou_dian:
                CodeUtils.isLightEnable(!isLight);
                isLight = !isLight;
                break;
        }
    }

}
