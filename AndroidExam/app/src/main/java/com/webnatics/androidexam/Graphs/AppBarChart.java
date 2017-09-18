package com.webnatics.androidexam.Graphs;

import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.webnatics.androidexam.R;
import com.webnatics.androidexam.models.DataModel;
import com.webnatics.androidexam.utils.ColorPalet;

import java.util.ArrayList;

/**
 * Created by Sanduni on 9/18/17.
 */

public class AppBarChart {

    BarChart drawingChart;
    ArrayList<BarEntry> yAxisList = new ArrayList<>();
    ArrayList<String> xAxisList = new ArrayList<>();
    ArrayList<DataModel> dataArrayList;
    int entryPosition = 0;

    public AppBarChart(BarChart myDraweingChart, ArrayList<DataModel> dataArray) {

        this.dataArrayList = dataArray;
        this.drawingChart = myDraweingChart;

    }

    public void drawBarGraph() {

        for (DataModel myData : dataArrayList) {
            yAxisList.add(new BarEntry(Float.valueOf(myData.getValue()), entryPosition));
            xAxisList.add(myData.getKey());
            entryPosition++;
        }
        BarDataSet dataset = new BarDataSet(yAxisList, "Activity Count");
        dataset.setColor(ColorPalet.BAR_CHART_COLOR[0]);
        BarData data = new BarData(xAxisList, dataset);

        XAxis xAxis = drawingChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis yAxisRight = drawingChart.getAxisRight();
        yAxisRight.setEnabled(false);
        drawingChart.setData(data);
        drawingChart.animateY(5000);
    }
}
