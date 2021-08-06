package com.ort.apportresuelve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

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

    EditText nombreUsuario;
    EditText contraseñaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        nombreUsuario=findViewById(R.id.usuario);
        contraseñaUsuario=findViewById(R.id.contraseña);
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
        if(TextUtils.isEmpty(nombreUsuario.getText())){

            Context context = getApplicationContext();
            CharSequence text = "Completa el nombre de usuario valido";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            nombreUsuario.setError( "Completa el campo con un nombre valido" );

        }else{

            if(TextUtils.isEmpty(contraseñaUsuario.getText())){

                Context context = getApplicationContext();
                CharSequence text = "Completa con la contraseña valida";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                contraseñaUsuario.setError( "Completa el campo con una contraseña valida" );

            }else{
                Intent intent= new Intent (v.getContext (), misreclamos.class);
                startActivity (intent);
            }
        }


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
