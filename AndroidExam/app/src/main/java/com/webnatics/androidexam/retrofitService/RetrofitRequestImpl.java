package com.webnatics.androidexam.retrofitService;

/**
 * Created by Sanduni on 9/18/17.
 */

public interface RetrofitRequestImpl {

    void monthlySalesRequest(String dataType,String year);
    void hourlyActivityCount(String dataType,String year);
}
