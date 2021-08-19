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
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class reclamosRecibidos extends AppCompatActivity {

    //DatabaseReference bdd= FirebaseDatabase.getInstance ().getReference ();
    FirebaseFirestore bdd = FirebaseFirestore.getInstance();
    CollectionReference reclamos =bdd.collection("Reclamos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_misreclamos);
        mostrar();
    }
    public void pasar(View v){
        Intent intent= new Intent (v.getContext (), login.class);
        startActivity (intent);
    }


    public void mostrar(){
        ArrayList<Ubicacion> nuestrosReclamosRecibidos = new ArrayList<>();
        //Ubicacion nuestroReclamo = (Ubicacion) document.toObject(Ubicacion.class);


        bdd.collection("Reclamos")
                .whereEqualTo("tipoDeReclamo", "Mantenimiento")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot> () {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                nuestrosReclamosRecibidos.add(document.toObject(Ubicacion.class));


                            }
                            Log.d("TraerReclamosRecibidos","Recibido");
                            //for recorriendo nuestrasubicaciones para obtener todas las ubicaciones y mostrarlas en la list view con sus atributos.
                            TextView miseleccion =findViewById (R.id.seleccion);
                            ListView lista=findViewById (R.id.mlista);

                            Context contexto = null;
                            AdaptadorUbicacion ubis= new AdaptadorUbicacion( reclamosRecibidos.this,nuestrosReclamosRecibidos); //(this, android.R.layout.simple_list_item_1 , nuestrasubicaciones);
                            lista.setAdapter (ubis);
                        } else {
                            //MUESTRO ERROR
                        }
                    }
                });

        TextView seleccion=findViewById (R.id.seleccion);
        TextView seleccion2=findViewById (R.id.seleccion2);

        ArrayAdapter<Ubicacion> adaptador= new ArrayAdapter<Ubicacion> (this, android.R.layout.simple_list_item_1 , nuestrosReclamosRecibidos);
        ListView milista=findViewById (R.id.mlista);
        milista.setAdapter (adaptador);
        milista.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                seleccion.setText (parent.getItemAtPosition (position).toString ());
                Log.d("EstoyTrayendo",seleccion.toString());


            }
        });

    }
    public void ver_reclamos_recibidos(View v){
        Intent i= new Intent (v.getContext (), reclamosRecibidos.class);
        startActivity (i);
    }
}