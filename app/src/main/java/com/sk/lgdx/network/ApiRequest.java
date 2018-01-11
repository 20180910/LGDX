package com.sk.lgdx.network;

import com.github.retrofitutil.NoNetworkException;
import com.sk.lgdx.Config;
import com.sk.lgdx.base.BaseApiRequest;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.my.network.request.UploadImgBody;

import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    /*public static Observable getRegisterXieYi(String rnd, String sign){
        return getCommonClient(com.sk.yangyu.module.home.network.IRequest.class).getPayNotifyUrl(rnd,sign).compose(RxResult.appSchedulers()).compose(RxResult.handleResult());
    }*/

    public static void tuanGouSureOrder(Map map ,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;}
        getGeneralClient(IRequest.class).tuanGouSureOrder(map).enqueue(callBack);
    }
    //退出登录
    public static void getLogOut(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getLogOut(map).enqueue(callBack);
    }
    //base64图片上传
    public static void uploadImg(Map map, UploadImgBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).uploadImg(map, body).enqueue(callBack);
    }
   // 发送邮件验证码

    public static void getSMSCode(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getSMSCode(map).enqueue(callBack);
    }
    //上传文件

    public static void postUploadFile(Map map, MultipartBody.Part file, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).postUploadFile(map,file).enqueue(callBack);
    }


    public static void uploadFile(Map map, MultipartBody.Part file, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).uploadFile(map,file).enqueue(callBack);
//        NetWorkManager.getSimpleClient().create(IRequest.class).uploadFile(map,file).enqueue(callBack);
    }





    /**********************************分割线**************************************/





    public static void paymentURL(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).paymentURL(map).enqueue(callBack);
    }

    //获取省份
    public static void getProvince(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getProvince(map).enqueue(callBack);
    }

    //获取城市
    public static void getCity(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getCity(map).enqueue(callBack);
    }

    //获取区/县
    public static void getArea(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getArea(map).enqueue(callBack);
    }

    //申请合伙人
    public static void getApplyForPartner(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getApplyForPartner(map).enqueue(callBack);
    }

    //获取支付信息
    public static void PayInfo(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).PayInfo(map).enqueue(callBack);
    }
    public static void getAllCity(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getAllCity(map).enqueue(callBack);
    }


    //保存定位信息
    public static void getLatLng(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getLatLng(map).enqueue(callBack);
    }
    //保存定位信息
    public static void fenXiang(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).fenXiang(map).enqueue(callBack);
    }

}
