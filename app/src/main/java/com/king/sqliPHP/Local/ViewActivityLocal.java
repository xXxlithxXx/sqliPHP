package com.king.sqliPHP.Local;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.king.sqliPHP.R;
import com.king.sqliPHP.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewActivityLocal extends AppCompatActivity {
    public static int del;
    DBHelper mydb;
    Button btnBack;
    ListView lview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_local);
        setTitle("Local Database List");
        mydb = new DBHelper(this);
        reload();
        lview = (ListView) findViewById(R.id.listViewL);
        btnBack = (Button) findViewById(R.id.btnToBack);


        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        lview = (ListView) findViewById(R.id.listViewL);
        List<Student> data = mydb.getAllcontactDetails();
        for (Student val : data) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", String.valueOf(val.getId()));
            map.put("name", val.getName());
            map.put("phone", val.getPhone());
            map.put("email", val.getEmail());
            Items.add(map);
        }

        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows, new String[]{"id", "name", "phone", "email"},
                new int[]{R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});

        lview.setAdapter(myadapter);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(ViewActivityLocal.this, MainActivityLocal.class);
                startActivity(Back);
                //finish();
            }
        });

    }


    void reload() {
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        lview = (ListView) findViewById(R.id.listViewL);
        List<Student> data = mydb.getAllcontactDetails();
        for (Student val : data) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", String.valueOf(val.getId()));
            map.put("name", val.getName());
            map.put("phone", val.getPhone());
            map.put("email", val.getEmail());
            Items.add(map);
        }

        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows, new String[]{"id", "name", "phone", "email"},
                new int[]{R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});

        lview.setAdapter(myadapter);
    }

}
