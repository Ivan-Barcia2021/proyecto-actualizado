package com.ort.apportresuelve;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
//import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

class AdaptadorUbicacion  extends BaseAdapter {

    ArrayList<Ubicacion> reclamosUB;
    OnCompleteListener<QuerySnapshot> context;
private Context micontexto;
String departamento;
int numero;
int codigo;
String descripcion;
ArrayList<String> misdescripciones2;
String estado="No atendido";
ArrayList<String> misdescripciones;
    AdaptadorUbicacion(Context context, ArrayList<Ubicacion> reclamosUB) {
        this.reclamosUB = reclamosUB;
        //this.context = context;
        this.micontexto=context;
    }
public void pasardatos(String dpto, int nro ){
        departamento=dpto;
        numero=nro;

}
public void pasarcodigo(int c, String d, ArrayList<String>m, ArrayList<String>m2){
        codigo=c;
        descripcion=d;
       misdescripciones2=m2;


    misdescripciones=m;
}
    @Override
    public int getCount() {
        return reclamosUB.size();
    }

    @Override
    public Object getItem(int position) {
        return reclamosUB.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater;

             inflater=(LayoutInflater)micontexto.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_reclamos, viewGroup, false);
        }



        TextView tipoReclamoAmostrar = (TextView) view.findViewById(R.id.tipodeReclamoAmostrar);
        TextView fechaAmostrar = (TextView) view.findViewById(R.id.fechaAmostrar);
        TextView miestado=(TextView) view.findViewById (R.id.miestado);
        Ubicacion p = reclamosUB.get(position);
        tipoReclamoAmostrar.setText(p.getTipoDeReclamo());
        fechaAmostrar.setText(p.getFecha());
        String mi_descripcion=p.getDescripcion ();
        if(numero==0){
            miestado.setText ("Ver reclamo");
        }
      /*  if(numero==0 && mi_descripcion.equals ("no anda el ascensor")){
            miestado.setText ("En Proceso");
        }
        if(numero==0 && mi_descripcion.equals ("Se rompio una ventana")){
            miestado.setText ("En Proceso");
        }*/
        int posicion=0;
        String desc=p.getDescripcion ();
        if(misdescripciones2.size ()>0){
            for(int i=0; i<misdescripciones2.size (); i++){
                if(mi_descripcion.equals (misdescripciones2.get (i))){
                    estado="Resuelto";
                }
            }
        }
else if(misdescripciones.size ()>0){
    for(int i= 0; i<misdescripciones.size (); i++){


        if (mi_descripcion.equals (misdescripciones.get (i))) {
            estado = "En Proceso";

        }







    }
}

 if(estado.equals ("Resuelto")){
    miestado.setText ("Resuelto");
        }
        if(estado.equals ("En Proceso")){
            miestado.setText ("En Proceso");
        }






        Log.d("TraerReclamo","CHAU");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle paquetedatos= new Bundle();
                paquetedatos.putString("descripcion", p.getDescripcion());
                paquetedatos.putString("edificio", p.getEdificio());
                paquetedatos.putString("piso", p.getPiso());
                paquetedatos.putString("aula", p.getAula());
                paquetedatos.putString("tipoDeReclamo", p.getTipoDeReclamo());
                paquetedatos.putString("fecha", p.getFecha());
                paquetedatos.putString("nombreReclamador", p.getnombreUsuario());
                paquetedatos.putString ("Departamento", departamento);
                paquetedatos.putInt ("numero", numero);
                Intent intent= new Intent(v.getContext(), DescReclamos.class );
                intent.putExtras(paquetedatos);
                micontexto.startActivity(intent);
            }
        });
        return view;

    }


}
