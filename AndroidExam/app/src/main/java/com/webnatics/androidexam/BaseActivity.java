package com.webnatics.androidexam;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Sanduni on 9/18/17.
 */

public abstract class BaseActivity extends AppCompatActivity{

    public abstract void onEventMainThread(Object event);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void setupActionBar(String title, String subtitle,
                               boolean homeAsUpEnabled) {
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subtitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        getSupportActionBar().setDisplayShowHomeEnabled(homeAsUpEnabled);
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // we need to toggle here
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
