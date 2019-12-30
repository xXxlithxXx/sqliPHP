package com.king.sqliPHP.Local;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.king.sqliPHP.R;
import com.king.sqliPHP.StartActivity;

public class MainActivityLocal extends AppCompatActivity {
    public static int del;
    DBHelper mydb;
    Button View, ToAddL, ToUpdateL, ToDeleteL, ToSearchL, btnBack;
    ListView lview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_local);
        setTitle("Main menu Local");
        mydb = new DBHelper(this);

        View = (Button) findViewById(R.id.btnToView);

        ToAddL = (Button) findViewById(R.id.bttnToAddL);
        ToUpdateL = (Button) findViewById(R.id.bttnToUpdateL);
        ToDeleteL = (Button) findViewById(R.id.bttnToDeleteL);
        ToSearchL = (Button) findViewById(R.id.bttnToSearchL);
        btnBack = (Button) findViewById(R.id.btnToBack);
        /********************************************************************************/

        ToAddL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityLocal.this, InsertActivityLocal.class);
                startActivity(updateStudent);
            }
        });
        ToAddL.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityLocal.this, "ADD new Student", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /********************************************************************************/

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityLocal.this, ViewActivityLocal.class);
                startActivity(updateStudent);
                //finish();
            }
        });
        View.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityLocal.this, "View list of students", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /********************************************************************************/

        ToUpdateL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityLocal.this, UpdateActivityLocal.class);
                startActivity(updateStudent);
                //finish();
            }
        });
        ToUpdateL.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityLocal.this, "Update students", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /********************************************************************************/

        ToDeleteL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityLocal.this, DeleteActivityLocal.class);
                startActivity(updateStudent);
                //finish();
            }
        });
        ToDeleteL.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityLocal.this, "Delete students", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /********************************************************************************/

        ToSearchL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityLocal.this, SearchActivityLocal.class);
                startActivity(updateStudent);
                //finish();
            }
        });
        ToSearchL.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityLocal.this, "Search in students", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /********************************************************************************/

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(MainActivityLocal.this, StartActivity.class);
                startActivity(Back);

            }
        });
        btnBack.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityLocal.this, "Go to beginning", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /********************************************************************************/

    }// end of create

}
