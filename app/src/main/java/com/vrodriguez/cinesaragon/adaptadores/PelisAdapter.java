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

public class PelisAdapter extends RecyclerView.Adapter<PelisAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Pelicula> persons;
    private ArrayList<Pelicula> personsFilter;
    private Context context;

//Recoger un click ???
    private View.OnClickListener listener;




    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titulo,genero,clasi;
        public ImageView portada;

        public ViewHolder(View itemView) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.titulotxt);
            genero = (TextView) itemView.findViewById(R.id.generotxt);
            clasi = (TextView) itemView.findViewById(R.id.clasitxt);
            portada = (ImageView) itemView.findViewById(R.id.portada);
        }
    }

    private List<Pelicula> mPelicula;
    public PelisAdapter(Context context, List<Pelicula> peliculas) {
        this.mPelicula = peliculas;
        this.context = context;
    }

    public PelisAdapter (Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public PelisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View pelisView = inflater.inflate(R.layout.lista_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(pelisView);

        //Recoger un click
        pelisView.setOnClickListener(this);
        //

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PelisAdapter.ViewHolder viewHolder, int position) {

        final Pelicula pelicula = mPelicula.get(position);

        TextView titulo = viewHolder.titulo;
        titulo.setText(pelicula.getTitulo());
        TextView genero = viewHolder.genero;
        genero.setText(pelicula.getGenero());
        TextView clasi = viewHolder.clasi;
        clasi.setText(pelicula.getClasi());

        ImageView portada = viewHolder.portada;
        Picasso.get().load(pelicula.getImagen()).placeholder(R.drawable.cines2).into(portada);

        //recoger un click
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pelintent = new Intent(context, DetallePeli.class);
                pelintent.putExtra("id", pelicula.getId().toString());
                context.startActivity(pelintent);
            }
        });

        ///
    }

    @Override
    public int getItemCount() {
        return mPelicula.size();
    }

//recoger un click???

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener != null) listener.onClick(v);
    }
    //
}
