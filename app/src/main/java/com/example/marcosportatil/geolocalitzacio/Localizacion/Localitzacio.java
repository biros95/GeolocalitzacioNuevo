package com.example.marcosportatil.geolocalitzacio.Localizacion;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcosportatil.geolocalitzacio.MainActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by ALUMNEDAM on 24/01/2017.
 */

public class Localitzacio extends Service {
    double latitud = 0;
    double longitud = 0;
    String date, matricula;
    private LocationManager locationManager;
    private LocationListener listener;
    private Location loc;
    private GoogleApiClient apiClient;

    private TextView lblLatitud;
    private TextView lblLongitud;

    private LocationRequest locRequest;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("info", "estoy aqui");

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location loc) {
                //Inicializamos la clase interna
                TareaAsincrona tareaAsincrona = new TareaAsincrona();
                //Obtenemos latitud y longitud
                latitud = loc.getLatitude();
                longitud = loc.getLongitude();
                //Obtenemos la fecha actual
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date today = Calendar.getInstance().getTime();
                date = df.format(today);
                //Metodo que inicia la clase interna con AsyncTask
                tareaAsincrona.execute();
            }


            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }


        };
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, listener);

    }

    /**
     * Metodo que inicia el Service, le llega un intent con los parametros llegados de la MainActivity
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Iniciando servicio", Toast.LENGTH_SHORT).show();
        matricula = intent.getStringExtra("matricula");
        Toast.makeText(this, "" + matricula, Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @SuppressWarnings("MissingPermission")
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates((android.location.LocationListener) listener);
        }
    }

    class TareaAsincrona extends AsyncTask<Void, Void, Boolean> {

        Button button;
        Localitzacio localitzacio = new Localitzacio();

        public TareaAsincrona() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //Boolean utilizado para saber si se ha insertado o no la ubicacion
            boolean resul = true;
            //Inicializamos el tipo HttpClient
            HttpClient httpClient = new DefaultHttpClient();
            //Creamos un HttpPost con la IP de nuestro WebService para realizar los Insert Intos
            HttpPost post = new HttpPost("http://192.168.1.133:8080/WebClientRest/webresources/generic");
            post.setHeader("content-type", "application/json");
            try {
                //Creamos un objeto JSON
                JSONObject ubicacion = new JSONObject();
                //Introducimos el objeto JSON los atributos que queremos que tenga
                ubicacion.put("matricula", matricula);
                ubicacion.put("latitud", latitud);
                ubicacion.put("longitud", longitud);
                ubicacion.put("data", date);
                //Creamos un tipo StringEntity para convertir el JSON a String
                StringEntity entity = new StringEntity(ubicacion.toString());
                post.setEntity(entity);
                //Creamos un HttpResponse para ejecutar la sentencia POST
                HttpResponse resp = httpClient.execute(post);
                String respStr = EntityUtils.toString(resp.getEntity());

                if (!respStr.equals("true")) {
                    resul = true;
                }


            } catch (Exception e) {
                Log.e("ServicioRest", "Error!", e);
                resul = false;
            }
            return resul;
        }
    }
}