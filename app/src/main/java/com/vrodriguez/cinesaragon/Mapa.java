package com.vrodriguez.cinesaragon;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final int LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private Marker markerHuesca, markerTeruel, markerVenecia, markerAragonia, markerCasa, markerYelmo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);

        googleMap.setOnMarkerClickListener(this);
        //Marcadores
        //Añadimos los marcadores de los cines en el mapa

        LatLng cineHuesca = new LatLng(42.139, -0.419);
        LatLng cineTeruel = new LatLng(40.344, -1.107);
        LatLng cineVenecia = new LatLng(41.608, -0.884);
        LatLng cineAragonia = new LatLng(41.639, -0.908);
        LatLng cineCasa = new LatLng(41.670, -0.890);
        LatLng cineYelmo = new LatLng(41.641, -1.015);

        // Add a marker in Sydney and move the camera
        markerHuesca = mMap.addMarker(new MarkerOptions().position(cineHuesca).title("Cine Mundo").icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcadorcine)));
        markerTeruel = mMap.addMarker(new MarkerOptions().position(cineTeruel).title("Cine Maravilla").icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcadorcine)));
        markerVenecia= mMap.addMarker(new MarkerOptions().position(cineVenecia).title("Cine Puerto Venecia").icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcadorcine)));
        markerAragonia= mMap.addMarker(new MarkerOptions().position(cineAragonia).title("Cine Aragonia").icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcadorcine)));
        markerCasa= mMap.addMarker(new MarkerOptions().position(cineCasa).title("Cine Gran Casa").icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcadorcine)));
        markerYelmo= mMap.addMarker(new MarkerOptions().position(cineYelmo).title("Cine Yelmo").icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcadorcine)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cineVenecia));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            // ¿Permisos asignados?
            if (permissions.length > 0 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(this, "Error de permisos", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {



        if (marker.equals(markerHuesca)) {
            Intent cine = new Intent (Mapa.this, Cines.class);
            cine.putExtra("id",1);
            startActivity(cine);
        } else if (marker.equals(markerTeruel)) {
            Intent cine = new Intent (Mapa.this, Cines.class);
            cine.putExtra("id", 2);
            startActivity(cine);
        } else if (marker.equals(markerVenecia)) {
            Intent cine = new Intent (Mapa.this, Cines.class);
            cine.putExtra("id", 3);
            startActivity(cine);
        } else if (marker.equals(markerAragonia)) {
            Intent cine = new Intent (Mapa.this, Cines.class);
            cine.putExtra("id", 4);
            startActivity(cine);
        } else if (marker.equals(markerCasa)) {
            Intent cine = new Intent (Mapa.this, Cines.class);
            cine.putExtra("id", 5);
            startActivity(cine);
        } else if (marker.equals(markerYelmo)) {
            Intent cine = new Intent (Mapa.this, Cines.class);
            cine.putExtra("id", 6);
            startActivity(cine);
        }
        return false;
    }
}
