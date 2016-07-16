package com.rokki.kttsofttest.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.rokki.kttsofttest.DBHelper;
import com.rokki.kttsofttest.R;

import java.util.ArrayList;

import com.rokki.kttsofttest.adapters.TabListAdapter;
import com.rokki.kttsofttest.models.WorkerModel;

public class TableFragment extends Fragment implements View.OnClickListener{


    private TabListAdapter adapter;
    private Button btnAdd;
    private Button btnReset;
    private ListView lvTable;
    private View header;
    final String LOG_TAG = "LOOOOG";

    DBHelper dbHelper;
    ContentValues cv;
    SQLiteDatabase db;

    ArrayList <WorkerModel> data;
    ArrayList <WorkerModel> queryData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_table, container, false);

        btnAdd = (Button) v.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        btnReset = (Button) v.findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(this);

        data = new ArrayList<>();

        adapter = new TabListAdapter(getActivity(), R.layout.item_table, data);


        lvTable = (ListView) v.findViewById(R.id.list_table);
        View viewHeader = getActivity().getLayoutInflater().inflate(R.layout.header, null);
        lvTable.addHeaderView(viewHeader);
        lvTable.setAdapter(adapter);
        lvTable.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(btnReset.getVisibility()==View.VISIBLE){
                    return false;
                }
                WorkerModel wm = data.get(position-1);
                String dep = wm.getDepartment();
                setQuery(dep);
                btnReset.setVisibility(View.VISIBLE);
                return false;
            }
        });

        queryData = new ArrayList<>();

        dbHelper = new DBHelper(getActivity());

        setData();


        return v;
    }

    View createHeader(String text) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.header, null);
//        ((TextView)v.findViewById(R.id.tvText)).setText(text);
        return v;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_add:

                replaceFragment(new WorkerBlankFragment(), R.id.fragment_container);
                break;
            case R.id.btn_reset:
                setData();
                btnReset.setVisibility(View.GONE);
                break;

        }
        dbHelper.close();
    }


    private void replaceFragment(Fragment fragment, int containerId) {


        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, null);
        ft.addToBackStack(null);
        ft.commit();

    }

    private void setData(){
        cv = new ContentValues();

         db = dbHelper.getWritableDatabase();

        Cursor c = db.query("mytable", null, null, null, null, null, null);
        data.clear();

        if (c.moveToFirst()) {

            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int posColIndex = c.getColumnIndex("position");
            int depColIndex = c.getColumnIndex("department");
            int ageColIndex = c.getColumnIndex("age");
            int salaryColIndex = c.getColumnIndex("salary");


            do {
                data.add(new WorkerModel(c.getString(nameColIndex), c.getString(posColIndex), c.getString(depColIndex),
                        c.getString(ageColIndex), c.getString(salaryColIndex)));

            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        adapter = new TabListAdapter(getActivity(), R.layout.item_table, data);
        lvTable.setAdapter(adapter);
        c.close();
    }

    private void setQuery(String dep){
        queryData.clear();
      for(int i=0; i<data.size(); i++){
          if(data.get(i).getDepartment().equals(dep)){
              queryData.add(data.get(i));
              Log.d(LOG_TAG, "0 rows"+queryData.size());
          }
      }

        Log.d(LOG_TAG, "0 data" + data.size());
        adapter = new TabListAdapter(getActivity(), R.layout.item_table, queryData);
        lvTable.setAdapter(adapter);
    }
}
