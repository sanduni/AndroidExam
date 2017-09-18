package com.webnatics.androidexam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.webnatics.androidexam.R;
import com.webnatics.androidexam.models.DataModel;

import java.util.ArrayList;

/**
 * Created by Sanduni on 9/18/17.
 */

public class MonthlySaleAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DataModel> monthlySales;
    private LayoutInflater inflater;

    public MonthlySaleAdapter(Context context, ArrayList<DataModel> monthlySales) {

        this.context = context;
        this.monthlySales = monthlySales;
    }

    @Override
    public int getCount() {
        return monthlySales.size();
    }

    @Override
    public Object getItem(int i) {
        return monthlySales.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.adapter_monthly_listview_item, null);
        TextView textViewMonth = (TextView) convertView.findViewById(R.id.month);
        TextView textMonthVal = (TextView) convertView.findViewById(R.id.val);
        textViewMonth.setText(monthlySales.get(i).getKey());
        textMonthVal.setText(monthlySales.get(i).getValue());
        return convertView;
    }


}
