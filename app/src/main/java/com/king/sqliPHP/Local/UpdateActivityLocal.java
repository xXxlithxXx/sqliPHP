package com.king.sqliPHP.Local;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.king.sqliPHP.R;
import com.king.sqliPHP.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateActivityLocal extends AppCompatActivity {
    public static int del;
    DBHelper mydb;
    ListView lview;
    Button btnBack;
    private ArrayList<Student> studentlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_local);
        setTitle("Update Data Local");
        mydb = new DBHelper(this);
        studentlist = new ArrayList<Student>();
        lview = (ListView) findViewById(R.id.listViewUpL);
        btnBack = (Button) findViewById(R.id.btnToBack);

        load();
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String sid = ((TextView) view.findViewById(R.id.idText)).getText().toString();
                String name = ((TextView) view.findViewById(R.id.NameText)).getText().toString();
                String phone = ((TextView) view.findViewById(R.id.PhoneText)).getText().toString();
                String email = ((TextView) view.findViewById(R.id.EmailText)).getText().toString();
                del = Integer.parseInt(sid);
                Intent searchStudent = new Intent(UpdateActivityLocal.this, Update1ActivityLocal.class);
                startActivity(searchStudent);
            }
        });
        /********************************************************************************/

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(UpdateActivityLocal.this, MainActivityLocal.class);
                startActivity(Back);

            }
        });
        /********************************************************************************/

    }

    void load() {
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        lview = (ListView) findViewById(R.id.listViewUpL);
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
