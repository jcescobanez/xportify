package com.pizzamoto.xportify_admin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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

public class addproduct extends AppCompatActivity {

    EditText et_productname, et_productdesc, et_quantity, et_price;


    String productname, productdesc, quantity, price, personalinfo, country = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        et_productname = (EditText)findViewById(R.id.productname);
        et_productdesc = (EditText)findViewById(R.id.productdesc);
        et_quantity = (EditText)findViewById(R.id.quantity);
        et_price = (EditText)findViewById(R.id.price);

        personalinfo =  PreferenceManager.getDefaultSharedPreferences(this).getString("jsondata", "{null}");
        try {
            country = new JSONObject(personalinfo).getString("country");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void add(View v){
        productname = et_productname.getText().toString();
        productdesc = et_productdesc.getText().toString();
        quantity = et_quantity.getText().toString();
        price = et_price.getText().toString();


        if(productname.equals("")|productdesc.equals("")|quantity.equals("")|price.equals("")){
            Toast.makeText(addproduct.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
        }
        else{
            query();
        }
    }

    private Dialog loadingDialog;
    RequestQueue queue;
    Context context = this;

    public void query() {
        queue = Volley.newRequestQueue(this);
        loadingDialog = ProgressDialog.show(this, "", "Loading...");
        String url = "http://creativephil413.com/codeblaze_api/addproductsseller.php";

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
                params.put("productname", productname);
                params.put("productdesc", productdesc);
                params.put("quantity", quantity);
                params.put("price", price);
                params.put("country", "philippines");

                return params;
            }
        };

        queue.add(req);
    }
}
