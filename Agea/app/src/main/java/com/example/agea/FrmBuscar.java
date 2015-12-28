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
    ArrayList<String> items=new ArrayList<String>();

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
                pDialog.dismiss();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = false;
                    if (!error) {
                        JSONArray perfiles = jObj.getJSONArray("Perfiles");
                        for (int i = 0; i < perfiles.length(); i++) {
                            JSONObject NombreP = perfiles.getJSONObject(i);
                            items.add(NombreP.getString("nombre"));
                        }
                    } else {
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", "co");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}

