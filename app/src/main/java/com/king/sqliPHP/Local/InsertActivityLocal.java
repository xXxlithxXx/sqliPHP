package com.king.sqliPHP.Local;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.king.sqliPHP.R;

public class InsertActivityLocal extends AppCompatActivity {
    DBHelper mydb;
    EditText name, phone, email;
    Button insert, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_local);
        setTitle("ADD Data In Local");
        mydb = new DBHelper(this);
        name = (EditText) findViewById(R.id.editTextNameL);
        phone = (EditText) findViewById(R.id.editTextPhoneL);
        email = (EditText) findViewById(R.id.editTextEmailL);
        insert = (Button) findViewById(R.id.bttnAddL);
        btnBack = (Button) findViewById(R.id.btnToBack);
        insert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //remove the following toast...
                //Toast.makeText(getApplicationContext(), "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();
                String getName = name.getText().toString();
                String getPhone = phone.getText().toString();
                String getEmail = email.getText().toString();
                if (isEmpty(name)) {
                    name.setError("Enter Name");
                } else if (isEmpty(phone)) {
                    phone.setError("phone is requiard");
                } else if (isEmail(email) == false) {
                    email.setError("Enter Correct Email");
                } else {
                    if (mydb.insertContact(getName, getPhone, getEmail)) {
                        Log.v("georgeLog", "Successfully inserted record to db");
                        Toast.makeText(getApplicationContext(),
                                "Inserted:" + getName + ", " + getPhone + "," + getEmail, Toast.LENGTH_SHORT).show();
                        name.setText("");
                        phone.setText("");
                        email.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "DID NOT insert to db :-(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*****************************************************************************************************/

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(InsertActivityLocal.this, MainActivityLocal.class);
                startActivity(Back);

            }
        });
        /*****************************************************************************************************/

    }//end of create

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}
