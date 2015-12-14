package com.example.agea;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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


    }

    private void initVariable(){
        lvPerfil = (ListView) findViewById(R.id.lvPerfiles);
        items=new ArrayList<String>();
        adapter=new ArrayAdapter(this, R.layout.items_buscar,R.id.tvNombrePerfil,items);
        lvPerfil.setAdapter(adapter);
    }

    private void getPerfil(){
        // Tag used to cancel the request
        String tag_string_req = "req_eliminar_perfil";

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Registrando ...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_DELETE_PERFIL, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                // Log.d(TAG, "Register Response: " + response.toString());
                pDialog.hide();
                try {
                    JSONObject jObj = null;
                    for (int x = 0;x<response.length();x++) {
                        jObj = response.getJSONObject(x);
                        items.add(jObj.getString("perNombre"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                //hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}

