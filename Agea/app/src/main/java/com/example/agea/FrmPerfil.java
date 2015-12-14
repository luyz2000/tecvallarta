package com.example.agea;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FrmPerfil extends ActionBarActivity {

    private EditText etNombrePerfil;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9;
    private int opcion = 0;
    private String[] checked = new String[9];
    private entPerfil perfil = new entPerfil();
    private String privilegio, id;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_perfil);
        initVariables();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_frm_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buscar_registro_perfil:
                opcion = 3;
                return true;
            case R.id.nuevo_registro_perfil:
                nuevoPerfil();
                return true;
            case R.id.guardar_registro_perfil:
                if (opcion == 1) {
                    Entity();
                    registerPerfil(etNombrePerfil.getText().toString(), privilegio);
                    limpiarCampos(false);
                    opcion = 0;
                } else {
                    Toast.makeText(getApplicationContext(), "NO HAZ SELECCIONADO LA OPCION DE NUEVO REGISTRO", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.eliminar_registro_perfil:
                if (opcion == 3) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("¿DESEAS ELIMINAR EL PERFIL '" + etNombrePerfil.getText().toString() + "'?");
                    alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            eliminarPerfil(id);
                        }
                    });
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "CONTINUA", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();
                }
                return true;
            case R.id.cancelar_registro_perfil:
                if (opcion != 0) {
                    limpiarCampos(false);
                    opcion = 0;
                }
                return true;
            case R.id.salir_registro_perfil:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("¿DESEAS SALIR DE LA APP AGEA?");
                alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "CONTINUA", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Entity() {
        if (cb1.isChecked()) {
            checked[0] = "1";
        } else {
            checked[0] = "0";
        }
        if (cb2.isChecked()) {
            checked[1] = "1";
        } else {
            checked[1] = "0";
        }
        if (cb3.isChecked()) {
            checked[2] = "1";
        } else {
            checked[2] = "0";
        }
        if (cb4.isChecked()) {
            checked[3] = "1";
        } else {
            checked[3] = "0";
        }
        if (cb5.isChecked()) {
            checked[4] = "1";
        } else {
            checked[4] = "0";
        }
        if (cb6.isChecked()) {
            checked[5] = "1";
        } else {
            checked[5] = "0";
        }
        if (cb7.isChecked()) {
            checked[6] = "1";
        } else {
            checked[6] = "0";
        }
        if (cb8.isChecked()) {
            checked[7] = "1";
        } else {
            checked[7] = "0";
        }
        if (cb9.isChecked()) {
            checked[8] = "1";
        } else {
            checked[8] = "0";
        }
        privilegio = "";
        for (int x = 0; x < checked.length; x++) {
            privilegio = privilegio + checked[x];
        }
    }

    private void initVariables() {
        opcion = 0;
        etNombrePerfil = (EditText) findViewById(R.id.etNombrePerfil);
        cb1 = (CheckBox) findViewById(R.id.chUsuarios);
        cb2 = (CheckBox) findViewById(R.id.chActividades);
        cb3 = (CheckBox) findViewById(R.id.chParticipantes);
        cb4 = (CheckBox) findViewById(R.id.chPagos);
        cb5 = (CheckBox) findViewById(R.id.chAsistencias);
        cb6 = (CheckBox) findViewById(R.id.chPerfiles);
        cb7 = (CheckBox) findViewById(R.id.chGestores);
        cb8 = (CheckBox) findViewById(R.id.chConstancias);
        cb9 = (CheckBox) findViewById(R.id.chReportes);
        limpiarCampos(false);
        activarCampos(false);
    }

    private void nuevoPerfil() {
        opcion = 1;
        limpiarCampos(true);
        activarCampos(true);
    }

    private void registerPerfil(final String nombre, final String permiso) {
        // Tag used to cancel the request
        String tag_string_req = "req_register_perfil";

        //pDialog.setMessage("Registrando ...");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER_PERFIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // Log.d(TAG, "Register Response: " + response.toString());
                // hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        //String uid = jObj.getString("uid");
                        //String name = user.getString("name");

                        Toast.makeText(getApplicationContext(), "Perfil exitosamente registrado. Trata de entrar ahora!", Toast.LENGTH_LONG).show();
                    } else {
                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
                params.put("nombre", nombre);
                params.put("permiso", permiso);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void eliminarPerfil(final String id) {
        // Tag used to cancel the request
        String tag_string_req = "req_eliminar_perfil";

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Registrando ...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_DELETE_PERFIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.d(TAG, "Register Response: " + response.toString());
                pDialog.hide();

                try {
                    JSONObject jObj = new JSONObject(response);
                    int intError = jObj.getInt("error");
                    boolean error = (intError > 0) ? true : false;
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        //String uid = jObj.getString("uid");
                        //String name = user.getString("name");

                        Toast.makeText(getApplicationContext(), "Perfil exitosamente eliminado. Trata de entrar ahora!", Toast.LENGTH_LONG).show();
                    } else {
                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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

    private void limpiarCampos(boolean campo) {
        etNombrePerfil.setText("");
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
        cb4.setChecked(false);
        cb5.setChecked(false);
        cb6.setChecked(false);
        cb7.setChecked(false);
        cb8.setChecked(false);
        cb9.setChecked(false);
        activarCampos(campo);
    }

    private void activarCampos(boolean valor) {
        etNombrePerfil.setEnabled(valor);
        cb1.setEnabled(valor);
        cb2.setEnabled(valor);
        cb3.setEnabled(valor);
        cb4.setEnabled(valor);
        cb5.setEnabled(valor);
        cb6.setEnabled(valor);
        cb7.setEnabled(valor);
        cb8.setEnabled(valor);
        cb9.setEnabled(valor);
    }
}