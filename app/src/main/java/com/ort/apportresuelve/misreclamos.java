package com.ort.apportresuelve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

    void mostrar(){
        ValueEventListener userListener= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Ubicacion> nuestrasubicaciones = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot d2 : dataSnapshot.getChildren()) {
                        Ubicacion miubicacion = d2.getValue(Ubicacion.class);
                        nuestrasubicaciones.add(miubicacion);
                        Log.d("Aula", miubicacion.getAula());
                        Log.d("Descripcion", miubicacion.getDescripcion());
                        Log.d("Piso", miubicacion.getPiso());
                        Log.d("Edificio", miubicacion.getEdificio());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        DatabaseReference mibdd= FirebaseDatabase.getInstance().getReference();
        mibdd.addValueEventListener(userListener);
    }
}