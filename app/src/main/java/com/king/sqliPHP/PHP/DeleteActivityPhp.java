package com.king.sqliPHP.PHP;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DeleteActivityPhp extends AppCompatActivity {
    public String URLDelete = "https://jsonphp.000webhostapp.com/sqli2020/Delete.php";
    EditText id;
    HttpParse httpParse = new HttpParse();
    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    Button delete, btnBack;
    ListView listViewDP;
    ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
    private Truncate syn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Delete PHP");
        syn = new Truncate();
        getJSON(ViewActivityPHP.URLView);
        setContentView(R.layout.activity_delete_php);
        delete = (Button) findViewById(R.id.bttnDeleteP);
        id = (EditText) findViewById(R.id.editFindP);


        syn = new Truncate();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Log.v("showSingleTest", " delete method  ");

                    if (isEmpty(id)) {
                        Log.v("showSingleTest", " delete empty id");

                        id.setError("Enter Corrcet ID");
                    } else
                        StudentDelete(id.getText().toString());

                } catch (Exception e) {


                }
            }

        });
        /***************************************************************/
        btnBack = (Button) findViewById(R.id.btnToBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(DeleteActivityPhp.this, MainActivityPHP.class);
                startActivity(Back);

            }
        });
        /***************************************************************/

    }//end of create

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
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
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            /********************************************************************************/

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

    /********************************************************************************/

    private void loadIntoListView(final String json) throws JSONException {
        Items.clear();
        JSONArray jsonArray = new JSONArray(json);
        String[] heroes = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            JSONObject obj = jsonArray.getJSONObject(i);
            String idd = heroes[i] = obj.getString("id");
            map.put("id", idd);
            String name = heroes[i] = obj.getString("name");
            map.put("name", name);
            String phone = heroes[i] = obj.getString("phone");
            map.put("phone", phone);
            String email = heroes[i] = obj.getString("email");
            map.put("email", email);
            Items.add(map);
        }
        listViewDP = (ListView) findViewById(R.id.listViewDP);
        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows, new String[]{"id", "name", "phone", "email"},
                new int[]{R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});
        listViewDP.setAdapter(myadapter);
        listViewDP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String sid = ((TextView) view.findViewById(R.id.idText)).getText().toString();
                AlertDialog.Builder alert = new AlertDialog.Builder(DeleteActivityPhp.this);
                alert.setTitle("Delete!");
                alert.setMessage("Do you Sure want delete ?");
                alert.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                StudentDelete(sid);
                            }
                        });
                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DeleteActivityPhp.this, "No", Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();
            }
        });
    }

    /********************************************************************************/

    public void StudentDelete(final String id1) {
        class StudentDeleteClass extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.v("showSingleTest", " delete method login_id " + id1);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                Log.v("showSingleTest", " delete method login_id2 " + id1);

                Toast.makeText(DeleteActivityPhp.this, httpResponseMsg, Toast.LENGTH_LONG).show();
                if (httpResponseMsg.equalsIgnoreCase("Record Deleted Successfully")) {
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            protected String doInBackground(String... params) {
                Log.v("showSingleTest", " delete method login_id3 " + id1);
                // Sending STUDENT id.
                hashMap.put("id", params[0]);
                finalResult = httpParse.postRequest(hashMap, URLDelete);
                return finalResult;
            }
        }
        StudentDeleteClass studentDeleteClass = new StudentDeleteClass();
        studentDeleteClass.execute(id1);
    }
}
