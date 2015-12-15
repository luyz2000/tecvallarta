package com.example.agea;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Light on 11/12/2015.
 */

public class FrmBuscar extends ActionBarActivity{

    private ListView lvPerfil;
    private ProgressDialog pDialog;
    ArrayAdapter<String> adapter;
    ArrayList<String> items=new ArrayList<String>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_buscar);
        initVariable();
        getPerfil();
        fillListView(items);
    }

    private void initVariable(){
        lvPerfil = (ListView) findViewById(R.id.lvPerfiles);
    }

    private void fillListView(ArrayList<String> datos){
        ArrayAdapter<String> perfilArray = new ArrayAdapter<String>(this,R.layout.items_buscar, R.id.tvNombrePerfil, datos);
        lvPerfil.setAdapter(perfilArray);
    }

    private void getPerfil(){
        // Tag used to cancel the request
        final String tag_string_req = "req_get_perfil";

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Cargando ...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,AppConfig.URL_GET_PERFIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.d(TAG, "Login Response: " + response.toString());
                pDialog.dismiss();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = false;
                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        JSONArray perfiles = jObj.getJSONArray("Perfiles");
                        for (int i = 0; i < perfiles.length(); i++) {
                            JSONObject NombreP = perfiles.getJSONObject(i);
                            items.add(NombreP.getString("nombre"));
                        }
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", "co");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}

