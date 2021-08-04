package com.ort.apportresuelve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

    }

    public void avanzar(View v){


    /*    ArrayList<usuario> nuestrosusuarios= new ArrayList<> ();
        EditText contraseña=findViewById (R.id.contraseña);
        EditText usuario=findViewById (R.id.usuario);
        String contraseñaingresada=contraseña.toString ();
        String usuarioingresado=usuario.toString ();

       FirebaseFirestore db = null;
        db.collection("Usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot> () {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                nuestrosusuarios.add (document.toObject (usuario.class));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });*/
        Intent intent= new Intent (v.getContext (), login.class);
        startActivity (intent);

        //debo traer el dni y la contraseña, compararlos con el ingreso y si es correcto, loguear al usuario.
     /*   FirebaseFirestore db = null;
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

      */
    }
    }
