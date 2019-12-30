package com.king.sqliPHP.Local;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.king.sqliPHP.R;

public class Update1ActivityLocal extends AppCompatActivity {
    EditText Name, Phone, Email;
    TextView ID;
    Button update1, btnBack;
    DBHelper mydb;
    private String dName, dPhone, dEmail;
    private int found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update1_local);
        setTitle("Update Data Local");
        ID = (TextView) findViewById(R.id.TextIDUpL);
        Name = (EditText) findViewById(R.id.editTextNameUpL);
        Phone = (EditText) findViewById(R.id.editTextPhoneUpL);
        Email = (EditText) findViewById(R.id.editTextEmailUpL);
        update1 = (Button) findViewById(R.id.bttnUpdateL);
        btnBack = (Button) findViewById(R.id.btnToBack);
        mydb = new DBHelper(this);
        found = UpdateActivityLocal.del;
        final Cursor getRecord = mydb.getData(found);
        if (getRecord.moveToNext()) {
            String dId = getRecord.getString(getRecord.getColumnIndex("id"));
            dName = getRecord.getString(getRecord.getColumnIndex("name"));
            dPhone = getRecord.getString(getRecord.getColumnIndex("phone"));
            dEmail = getRecord.getString(getRecord.getColumnIndex("email"));
            ID.setText("ID : " + dId);
            Name.setText(dName);
            Phone.setText(dPhone);
            Email.setText(dEmail);
        }
        /********************************************************************************/

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(Update1ActivityLocal.this, UpdateActivityLocal.class);
                startActivity(Back);

            }
        });
        /********************************************************************************/

        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String phone = Phone.getText().toString();
                String email = Email.getText().toString();
                boolean updateData = mydb.update1Contact(found, name, phone, email);
                if (updateData == true) {
                    Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT).show();
                    Intent activity = new Intent(Update1ActivityLocal.this, UpdateActivityLocal.class);
                    startActivity(activity);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
