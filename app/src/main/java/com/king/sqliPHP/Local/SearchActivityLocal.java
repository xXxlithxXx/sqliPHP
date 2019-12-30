package com.king.sqliPHP.Local;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.king.sqliPHP.R;
import com.king.sqliPHP.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchActivityLocal extends AppCompatActivity {
    public static int del;
    Button bttnSearch, btnBack;
    EditText editSearch;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_local);
        setTitle("Search Local");
        mydb = new DBHelper(this);
        bttnSearch = (Button) findViewById(R.id.bttnSearchL);
        btnBack = (Button) findViewById(R.id.btnToBack);

        editSearch = (EditText) findViewById(R.id.editTextSearchL);
        bttnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String getText = editSearch.getText().toString();
                //      mydb.getDataSearch(getText);
                s(getText);
                editSearch.setText("");
            }
        });
        /********************************************************************************/

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(SearchActivityLocal.this, MainActivityLocal.class);
                startActivity(Back);

            }
        });
        /********************************************************************************/

    }//end of create

    /********************************************************************************/

    public void s(String n) {
        List<Student> data = mydb.getDataSearch(n);
        ListView lview = (ListView) findViewById(R.id.listViewSL);

        //listViewAdapter adapter = new listViewAdapter(this, studentlist);

        ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
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

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String sid = ((TextView) view.findViewById(R.id.idText)).getText().toString();
                String name = ((TextView) view.findViewById(R.id.NameText)).getText().toString();
                String phone = ((TextView) view.findViewById(R.id.PhoneText)).getText().toString();
                String email = ((TextView) view.findViewById(R.id.EmailText)).getText().toString();
                del = Integer.parseInt(sid);
            }
        });

    }
}
