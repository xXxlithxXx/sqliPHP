package com.king.sqliPHP.PHP;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.king.sqliPHP.Local.DBHelper;
import com.king.sqliPHP.R;
import com.king.sqliPHP.StartActivity;

public class MainActivityPHP extends AppCompatActivity {
    public static int del;
    DBHelper mydb;
    Button View, Add, Update, Delete, Search, btnBack;
    ListView lview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_php);
        setTitle("Main menu PHP");
        mydb = new DBHelper(this);

        View = (Button) findViewById(R.id.btnToView);
        Add = (Button) findViewById(R.id.bttnToAddP);
        Update = (Button) findViewById(R.id.bttnToUpdateP);
        Delete = (Button) findViewById(R.id.bttnToDeleteP);
        Search = (Button) findViewById(R.id.bttnToSearchP);
        btnBack = (Button) findViewById(R.id.btnToBack);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityPHP.this, InsertActivityPhp.class);
                startActivity(updateStudent);
            }
        });
        Add.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityPHP.this, "ADD new student", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /*********************************************************************************************/

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityPHP.this, ViewActivityPHP.class);
                startActivity(updateStudent);
                //finish();
            }
        });
        View.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityPHP.this, "View list of students", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /*****************************************************************************************************/

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityPHP.this, UpdateActivityPhp.class);
                startActivity(updateStudent);
                //finish();
            }
        });
        Update.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityPHP.this, "Update students", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /*****************************************************************************************************/

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityPHP.this, DeleteActivityPhp.class);
                startActivity(updateStudent);
                //finish();
            }
        });
        Delete.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityPHP.this, "Delete students", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /*****************************************************************************************************/

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(MainActivityPHP.this, SearchActivityPhp.class);
                startActivity(updateStudent);
                //finish();
            }
        });
        Search.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityPHP.this, "Search in students", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /*****************************************************************************************************/

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(MainActivityPHP.this, StartActivity.class);
                startActivity(Back);

            }
        });
        btnBack.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivityPHP.this, "Go to beginning", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        /*****************************************************************************************************/
    }//end of create

}
