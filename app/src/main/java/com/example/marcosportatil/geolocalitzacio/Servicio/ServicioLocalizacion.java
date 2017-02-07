package com.example.marcosportatil.geolocalitzacio.Servicio;

import android.location.Location;
import android.os.AsyncTask;
import android.widget.Button;
import com.example.marcosportatil.geolocalitzacio.Localizacion.Localitzacio;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ALUMNEDAM on 25/01/2017.
 */

public class ServicioLocalizacion extends AsyncTask<String,Integer,Boolean> implements LocationListener {
    double lat,longitud;
    Button button;
    Localitzacio localitzacio = new Localitzacio();
public void proceso() {
    Thread thread = new Thread(new Runnable() {
        public void run() {
           lat =  localitzacio.getlatitudDoueble();
            longitud = localitzacio.getlongitudDouble();
            //GoogleMap map = new GoogleMap();
         //   onMapReady(map);
        }
    });
    for(int i=1; i<=10; i++) {
                proceso();
    }
}


        public void onMapReady(GoogleMap map) {
            map.addMarker(new MarkerOptions().position(new LatLng(lat, longitud)).title("Marker"));

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    protected Boolean doInBackground(String... params) {
        if (params.equals("Matricula")) {
            lat = localitzacio.getlatitudDoueble();
            longitud = localitzacio.getlongitudDouble();
        }
        return true;
    }
}
