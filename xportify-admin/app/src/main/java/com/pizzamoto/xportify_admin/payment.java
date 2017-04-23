package com.pizzamoto.xportify_admin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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

public class payment extends AppCompatActivity {

    String[] arr_paymenttype = {"Select Type", "Credit Card", "BitCoin"};

    String name, price, quantity, personalinfo, orderinfo;

    Spinner spinnerpayment;

    String u_username, u_first_name, u_middle_name, u_last_name, u_contactno, u_address, u_city, u_country;

    String s_user_id, s_product_id;




    View viewcc, viewbitcoin;

    TextView tvname, tvprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        price = i.getStringExtra("price");
        quantity = i.getStringExtra("quantity");

        viewcc = findViewById(R.id.viewcc);
        viewbitcoin = findViewById(R.id.viewbitcoin);


        tvname = (TextView)findViewById(R.id.name);
        tvprice = (TextView)findViewById(R.id.price);

        tvname.setText(name);
        tvprice.setText(quantity+ " pc, " + price + " USD");

        spinnerpayment = (Spinner) findViewById(R.id.spinnerpayment);
        parsePayment();

    }


    public void parsePayment() {

        ArrayAdapter<String> adapter_region = new ArrayAdapter<String>(this, R.layout.spinner, arr_paymenttype);
        spinnerpayment.setAdapter(adapter_region);
        spinnerpayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (arr_paymenttype[position].equals("Credit Card")){
                    viewcc.setVisibility(View.VISIBLE);
                    viewbitcoin.setVisibility(View.GONE);


                }
                else if (arr_paymenttype[position].equals("BitCoin")){
                    viewcc.setVisibility(View.GONE);
                    viewbitcoin.setVisibility(View.VISIBLE);


                }
                else {

                    viewcc.setVisibility(View.GONE);
                    viewbitcoin.setVisibility(View.GONE);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }


    public void pay(View v){

        personalinfo =  PreferenceManager.getDefaultSharedPreferences(this).getString("jsondata", "{null}");
        orderinfo =  PreferenceManager.getDefaultSharedPreferences(this).getString("singleorder", "{null}");
        //Toast.makeText(payment.this, personalinfo, Toast.LENGTH_SHORT).show();

        try {
            u_username = new JSONObject(personalinfo).getString("username");
            u_first_name = new JSONObject(personalinfo).getString("first_name");
            u_middle_name = new JSONObject(personalinfo).getString("middle_name");
            u_last_name = new JSONObject(personalinfo).getString("last_name");
            u_contactno = new JSONObject(personalinfo).getString("contactno");
            u_address = new JSONObject(personalinfo).getString("address");
            u_city = new JSONObject(personalinfo).getString("city");
            u_country = new JSONObject(personalinfo).getString("country");
            s_user_id = new JSONObject(orderinfo).getString("user_id");
            s_product_id = new JSONObject(orderinfo).getString("product_id");
            query();
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    private Dialog loadingDialog;
    RequestQueue queue;
    Context context = this;

    public void query() {
        queue = Volley.newRequestQueue(this);
        loadingDialog = ProgressDialog.show(this, "", "Loading...");
        String url = "http://creativephil413.com/codeblaze_api/orderproducts.php";

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
                        Intent i = new Intent(payment.this, dashboard.class);
                        Toast.makeText(payment.this, "Payment success.", Toast.LENGTH_SHORT).show();
                        startActivity(i);
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
                params.put("username", u_username);
                params.put("first_name", u_first_name);
                params.put("middle_name", u_middle_name);
                params.put("last_name", u_last_name);
                params.put("contactno", u_contactno);
                params.put("address", u_address);
                params.put("city", u_city);
                params.put("country", u_country);
                params.put("user_id", s_user_id);
                params.put("product_id", s_product_id);
                params.put("quantity", quantity);
                params.put("price", price);
                params.put("productname", name);


                return params;
            }
        };


        queue.add(req);
    }
}
