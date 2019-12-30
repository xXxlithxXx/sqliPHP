package com.king.sqliPHP.PHP;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.king.sqliPHP.R;

import java.util.HashMap;

public class Update1ActivityPhp extends AppCompatActivity {
    public String URLupdate = "https://jsonphp.000webhostapp.com/sqli2020/Update.php";
    HttpParse httpParse = new HttpParse();
    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    EditText Name, Phone, Email;
    TextView ID;
    Truncate syn;
    Button update1P, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update1_php);
        setTitle("Update one record PHP");
        ID = (TextView) findViewById(R.id.TextIDUpP);
        Name = (EditText) findViewById(R.id.editTextNameUpP);
        Phone = (EditText) findViewById(R.id.editTextPhoneUpP);
        Email = (EditText) findViewById(R.id.editTextEmailUpP);
        update1P = (Button) findViewById(R.id.bttnUpdatePhp);
        btnBack = (Button) findViewById(R.id.btnToBack);

        syn = new Truncate();
        final String IDD = String.valueOf(UpdateActivityPhp.upid);
        ID.setText(IDD);
        Name.setText(UpdateActivityPhp.upname);
        Phone.setText(UpdateActivityPhp.upphone);
        Email.setText(UpdateActivityPhp.upemail);

        Log.v("showSingleTest", " delete method login_id3 " + ID.getText() + Name.getText() + Phone.getText() + Email.getText());

        update1P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentRecordUpdate(ID.getText().toString(), Name.getText().toString(), Phone.getText().toString(), Email.getText().toString());
                Log.v("showSingleTest", " delete method login_id4 " + ID.getText() + Name.getText() + Phone.getText() + Email.getText());
            }
        });
        /***************************************************************/
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(Update1ActivityPhp.this, UpdateActivityPhp.class);
                finish();
                startActivity(Back);
            }
        });
        /***************************************************************/
    }

    // Method to Update User Record.
    public void StudentRecordUpdate(final String ID, final String name, final String phone, final String emai) {
        class StudentRecordUpdateClass extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                Toast.makeText(Update1ActivityPhp.this, httpResponseMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("id", params[0]);
                hashMap.put("name", params[1]);
                hashMap.put("phone", params[2]);
                hashMap.put("email", params[3]);
                finalResult = httpParse.postRequest(hashMap, URLupdate);
                return finalResult;
            }
        }
        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();
        studentRecordUpdateClass.execute(ID, name, phone, emai);
    }
}//end of create
