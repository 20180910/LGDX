package com.sk.lgdx.module.home.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.github.baseclass.rx.MySubscriber;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyRadioButton;
import com.sk.lgdx.Config;
import com.sk.lgdx.GetSign;
import com.sk.lgdx.R;
import com.sk.lgdx.base.BaseActivity;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.broadcast.MyOperationBro;
import com.sk.lgdx.module.home.event.StudyEvent;
import com.sk.lgdx.module.home.fragment.HomeFragment;
import com.sk.lgdx.module.home.fragment.MyFragment;
import com.sk.lgdx.module.home.fragment.StudyFragment;
import com.sk.lgdx.module.home.fragment.TaoLunFragment;
import com.sk.lgdx.module.home.network.ApiRequest;
import com.sk.lgdx.module.home.network.response.BanbengengxinObj;
import com.sk.lgdx.tools.download.entity.AppInfo;
import com.sk.lgdx.tools.download.service.DownloadService;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/4.
 */

public class MainActivity extends BaseActivity {
//    @BindView(R.id.status_bar)
//    View status_bar;

    HomeFragment homeFragment;
    StudyFragment studyFragment;
    TaoLunFragment taoLunFragment;
    MyFragment myFragment;

    @BindView(R.id.layout_main_content)
    FrameLayout layout_main_content;
    @BindView(R.id.rb_home)
    MyRadioButton rb_home;

    @BindView(R.id.rb_home_near)
    MyRadioButton rb_home_near;

    @BindView(R.id.rb_home_order)
    MyRadioButton rb_home_order;

    @BindView(R.id.rb_home_my)
    MyRadioButton rb_home_my;

    private MyRadioButton selectButton;
    private LocalBroadcastManager localBroadcastManager;
    private MyOperationBro myOperationBro;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        /*int statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = statusBarHeight;
        status_bar.setLayoutParams(layoutParams);
        status_bar.setBackgroundColor(getResources().getColor(R.color.white));*/
//        setBroadcast();








        selectButton = rb_home;
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, homeFragment).commitAllowingStateLoss();
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if(Config.exitAPP.equals(intent.getAction())){
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initData() {
//        showLoading();
//        getVersionUpdate();
    }
    private void getVersionUpdate() {

        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",GetSign.getSign(map));
        ApiRequest.getVersionUpdate(map, new MyCallBack<BanbengengxinObj>(mContext) {
            @Override
            public void onSuccess(BanbengengxinObj obj) {
                dismissLoading();
                Log.i("===","===getAppVersionCode="+getAppVersionCode());

                if (obj.getAndroid_version()>getAppVersionCode()) {

                    MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                    mDialog.setMessage("检测到app有新版本是否更新?");
                    mDialog.setNegativeButton((dialog, which) -> dialog.dismiss());
                    mDialog.setPositiveButton((dialog, which) -> {
                        dialog.dismiss();
                        AppInfo info=new AppInfo();
                        info.setUrl(obj.getAndroid_vs_url());
                        info.setHouZhui("apk");
                        info.setFileName("lgdx");
                        info.setTitle("上理传播E学堂");
                        info.setImage("lgdx上理传播E学堂");
                        info.setId(obj.getAndroid_version()+"");
                        downloadApp(info);
                    });
                    mDialog.create().show();

                }else {





                }


            }
        });




    }
    private void downloadApp(AppInfo info) {
       showMsg("上理传播E学堂正在下载中...");
        DownloadService.intentDownload(mContext, info);
    }



    @Override
    protected void initRxBus() {
        super.initRxBus();
        getRxBusEvent(StudyEvent.class, new MySubscriber<StudyEvent>() {
            @Override
            public void onMyNext(StudyEvent event) {
//                rb_home.setChecked(false);
                rb_home_near.setChecked(true);
                selectNear();


            }
        });
    }

    @OnClick({R.id.rb_home, R.id.rb_home_near, R.id.rb_home_order, R.id.rb_home_my})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
//                status_bar.setBackgroundColor(getResources().getColor(R.color.white));
                selectHome();
                break;
            case R.id.rb_home_near:
//                status_bar.setBackgroundColor(getResources().getColor(R.color.white));
                selectNear();
                break;
            case R.id.rb_home_order:
//                if ("0".equals(getUserId())) {
//                    selectButton.setChecked(true);
//                    STActivity(LoginActivity.class);
//                    return;
//                }
//                status_bar.setBackgroundColor(getResources().getColor(R.color.white));
                selectOrder();
                break;
            case R.id.rb_home_my:
//                if ("0".equals(getUserId())) {
//                    selectButton.setChecked(true);
//                    STActivity(LoginActivity.class);
//                    return;
//                }
//                status_bar.setBackgroundColor(getResources().getColor(R.color.home_green));
                selectMy();
                break;
        }
    }

    private void selectMy() {
        selectButton = rb_home_my;
        if (myFragment == null) {
            myFragment = new MyFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, myFragment).commitAllowingStateLoss();
        } else {
            showFragment(myFragment);
        }
        hideFragment(homeFragment);
        hideFragment(studyFragment);
        hideFragment(taoLunFragment);
    }

    private void selectOrder() {
        selectButton = rb_home_order;
        if (taoLunFragment == null) {
            taoLunFragment = new TaoLunFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, taoLunFragment).commitAllowingStateLoss();
        } else {
            showFragment(taoLunFragment);
        }
        hideFragment(myFragment);
        hideFragment(studyFragment);
        hideFragment(homeFragment);
    }

    private void selectNear() {
        selectButton = rb_home_near;
        if (studyFragment == null) {
            studyFragment = new StudyFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, studyFragment).commitAllowingStateLoss();
        } else {
            showFragment(studyFragment);
        }
        hideFragment(homeFragment);
        hideFragment(myFragment);
        hideFragment(taoLunFragment);
    }

    private void selectHome() {
        selectButton = rb_home;
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.layout_main_content, homeFragment).commitAllowingStateLoss();
        } else {
            showFragment(homeFragment);
        }
        hideFragment(studyFragment);
        hideFragment(taoLunFragment);
        hideFragment(myFragment);
    }

    private void setBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        myOperationBro = new MyOperationBro(new MyOperationBro.LoginBroInter() {
            @Override
            public void loginSuccess() {
                selectMy();
                selectButton.setChecked(true);

//                registerHuanXin();
            }

            @Override
            public void exitLogin() {
                selectHome();
                selectButton.setChecked(true);
                myFragment=null;
            }
        });
        localBroadcastManager.registerReceiver(myOperationBro, new IntentFilter(Config.Bro.operation));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(myOperationBro);
        }
    }


    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            showToastS("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}
