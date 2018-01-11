package com.sk.lgdx.module.home.network;

import com.sk.lgdx.base.BaseObj;
import com.sk.lgdx.base.ResponseObj;
import com.sk.lgdx.module.home.network.request.HomeTypeMerchantListBody;
import com.sk.lgdx.module.home.network.request.QuerentijiaoBody;
import com.sk.lgdx.module.home.network.response.AreaBusinessCircleObj;
import com.sk.lgdx.module.home.network.response.BanbengengxinObj;
import com.sk.lgdx.module.home.network.response.CityIdObj;
import com.sk.lgdx.module.home.network.response.HomeAnnouncementObj;
import com.sk.lgdx.module.home.network.response.HomeDailybestObj;
import com.sk.lgdx.module.home.network.response.HomeLikeObj;
import com.sk.lgdx.module.home.network.response.HomePageImageObj;
import com.sk.lgdx.module.home.network.response.HomeRoastingChartObj;
import com.sk.lgdx.module.home.network.response.HomeTypeAssemblageObj;
import com.sk.lgdx.module.home.network.response.HomeTypeMerchantListObj;
import com.sk.lgdx.module.home.network.response.InformationListObj;
import com.sk.lgdx.module.home.network.response.InformationMoreObj;
import com.sk.lgdx.module.home.network.response.KaoqinObj;
import com.sk.lgdx.module.home.network.response.KechengbiaoObj;
import com.sk.lgdx.module.home.network.response.NewListObj;
import com.sk.lgdx.module.home.network.response.NewsDetailObj;
import com.sk.lgdx.module.home.network.response.NextLessonObj;
import com.sk.lgdx.module.home.network.response.StudentOperationListObj;
import com.sk.lgdx.module.home.network.response.TypeAssemBlageObj;
import com.sk.lgdx.module.home.network.response.UnreadNewsObj;

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

    //首页轮播图信息
    @GET ("api/SHLGInformation/GetRoastingChart")
    Call<ResponseObj<HomeRoastingChartObj>> getRoastingChart(@QueryMap Map<String, String> map);


    //首页类别集合信息-学生(学习、课程表、考勤、作业)
    @GET ("api/SHLGInformation/GetTypeAssemBlageTeacher")
    Call<ResponseObj<List<TypeAssemBlageObj>>> getTypeAssemBlage(@QueryMap Map<String, String> map);

    //首页学院咨询列表数据
    @GET ("api/SHLGInformation/GetInformationList")
    Call<ResponseObj<List<InformationListObj>>> getInformationList(@QueryMap Map<String, String> map);

    //学院咨询详情
    @GET ("api/SHLGInformation/GetInformationMore")
    Call<ResponseObj<InformationMoreObj>> getInformationMore(@QueryMap Map<String, String> map);

    //我的消息列表
    @GET ("api/SHLGUser/GetNewList")
    Call<ResponseObj<List<NewListObj>>> getNewList(@QueryMap Map<String, String> map);

    //我的消息详情
    @GET ("api/SHLGUser/GetNewsDetail")
    Call<ResponseObj<NewsDetailObj>> getNewsDetail(@QueryMap Map<String, String> map);


    //获取首页右上角和作业未读消息条数
    @GET ("api/SHLGInformation/GetUnreadNews")
    Call<ResponseObj<UnreadNewsObj>> getUnreadNews(@QueryMap Map<String, String> map);

    //作业列表
    @GET ("api/SHLGStudent/GetStudentOperationList")
    Call<ResponseObj<List<StudentOperationListObj>>> getStudentOperationList(@QueryMap Map<String, String> map);
    //提交作业
    @POST("api/SHLGStudent/PostOperationSubmit")
    Call<ResponseObj<BaseObj>> postOperationSubmit(@QueryMap Map<String, String> map,@Body QuerentijiaoBody body );


    //课程表
    @GET ("api/SHLGInformation/GetCurriculumSchedule")
    Call<ResponseObj<List<KechengbiaoObj>>> getCurriculumSchedule(@QueryMap Map<String, String> map);

    //考勤
    @GET ("api/SHLGStudent/GetAttendanceStudent")
    Call<ResponseObj<List<KaoqinObj>>> getKaoqin(@QueryMap Map<String, String> map);

    //扫码签到
    @GET ("api/SHLGStudent/GetSignIn")
    Call<ResponseObj<BaseObj>> getSignIn(@QueryMap Map<String, String> map);

    //下一节课
    @GET ("api/SHLGStudent/GetNextLesson")
    Call<ResponseObj<NextLessonObj>> getNextLesson(@QueryMap Map<String, String> map);
    //Android版本更新
    @GET ("api/SHLGPub/GetVersionUpdate")
    Call<ResponseObj<BanbengengxinObj>> getVersionUpdate(@QueryMap Map<String, String> map);



    /**************************************分割线******************************************/



    //首页类别集合信息
    @GET("api/Information/GetTypeAssemblage")
    Call<ResponseObj<HomeTypeAssemblageObj>> getTypeAssemblage(@QueryMap Map<String, String> map);

    //首页中部图片信息
    @GET("api/Information/GetHomePageImage")
    Call<ResponseObj<HomePageImageObj>> getHomePageImage(@QueryMap Map<String, String> map);

    //首页每日精选
    @GET("api/Information/GetDailybest")
    Call<ResponseObj<HomeDailybestObj>> getDailybest(@QueryMap Map<String, String> map);

    //根据城市名获取ID
    @GET("api/Lib/GetCityID")
    Call<ResponseObj<CityIdObj>> getCityId(@QueryMap Map<String, String> map);

    //获取全部区/县商业圈
    @GET("api/Lib/GetAreaBusinessCircle")
    Call<ResponseObj<List<AreaBusinessCircleObj>>> getAreaBusinessCircle(@QueryMap Map<String, String> map);
    //商家列表(分类)
    @POST("api/MerchantCenter/PostMerchantList")
    Call<ResponseObj<HomeTypeMerchantListObj>> postMerchantList(@QueryMap Map<String, String> map, @Body HomeTypeMerchantListBody body);

    //首页公告信息
    @GET("api/Information/GetAnnouncement")
    Call<ResponseObj<List<HomeAnnouncementObj>>> getAnnouncement(@QueryMap Map<String, String> map);





    //猜你喜欢
    @GET("api/Information/GetGuessYouLike")
    Call<ResponseObj<HomeLikeObj>>getGuessYouLike(@QueryMap Map<String, String> map);



}
