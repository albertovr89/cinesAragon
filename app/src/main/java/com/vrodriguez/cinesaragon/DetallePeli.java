package com.vrodriguez.cinesaragon;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.squareup.picasso.Picasso;
import com.vrodriguez.cinesaragon.modelos.Pelicula;

import org.parceler.Parcels;

import java.util.Objects;

public class DetallePeli extends AppCompatActivity {

    TextView titulotxt, clasitxt, generotxt, sinopsistxt, duraciontxt, estrellastxt;
    VideoView trailer;
    ImageView foto1, foto2, foto3, foto4;
    String urltrailer;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_peli);

        titulotxt = findViewById(R.id.nombrepelitoolbar);
        trailer = findViewById(R.id.trailer);
        clasitxt = findViewById(R.id.clasitxt);
        generotxt = findViewById(R.id.generotxt);
        sinopsistxt = findViewById(R.id.sinopsistxt);
        duraciontxt = findViewById(R.id.duraciontxt);
        estrellastxt = findViewById(R.id.estrellastxt);
        foto1 = findViewById(R.id.foto1);
        foto2 = findViewById(R.id.foto2);
        foto3 = findViewById(R.id.foto3);
        foto4 = findViewById(R.id.foto4);

        Toolbar toolbar = findViewById(R.id.toolbarpeli);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Pelicula pelicula = Parcels.unwrap(getIntent().getParcelableExtra("pelicula"));
        assert pelicula != null;
        titulotxt.setText(pelicula.getTitulo());
        clasitxt.setText(pelicula.getClasi());
        generotxt.setText(pelicula.getGenero());
        sinopsistxt.setText(pelicula.getSinopsis());
        duraciontxt.setText(pelicula.getDuracion());
        estrellastxt.setText(pelicula.getEstrellas());
        urltrailer = pelicula.getTrailer();

        Picasso.get()
                .load(pelicula.getFoto1()).fit().centerCrop()
                .error(R.drawable.cines2)
                .into(foto1);

        Picasso.get()
                .load(pelicula.getFoto2()).fit().centerCrop()
                .error(R.drawable.cines2)
                .into(foto2);

        Picasso.get()
                .load(pelicula.getFoto3()).fit().centerCrop()
                .error(R.drawable.cines2)
                .into(foto3);

        Picasso.get()
                .load(pelicula.getFoto4()).fit().centerCrop()
                .error(R.drawable.cines2)
                .into(foto4);


        trailer.setVideoPath("http://192.168.1.129/cinesAragon/raw/"+urltrailer);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(trailer);
        trailer.setMediaController(mediaController);
        trailer.requestFocus();
        trailer.seekTo(10000);
        trailer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                if(trailer.performClick()){
                    trailer.seekTo(0);
                    trailer.start();
                }
            }
        });
    }
}