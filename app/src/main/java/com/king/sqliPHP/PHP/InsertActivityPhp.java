package com.king.sqliPHP.PHP;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.king.sqliPHP.R;

import java.util.HashMap;

public class InsertActivityPhp extends AppCompatActivity {
    public String URLAdd = "https://jsonphp.000webhostapp.com/sqli2020/Add.php";
    HttpParse httpParse = new HttpParse();
    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    EditText name, phone, email;
    Button insert, btnBack;
    private Truncate syn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_php);
        setTitle("ADD Data In PHP");
        this.syn = new Truncate();
        insert = (Button) findViewById(R.id.bttnAddP);
        name = (EditText) findViewById(R.id.editTextNameP);
        phone = (EditText) findViewById(R.id.editTextPhoneP);
        email = (EditText) findViewById(R.id.editTextEmailP);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmpty(name)) {
                    name.setError("Enter Name");
                } else if (isEmpty(phone)) {
                    phone.setError("phone is required");
                } else if (isEmail(email) == false) {
                    email.setError("Enter Correct Email");
                } else {
                    StudentFunction(name.getText().toString(), phone.getText().toString(), email.getText().toString());
                    name.setText("");
                    phone.setText("");
                    email.setText("");
                }
            }
        });
        /***************************************************************/
        btnBack = (Button) findViewById(R.id.btnToBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(InsertActivityPhp.this, MainActivityPHP.class);
                startActivity(Back);

            }
        });
        /***************************************************************/
    }//end of create

    /********************************************************************************/

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    /********************************************************************************/

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    /********************************************************************************/

    public void StudentFunction(final String name, final String phone, final String email) {
        class StudentRegisterClass extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                Toast.makeText(InsertActivityPhp.this, httpResponseMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                hashMap.put("name", params[0]);
                hashMap.put("phone", params[1]);
                hashMap.put("email", params[2]);
                finalResult = httpParse.postRequest(hashMap, URLAdd);
                return finalResult;
            }
        }
        StudentRegisterClass userRegisterFunctionClass = new StudentRegisterClass();
        userRegisterFunctionClass.execute(name, phone, email);
    }
}
