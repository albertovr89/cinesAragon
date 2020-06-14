package com.vrodriguez.cinesaragon;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.vrodriguez.cinesaragon.modelos.Persona;

import org.parceler.Parcels;

public class MenuyCines extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView usernametxt;
    String idPersona;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private CinesAragonApplication application;
    Persona usuariologeado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuy_cines);

        Toolbar toolbar = findViewById(R.id.toolbarmenudrawer);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        usernametxt = header.findViewById(R.id.usernametxt);
        
        setTitle("Menú principal");
        setToolbar();

        application = (CinesAragonApplication )getApplication();
        usuariologeado = application.getUsuarioLogueado();
        usernametxt.setText(usuariologeado.getUsuario());

        navigationView.setNavigationItemSelectedListener(this);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void hideItems(){
        navigationView = findViewById(R.id.nav_view);
        Menu nav_menu = (Menu) navigationView.getMenu();
        ((android.view.Menu) nav_menu).findItem(R.id.nav_cines).setVisible(false);
        ((android.view.Menu) nav_menu).findItem(R.id.nav_cartelera).setVisible(false);
        ((android.view.Menu) nav_menu).findItem(R.id.nav_entradas).setVisible(false);
        ((android.view.Menu) nav_menu).findItem(R.id.nav_personal).setVisible(false);
        ((android.view.Menu) nav_menu).findItem(R.id.nav_exit).setVisible(false);
    }

    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent i;

        switch (id){

            case R.id.nav_cines:
                i = new Intent(this, ListadeCines.class);
                startActivity(i);
                break;

            case R.id.nav_cartelera:
                i = new Intent(this, Cartelera.class);
                startActivity(i);
                break;

            case R.id.nav_entradas:
                i= new Intent(this, Entradas.class);
                startActivity(i);
                break;

            case R.id.nav_personal:
                i= new Intent(this, Ajustes.class);
                startActivity(i);
                break;

            case R.id.nav_exit:
                application.setUsuarioLogueado(null);
                i= new Intent(this, MainActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }
/*
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        if(!drawer.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.activity_main_drawer, (android.view.Menu) menu);
            return true;
        }
        return super.onCreateOptionsMenu((android.view.Menu) menu);
    }
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_launcher_background);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        
    }

    public void setSupportActionBar(Toolbar toolbar) {
    }
}