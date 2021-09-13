package com.ort.apportresuelve;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescReclamos extends AppCompatActivity {

    private Ubicacion reclamoDetallado;
    TextView descripcion, edificio, piso, aula, fecha, nombreReclamador;
    String _descripcion, _edificio, _piso, _aula, _fecha, _nombreReclamador;

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

    }



}
