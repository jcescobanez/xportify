package com.pizzamoto.xportify_admin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText et_username, et_password;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText)findViewById(R.id.username);
        et_password = (EditText)findViewById(R.id.password);
    }

    public void register(View v){
        Intent i = new Intent(this, register.class);
        startActivity(i);
    }

    public void login(View v){
        username = et_username.getText().toString();
        password = et_password.getText().toString();

        if (username.equals("")|password.equals("")){
            Toast.makeText(this, "Please fill up all fields.", Toast.LENGTH_SHORT).show();
        }
        else {
            query();
        }
    }



    private Dialog loadingDialog;
    RequestQueue queue;
    Context context = this;

    public void query() {
        queue = Volley.newRequestQueue(this);
        loadingDialog = ProgressDialog.show(this, "", "Loading...");
        String url = "http://creativephil413.com/codeblaze_api/loginseller.php";

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadingDialog.dismiss();
               //Toast.makeText(context, response, Toast.LENGTH_SHORT).show();


                try {
                    String status = new JSONObject(response).getString("status");
                    String message = new JSONObject(response).getString("message");
                    if (status.equals("failed")){
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        PreferenceManager
                                .getDefaultSharedPreferences(context)
                                .edit()
                                .putString("jsondata",response)
                                .commit();
                        Intent i = new Intent (context, dashboard.class);
                        startActivity(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingDialog.dismiss();
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);


                return params;
            }
        };


        queue.add(req);
    }
}
