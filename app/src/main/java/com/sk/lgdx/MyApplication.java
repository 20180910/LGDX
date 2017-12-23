package com.sk.lgdx;


import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadManager;
import com.github.androidtools.SPUtils;
import com.github.baseclass.view.Loading;
import com.github.retrofitutil.NetWorkManager;
import com.sk.lgdx.tools.download.CrashHandler;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by administartor on 2017/8/8.
 */

public class MyApplication extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
//        SpeechUtility.createUtility(this, "appid=" + Config.xunfei_app_id);
        super.onCreate();
//http://121.40.186.118:1554
        NetWorkManager.getInstance(getApplicationContext(),"http://121.40.186.118:1554/",BuildConfig.DEBUG).complete();
        Loading.setLoadView(R.layout.loading_view);
        ZXingLibrary.initDisplayOpinion(this);
        //二维码
//        SDKInitializer.initialize(getApplicationContext());
//        PlatformConfig.setWeixin(Config.weixing_id, Config.weixing_AppSecret);
//        PlatformConfig.setQQZone(Config.qq_id, Config.qq_key);
//        UMShareAPI.get(this);
        initDownloader();
        CrashHandler.getInstance(getApplicationContext());
    }
    private void initDownloader() {
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(10);
        configuration.setThreadNum(3);
        DownloadManager.getInstance().init(getApplicationContext(), configuration);
    }


    //经度
   public static double longitude;//=121.432986;
    //纬度
   public static double latitude;//=31.229504;

    /**
     * 经度
     * @param context
     * @return
     */
    public static double getJingDu(Context context){
        if(longitude==0){
            longitude=SPUtils.getPrefFloat(context,Config.longitude,0);
            return longitude;
        }else{
            return longitude;
        }
    }

    /**
     * 纬度
     * @param context
     * @return
     */
    public static double getWeiDu(Context context){
        if(latitude==0){
            latitude=SPUtils.getPrefFloat(context,Config.latitude,0);
            return latitude;
        }else{
            return latitude;
        }
    }
    /**
     * 经度
     * @param context
     * @return
     */
    public static double Lng(Context context){
        if(longitude==0){
            longitude=SPUtils.getPrefFloat(context,Config.longitude,0);
            return longitude;
        }else{
            return longitude;
        }
    }
    /**
     * 纬度
     * @param context
     * @return
     */
    public static double Lat(Context context){
        if(latitude==0){
            latitude=SPUtils.getPrefFloat(context,Config.latitude,0);
            return latitude;
        }else{
            return latitude;
        }
    }
}
