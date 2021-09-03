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
import com.google.firebase.firestore.CollectionReference;
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
    String dniUsuario;
    String contUsuario;
    String cargoUsuario;
    String deptoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        nombreUsuario=findViewById(R.id.usuario);
        contraseñaUsuario=findViewById(R.id.contraseña);
    }

    public void avanzar(View v){

        if(TextUtils.isEmpty(nombreUsuario.getText())){

            Context context = getApplicationContext();
            CharSequence text = "Completa el dni de usuario";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            nombreUsuario.setError( "Completa el campo con un dni valido" );
        }
        else{

            if(TextUtils.isEmpty(contraseñaUsuario.getText())){

                Context context = getApplicationContext();
                CharSequence text = "Completa la contraseña de usuario";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                contraseñaUsuario.setError( "Completa el campo con una contraseña" );
            }
            else{
                dniUsuario = nombreUsuario.getText().toString();
                contUsuario = contraseñaUsuario.getText().toString();

                FirebaseFirestore mibase=FirebaseFirestore.getInstance();
                CollectionReference usuarios=mibase.collection("Usuarios");
                ArrayList<usuario> nuestrosusuarios=new ArrayList<>();

                mibase.collection("Usuarios")
                        .whereEqualTo("DNI", dniUsuario)
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                usuario nuestroUsuario = (usuario)document.toObject(usuario.class);
                                cargoUsuario = nuestroUsuario.getCargo();
                                deptoUsuario = nuestroUsuario.getDepartamento();

                                if(contUsuario.equals(nuestroUsuario.getContrasena())){
                                    if(cargoUsuario.equals("empleado")){
                                        Bundle paqueteDepto= new Bundle();
                                        paqueteDepto.putString("Departamento", deptoUsuario);
                                        Intent intent= new Intent (v.getContext (), reclamosRecibidos.class);
                                        intent.putExtras(paqueteDepto);
                                        startActivity(intent);
                                    }
                                    else{
                                        Bundle paquete= new Bundle();
                                        paquete.putString("cargo", cargoUsuario);
                                        paquete.putString("Departamento", deptoUsuario);
                                        if(deptoUsuario.equals(null)){
                                            deptoUsuario = "Nodepto";
                                        }
                                        Intent intent= new Intent (v.getContext (), misreclamos.class);
                                        intent.putExtras(paquete);
                                        startActivity (intent);
                                    }
                                }
                                else{
                                    Context context = getApplicationContext();
                                    CharSequence text = "Ingrese una contraseña valida";
                                    int duration = Toast.LENGTH_LONG;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                    Log.d("TraerUsuario", "contraseña invalida");
                                }
                            }
                        }
                        else {
                            Context context = getApplicationContext();
                            CharSequence text = "Ingrese un dni valido";
                            int duration = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            Log.d("TraerUsuario", "No existe el usuario");
                        }

                    }

                });

            }
            }
    }
}


