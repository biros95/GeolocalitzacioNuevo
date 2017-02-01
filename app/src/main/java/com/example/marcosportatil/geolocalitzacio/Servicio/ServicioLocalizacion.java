package com.example.marcosportatil.geolocalitzacio.Servicio;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.marcosportatil.geolocalitzacio.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.LocationSource;

/**
 * Created by ALUMNEDAM on 25/01/2017.
 */

public class ServicioLocalizacion extends AsyncTask<Void,Integer,Boolean> implements LocationListener {

public void proceso() {
    Thread thread = new Thread(new Runnable() {
        public void run() {
            //TODO BUSCAR LOCALIZACION

        }
    });


    for(int i=1; i<=10; i++) {
                tareaLarga();
                pbarProgreso.post(new Runnable() {
                    public void run() {
                        pbarProgreso.incrementProgressBy(10);
                    }
                });
            }

            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(MainHilos.this, "Tarea finalizada!",
                            Toast.LENGTH_SHORT).show();
                }
            });
    }).start();







    private void tareaLarga()
    {
        onLocationChanged(loc);
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
