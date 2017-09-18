package com.webnatics.androidexam.Event;

import com.webnatics.androidexam.models.DataModel;

import java.util.ArrayList;

/**
 * Created by Sanduni on 9/18/17.
 */

public class EventHourlyRate {
    ArrayList<DataModel> hourlyRate;

    public ArrayList<DataModel> getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(ArrayList<DataModel> hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
