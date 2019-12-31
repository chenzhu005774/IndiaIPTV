package com.amt.indiaiptv.utils.net;


import com.amt.indiaiptv.utils.bean.CategoryBean;

import java.util.Map;

import javax.xml.transform.Result;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * des:ApiService
 */
public interface ApiService {

    @GET("福利/10/1")
    Call<Result> getMezi();
    /**
     * 删除历史记录
     *
     * @param uid
     * @param sourceCodes
     * @return
     */
    @GET("delUserPlayHistory.ca")
    Call<ResponseBody> deleteRecord(
            @Query("uid") String uid, @Query("sourceCodes") String sourceCodes);

    @POST("EntranceGuardes/app/appOpen_pushdDataToApp.action")
    Call<ResponseBody> upload(@Part("userId") RequestBody description,
                              @Part MultipartBody.Part file);

    @POST("queryCategory")
    Call<ResponseBody> test(@Body CategoryBean parms);
//    CategoryBean categoryBean =  new  CategoryBean();
//    categoryBean.setCategoryId("123");
//    categoryBean.setCopid("12313");
//    categoryBean.setCustomerId("213123");
//    Api.getDefault().test(categoryBean).enqueue(new Callback<ResponseBody>() {
//        @Override
//        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//            LogUtils.i(response.body() .toString() +" --" +response.message());
//        }
//        @Override
//        public void onFailure(Call<ResponseBody> call, Throwable t) {
//            LogUtils.i( " --" +t);
//        }
//    });

    @POST("transporter/update")
    Call<ResponseBody> changBind(@Body Map<String, Object> params);
   //    在 Post 请求中，我们通过 @Body 注解来标记需要传递给服务器的对象


    //获取首页
    @GET("apk/template/getPage")
    Call<ResponseBody> getPage(@Query("dataCode") String dataCode, @Query("orgCode") String orgCode);


    //   获取token
    @GET("apk/auth/getToken")
    Call<ResponseBody> getLoginToken();

    //  绑定机顶盒账号与唯一标识符
    @GET("apk/auth/bindMacToAccount")
    Call<ResponseBody> bindMacToAccount(@Query("mac") String mac);

    @POST("apk/auth/authentication")
    Call<ResponseBody> login(@Body Map<String, String> params);

    @GET("apk/channel/findPage")
    Call<ResponseBody> getChannel(@Query("dataCode") String dataCode, @Query("pageNumber") int pageNumber, @Query("pagesize") int pagesize);

}
