package com.sk.lgdx.module.study.network;

import com.github.retrofitutil.NoNetworkException;
import com.sk.lgdx.Config;
import com.sk.lgdx.base.BaseApiRequest;
import com.sk.lgdx.base.BaseBody;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.study.network.request.CommitOrderBody;
import com.sk.lgdx.module.study.network.request.NearShangJiaBody;
import com.sk.lgdx.module.study.network.request.ShowOrderBody;
import com.sk.lgdx.module.study.network.request.YuYueBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    /*public static Observable getRegisterXieYi(String rnd, String sign){
        return getCommonClient(com.sk.yangyu.module.home.network.IRequest.class).getPayNotifyUrl(rnd,sign).compose(RxResult.appSchedulers()).compose(RxResult.handleResult());
    }*/

    //学习页面-课程类别列表
    public static void getMajorType(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getMajorType(map).enqueue(callBack);
    }


    //学习页面-课件列表
    public static void getHistoryCourseWareList(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getHistoryCourseWareList(map).enqueue(callBack);
    }

    //课件详情
    public static void getCourseWareDetail(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getCourseWareDetail(map).enqueue(callBack);
    }

    //收藏/取消收藏
    public static void getCollectMerchant(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getCollectMerchant(map).enqueue(callBack);
    }

    //课件--发表评论/发表二级评论
    public static void getAddCommentCourseWare(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getAddCommentCourseWare(map).enqueue(callBack);
    }


    /***************************************分割线***********************************************/
    public static void getNearShangJiaType(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getNearShangJiaType(map).enqueue(callBack);
    }

    public static void getNearShangJiaList(Map map, NearShangJiaBody body,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getNearShangJiaList(map,body).enqueue(callBack);
    }
    public static void getShangJiaDetail(Map map, BaseBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getShangJiaDetail(map,body).enqueue(callBack);
    }
    public static void getShangJiaInfo(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getShangJiaInfo(map ).enqueue(callBack);
    }
    public static void getShangJiaEvaluateNum(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
         getGeneralClient(IRequest.class).getShangJiaEvaluateNum(map).enqueue(callBack);;
    }
    public static void collectShangJia(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).collectShangJia(map ).enqueue(callBack);
    }
    public static void getShangJiaEvaluateList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getShangJiaEvaluateList(map ).enqueue(callBack);
    }
    public static void getShangJiaGoods(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getShangJiaGoods(map ).enqueue(callBack);
    }
    public static void getShangJiaYuYue(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getShangJiaYuYue(map ).enqueue(callBack);
    }
    public static void startYuYue(Map map, YuYueBody body,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).startYuYue(map,body).enqueue(callBack);
    }
    public static void showOrder(Map map, ShowOrderBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).showOrder(map,body ).enqueue(callBack);
    }
    public static void commitOrder(Map map, CommitOrderBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).commitOrder(map,body ).enqueue(callBack);
    }
    public static void commitJiaCaiOrder(Map map, CommitOrderBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).commitJiaCaiOrder(map,body ).enqueue(callBack);
    }
    public static void yuePay(Map map,  MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).yuePay(map ).enqueue(callBack);
    }
    public static void payTuiJianShangJia(Map map,  MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).payTuiJianShangJia(map ).enqueue(callBack);
    }

}
