package com.king.sqliPHP.PHP;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

public class UpdateActivityPhp extends AppCompatActivity {
    public static int upid;
    public static String upname, upphone, upemail;
    Button btnBack;
    ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
    private ListView listViewP;
    private Truncate syn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_php);
        setTitle("Update Data PHP");
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        this.syn = new Truncate();
        listViewP = (ListView) findViewById(R.id.listViewUpP);
        getJSON(syn.URL + "view.php");
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
                try {
                    loadIntoListView(s);
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

    /********************************************************************************/
    private void loadIntoListView(String json) throws JSONException {
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
        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows, new String[]{"id", "name", "phone", "email"},
                new int[]{R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});
        listViewP.setAdapter(myadapter);
        listViewP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String sid = ((TextView) view.findViewById(R.id.idText)).getText().toString();
                upname = ((TextView) view.findViewById(R.id.NameText)).getText().toString();
                upphone = ((TextView) view.findViewById(R.id.PhoneText)).getText().toString();
                upemail = ((TextView) view.findViewById(R.id.EmailText)).getText().toString();
                upid = Integer.parseInt(sid);
                /********************************************************************************/
                Intent searchStudent = new Intent(UpdateActivityPhp.this, Update1ActivityPhp.class);
                startActivity(searchStudent);
                //finish();
            }
        });
        /***************************************************************/
        btnBack = (Button) findViewById(R.id.btnToBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(UpdateActivityPhp.this, MainActivityPHP.class);
                startActivity(Back);
            }
        });
        /***************************************************************/
    }
}//end of create
