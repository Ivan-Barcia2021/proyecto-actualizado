package com.ort.apportresuelve;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
//import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

class AdaptadorUbicacion extends BaseAdapter {

    ArrayList<Ubicacion> reclamosUB;
    OnCompleteListener<QuerySnapshot> context;
    private Context micontexto;
    String departamento;
    int numero;
    String estado;

    AdaptadorUbicacion(Context context, ArrayList<Ubicacion> reclamosUB) {
        this.reclamosUB = reclamosUB;
        //this.context = context;
        this.micontexto = context;
    }

    public void pasardatos(String dpto, int nro) {
        numero = nro;
        departamento = dpto;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater;

            inflater = (LayoutInflater) micontexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_reclamos, viewGroup, false);
        }

        TextView tipoReclamoAmostrar = (TextView) view.findViewById(R.id.tipodeReclamoAmostrar);
        TextView fechaAmostrar = (TextView) view.findViewById(R.id.fechaAmostrar);
        TextView miestado = (TextView) view.findViewById(R.id.miestado);
        Button botonColorEstado = view.findViewById(R.id.botonEstado);
        Ubicacion p = reclamosUB.get(position);
        tipoReclamoAmostrar.setText(p.getTipoDeReclamo());
        fechaAmostrar.setText(p.getFecha());
        estado = p.getEstadoDelReclamo();



        if (estado.equals("Resuelto")) {
            miestado.setText("Resuelto");
            botonColorEstado.setBackgroundColor(Color.parseColor("#1EAE0F"));
            botonColorEstado.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1EAE0F")));
        }
        else if (estado.equals("En Proceso")) {
            miestado.setText("En Proceso");
            botonColorEstado.setBackgroundColor(Color.parseColor("#CAA409"));
            botonColorEstado.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#CAA409")));
        }


        Log.d("TraerReclamo", "CHAU");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle paquetedatos = new Bundle();
                paquetedatos.putString("descripcion", p.getDescripcion());
                paquetedatos.putString("edificio", p.getEdificio());
                paquetedatos.putString("piso", p.getPiso());
                paquetedatos.putString("aula", p.getAula());
                paquetedatos.putString("tipoDeReclamo", p.getTipoDeReclamo());
                paquetedatos.putString("fecha", p.getFecha());
                paquetedatos.putString("nombreReclamador", p.getnombreUsuario());
                paquetedatos.putString("estadoDelReclamo", p.getEstadoDelReclamo());
                paquetedatos.putString("Departamento", departamento);
                paquetedatos.putInt("numeroActivity", numero);
                paquetedatos.putString("id", p.getId());
                Intent intent = new Intent(v.getContext(), DescReclamos.class);
                intent.putExtras(paquetedatos);
                micontexto.startActivity(intent);
            }
        });
        return view;

    }


}
