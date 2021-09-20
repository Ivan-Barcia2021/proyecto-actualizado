package com.ort.apportresuelve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
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
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class login extends AppCompatActivity {
    Button botonpresionado;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> DatosLista;
    ArrayList<String> DatosLista2;
    ArrayAdapter<String> Adaptador;
    ArrayAdapter<String> Adaptador2;
    String textoSeleccionado;
    String textoSeleccionado2;
    EditText aula;
    Button botonContinuar;
    String nombreusuario;

    EditText mitexto;

    String tipoDeReclamoSeleccionado;
    Date d = new Date(); CharSequence fechaFormat = DateFormat.format("d/MM", d.getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);


        botonContinuar = findViewById (R.id.boton1);
        aula = findViewById (R.id.aula);
        mitexto = findViewById (R.id.mitexto);
        DatosLista = new ArrayList<> ();
        DatosLista.add ("1 Yatay");
        DatosLista.add ("2 Rio");
        DatosLista.add ("4 Yatay Nuevo");

        Adaptador = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, DatosLista);
        Adaptador.setDropDownViewResource ((android.R.layout.simple_dropdown_item_1line));

        Spinner spnOpciones = (Spinner) findViewById (R.id.spinner1);
        spnOpciones.setAdapter (Adaptador);
        spnOpciones.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textoSeleccionado = spnOpciones.getItemAtPosition (position).toString ();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Bundle paqueterecibido=this.getIntent().getExtras();
        nombreusuario=paqueterecibido.getString("nombre");


        DatosLista2 = new ArrayList<> ();
        DatosLista2.add ("PB");
        DatosLista2.add ("1");
        DatosLista2.add ("2");
        DatosLista2.add ("3");
        DatosLista2.add ("4");

        Adaptador2 = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, DatosLista2);
        Adaptador2.setDropDownViewResource ((android.R.layout.simple_dropdown_item_1line));

        Spinner spinner2 = (Spinner) findViewById (R.id.spinner2);
        spinner2.setAdapter (Adaptador2);
        spinner2.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textoSeleccionado2 = spinner2.getItemAtPosition (position).toString ();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        botonContinuar.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String edificio;
                String aulaIngresada;
                String piso;
                String descripcion;
                botonpresionado = (Button) v;
                aulaIngresada = aula.getText ().toString ();
                edificio = textoSeleccionado;
                piso = textoSeleccionado2;
                descripcion = mitexto.getText ().toString ();

                Map<String, Object> registro = new HashMap<> ();
                registro.put ("edificio", edificio);
                registro.put ("piso", piso);
                registro.put ("aula", aulaIngresada);
                registro.put ("descripcion", descripcion);
                registro.put("tipoDeReclamo", tipoDeReclamoSeleccionado);
                registro.put("fecha", fechaFormat);
                registro.put("nombreUsuario", nombreusuario);
                db.collection ("Reclamos").add(registro).addOnSuccessListener (new OnSuccessListener<DocumentReference> () {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Reclamos", "se creo el reclamo" + documentReference.getId ());
                    }

                }).addOnFailureListener (new OnFailureListener () {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Reclamos", "NO se creo el reclamo"+ e);
                    }
                });

                Bundle paquete;
                paquete = new Bundle ();
                paquete.putString ("piso", piso);
                paquete.putString ("edificio", edificio);
                paquete.putString ("aulaIngresada", aulaIngresada);
                paquete.putString ("descripcion", descripcion);
                paquete.putString("tipoDeReclamo", tipoDeReclamoSeleccionado);
                paquete.putString("fecha", (String) fechaFormat);
                paquete.putString("nombreUsuario", nombreusuario);
                Intent myIntent = new Intent (v.getContext (), misreclamos.class);
                startActivity (myIntent);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.mantenimientoBoton:
                if (checked)
                    // selecciono mantenimiento
                    tipoDeReclamoSeleccionado="Mantenimiento";
                break;
            case R.id.tecnologiaBoton:
                if (checked)
                    // selecciono tecnologia
                    tipoDeReclamoSeleccionado="Tecnologia";
                break;
            case R.id.vinculosBoton:
                if (checked)
                    // selecciono vinculos
                    tipoDeReclamoSeleccionado="Vinculos";
                break;
        }
    }

   }