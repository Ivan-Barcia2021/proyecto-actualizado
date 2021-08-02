package com.ort.apportresuelve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

    }

    public void avanzar(View v){
        FirebaseFirestore db = null;
        ArrayList<usuario> nuestrosusuarios= new ArrayList<> ();
        Task<QuerySnapshot> Usuarios=db.collection ("Usuarios").get ().addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                       nuestrosusuarios.add (document.toObject (usuario.class));
                    }
                    EditText usuario=findViewById (R.id.usuario);
                    EditText contraseña=findViewById (R.id.contraseña);

                }
                else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
        }




    });
      Intent intent= new Intent (v.getContext (), MainActivity.class);
      startActivity (intent);
}}