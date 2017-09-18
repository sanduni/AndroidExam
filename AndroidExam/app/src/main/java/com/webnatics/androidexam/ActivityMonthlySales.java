package com.webnatics.androidexam;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.webnatics.androidexam.Event.EventMonthlySale;
import com.webnatics.androidexam.adapter.MonthlySaleAdapter;
import com.webnatics.androidexam.retrofitService.RetrofitRequest;
import com.webnatics.androidexam.utils.AppHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by Sanduni on 9/18/17.
 */

public class ActivityMonthlySales extends BaseActivity {

    ListView monthlySalesListView;
    ProgressDialog pd;
    private ArrayList<String> dataMonthlySales;
    private ArrayList<String> dataMonths;

    @Subscribe
    public void onEventMainThread(Object event) {
        if (pd != null) {
            pd.dismiss();
        }
        if (event instanceof EventMonthlySale) {

            MonthlySaleAdapter adapter = new MonthlySaleAdapter(this, ((EventMonthlySale) event).getMonthlySales());
            monthlySalesListView.setAdapter(adapter);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_list);
        pd = new ProgressDialog(ActivityMonthlySales.this);
        setupView();
        setupActionBar(AppHelper.ANDROID_EXAM, AppHelper.MONTHLY_SALES, true);
        if (isNetworkConnected()) {
            RetrofitRequest.getInstantiate(getApplicationContext()).monthlySalesRequest(AppHelper.DATA_TYPE, AppHelper.YEAR);
            pd.setMessage("Loading......");
            pd.show();
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG);
        }


    }

    private void setupView() {
        monthlySalesListView = (ListView) findViewById(R.id.monthlyListview);

    }
}
