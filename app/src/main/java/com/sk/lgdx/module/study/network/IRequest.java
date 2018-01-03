package com.sk.lgdx.module.study.network;

import com.sk.lgdx.base.BaseBody;
import com.sk.lgdx.base.BaseObj;
import com.sk.lgdx.base.ResponseObj;
import com.sk.lgdx.module.study.network.request.CommitOrderBody;
import com.sk.lgdx.module.study.network.request.NearShangJiaBody;
import com.sk.lgdx.module.study.network.request.ShowOrderBody;
import com.sk.lgdx.module.study.network.request.YuYueBody;
import com.sk.lgdx.module.study.network.response.CommitOrderResultObj;
import com.sk.lgdx.module.study.network.response.HistoryCourseWareListObj;
import com.sk.lgdx.module.study.network.response.MajorTypeObj;
import com.sk.lgdx.module.study.network.response.NearListObj;
import com.sk.lgdx.module.study.network.response.NearShangJiaObj;
import com.sk.lgdx.module.study.network.response.PaySuccessObj;
import com.sk.lgdx.module.study.network.response.SearchObj;
import com.sk.lgdx.module.study.network.response.SearchResultObj;
import com.sk.lgdx.module.study.network.response.ShangJiaEvaluateListObj;
import com.sk.lgdx.module.study.network.response.ShangJiaEvaluateNumObj;
import com.sk.lgdx.module.study.network.response.ShangJiaInfoObj;
import com.sk.lgdx.module.study.network.response.ShangJiaObj;
import com.sk.lgdx.module.study.network.response.ShangJiaShangPingObj;
import com.sk.lgdx.module.study.network.response.StudyDetailObj;
import com.sk.lgdx.module.study.network.response.TiJiaoOrderObj;
import com.sk.lgdx.module.study.network.response.YuYueTimeObj;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {
    @GET("api/HomePage/GetProductGroupOrderShow")
    Call<ResponseObj<BaseObj>> tuanGouSureOrder(@QueryMap Map<String, String> map);

   // 学习页面-课程类别列表
    @GET("api/SHLGStudent/GetMajorType")
    Call<ResponseObj<List<MajorTypeObj>>> getMajorType(@QueryMap Map<String, String> map);

   // 学习页面-课件列表
    @GET("api/SHLGStudent/GetHistoryCourseWareList")
    Call<ResponseObj<HistoryCourseWareListObj>> getHistoryCourseWareList(@QueryMap Map<String, String> map);

   // 课件详情
    @GET("api/SHLGInformation/GetCourseWareDetail")
    Call<ResponseObj<StudyDetailObj>> getCourseWareDetail(@QueryMap Map<String, String> map);

   // 收藏/取消收藏
    @GET("api/SHLGInformation/GetCollectMerchant")
    Call<ResponseObj<BaseObj>> getCollectMerchant(@QueryMap Map<String, String> map);

   // 课件--发表评论/发表二级评论
    @GET("api/SHLGInformation/GetAddCommentCourseWare")
    Call<ResponseObj<BaseObj>> getAddCommentCourseWare(@QueryMap Map<String, String> map);

   // 增加下载记录(下载完成后调取)
    @GET("api/SHLGInformation/GetDownloadRecord")
    Call<ResponseObj<BaseObj>> getDownloadRecord(@QueryMap Map<String, String> map);



 //热门搜索词、历史搜索词
 @GET("api/SHLGInformation/GetHottestSearch")
 Call<ResponseObj<SearchObj>> getHottestSearch(@QueryMap Map<String, String> map);

 //删除历史搜索词
 @GET("api/SHLGInformation/GetDelRecentlySearch")
 Call<ResponseObj<BaseObj>> getDelRecentlySearch(@QueryMap Map<String, String> map);

 //删除历史搜索词
 @GET("api/SHLGInformation/GetSearchCourseWare")
 Call<ResponseObj<SearchResultObj>> getSearchCourseWare(@QueryMap Map<String, String> map);


 /*************************************分割线*********************************************************/

    //商家列表类别
    @GET("api/Information/GetTypeAssemblage")
    Call<ResponseObj<NearShangJiaObj>> getNearShangJiaType(@QueryMap Map<String, String> map);


    //商家列表
    @POST("api/MerchantCenter/PostVicinityMerchantList")
    Call<ResponseObj<NearListObj>> getNearShangJiaList(@QueryMap Map<String, String> map, @Body NearShangJiaBody body);


    //商家详情
    @POST("api/MerchantCenter/PostMerchantDetail")
    Call<ResponseObj<ShangJiaObj>> getShangJiaDetail(@QueryMap Map<String, String> map, @Body BaseBody body);

    //商家信息
    @GET("api/MerchantCenter/GetMerchantInformation")
    Call<ResponseObj<ShangJiaInfoObj>> getShangJiaInfo(@QueryMap Map<String, String> map);

    //商家收藏和取消收藏
    @GET("api/MerchantCenter/GetCollectMerchant")
    Call<ResponseObj<BaseObj>> collectShangJia(@QueryMap Map<String, String> map);

    //商家评价数量
    @GET("api/MerchantCenter/GetScoringNum")
    Call<ResponseObj<ShangJiaEvaluateNumObj>> getShangJiaEvaluateNum(@QueryMap Map<String, String> map);

    //商家评价数据
    @GET("api/MerchantCenter/GetScoringList")
    Call<ResponseObj<ShangJiaEvaluateListObj>> getShangJiaEvaluateList(@QueryMap Map<String, String> map);

    //商家商品
    @GET("api/MerchantCenter/GetMerchantGoods")
    Call<ResponseObj<ShangJiaShangPingObj>> getShangJiaGoods(@QueryMap Map<String, String> map);

    //商家预约时间段
    @GET("api/MerchantCenter/GetAppointmentSchedule")
    Call<ResponseObj<List<YuYueTimeObj>>> getShangJiaYuYue(@QueryMap Map<String, String> map);

    //立即预约
    @POST("api/MerchantCenter/PostMakeAppointment")
    Call<ResponseObj<BaseObj>> startYuYue(@QueryMap Map<String, String> map, @Body YuYueBody body);

    //订单显示
    @POST("api/Order/PostShowOrder")
    Call<ResponseObj<TiJiaoOrderObj>> showOrder(@QueryMap Map<String, String> map, @Body ShowOrderBody body);

    //提交订单
    @POST("api/Order/PostSaveOrder")
    Call<ResponseObj<CommitOrderResultObj>> commitOrder(@QueryMap Map<String, String> map, @Body CommitOrderBody body);

    //加菜-提交订单
    @POST("api/Order/PostAddFoodOrder")
    Call<ResponseObj<TiJiaoOrderObj>> commitJiaCaiOrder(@QueryMap Map<String, String> map, @Body CommitOrderBody body);

    //余额支付订单
    @GET("api/Order/GetBalancePayment")
    Call<ResponseObj<PaySuccessObj>> yuePay(@QueryMap Map<String, String> map);

    //支付完成推荐商家
    @GET("api/Order/GetPayRecommend")
    Call<ResponseObj<PaySuccessObj>> payTuiJianShangJia(@QueryMap Map<String, String> map);
}
