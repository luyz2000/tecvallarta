package com.example.agea;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

/**
 * Created by Light on 11/12/2015.
 */
public class FrmBuscar extends ActionBarActivity{

    private ListView lvPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_buscar);
        initVariable();

    }

    private void initVariable(){
        lvPerfil = (ListView) findViewById(R.id.lvPerfiles);

    }
}
