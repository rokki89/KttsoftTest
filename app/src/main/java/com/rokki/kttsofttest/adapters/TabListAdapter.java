package com.rokki.kttsofttest.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rokki.kttsofttest.R;

import java.util.ArrayList;

import com.rokki.kttsofttest.models.WorkerModel;

/**
 * Created by pcc on 15.07.2016.
 */
public class TabListAdapter extends BaseAdapter {


    private LayoutInflater lInflater;
    private Context context;
    private ArrayList<WorkerModel> models;

    public TabListAdapter(Context context, int item_table, ArrayList<WorkerModel> models) {
        this.context = context;
        this.models = models;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public WorkerModel getWorker(int position) {
        return ((WorkerModel) getItem(position));
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_table, parent, false);
        }

        WorkerModel model = getWorker(position);

        FrameLayout ll = (FrameLayout) view.findViewById(R.id.fl_item);

        switch (model.getDepartment()){
            case "designing":
            ll.setBackgroundColor(Color.parseColor("#E1BEE7"));
                    break;
            case "building":
            ll.setBackgroundColor(Color.parseColor("#F8BBD0"));
                    break;
            case "sales":
            ll.setBackgroundColor(Color.parseColor("#FFCDD2"));
                    break;
            case "architect":
            ll.setBackgroundColor(Color.parseColor("#B3E5FC"));
                    break;
        }


        ((TextView) view.findViewById(R.id.tv_name)).setText(model.getName());
        ((TextView) view.findViewById(R.id.tv_position)).setText(model.getPosition());
        ((TextView) view.findViewById(R.id.tv_department)).setText(model.getDepartment());
        return view;
    }
}
