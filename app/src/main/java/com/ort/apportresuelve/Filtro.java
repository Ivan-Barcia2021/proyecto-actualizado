package com.ort.apportresuelve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Filtro extends AppCompatActivity {
  String dptousuario;
  String nombreusuario;
  String cargousuario;
  int codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_filtro);
        Bundle paqueterecibido= this.getIntent ().getExtras ();
        dptousuario=paqueterecibido.getString ("Departamento");
        nombreusuario=paqueterecibido.getString ("NombreUsuario");
        cargousuario=paqueterecibido.getString ("cargo");
    }
    public void presionaboton(View v){
        Button botonpresionado;
        botonpresionado=(Button) v;
        if(botonpresionado.getId ()==R.id.miboton){
            codigo=0;

        }
        else if(botonpresionado.getId ()==R.id.miboton2){
            codigo=1;
        }
        else {
            codigo=2;
        }
        Intent intent= new Intent (v.getContext (), misreclamos.class);
        Bundle paquete= new Bundle ();
        paquete.putString("Departamento", dptousuario);
        paquete.putString("NombreUsuario", nombreusuario);
        paquete.putString ("cargo", cargousuario);
        paquete.putInt ("codigo", codigo);
        intent.putExtras (paquete);
        startActivity (intent);

    }
}