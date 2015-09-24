package com.example.agea;

import android.os.StrictMode;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;

/**
 * Created by Frederick on 24/09/2015.
 */
public class SQLConexion {

    public void Conectar(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
