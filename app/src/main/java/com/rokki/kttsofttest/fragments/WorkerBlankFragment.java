package com.rokki.kttsofttest.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rokki.kttsofttest.DBHelper;
import com.rokki.kttsofttest.R;

public class WorkerBlankFragment extends Fragment {

    private EditText edtName;
    private EditText edtSalary;
    private EditText edtAge;
    private EditText edtPos;
    private Spinner spinner;
    private Button btnSave;

    DBHelper dbHelper;

    String [] data = {"designing", "building", "sales", "architect"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_worker_blank, container, false);

        dbHelper = new DBHelper(getActivity());

        edtName = (EditText) v.findViewById(R.id.edt_full_name);
        edtSalary = (EditText) v.findViewById(R.id.edt_salary);
        edtAge = (EditText) v.findViewById(R.id.edt_age);
        edtPos = (EditText) v.findViewById(R.id.edt_position);
        btnSave = (Button) v.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().toString().equals("") ||  edtSalary.getText().toString().equals("")||
                        edtAge.getText().toString().equals("") || edtPos.getText().toString().equals("")){

                    Snackbar.make(v, "fill all lines", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                }else{


                    addToDB();

                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, new TableFragment(), null);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {


            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        return v;
    }

    public void addToDB(){
        // создаем объект для данных
        ContentValues cv = new ContentValues();



        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cv.put("name", edtName.getText().toString());
        cv.put("salary", edtSalary.getText().toString());
        cv.put("department", spinner.getSelectedItem().toString());
        cv.put("position", edtPos.getText().toString());
        cv.put("age", edtAge.getText().toString());
        // вставляем запись и получаем ее ID
        long rowID = db.insert("mytable", null, cv);

        dbHelper.close();
    }



}
