package com.pizzamoto.xportify_admin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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




public class register extends AppCompatActivity {


    EditText et_username, et_email, et_password, et_repeatpassword, et_firstname, et_lastname, et_middlename, et_address,
            et_city, et_country, et_countrycode, et_contactno;

    String username, email, password, repeatpassword, firstname,
            lastname, middlename, address, city, country, countrycode, contactno;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        et_username = (EditText)findViewById(R.id.username);
        et_email = (EditText)findViewById(R.id.email);
        et_password = (EditText)findViewById(R.id.password);
        et_repeatpassword = (EditText)findViewById(R.id.repeatpassword);
        et_firstname = (EditText)findViewById(R.id.firstname);
        et_lastname = (EditText)findViewById(R.id.lastname);
        et_middlename = (EditText)findViewById(R.id.middlename);
        et_address = (EditText)findViewById(R.id.address);
        et_city = (EditText)findViewById(R.id.city);
        et_country = (EditText)findViewById(R.id.country);
        et_countrycode = (EditText)findViewById(R.id.countrycode);
        et_contactno = (EditText)findViewById(R.id.contactno);


    }


    public void register(View v){
        username = et_username.getText().toString();
        email = et_email.getText().toString();
        password = et_password.getText().toString();
        repeatpassword = et_repeatpassword.getText().toString();
        firstname = et_firstname.getText().toString();
        lastname = et_lastname.getText().toString();
        middlename = et_middlename.getText().toString();
        address = et_address.getText().toString();
        city = et_city.getText().toString();
        country = et_city.getText().toString();
        countrycode = et_countrycode.getText().toString();
        contactno = et_contactno.getText().toString();


        if (
                username.equals("")|
                        email.equals("")|
                        password.equals("")|
                        repeatpassword.equals("")|
                        firstname.equals("")|
                        lastname.equals("")|
                        middlename.equals("")|
                        address.equals("")|
                        city.equals("")|
                        country.equals("")|
                        countrycode.equals("")|
                        contactno.equals(""))
        {
            Toast.makeText(context, "Please fill up all fields.", Toast.LENGTH_SHORT).show();
        }
        else{
            if(password.equals(repeatpassword)){
                query();
            }
            else{
                Toast.makeText(context, "Password mismatch.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Dialog loadingDialog;
    RequestQueue queue;
    Context context = this;

    public void query() {
        queue = Volley.newRequestQueue(this);
        loadingDialog = ProgressDialog.show(this, "", "Loading...");
        String url = "http://creativephil413.com/codeblaze_api/registerseller.php";

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadingDialog.dismiss();

                try {
                    String status = new JSONObject(response).getString("status");
                    String message = new JSONObject(response).getString("message");
                    if (status.equals("failed")){
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        finish();
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
                params.put("first_name", firstname);
                params.put("middle_name", middlename);
                params.put("last_name", lastname);
                params.put("contactno", contactno);
                params.put("address", address);
                params.put("city", city);
                params.put("country", country);
                params.put("country_code", countrycode);
                params.put("profile_img", "");
                params.put("username", username);
                params.put("password", password);
                params.put("email_add", email);

                return params;
            }
        };


        queue.add(req);
    }
}
