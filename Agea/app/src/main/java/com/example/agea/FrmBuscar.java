package com.example.agea;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_buscar);
        initVariable();
        getPerfil();
    }

    private void initVariable(){
        lvPerfil = (ListView) findViewById(R.id.lvPerfiles);
        items=new ArrayList<String>();
        adapter=new ArrayAdapter(this, R.layout.items_buscar,R.id.tvNombrePerfil,items);
        lvPerfil.setAdapter(adapter);
    }

    private void getPerfil(){
        // Tag used to cancel the request
        final String tag_string_req = "req_get_perfil";

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Cargando ...");
        pDialog.show();

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(AppConfig.URL_GET_PERFIL, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyTest", error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(request, tag_string_req);
    }

}

