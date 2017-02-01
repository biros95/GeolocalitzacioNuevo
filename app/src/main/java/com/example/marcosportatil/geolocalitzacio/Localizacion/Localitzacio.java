package com.example.marcosportatil.geolocalitzacio.Localizacion;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import static com.google.android.gms.analytics.internal.zzy.e;

/**
 * Created by ALUMNEDAM on 24/01/2017.
 */

public class Localitzacio extends Service implements  GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, LocationListener {
    double lblLatitud = 0;
    double lblLongitud = 0;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    //  String btnActualizar = (ToggleButton) findViewById(R.id.btnActualizar);
    GoogleApiClient apiClient;
    private Location loc;

    public Localitzacio(double lblLatitud, double lblLongitud) {
        this.lblLatitud = lblLatitud;
        this.lblLongitud = lblLongitud;
    }

    public double getLblLongitud() {
        return lblLongitud;
    }

    public void setLblLongitud(double lblLongitud) {
        this.lblLongitud = lblLongitud;
    }

    public void setLblLatitud(double lblLatitud) {
        this.lblLatitud = lblLatitud;
    }

    public double getLblLatitud() {
        return lblLatitud;
    }

    private void updateUI(Location loc) {
        if (loc != null) {
            //  lblLatitud = ("Latitud: " + String.valueOf(loc.getLatitude()));
            //lblLongitud = ("Longitud: " + String.valueOf(loc.getLongitude()));
        } else {
            //  lblLatitud = ("Latitud: (desconocida)");
            // lblLongitud = ("Longitud: (desconocida)");
        }
    }

    @Override
    public void onLocationChanged(Location loc) {
        // Este mŽtodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
        // debido a la deteccion de un cambio de ubicacion
        loc.getLatitude();
        loc.getLongitude();
        String Text = "Mi ubicacon actual es: " + "\n Lat = "
                + loc.getLatitude() + "\n Long = " + loc.getLongitude();
        lblLatitud = loc.getLatitude();
        lblLongitud = loc.getLongitude();
        this.setLocation(loc);
    }

    public void setLocation(Location loc) {
        //Obtener la direcci—n de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            this.loc = loc;
        }






    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}



