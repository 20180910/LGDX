package com.sk.lgdx.module.home.network;

import com.github.retrofitutil.NoNetworkException;
import com.sk.lgdx.Config;
import com.sk.lgdx.base.BaseApiRequest;
import com.sk.lgdx.base.MyCallBack;
import com.sk.lgdx.module.home.network.request.HomeTypeMerchantListBody;
import com.sk.lgdx.module.home.network.request.QuerentijiaoBody;
import com.sk.lgdx.module.home.network.request.SearchResultBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    /*public static Observable getRegisterXieYi(String rnd, String sign){
        return getCommonClient(com.sk.yangyu.module.home.network.IRequest.class).getPayNotifyUrl(rnd,sign).compose(RxResult.appSchedulers()).compose(RxResult.handleResult());
    }*/

    public static void tuanGouSureOrder(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(com.sk.lgdx.network.IRequest.class).tuanGouSureOrder(map).enqueue(callBack);
    }

    //首页轮播图信息
    public static void getRoastingChart(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getRoastingChart(map).enqueue(callBack);
    }

    //首页类别集合信息-学生(学习、课程表、考勤、作业)
    public static void getTypeAssemBlage(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getTypeAssemBlage(map).enqueue(callBack);
    }

    //首页学院咨询列表数据
    public static void getInformationList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getInformationList(map).enqueue(callBack);
    }

    //学院咨询详情
    public static void getInformationMore(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getInformationMore(map).enqueue(callBack);
    }

    //我的消息列表
    public static void getNewList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getNewList(map).enqueue(callBack);
    }

    //我的消息详情
    public static void getNewsDetail(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getNewsDetail(map).enqueue(callBack);
    }
    //获取首页右上角和作业未读消息条数
    public static void getUnreadNews(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getUnreadNews(map).enqueue(callBack);
    }

    //作业列表
    public static void getStudentOperationList(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getStudentOperationList(map).enqueue(callBack);
    }

    //提交作业
    public static void postOperationSubmit(Map map, List<QuerentijiaoBody> body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).postOperationSubmit(map, body).enqueue(callBack);
    }


    /**************************************分割线******************************************/

    //首页类别集合信息
    public static void getTypeAssemblage(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getTypeAssemblage(map).enqueue(callBack);
    }

    //首页中部图片信息
    public static void getHomePageImage(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getHomePageImage(map).enqueue(callBack);
    }

    //首页每日精选
    public static void getDailybest(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getDailybest(map).enqueue(callBack);
    }

    //根据城市名获取ID
    public static void getCityId(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getCityId(map).enqueue(callBack);
    }

    //获取全部区/县商业圈
    public static void getAreaBusinessCircle(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getAreaBusinessCircle(map).enqueue(callBack);
    }
    //商家列表(分类)
    public static void postMerchantList(Map map, HomeTypeMerchantListBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).postMerchantList(map,body).enqueue(callBack);
    }

    //首页公告信息
    public static void getAnnouncement(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getAnnouncement(map).enqueue(callBack);
    }



    //热门搜索词、历史搜索词
    public static void getHottestSearch(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getHottestSearch(map).enqueue(callBack);
    }

    //删除历史搜索词
    public static void getDelRecentlySearch(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getDelRecentlySearch(map).enqueue(callBack);
    }
    //搜索商家
    public static void postSearchMerchant(Map map, SearchResultBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).postSearchMerchant(map,body).enqueue(callBack);
    }

    //猜你喜欢
    public static void getGuessYouLike(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) {
            callBack.onFailure(null, new NoNetworkException(Config.noNetWork));
            return;
        }
        getGeneralClient(IRequest.class).getGuessYouLike(map).enqueue(callBack);
    }
}
