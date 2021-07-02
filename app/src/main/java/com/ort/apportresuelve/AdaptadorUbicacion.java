package com.ort.apportresuelve;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
//import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;
/*
    public class AdaptadorUbicacion extends RecyclerView.Adapter<AdaptadorUbicacion.ViewHolder> {

        private List<Ubicacion> ubicacions;
        private Context context;
        private FirebaseFirestore firestoreDB;

        public AdaptadorUbicacion(List<Ubicacion> ubicaciones, Context context, FirebaseFirestore firestoreDB) {
            this.ubicacions = ubicaciones;
            this.context = context;
            this.firestoreDB = firestoreDB;
        }

        @Override
        public AdaptadorUbicacion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_reclamos, parent, false);

            return new AdaptadorUbicacion.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AdaptadorUbicacion.ViewHolder holder, int position) {
            final int itemPosition = position;
            final Ubicacion ubi = Ubicacion.get(itemPosition);
            // progressBar.setVisibility(View.GONE);
            holder.nombre.setText(ubi.getEdificio());
            holder.division.setText(ubi.getPiso());
            holder.itemView.setOnClickListener(v ->
            {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("edificio", ubi.getEdificio());
                intent.putExtra("piso", ubi.getPiso());
                intent.putExtra("aula", ubi.getAula());
                intent.putExtra("descripcion", ubi.getDescripcion());
                intent.putExtra("tipoDeReclamo", ubi.getTipoDeReclamo());

                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return alumnos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.nombre)
            TextView nombre;
            @BindView(R.id.division)
            TextView division;
            @BindView(R.id.calificacion)
            TextView calificacion;

        }


    }
    */
