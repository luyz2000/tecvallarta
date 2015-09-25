package com.example.agea;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class FrmPerfil extends ActionBarActivity {

    private EditText etNombrePerfil;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9;
    private int opcion = 0;
    private entPerfil perfil = new entPerfil();
    private String privilegio;

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
        switch (item.getItemId()){
            case R.id.buscar_registro_perfil:

                return true;
            case R.id.nuevo_registro_perfil:
                nuevoPerfil();
                return true;
            case R.id.guardar_registro_perfil:
                if(opcion == 1) {

                } else {
                    Toast.makeText(getApplicationContext(), "NO HAZ SELECCIONADO LA OPCION DE NUEVO REGISTRO", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.eliminar_registro_perfil:
                return true;
            case R.id.cancelar_registro_perfil:
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

    private void initVariables(){
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
        limpiarCampos();
        activarCampos(false);
    }

    private void nuevoPerfil(){
        opcion = 1;
        limpiarCampos();
        activarCampos(true);
    }

    private void limpiarCampos(){
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
        activarCampos(true);
    }

    private void activarCampos(boolean valor){
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