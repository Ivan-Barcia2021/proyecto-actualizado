package com.ort.apportresuelve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class misreclamos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_misreclamos);
    }
    public void pasar(View v){
        Intent intent= new Intent (v.getContext (), reclamosRecibidos.class);
        startActivity (intent);
    }

    public void mostrar(View vista){
        ArrayList<Ubicacion> nuestrasubicaciones = new ArrayList<>();

        ValueEventListener userListener= new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot d2 : dataSnapshot.getChildren()) {
                        Ubicacion miubicacion = d2.getValue(Ubicacion.class);

                        Log.d("Aula", miubicacion.getAula());
                        Log.d("Descripcion", miubicacion.getDescripcion());
                        Log.d("Piso", miubicacion.getPiso());
                        Log.d("Edificio", miubicacion.getEdificio());
                        nuestrasubicaciones.add(miubicacion);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        TextView seleccion=findViewById (R.id.seleccion);
        DatabaseReference mibdd= FirebaseDatabase.getInstance().getReference();
        mibdd.addValueEventListener(userListener);
        ArrayAdapter<Ubicacion> adaptador= new ArrayAdapter<> (this, android.R.layout.simple_list_item_1 , nuestrasubicaciones);
        ListView milista=findViewById (R.id.mlista);
        milista.setAdapter (adaptador);
        milista.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleccion.setText (parent.getItemAtPosition (position).toString ());
                //los reclamos hechos pueden verse pero hay que apretar muchas veces el boton y se ven mal, hay que corregirlo.
            }
        });

    }
}