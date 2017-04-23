package com.pizzamoto.xportify_admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dashboard extends AppCompatActivity {

    View profile, worldwide, country;
    String profstat = "0";
    String jsondata, str_country = "";

    String result = "";

    TextView name;

    private static final String TAG_PRODUCT_ID="product_id";
    private static final String TAG_COUNTRY = "country";
    private static final String TAG_PRODUCTNAME = "productname";
    private static final String TAG_PRODUCTDESC ="productdesc";
    private static final String TAG_QUANTITY ="quantity";
    private static final String TAG_PRICE ="price";

    private static final String TAG_FNAME ="first_name";
    private static final String TAG_LNAME ="last_name";



    JSONArray peoples = null;
    ArrayList<HashMap<String, Object>> personList;
    ListView list;

    JSONArray peoples2 = null;
    ArrayList<HashMap<String, Object>> personList2;

    String api_link = "http://creativephil413.com/codeblaze_api/orderdisplay.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String,Object>>();

       // list = (ListView) findViewById(R.id.listView);
        personList2 = new ArrayList<HashMap<String,Object>>();

        name = (TextView)findViewById(R.id.name);

        jsondata =  PreferenceManager.getDefaultSharedPreferences(this).getString("jsondata", "{null}");
        //Toast.makeText(dashboard.this, jsondata, Toast.LENGTH_SHORT).show();

        try {
            str_country = new JSONObject(jsondata).getString("user_id");
            name.setText(new JSONObject(jsondata).getString("first_name"));
            query();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        profile = findViewById(R.id.profile);
        worldwide = findViewById(R.id.worldwide);
        country = findViewById(R.id.country);
    }

    public void showprofile(View v){
        if (profstat.equals("0")) {
            profile.setVisibility(View.VISIBLE);
            profstat = "1";
        }
        else{
            profile.setVisibility(View.GONE);
            profstat = "0";
        }
    }

    public void country(View v){
        country.setBackgroundColor(Color.parseColor("#8022526e"));
        worldwide.setBackgroundColor(Color.parseColor("#3022526e"));
        api_link = "http://creativephil413.com/codeblaze_api/orderdisplay.php";
        query();
    }

    public void worldwide(View v){
        country.setBackgroundColor(Color.parseColor("#3022526e"));
        worldwide.setBackgroundColor(Color.parseColor("#8022526e"));
        api_link = "http://creativephil413.com/codeblaze_api/products_seller.php";

        query();

    }

    private Dialog loadingDialog;
    RequestQueue queue;
    Context context = this;

    public void query() {
        queue = Volley.newRequestQueue(this);
        loadingDialog = ProgressDialog.show(this, "", "Loading...");
        String url = api_link;

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadingDialog.dismiss();


                if (response.equals("[]")){
                    Toast.makeText(context, "No products available yet.", Toast.LENGTH_SHORT).show();
                }
                else{
                    //   Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                    result = response;


                    if (api_link.equals("http://creativephil413.com/codeblaze_api/orderdisplay.php")){
                        showList2();

                    }
                    else{
                        showList();

                    }

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingDialog.dismiss();
                // Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", str_country);



                return params;
            }
        };


        queue.add(req);
    }



    protected void showList(){
        personList.clear();
        try {
            peoples = new JSONArray(result);

            for(int i=0;i<peoples.length();i++) {
                JSONObject c = peoples.getJSONObject(i);



                String product_id = c.getString(TAG_PRODUCT_ID);
                String country = c.getString(TAG_COUNTRY);
                String productname = c.getString(TAG_PRODUCTNAME);
                String productdesc = c.getString(TAG_PRODUCTDESC);
                String quantity = c.getString(TAG_QUANTITY);
                String price = c.getString(TAG_PRICE);




                ;

                HashMap<String, Object> persons = new HashMap<String, Object>();


                persons.put(TAG_PRODUCT_ID, product_id);
                persons.put(TAG_COUNTRY, country);
                persons.put(TAG_PRODUCTNAME, productname);
                persons.put(TAG_PRODUCTDESC, productdesc);
                persons.put(TAG_QUANTITY, quantity);
                persons.put(TAG_PRICE, price);




                personList.add(persons);


            }

//            testAdapter adapter =  new testAdapter(
//                    test.this, contactList,
//                    R.layout.list_item, new String[] {},
//                    new int[] {});
//            // updating listview
//            setListAdapter(adapter);


            ExtendedSimpleAdapter adapter = new ExtendedSimpleAdapter(
                    this, personList, R.layout.list_products,
                    new String[]{},
                    new int[]{}
            );

            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    try {

                        JSONObject single_data = peoples.getJSONObject(position);

                        Intent intent = new Intent(dashboard.this, order.class);
                        //   Toast.makeText(dashboard.this, single_data.toString(), Toast.LENGTH_SHORT).show();

                        PreferenceManager
                                .getDefaultSharedPreferences(dashboard.this)
                                .edit()
                                .putString("singleorder",single_data.toString())
                                .commit();

                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


    public class ExtendedSimpleAdapter extends SimpleAdapter {

        private Context mContext;
        public LayoutInflater inflater=null;
        public ExtendedSimpleAdapter (Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            mContext = context;
            inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi=convertView;
            if(convertView==null)
                vi = inflater.inflate(R.layout.list_products, null);

            HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
            TextView text = (TextView)vi.findViewById(R.id.productname);
            text.setText((String) data.get(TAG_PRODUCTNAME));

            TextView productdesc = (TextView)vi.findViewById(R.id.productdesc);
            productdesc.setText((String)data.get(TAG_PRODUCTDESC));

            TextView country = (TextView)vi.findViewById(R.id.country);
            String str_country = (String)data.get(TAG_COUNTRY);
            country.setText("Product from " + str_country);

            TextView  stock = (TextView)vi.findViewById(R.id.stock);
            String quantity = (String)data.get(TAG_QUANTITY);
            String price = (String)data.get(TAG_PRICE);

            if (quantity.equals("0")){
                quantity = "Out of Stock";
            }
            else{
                quantity = "In Stock";
            }

            stock.setText(quantity+" "+price+" USD");





//            ImageView image=(ImageView)vi.findViewById(R.id.image);
//            int loader = R.drawable.bg;
//            imageLoader.DisplayImage("https://hp.ikonsulta.com/"+data.get(TAG_IMAGE), loader, image);
            return vi;
        }
    }

    protected void showList2(){
        personList2.clear();
        try {
            peoples2 = new JSONArray(result);

            for(int i=0;i<peoples2.length();i++) {
                JSONObject c = peoples2.getJSONObject(i);



                String product_id = c.getString(TAG_PRODUCT_ID);
                String country = c.getString(TAG_COUNTRY);
                String productname = c.getString(TAG_PRODUCTNAME);
                String quantity = c.getString(TAG_QUANTITY);
                String price = c.getString(TAG_PRICE);
                String fname = c.getString(TAG_FNAME);
                String lname = c.getString(TAG_LNAME);




                ;

                HashMap<String, Object> persons = new HashMap<String, Object>();


                persons.put(TAG_PRODUCT_ID, product_id);
                persons.put(TAG_COUNTRY, country);
                persons.put(TAG_PRODUCTNAME, productname);
                persons.put(TAG_QUANTITY, quantity);
                persons.put(TAG_PRICE, price);
                persons.put(TAG_FNAME, fname);
                persons.put(TAG_LNAME, lname);




                personList2.add(persons);


            }

//            testAdapter adapter =  new testAdapter(
//                    test.this, contactList,
//                    R.layout.list_item, new String[] {},
//                    new int[] {});
//            // updating listview
//            setListAdapter(adapter);


            ExtendedSimpleAdapter2 adapter = new ExtendedSimpleAdapter2(
                    this, personList2, R.layout.list_products,
                    new String[]{},
                    new int[]{}
            );

            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    try {

                        JSONObject single_data = peoples2.getJSONObject(position);

//                        Intent intent = new Intent(dashboard.this, order.class);
//                        //   Toast.makeText(dashboard.this, single_data.toString(), Toast.LENGTH_SHORT).show();
//
//                        PreferenceManager
//                                .getDefaultSharedPreferences(dashboard.this)
//                                .edit()
//                                .putString("singleorder",single_data.toString())
//                                .commit();
//
//                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


    public class ExtendedSimpleAdapter2 extends SimpleAdapter {

        private Context mContext;
        public LayoutInflater inflater=null;
        public ExtendedSimpleAdapter2 (Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            mContext = context;
            inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi=convertView;
            if(convertView==null)
                vi = inflater.inflate(R.layout.list_products, null);

            HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
            TextView text = (TextView)vi.findViewById(R.id.productname);
            text.setText((String) data.get(TAG_PRODUCTNAME));

            TextView productdesc = (TextView)vi.findViewById(R.id.productdesc);
            productdesc.setText((String)data.get(TAG_PRODUCTDESC));

            TextView country = (TextView)vi.findViewById(R.id.country);
            String str_country = (String)data.get(TAG_COUNTRY);
            String str_fname = (String)data.get(TAG_FNAME);
            String str_lname = (String)data.get(TAG_LNAME);
            country.setText("Order from " + str_country+", by "+str_fname +" "+ str_lname);

            TextView  stock = (TextView)vi.findViewById(R.id.stock);
            String quantity = (String)data.get(TAG_QUANTITY);
            String price = (String)data.get(TAG_PRICE);



            stock.setText(quantity+" pc/s."+" "+price+" USD");









//            ImageView image=(ImageView)vi.findViewById(R.id.image);
//            int loader = R.drawable.bg;
//            imageLoader.DisplayImage("https://hp.ikonsulta.com/"+data.get(TAG_IMAGE), loader, image);
            return vi;
        }
    }

    public void onBackPressed(){

    }

    public void logout (View v){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Are you sure you want to Logout your account?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(dashboard.this, MainActivity.class);
                        startActivity(intent);

                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void addproducts(View v){
        Intent i = new Intent(dashboard.this, addproduct.class);
        startActivity(i);
    }
}
