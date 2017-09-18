package com.webnatics.androidexam;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.webnatics.androidexam.Event.EventHourlyRate;
import com.webnatics.androidexam.Graphs.AppBarChart;
import com.webnatics.androidexam.retrofitService.RetrofitRequest;
import com.webnatics.androidexam.utils.AppHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by Sanduni on 9/18/17.
 */

public class ActivityHourlyCount extends BaseActivity {
    BarChart mBarChart;
    ProgressDialog pd;

    @Subscribe
    public void onEventMainThread(Object event) {
        if (pd != null) {
            pd.dismiss();
        }
        if (event instanceof EventHourlyRate) {

            AppBarChart barChart = new AppBarChart(mBarChart, ((EventHourlyRate) event).getHourlyRate());
            barChart.drawBarGraph();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);
        setupActionBar(AppHelper.ANDROID_EXAM, AppHelper.HOURLY_RATE, true);
        pd = new ProgressDialog(ActivityHourlyCount.this);
        if (isNetworkConnected()) {
            RetrofitRequest.getInstantiate(this).hourlyActivityCount(AppHelper.DATA_TYPE_HOURLY_RATE, AppHelper.DATE);
            pd.setMessage("Loading......");
            pd.show();
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG);
        }
        mBarChart = (BarChart) findViewById(R.id.chart);


    }
}

