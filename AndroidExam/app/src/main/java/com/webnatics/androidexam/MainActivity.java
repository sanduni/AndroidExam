package com.webnatics.androidexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView monthlySalesTxtView;
    TextView hourlyRateTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        setupUiView();
    }

    private void setupUiView(){
        monthlySalesTxtView = (TextView)findViewById(R.id.monthlySalesTxt);
        hourlyRateTxtView = (TextView)findViewById(R.id.hourlyActivityCount);
        monthlySalesTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityMonthlySales = new Intent(MainActivity.this,ActivityMonthlySales.class);
                startActivity(activityMonthlySales);
            }
        });
        hourlyRateTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityHourlyRate = new Intent(MainActivity.this,ActivityHourlyCount.class);
                startActivity(activityHourlyRate);
            }
        });
    }
}
