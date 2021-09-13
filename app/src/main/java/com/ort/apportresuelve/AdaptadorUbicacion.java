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

import java.util.ArrayList;
import java.util.List;

class AdaptadorUbicacion  extends BaseAdapter {

    ArrayList<Ubicacion> reclamosUB;
    OnCompleteListener<QuerySnapshot> context;
private Context micontexto;

    AdaptadorUbicacion(Context context, ArrayList<Ubicacion> reclamosUB) {
        this.reclamosUB = reclamosUB;
        //this.context = context;
        this.micontexto=context;
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

        Ubicacion p = reclamosUB.get(position);
        tipoReclamoAmostrar.setText(p.getTipoDeReclamo());
        fechaAmostrar.setText(p.getFecha());

        usuario nuestroUsuario = (usuario) new usuario();


        Log.d("TraerReclamo","CHAU");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("toque item 2",p.getDescripcion());
                Toast.makeText(micontexto,"Toque item", Toast.LENGTH_SHORT).show();
                Bundle paquetedatos= new Bundle();
                paquetedatos.putString("descripcion", p.getDescripcion());
                paquetedatos.putString("edificio", p.getEdificio());
                paquetedatos.putString("piso", p.getPiso());
                paquetedatos.putString("aula", p.getAula());
                paquetedatos.putString("tipoDeReclamo", p.getTipoDeReclamo());
                paquetedatos.putString("fecha", p.getFecha());
                paquetedatos.putString("nombreReclamador", nuestroUsuario.getNombre());
                Intent intent= new Intent(v.getContext(), DescReclamos.class );
                intent.putExtras(paquetedatos);
                micontexto.startActivity(intent);
            }
        });
        return view;

    }


}
