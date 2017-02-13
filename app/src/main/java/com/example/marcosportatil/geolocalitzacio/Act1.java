package com.example.marcosportatil.geolocalitzacio;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

/**
 * Created by Jaume on 13/02/2017.
 */

public class Act1 extends Activity implements OnMapReadyCallback {
    private GoogleMap map;
    private Marker marcadador;
    double lat = 0.0;
    double lng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportMapFragment()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        miUbicacion();

        //     LatLng sydney = new LatLng(-34,151);
        //   map.addMarker(new MarkerOptions().position(sydney).title("Estas aqui"));
        // map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbi = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);

        if (marcadador == null) marcadador.remove();
        marcadador = map.addMarker(new MarkerOptions()
                .postion(coordenadas)
                .title("Estas aqui")
                .icon());
        map.animateCamera(miUbi);

    }


    private void actulizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actulizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void miUbicacion() {

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actulizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,locationListener);
    }

}
