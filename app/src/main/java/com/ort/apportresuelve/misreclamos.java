package com.ort.apportresuelve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
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
import java.util.Date;

@SuppressWarnings("ALL")
public class misreclamos extends AppCompatActivity {
    FirebaseFirestore bdd = FirebaseFirestore.getInstance();
    CollectionReference reclamos =bdd.collection("Reclamos");

    Button reclamosRecibidosBtn;
    String cargoRecibido;
    String deptoRecibido;
    String nombreusuariorecibido;
    ListView lista;
    Button miboton, filtrarBoton;
    ArrayList<String> spinnerFiltrar;
    ArrayAdapter<String> Adaptador;
    String filtroSeleccionado;
    int numero=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_misreclamos);
        reclamosRecibidosBtn=findViewById(R.id.reclamosRecibidosBoton);
        filtrarBoton=findViewById(R.id.filtrarBoton);
        Bundle paqueterecibido=this.getIntent ().getExtras ();
        cargoRecibido = paqueterecibido.getString ("cargo");
        nombreusuariorecibido=paqueterecibido.getString("NombreUsuario");
        deptoRecibido = paqueterecibido.getString ("Departamento");

        lista=findViewById (R.id.mlista);
        ocultarBotonReclamosRecibidos (cargoRecibido);


        spinnerFiltrar = new ArrayList<> ();
        spinnerFiltrar.add ("Departamento");
        spinnerFiltrar.add ("Mas antiguos");
        spinnerFiltrar.add ("Mas recientes");

        Adaptador = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, spinnerFiltrar);
        Adaptador.setDropDownViewResource ((android.R.layout.simple_dropdown_item_1line));

        Spinner spnOpciones = (Spinner) findViewById (R.id.filtrador);
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
                if(filtroSeleccionado.equals("Departamento")){
                    mostrar();
                }
                else if(filtroSeleccionado.equals("Mas antiguos")){
                    mostrar2 ();
                }
                else if(filtroSeleccionado.equals("Mas recientes")){
                    mostrar3 ();
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
        paquete_nombre.putString("cargo", cargoRecibido);
        Intent intent= new Intent (v.getContext (), login.class);
        intent.putExtras(paquete_nombre);
        startActivity (intent);
    }


   public void mostrar() {
       FirebaseFirestore db = FirebaseFirestore.getInstance ();

       ArrayList<Ubicacion> nuestrasubicaciones = new ArrayList<> ();



       bdd.collection ("Reclamos")
               //.whereEqualTo ("nombreUsuario", nombreusuariorecibido)
               .orderBy ("tipoDeReclamo", Query.Direction.ASCENDING)
               .get ()
               .addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if (task.isSuccessful ()) {
                           for (QueryDocumentSnapshot document : task.getResult ()) {

                               nuestrasubicaciones.add (document.toObject (Ubicacion.class));
                               Ubicacion reclam = document.toObject(Ubicacion.class);
                               //reclam.setId(document.getId());
                               nuestrasubicaciones.add(reclam);

                           }
                           Log.d ("TraerReclamo", "HOLA");
                           //for recorriendo nuestrasubicaciones para obtener todas las ubicaciones y mostrarlas en la list view con sus atributos.
                           TextView miseleccion = findViewById (R.id.seleccion);

                           Context contexto = null;
                           AdaptadorUbicacion ubis = new AdaptadorUbicacion (misreclamos.this, nuestrasubicaciones); //(this, android.R.layout.simple_list_item_1 , nuestrasubicaciones);
                           lista.setAdapter (ubis);
                           ubis.pasardatos (deptoRecibido, numero);
                           //AdaptadorDetalles deta= new AdaptadorDetalles(misreclamos.this,nuestrasubicaciones);

                       } else {
                           Context context = getApplicationContext ();
                           CharSequence text = "No se pudo conectar a los reclamos";
                           int duration = Toast.LENGTH_SHORT;

                           Toast toast = Toast.makeText (context, text, duration);
                           toast.show ();
                       }
                   }
               });


       ArrayAdapter<Ubicacion> adaptador = new ArrayAdapter<Ubicacion> (this, android.R.layout.simple_list_item_1, nuestrasubicaciones);
       lista.setAdapter (adaptador);

       lista.setClickable (true);
       lista.setOnItemClickListener (new AdapterView.OnItemClickListener () {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Log.d ("toque item", "");
               Intent intent = new Intent (misreclamos.this, DescReclamos.class);
               intent.putExtra ("objetoDetalles", (Serializable) nuestrasubicaciones.get (position));
               startActivity (intent);
           }
       });

   }
   public void mostrar2(){
       FirebaseFirestore db = FirebaseFirestore.getInstance ();

       ArrayList<Ubicacion> nuestrasubicaciones = new ArrayList<> ();

       bdd.collection ("Reclamos")
               //.whereEqualTo ("nombreUsuario", nombreusuariorecibido)
               .orderBy ("fecha", Query.Direction.ASCENDING)
               .get ()
               .addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if (task.isSuccessful ()) {
                           for (QueryDocumentSnapshot document : task.getResult ()) {

                               nuestrasubicaciones.add (document.toObject (Ubicacion.class));

                           }
                           Log.d ("TraerReclamo", "HOLA");
                           //for recorriendo nuestrasubicaciones para obtener todas las ubicaciones y mostrarlas en la list view con sus atributos.
                           TextView miseleccion = findViewById (R.id.seleccion);

                           Context contexto = null;
                           AdaptadorUbicacion ubis = new AdaptadorUbicacion (misreclamos.this, nuestrasubicaciones); //(this, android.R.layout.simple_list_item_1 , nuestrasubicaciones);
                           lista.setAdapter (ubis);
                           ubis.pasardatos (deptoRecibido, numero);
                           //AdaptadorDetalles deta= new AdaptadorDetalles(misreclamos.this,nuestrasubicaciones);

                       } else {
                           Context context = getApplicationContext ();
                           CharSequence text = "No se pudo conectar a los reclamos";
                           int duration = Toast.LENGTH_SHORT;

                           Toast toast = Toast.makeText (context, text, duration);
                           toast.show ();
                       }
                   }
               });


       ArrayAdapter<Ubicacion> adaptador = new ArrayAdapter<Ubicacion> (this, android.R.layout.simple_list_item_1, nuestrasubicaciones);
       lista.setAdapter (adaptador);

       lista.setClickable (true);
       lista.setOnItemClickListener (new AdapterView.OnItemClickListener () {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Log.d ("toque item", "");
               Intent intent = new Intent (misreclamos.this, DescReclamos.class);
               intent.putExtra ("objetoDetalles", (Serializable) nuestrasubicaciones.get (position));
               startActivity (intent);
           }
       });

   }
   public void mostrar3(){
       FirebaseFirestore db = FirebaseFirestore.getInstance ();

       ArrayList<Ubicacion> nuestrasubicaciones = new ArrayList<> ();



       bdd.collection ("Reclamos")
               //.whereEqualTo ("nombreUsuario", nombreusuariorecibido)
               .orderBy ("fecha", Query.Direction.DESCENDING)
               .get ()
               .addOnCompleteListener (new OnCompleteListener<QuerySnapshot> () {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if (task.isSuccessful ()) {
                           for (QueryDocumentSnapshot document : task.getResult ()) {

                               nuestrasubicaciones.add (document.toObject (Ubicacion.class));

                           }
                           Log.d ("TraerReclamo", "HOLA");
                           //for recorriendo nuestrasubicaciones para obtener todas las ubicaciones y mostrarlas en la list view con sus atributos.
                           TextView miseleccion = findViewById (R.id.seleccion);

                           Context contexto = null;
                           AdaptadorUbicacion ubis = new AdaptadorUbicacion (misreclamos.this, nuestrasubicaciones); //(this, android.R.layout.simple_list_item_1 , nuestrasubicaciones);
                           lista.setAdapter (ubis);
                           ubis.pasardatos (deptoRecibido, numero);
                           //AdaptadorDetalles deta= new AdaptadorDetalles(misreclamos.this,nuestrasubicaciones);

                       } else {
                           Context context = getApplicationContext ();
                           CharSequence text = "No se pudo conectar a los reclamos";
                           int duration = Toast.LENGTH_SHORT;

                           Toast toast = Toast.makeText (context, text, duration);
                           toast.show ();
                       }
                   }
               });


       ArrayAdapter<Ubicacion> adaptador = new ArrayAdapter<Ubicacion> (this, android.R.layout.simple_list_item_1, nuestrasubicaciones);
       lista.setAdapter (adaptador);

       lista.setClickable (true);
       lista.setOnItemClickListener (new AdapterView.OnItemClickListener () {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Log.d ("toque item", "");
               Intent intent = new Intent (misreclamos.this, DescReclamos.class);
               intent.putExtra ("objetoDetalles", (Serializable) nuestrasubicaciones.get (position));
               startActivity (intent);
           }
       });

   }
    public void ver_reclamos_recibidos(View v){
        Bundle paqueteDepto= new Bundle();
        paqueteDepto.putString("Departamento", deptoRecibido);
        paqueteDepto.putString("NombreUsuario", nombreusuariorecibido);
        Intent intent= new Intent (v.getContext (), reclamosRecibidos.class);
        intent.putExtras(paqueteDepto);
        startActivity(intent);
    }
   void ocultarBotonReclamosRecibidos(String cargoRecibido){
        if(cargoRecibido.equals ("alumno")){
            reclamosRecibidosBtn.setVisibility(View.INVISIBLE);
        }
        else{
            reclamosRecibidosBtn.setVisibility(View.VISIBLE);
        }

    }
}