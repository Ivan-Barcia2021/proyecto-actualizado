package com.ort.apportresuelve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescReclamos extends AppCompatActivity {

    private Ubicacion reclamoDetallado;
    TextView descripcion, edificio, piso, aula, fecha, nombreReclamador;
    String _descripcion, _edificio, _piso, _aula, _fecha, _nombreReclamador;
    Button enProceso, resuelto;
    String deptoRecibido;
int numero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.desc_reclamos);
        Bundle paqueterecibido= new Bundle();
        paqueterecibido=this.getIntent().getExtras();
        _descripcion=paqueterecibido.getString("descripcion");
        _edificio=paqueterecibido.getString("edificio");
        _piso=paqueterecibido.getString("piso");
        _aula=paqueterecibido.getString("aula");
        _fecha=paqueterecibido.getString("fecha");
        _nombreReclamador=paqueterecibido.getString("nombreReclamador");
         deptoRecibido=paqueterecibido.getString ("Departamento");
         numero=paqueterecibido.getInt ("numero");
        enProceso=findViewById(R.id.botonEnProceso);
        resuelto=findViewById(R.id.botonResuelto);

        reclamoDetallado = (Ubicacion) getIntent().getSerializableExtra("objetoDetalles");

        descripcion=findViewById(R.id.descripcionTraida);
        edificio=findViewById(R.id.edificioTraido);
        piso=findViewById(R.id.pisoTraido);
        aula=findViewById(R.id.aulaTraida);
        fecha=findViewById(R.id.fechaTraida);
        nombreReclamador=findViewById(R.id.nombreReclamador);

        descripcion.setText(_descripcion);
        edificio.setText(_edificio);
        piso.setText(_piso);
        aula.setText(_aula);
        fecha.setText(_fecha);
        nombreReclamador.setText(_nombreReclamador);
ocultar ();
        //Bundle paqueteRecibidoDelDepto=this.getIntent ().getExtras ();
        //deptoRecibido = paqueteRecibidoDelDepto.getString ("Departamento");
    }
public void ocultar(){
        if(numero==0){
           enProceso.setVisibility (View.INVISIBLE);
           resuelto.setVisibility (View.INVISIBLE);
        }
}
    public void cambiar_estado_enProceso(View v){
        Bundle paqueteDepto= new Bundle();
        paqueteDepto.putString("Departamento", deptoRecibido);
        Intent intent= new Intent (v.getContext (), reclamosRecibidos.class);
        intent.putExtras(paqueteDepto);
        startActivity(intent);
    }


}
