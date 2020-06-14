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
import com.vrodriguez.cinesaragon.Cines;
import com.vrodriguez.cinesaragon.R;
import com.vrodriguez.cinesaragon.modelos.Edificio;


import java.util.List;

public class CinesAdapter extends RecyclerView.Adapter<CinesAdapter.ViewHolder> implements View.OnClickListener {

    private List<Edificio> mCine;
    private Context context;

    private View.OnClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titulo;
        public ImageView fotocine;

        public ViewHolder(View itemView) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.nombrecine);
            fotocine = (ImageView) itemView.findViewById(R.id.fotocinelista);
        }
    }

    public CinesAdapter(Context context, List<Edificio> cines) {
        this.mCine = cines;
        this.context = context;
    }

    public CinesAdapter (Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CinesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View cinesView = inflater.inflate(R.layout.lista_cines_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(cinesView);

        //Recoger un click
        cinesView.setOnClickListener(this);
        //

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CinesAdapter.ViewHolder viewHolder, int position) {

        final Edificio cine = mCine.get(position);

        TextView titulo = viewHolder.titulo;
        titulo.setText(cine.getNombre());

        ImageView fotocine = viewHolder.fotocine;
        Picasso.get().load(cine.getImagen()).placeholder(R.drawable.cines2).into(fotocine);

        //recoger un click
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cineintent = new Intent(context, Cines.class);
                cineintent.putExtra("id", cine.getId().toString());
                context.startActivity(cineintent);
            }
        });

        ///
    }

    @Override
    public int getItemCount() {
        return mCine.size();
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