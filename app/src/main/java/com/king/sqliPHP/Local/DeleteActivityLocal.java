package com.king.sqliPHP.Local;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.king.sqliPHP.R;
import com.king.sqliPHP.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeleteActivityLocal extends AppCompatActivity {
    // public static int dell;
    DBHelper mydb;
    Button delete, btnBack;
    EditText finde;
    ListView lview;
    private ArrayList<Student> studentlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_local);
        setTitle("Delete Local");
        mydb = new DBHelper(this);
        delete = (Button) findViewById(R.id.bttnDeleteL);
        btnBack = (Button) findViewById(R.id.btnToBack);
        finde = (EditText) findViewById(R.id.editFind);
        mydb = new DBHelper(this);
        reloead();
        studentlist = new ArrayList<Student>();

        lview = (ListView) findViewById(R.id.listViewDL);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String sid = ((TextView) view.findViewById(R.id.idText)).getText().toString();
                AlertDialog.Builder alert = new AlertDialog.Builder(DeleteActivityLocal.this);
                alert.setTitle("Delete!");
                alert.setMessage("Do you Sure want delete ?");
                alert.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mydb.deleteContact(Integer.parseInt(sid));
                                reloead();
                            }
                        });

                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DeleteActivityLocal.this, "No", Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(DeleteActivityLocal.this, MainActivityLocal.class);
                startActivity(Back);

            }
        });

        /********************************************************************************/
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int found = Integer.parseInt(finde.getText().toString());


                    Cursor getRecord = mydb.getDataDelete(found);
                    if (!getRecord.moveToNext()) {
                        Toast.makeText(getApplicationContext(), "finish delete", Toast.LENGTH_LONG).show();
                        reloead();

                    } else {
                        Toast.makeText(getApplicationContext(), "no dolete", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    finde.setError("Enter Correct ID");
                }


            }
        });

    }//end of create

    /********************************************************************************/

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    /********************************************************************************/

    void reloead() {
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        lview = (ListView) findViewById(R.id.listViewDL);
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
