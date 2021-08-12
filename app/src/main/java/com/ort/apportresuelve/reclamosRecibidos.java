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

public class reclamosRecibidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_reclamos_recibidos);
        mostrar ();
    }

    public void pasar(View v){
        Intent intent= new Intent (v.getContext (), login.class);
        startActivity (intent);
    }

    void mostrar(){
        DatabaseReference bdd= FirebaseDatabase.getInstance ().getReference ();

        ValueEventListener userlistener= new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<tecnologia> listareclamos = new ArrayList<>();
                //for (DataSnapshot d : snapshot.getChildren()) {
                    DataSnapshot u = snapshot.child("Ubicacion");
                    for (DataSnapshot d2 : u.getChildren()) {
                        tecnologia t = d2.getValue(tecnologia.class);
                        listareclamos.add(t);
                        Log.d("Aula", t.getAula());
                        Log.d("Descripcion", t.getDescripcion());
                        Log.d("Piso", t.getPiso());
                        Log.d("Edificio", t.getEdificio());
                    }
                }
            //}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        bdd.addValueEventListener (userlistener);
    }
}