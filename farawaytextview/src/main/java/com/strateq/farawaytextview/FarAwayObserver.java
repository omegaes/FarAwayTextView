package com.strateq.farawaytextview;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by abdulrahmanbabil on 1/11/18.
 */

public class FarAwayObserver extends Observable implements LocationListener {

    private static FarAwayObserver instance;
    protected LocationManager locationManager;


    @Override
    public synchronized boolean hasChanged() {
        return true;
    }

    public static FarAwayObserver getInstance(Context context) {
        if (instance == null)
            instance = new FarAwayObserver(context);

        return instance;
    }

    private FarAwayObserver(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        LocationPermissionGranted(context);
    }

    public void LocationPermissionGranted(Context context) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, this);

        notifyObservers(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
        notifyObservers(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));

    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
        shutDown();
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
        if (countObservers() == 0) {
            shutDown();
        }
    }

    private void shutDown() {
        locationManager.removeUpdates(this);
        instance = null;
    }


    @Override
    public void onLocationChanged(Location location) {
        notifyObservers(location);
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
}
