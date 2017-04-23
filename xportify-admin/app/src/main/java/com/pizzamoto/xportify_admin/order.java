package com.pizzamoto.xportify_admin;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class order extends AppCompatActivity {

    TextView quantity, name, country, description, price;
    int qty = 1;

    String singledata, str_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        quantity = (TextView)findViewById(R.id.quantity);
        name = (TextView)findViewById(R.id.name);
        country = (TextView)findViewById(R.id.country);
        description = (TextView)findViewById(R.id.description);
        price = (TextView)findViewById(R.id.price);

        singledata =  PreferenceManager.getDefaultSharedPreferences(this).getString("singleorder", "{null}");


        try {
            name.setText(new JSONObject(singledata).getString("productname"));
            country.setText(new JSONObject(singledata).getString("country"));
            description.setText(new JSONObject(singledata).getString("productdesc"));
            price.setText(new JSONObject(singledata).getString("price"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void plus(View v){
        qty = qty + 1;
        quantity.setText(String.valueOf(qty));

        try {
            double db_price = Double.parseDouble(new JSONObject(singledata).getString("price"));
            double db_qty = Double.parseDouble(String.valueOf(qty));

            double total = db_price * db_qty;

            price.setText(String.valueOf(total)+" USD");
            str_price = String.valueOf(total);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void minus(View v){

        if (qty != 1) {

            qty = qty - 1;
            quantity.setText(String.valueOf(qty));

            try {
                double db_price = Double.parseDouble(new JSONObject(singledata).getString("price"));
                double db_qty = Double.parseDouble(String.valueOf(qty));

                double total = db_price * db_qty;

                price.setText(String.valueOf(total)+" USD");


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void pay (View v){
        Intent i = new Intent(this, payment.class);
        i.putExtra("name", name.getText().toString());
        i.putExtra("price", price.getText().toString());
        i.putExtra("quantity", quantity.getText().toString());
        startActivity(i);
    }
}

