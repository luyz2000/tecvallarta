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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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

        JsonArrayRequest jreq = new JsonArrayRequest(AppConfig.URL_GET_PERFIL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try{
                            JSONArray jsonArray = response.getJSONArray(1);

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject c = jsonArray.getJSONObject(i);
                                String name = c.getString("nombre");
                                items.add(name);
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            Log.e("JSONException", "Query Error: " + e.getMessage());
                        }
                        pDialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(tag_string_req, "Registration Error: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(jreq, tag_string_req);
    }

}

