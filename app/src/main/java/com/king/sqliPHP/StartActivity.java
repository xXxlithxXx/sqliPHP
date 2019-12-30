package com.king.sqliPHP;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.king.sqliPHP.Local.DBHelper;
import com.king.sqliPHP.Local.MainActivityLocal;
import com.king.sqliPHP.PHP.MainActivityPHP;
import com.king.sqliPHP.PHP.Truncate;
import com.king.sqliPHP.PHP.ViewActivityPHP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StartActivity extends AppCompatActivity {
    public int count = 0;
    //Button ToPhp,ToLocal,Truncate;
    DBHelper mydb;
    Cursor cursor;
    Truncate truncate;
    Button local, php, synchronize, changeColor;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setTitle("sqlite and PHP");

        /* it's implied that it's safe to use the SDK_INT constant on Android 2.0 and above
         to wrap calls to newer APIs, without using reflection.
        Using the SDK_INT to prevent older system's from executing new APIs works in this way on Android 2.0
         (API level 5) and higher only. Older versions will encounter a runtime exception.*/
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        constraintLayout = findViewById(R.id.startColors);
        mydb = new DBHelper(this);
        truncate = new Truncate();
        /*************************************************************************************/
        local = (Button) findViewById(R.id.local);
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(StartActivity.this, MainActivityLocal.class);
                startActivity(updateStudent);
            }
        });
        /*************************************************************************************/
        php = (Button) findViewById(R.id.php);
        php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateStudent = new Intent(StartActivity.this, MainActivityPHP.class);
                startActivity(updateStudent);
            }
        });
        /*************************************************************************************/
        synchronize = (Button) findViewById(R.id.synchronize);
        synchronize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJSON(ViewActivityPHP.URLView);
                syntoonline();
            }
        });
        /*************************************************************************************/
        constraintLayout = findViewById(R.id.startColors);
        changeColor = (Button) findViewById(R.id.changeCollors);
        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colors();
            }
        });
    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), "The data match", Toast.LENGTH_SHORT).show();
                try {
                    loadData2(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {


                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }
                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    /*************************************************************************************/
    private void loadData2(String json) throws JSONException {
        //SQLiteDatabase db = mydb.getReadableDatabase();
        JSONArray jsonArray = new JSONArray(json);
        int ii = 1;
        String nameL = "";
        String nameP = "";
        String phoneP = "";
        String emailP = "";
        //String[] heroes = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            nameP = obj.get("name").toString();
            phoneP = obj.get("phone").toString();
            emailP = obj.get("email").toString();
            System.out.println("ii=" + ii);

            System.out.println("namePHP = " + nameP);
            cursor = mydb.getAllData();

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                nameL = cursor.getString(1);
                System.out.println("nameLocal = " + nameL);
                System.out.println("===============");
                if (nameP.equals(nameL)) {
                    System.out.println(" yeah ");
                    System.out.println(nameL + " " + nameP);
                    ii = 0;

                }
                cursor.moveToNext();
            }
            if (ii == 1) {
                System.out.println("'" + nameL + "'");
                mydb.insertContact(nameP, phoneP, emailP);
                System.out.println();
            }

            ii = 1;
            System.out.println("=/=/=/=/=/=/=");
        }

    }

    /*************************************************************************************/
    private void syntoonline() {
        truncate.doInBackground("Truncate");
        cursor = mydb.getAllData();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            truncate.StudentFunction(name, phone, email);
            cursor.moveToNext();
        }
    }

    private void colors() {
        if (count == 0) {
            constraintLayout.setBackgroundColor(Color.LTGRAY);
            php.setBackgroundColor(Color.GREEN);
            synchronize.setTextColor(Color.RED);
            local.setBackgroundColor(Color.YELLOW);
            count++;
        } else if (count == 1) {
            constraintLayout.setBackgroundColor(Color.GREEN);
            php.setBackgroundColor(Color.MAGENTA);
            synchronize.setTextColor(Color.YELLOW);
            local.setBackgroundColor(Color.LTGRAY);
            count++;
        } else if (count == 2) {
            constraintLayout.setBackgroundColor(Color.MAGENTA);
            php.setBackgroundColor(Color.WHITE);
            synchronize.setTextColor(Color.GREEN);
            local.setBackgroundColor(Color.BLUE);
            count++;
        } else if (count == 3) {
            constraintLayout.setBackgroundColor(Color.YELLOW);
            php.setBackgroundColor(Color.BLUE);
            synchronize.setTextColor(Color.LTGRAY);
            local.setBackgroundColor(Color.GREEN);
            count++;
        } else {
            constraintLayout.setBackgroundColor(Color.WHITE);
            php.setBackgroundColor(Color.RED);
            synchronize.setTextColor(Color.rgb(102, 53, 0));
            local.setBackgroundColor(Color.rgb(0, 184, 212));
            count = 0;
        }
    }
}
