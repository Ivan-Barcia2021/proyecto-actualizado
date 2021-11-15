package com.ort.apportresuelve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class reclamosRecibidos extends AppCompatActivity {
    FirebaseFirestore bdd = FirebaseFirestore.getInstance();
    CollectionReference reclamos =bdd.collection("Reclamos");
    String deptoRecibido;
    String nombreusuariorecibido;
    int numero=1;
    ListView lista;
    Button filtrarBoton;
    ArrayList<String> spinnerFiltrar;
    ArrayAdapter<String> Adaptador;
    String filtroSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_reclamos_recibidos);
        Bundle paqueterecibido=this.getIntent ().getExtras ();
        deptoRecibido = paqueterecibido.getString ("Departamento");
        nombreusuariorecibido=paqueterecibido.getString("NombreUsuario");
        filtrarBoton=findViewById(R.id.filtrarBoton);
        lista=findViewById (R.id.mlista);

        spinnerFiltrar = new ArrayList<> ();
        spinnerFiltrar.add ("Mas antiguos");
        spinnerFiltrar.add ("Mas recientes");
        spinnerFiltrar.add ("No atendidos");
        spinnerFiltrar.add ("En proceso");
        spinnerFiltrar.add ("Resueltos");

        Adaptador = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, spinnerFiltrar);
        Adaptador.setDropDownViewResource ((android.R.layout.simple_dropdown_item_1line));

        Spinner spnOpciones = (Spinner) findViewById (R.id.filtradorSpiner);
        spnOpciones.setAdapter (Adaptador);
        spnOpciones.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtroSeleccionado = spnOpciones.getItemAtPosition (position).toString ();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mostrar();

        filtrarBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if(filtroSeleccionado.equals("Mas antiguos")){
                    mostrar2 ();
                }
                else if(filtroSeleccionado.equals("Mas recientes")){
                    mostrar3 ();
                }
                else if(filtroSeleccionado.equals("No atendidos")){
                    mostrar4();
                }
                else if(filtroSeleccionado.equals("En proceso")){
                    mostrar5();
                }
                else if(filtroSeleccionado.equals("Resueltos")){
                    mostrar6();
                }
                else{
                    mostrar();
                }
            }
        });
    }


    public void ver_referencias(View v){
        Intent i= new Intent (v.getContext (), referencias.class);
        startActivity (i);

    }

    public void pasar(View v){
        Bundle paquete_nombre= new Bundle();
        paquete_nombre.putString("nombre", nombreusuariorecibido);
        paquete_nombre.putString("cargo","empleado");
        Intent intent= new Intent (v.getContext (), login.class);
        intent.putExtras(paquete_nombre);
        startActivity (intent);
    }

    public void mostrar(){
        ArrayList<Ubicacion> nuestrosReclamosRecibidos = new ArrayList<>();

        bdd.collection("Reclamos")

                .whereEqualTo("tipoDeReclamo", deptoRecibido)

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot> () {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Ubicacion u = document.toObject(Ubicacion.class);
                                u.setId(document.getId());
                                nuestrosReclamosRecibidos.add(u);


                            }
                            Log.d("TraerReclamosRecibidos", "Recibido");
                            //for recorriendo nuestrasubicaciones para obtener todas las ubicaciones y mostrarlas en la list view con sus atributos.
                            TextView miseleccion = findViewById(R.id.seleccion);
                            ListView lista = findViewById(R.id.mlista);

                            Context contexto = null;
                            AdaptadorUbicacion ubis = new AdaptadorUbicacion(reclamosRecibidos.this, nuestrosReclamosRecibidos); //(this, android.R.layout.simple_list_item_1 , nuestrasubicaciones);
                            lista.setAdapter(ubis);
                            ubis.pasardatos (deptoRecibido, numero);
                        }
                    }
                })
    ;}

    public void mostrar2(){
        ArrayList<Ubicacion> nuestrosReclamosRecibidos = new ArrayList<>();

        bdd.collection ("Reclamos")

            .orderBy ("fecha", Query.Direction.ASCENDING)
                .get ()
                .addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful ()) {
                            for (QueryDocumentSnapshot document : task.getResult ()) {

                                Ubicacion u = document.toObject(Ubicacion.class);
                                u.setId(document.getId());

                                if(u.getTipoDeReclamo ().equals (deptoRecibido)){
                                    nuestrosReclamosRecibidos.add(u);
                                }
                            }

                            Log.d ("TraerReclamo", "HOLA");

                            AdaptadorUbicacion ubis = new AdaptadorUbicacion (reclamosRecibidos.this, nuestrosReclamosRecibidos);
                            lista.setAdapter (ubis);
                            ubis.pasardatos (deptoRecibido, numero);

                        } else {
                            Context context = getApplicationContext ();
                            CharSequence text = "No se pudo conectar a los reclamos";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText (context, text, duration);
                            toast.show ();
                        }
                    }
                });
    }

    public void mostrar3(){
        ArrayList<Ubicacion> nuestrosReclamosRecibidos = new ArrayList<>();

        bdd.collection ("Reclamos")
            //    .whereEqualTo("tipoDeReclamo", deptoRecibido)
                .orderBy ("fecha", Query.Direction.DESCENDING)
                .get ()
                .addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful ()) {
                            for (QueryDocumentSnapshot document : task.getResult ()) {

                                Ubicacion u = document.toObject(Ubicacion.class);
                                u.setId (document.getId ());
                                if(u.getTipoDeReclamo ().equals (deptoRecibido)){
                                    nuestrosReclamosRecibidos.add(u);
                                }


                            }
                            Log.d ("TraerReclamo", "HOLA");

                            AdaptadorUbicacion ubis = new AdaptadorUbicacion (reclamosRecibidos.this, nuestrosReclamosRecibidos);
                            lista.setAdapter (ubis);
                            ubis.pasardatos (deptoRecibido, numero);

                        } else {
                            Context context = getApplicationContext ();
                            CharSequence text = "No se pudo conectar a los reclamos";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText (context, text, duration);
                            toast.show ();
                        }
                    }
                });
    }

    public void mostrar4(){
        ArrayList<Ubicacion> nuestrosReclamosRecibidos = new ArrayList<>();

        bdd.collection ("Reclamos")
                .whereEqualTo ("estadoDelReclamo", "No atendido")
                .whereEqualTo("tipoDeReclamo", deptoRecibido)
                .get ()
                .addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful ()) {
                            for (QueryDocumentSnapshot document : task.getResult ()) {

                                Ubicacion u = document.toObject(Ubicacion.class);
                                u.setId(document.getId());
                                nuestrosReclamosRecibidos.add(u);

                            }
                            Log.d ("TraerReclamo", "HOLA");

                            AdaptadorUbicacion ubis = new AdaptadorUbicacion (reclamosRecibidos.this, nuestrosReclamosRecibidos);
                            lista.setAdapter (ubis);
                            ubis.pasardatos (deptoRecibido, numero);

                        } else {
                            Context context = getApplicationContext ();
                            CharSequence text = "No se pudo conectar a los reclamos";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText (context, text, duration);
                            toast.show ();
                        }
                    }
                });
    }

    public void mostrar5(){
        ArrayList<Ubicacion> nuestrosReclamosRecibidos = new ArrayList<>();

        bdd.collection ("Reclamos")
                .whereEqualTo ("estadoDelReclamo", "En Proceso")
                .whereEqualTo("tipoDeReclamo", deptoRecibido)
                .get ()
                .addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful ()) {
                            for (QueryDocumentSnapshot document : task.getResult ()) {

                                Ubicacion u = document.toObject(Ubicacion.class);
                                u.setId(document.getId());
                                nuestrosReclamosRecibidos.add(u);

                            }
                            Log.d ("TraerReclamo", "HOLA");

                            AdaptadorUbicacion ubis = new AdaptadorUbicacion (reclamosRecibidos.this, nuestrosReclamosRecibidos);
                            lista.setAdapter (ubis);
                            ubis.pasardatos (deptoRecibido, numero);

                        } else {
                            Context context = getApplicationContext ();
                            CharSequence text = "No se pudo conectar a los reclamos";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText (context, text, duration);
                            toast.show ();
                        }
                    }
                });
    }

    public void mostrar6(){
        ArrayList<Ubicacion> nuestrosReclamosRecibidos = new ArrayList<>();

        bdd.collection ("Reclamos")
                .whereEqualTo ("estadoDelReclamo", "Resuelto")
                .whereEqualTo("tipoDeReclamo", deptoRecibido)
                .get ()
                .addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful ()) {
                            for (QueryDocumentSnapshot document : task.getResult ()) {

                                Ubicacion u = document.toObject(Ubicacion.class);
                                u.setId(document.getId());
                                nuestrosReclamosRecibidos.add(u);

                            }
                            Log.d ("TraerReclamo", "HOLA");

                            AdaptadorUbicacion ubis = new AdaptadorUbicacion (reclamosRecibidos.this, nuestrosReclamosRecibidos);
                            lista.setAdapter (ubis);
                            ubis.pasardatos (deptoRecibido, numero);

                        } else {
                            Context context = getApplicationContext ();
                            CharSequence text = "No se pudo conectar a los reclamos";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText (context, text, duration);
                            toast.show ();
                        }
                    }
                });
    }

    public void ver_mis_reclamos(View v){
        Bundle paquete= new Bundle();
        paquete.putString("cargo", "empleado");
        paquete.putString("Departamento", deptoRecibido);
        paquete.putString("NombreUsuario", nombreusuariorecibido);
        Intent intent= new Intent (v.getContext (), misreclamos.class);
        intent.putExtras(paquete);
        startActivity (intent);
    }
}