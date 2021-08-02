package com.ort.apportresuelve;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
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

    AdaptadorUbicacion(OnCompleteListener<QuerySnapshot> context, ArrayList<Ubicacion> reclamosUB, Context contextoausar) {
        this.reclamosUB = reclamosUB;
        this.context = context;
        micontexto=contextoausar;
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
        TextView descAmostrar = (TextView) view.findViewById(R.id.descripcionAmostrar);
        TextView edificioAmostrar = (TextView) view.findViewById(R.id.edificioAmostrar);
        TextView pisoAmostrar = (TextView) view.findViewById(R.id.pisoAmostrar);
        TextView aulaAmostrar = (TextView) view.findViewById(R.id.aulaAmostrar);

        Ubicacion p = reclamosUB.get(position);
        tipoReclamoAmostrar.setText(p.getTipoDeReclamo());
        descAmostrar.setText(p.getDescripcion());
        edificioAmostrar.setText(p.getEdificio());
        pisoAmostrar.setText(p.getPiso());
        aulaAmostrar.setText(p.getAula());
        Log.d("TraerReclamo","CHAU");
        return view;

    }


}
