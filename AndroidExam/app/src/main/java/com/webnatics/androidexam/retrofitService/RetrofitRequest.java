package com.webnatics.androidexam.retrofitService;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.webnatics.androidexam.Event.EventHourlyRate;
import com.webnatics.androidexam.Event.EventMonthlySale;
import com.webnatics.androidexam.models.DataModel;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sanduni on 9/18/17.
 */

public class RetrofitRequest implements RetrofitRequestImpl{

    public static final String TAG = "RETROFIT";
    RetrofitService service;
    ArrayList<DataModel>monthlySalesArray= new ArrayList<>();
    ArrayList<DataModel>monthlyHourlyCountArray= new ArrayList<>();
    /*Model initialization*/


    public RetrofitRequest() {
        service = ServiceGenerator.createService(RetrofitService.class);
    }

    public static Context mContext;

    public static RetrofitRequest getInstantiate(Context context) {
        mContext = context;
        return new RetrofitRequest();
    }
    @Override
    public void monthlySalesRequest(String dataType, String year) {
        Call<Object> mMonthlyListCall = service.getMonthlyList(dataType, year);
        mMonthlyListCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    try {
                        JSONObject mainJSONObj=new JSONObject(json);
                        JSONObject categoryJSONObj=mainJSONObj.getJSONObject("data");
                        Iterator<String> iterator = categoryJSONObj.keys();
                        while (iterator.hasNext()) {
                            String key = iterator.next();

                            Log.i("TAG","key:"+key +"--Value::"+categoryJSONObj.optString(key));
                            DataModel monthlySales= new DataModel();
                            monthlySales.setKey(key);
                            monthlySales.setValue(categoryJSONObj.optString(key));
                            monthlySalesArray.add(monthlySales);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    EventMonthlySale monthlySale=new EventMonthlySale();
                    monthlySale.setMonthlySales(monthlySalesArray);
                    EventBus.getDefault().post(monthlySale);
//
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    @Override
    public void hourlyActivityCount(String dataType, String date) {
        Call<Object> mHourlyActivity = service.getHourlyActivityCount(dataType, date);
        mHourlyActivity.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());
                    try {
                        JSONObject mainJSONObj=new JSONObject(json);
                        JSONObject categoryJSONObj=mainJSONObj.getJSONObject("data");
                        Iterator<String> iterator = categoryJSONObj.keys();
                        while (iterator.hasNext()) {
                            String key = iterator.next();

                            Log.i("TAG","key:"+key +"--Value::"+categoryJSONObj.optString(key));
                            DataModel monthlyHourlyCount= new DataModel();
                            monthlyHourlyCount.setKey(key);
                            monthlyHourlyCount.setValue(categoryJSONObj.optString(key));
                            monthlyHourlyCountArray.add(monthlyHourlyCount);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    EventHourlyRate hourlyRate=new EventHourlyRate();
                    hourlyRate.setHourlyRate(monthlyHourlyCountArray);
                    EventBus.getDefault().post(hourlyRate);
//
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

}
