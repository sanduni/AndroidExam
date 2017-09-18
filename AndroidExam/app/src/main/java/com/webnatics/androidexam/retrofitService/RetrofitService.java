package com.webnatics.androidexam.retrofitService;



import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Sanduni on 9/18/17.
 */

public interface RetrofitService {

    /*
     Monthly Sales Request
     */
    @FormUrlEncoded
    @POST("android-exam/list-data")
    Call<Object> getMonthlyList(@Field("data_type") String data_type, @Field("year") String year);
    @FormUrlEncoded
    @POST("android-exam/list-data")
    Call<Object> getHourlyActivityCount(@Field("data_type") String data_type, @Field("date") String date);
}
