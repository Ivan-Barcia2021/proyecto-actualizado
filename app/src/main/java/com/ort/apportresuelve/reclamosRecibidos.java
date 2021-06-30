package com.ort.apportresuelve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class reclamosRecibidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_reclamos_recibidos);
        mostrar ();
    }
    void mostrar(){
        DatabaseReference bdd= FirebaseDatabase.getInstance ().getReference ();

        ValueEventListener userlistener= new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d: snapshot.getChildren ()){
                    tecnologia t=d.getValue (tecnologia.class);
                    ArrayList<tecnologia> listareclamos= new ArrayList<> ();
                    listareclamos.add (t);
                    Log.d("Aula", t.getAula());
                    Log.d("Descripcion", t.getDescripcion());
                    Log.d("Piso", t.getPiso());
                    Log.d("Edificio", t.getEdificio());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        bdd.addValueEventListener (userlistener);
    }
}