package com.vrodriguez.cinesaragon.adaptadores;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vrodriguez.cinesaragon.R;
import com.vrodriguez.cinesaragon.modelos.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class PelisAdapter extends RecyclerView.Adapter<PelisAdapter.ViewHolder> implements Filterable {

    private ArrayList<Pelicula> persons;
    private ArrayList<Pelicula> personsFilter;
    private FiltroCustom mFilter;

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
    public PelisAdapter(List<Pelicula> peliculas) {
        mPelicula = peliculas;
    }


    @NonNull
    @Override
    public PelisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View pelisView = inflater.inflate(R.layout.lista_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(pelisView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PelisAdapter.ViewHolder viewHolder, int position) {

        Pelicula pelicula = mPelicula.get(position);

        TextView titulo = viewHolder.titulo;
        titulo.setText(pelicula.getTitulo());
        TextView genero = viewHolder.genero;
        genero.setText(pelicula.getGenero());
        TextView clasi = viewHolder.clasi;
        clasi.setText(pelicula.getClasi());

        ImageView portada = viewHolder.portada;
        portada

    }

    @Override
    public int getItemCount() {
        return mPelicula.size();
    }

    public class FiltroCustom extends Filter {
         private ListAdapter listAdapter;

         private FiltroCustom(ListAdapter listAdapter) {
             super();
             this.listAdapter = listAdapter;
         }

        @Override
        protected FilterResult hacerfiltro(CharSequence constraint) {
             personsFilter.clear();
             final FilterResults results = new FilterResults();
             if(constraint.length() == 0) {
                 personsFilter.addAll(mPelicula);
             } else {
                 final String filterPattern = constraint.toString().toLowerCase().trim();
                 for(final Pelicula pelicula:mPelicula) {
                     if(pelicula.getGenero().toLowerCase().contains(filterPattern)) {
                         personsFilter.add(pelicula)
                     }
                 }
             }
             results.values = personsFilter;
             results.count = personsFilter.size();
             return results;
         }

         @Override
        protected void publishResults(CharSequence constraint, FilterResults results){
             this.listAdapter.notifyDataSetChanged();
         }
    }
}
