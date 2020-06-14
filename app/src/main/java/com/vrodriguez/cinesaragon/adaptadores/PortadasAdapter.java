package com.vrodriguez.cinesaragon.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vrodriguez.cinesaragon.DetallePeli;
import com.vrodriguez.cinesaragon.R;
import com.vrodriguez.cinesaragon.modelos.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class PortadasAdapter extends RecyclerView.Adapter<PortadasAdapter.ViewHolder> {

    private List<Pelicula> mPelicula;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView cartelpeli;

        public ViewHolder(View itemView) {
            super(itemView);

            cartelpeli = (ImageView) itemView.findViewById(R.id.cartelpeli);
        }
    }


    public PortadasAdapter(Context context, List<Pelicula> peliculas) {
        this.mPelicula = peliculas;
        this.context = context;
    }

    public PortadasAdapter (Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public PortadasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View cartelView = inflater.inflate(R.layout.portadas_layout, parent, false);
        PortadasAdapter.ViewHolder viewHolder = new PortadasAdapter.ViewHolder(cartelView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PortadasAdapter.ViewHolder viewHolder, int position) {
        final Pelicula pelicula = mPelicula.get(position);

        ImageView portada = viewHolder.cartelpeli;
        Picasso.get().load(pelicula.getImagen()).placeholder(R.drawable.cines2).into(portada);
    }

    @Override
    public int getItemCount() {
        return mPelicula.size();
    }

}
