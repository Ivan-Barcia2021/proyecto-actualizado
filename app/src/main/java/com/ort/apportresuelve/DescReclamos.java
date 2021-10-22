package com.ort.apportresuelve;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DescReclamos extends AppCompatActivity {

    private Ubicacion reclamoDetallado;
    TextView descripcion, edificio, piso, aula, fecha, nombreReclamador;
    String _descripcion, _edificio, _piso, _aula, _fecha, _nombreReclamador, _estadoDelReclamo, _id;
    Button enProceso, resuelto;
    String deptoRecibido;
    int numero;
    TextView miestado;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desc_reclamos);
        Bundle paqueterecibido = new Bundle();
        paqueterecibido = this.getIntent().getExtras();
        _descripcion = paqueterecibido.getString("descripcion");
        _edificio = paqueterecibido.getString("edificio");
        _piso = paqueterecibido.getString("piso");
        _aula = paqueterecibido.getString("aula");
        _fecha = paqueterecibido.getString("fecha");
        _nombreReclamador = paqueterecibido.getString("nombreReclamador");
        _estadoDelReclamo = paqueterecibido.getString("estadoDelReclamo");
        _id = paqueterecibido.getString("id");
        deptoRecibido = paqueterecibido.getString("Departamento");
        numero=paqueterecibido.getInt ("numeroActivity");
        enProceso = findViewById(R.id.botonEnProceso);
        resuelto = findViewById(R.id.botonResuelto);

        reclamoDetallado = (Ubicacion) getIntent().getSerializableExtra("objetoDetalles");

        descripcion = findViewById(R.id.descripcionTraida);
        edificio = findViewById(R.id.edificioTraido);
        piso = findViewById(R.id.pisoTraido);
        aula = findViewById(R.id.aulaTraida);
        fecha = findViewById(R.id.fechaTraida);
        nombreReclamador = findViewById(R.id.nombreReclamador);
        miestado = findViewById(R.id.estado);
        descripcion.setText(_descripcion);
        edificio.setText(_edificio);
        piso.setText(_piso);
        aula.setText(_aula);
        fecha.setText(_fecha);
        nombreReclamador.setText(_nombreReclamador);

        ocultar();

    }

    public void ocultar() {
        if (numero == 0) {
            enProceso.setVisibility(View.INVISIBLE);
            resuelto.setVisibility(View.INVISIBLE);
        }
        else{
            enProceso.setVisibility(View.VISIBLE);
            resuelto.setVisibility(View.VISIBLE);
        }
    }

    public void cambiar_estado_enProceso(View v) {

        db.collection("Reclamos")
                .document(_id)
                .set("En Proceso")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Estado del reclamo modificado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("ActualizarReclamo", "Error al actualizar el estado del reclamo", e);
                        Toast.makeText(getApplicationContext(), "Ocurrió un error al actualizar la información", Toast.LENGTH_SHORT).show();
                    }
                });

        Bundle paqueteDepto = new Bundle();
        paqueteDepto.putString("Departamento", deptoRecibido);
        paqueteDepto.putString("descripcion", _descripcion);
        Intent intent = new Intent(v.getContext(), reclamosRecibidos.class);
        intent.putExtras(paqueteDepto);
        startActivity(intent);
    }

    public void cambiar_estado_solucionado(View v) {
        Bundle paqueteDepto = new Bundle();
        paqueteDepto.putString("Departamento", deptoRecibido);
        paqueteDepto.putString("descripcion", _descripcion);
        Intent intent = new Intent(v.getContext(), reclamosRecibidos.class);
        intent.putExtras(paqueteDepto);
        startActivity(intent);
    }
}
