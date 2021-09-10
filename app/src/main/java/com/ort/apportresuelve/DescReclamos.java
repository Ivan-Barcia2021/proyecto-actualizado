package com.ort.apportresuelve;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescReclamos extends AppCompatActivity {

    private Ubicacion reclamoDetallado;
    TextView descripcion, edificio, piso, aula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.desc_reclamos);

        reclamoDetallado = (Ubicacion) getIntent().getSerializableExtra("objetoDetalles");

        descripcion=findViewById(R.id.descripcionTraida);
        edificio=findViewById(R.id.edificioTraido);
        piso=findViewById(R.id.pisoTraido);
        aula=findViewById(R.id.aulaTraida);

        descripcion.setText(reclamoDetallado.getDescripcion());
        edificio.setText(reclamoDetallado.getEdificio());
        piso.setText(reclamoDetallado.getPiso());
        aula.setText(reclamoDetallado.getAula());

    }



}
