package com.sk.lgdx.network;

import com.sk.lgdx.base.BaseObj;
import com.sk.lgdx.base.ResponseObj;
import com.sk.lgdx.module.home.network.response.BanbengengxinObj;
import com.sk.lgdx.module.home.network.response.CitySearchObj;
import com.sk.lgdx.module.my.network.request.UploadImgBody;
import com.sk.lgdx.module.my.network.response.ProvinceObj;
import com.sk.lgdx.network.response.FenXiangObj;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {
    @GET("api/HomePage/GetProductGroupOrderShow")
    Call<ResponseObj<BaseObj>> tuanGouSureOrder(@QueryMap Map<String, String> map);

    //退出登录
    @GET("api/SHLGPub/GetLogOut")
    Call<ResponseObj<BaseObj>> getLogOut(@QueryMap Map<String, String> map);
    //图片上传
    @POST("api/SHLGPub/PostUploadFileBase64")
    Call<ResponseObj<BaseObj>> uploadImg(@QueryMap Map<String, String> map, @Body UploadImgBody body);

    //发送邮件验证码
    @GET("api/SHLGPub/GetEmail")
    Call<ResponseObj<BaseObj>> getSMSCode(@QueryMap Map<String, String> map);

    //上传文件
    @POST("api/SHLGPub/PostUploadFile")
    Call<ResponseObj<BaseObj>> postUploadFile(@QueryMap Map<String, String> map, @Part MultipartBody.Part file);

    @Multipart
    @POST("api/SHLGPub/PostUploadFile")
    Call<ResponseObj<BaseObj>> uploadFile(@QueryMap Map<String, String> map, @Part MultipartBody.Part file);




    //获取分享信息
    @GET ("api/SHLGPub/GetShareInformations")
    Call<ResponseObj<FenXiangObj>> getShareInformations(@QueryMap Map<String, String> map);



    //Android版本更新
    @GET ("api/SHLGPub/GetVersionUpdate")
    Call<ResponseObj<BanbengengxinObj>> getVersionUpdate(@QueryMap Map<String, String> map);

/**********************************分割线**************************************/

    //支付回传地址
    @GET("api/Lib/GetPayInfo")
    Call<ResponseObj<BaseObj>> paymentURL(@QueryMap Map<String, String> map);

    //获取省份
    @GET("api/Lib/GetProvince")
    Call<ResponseObj<List<ProvinceObj>>> getProvince(@QueryMap Map<String, String> map);

    //获取市
    @GET("api/Lib/GetCity")
    Call<ResponseObj<List<ProvinceObj>>> getCity(@QueryMap Map<String, String> map);

    //获取区/县
    @GET("api/Lib/GetArea")
    Call<ResponseObj<List<ProvinceObj>>> getArea(@QueryMap Map<String, String> map);

    //申请合伙人
    @GET("api/UserBase/GetApplyForPartner")
    Call<ResponseObj<BaseObj>> getApplyForPartner(@QueryMap Map<String, String> map);

    //获取支付信息
    @GET("api/Lib/GetPayInfo")
    Call<ResponseObj<BaseObj>> PayInfo(@QueryMap Map<String, String> map);

    //获取所有城市
    @GET("api/Lib/GetAllCity")
    Call<ResponseObj<List<CitySearchObj>>> getAllCity(@QueryMap Map<String, String> map);


    //保存定位信息
    @GET("api/Lib/GetLatLng")
    Call<ResponseObj<BaseObj>> getLatLng(@QueryMap Map<String, String> map);

    //获取分享信息
    @GET("api/Lib/GetShareInformation")
    Call<ResponseObj<FenXiangObj>> fenXiang(@QueryMap Map<String, String> map);





}
