package com.example.splashscreen;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.splashscreen.response_object.DriverLocationResponseObject;
import com.example.splashscreen.response_object.DriverResponseObject;
import com.example.splashscreen.services.EndPoints;
import com.example.splashscreen.services.Repository;
import com.example.splashscreen.services.RetrofitClientInstance;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;

import com.google.android.gms.internal.location.zzz;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.splashscreen.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(6.0f);
        LocationCallback locationCallback;
        final Marker[] busMarker = new Marker[1];
        LatLng myPosition;
        ProgressBar pgbar = (ProgressBar) findViewById(R.id.progressBar);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Timer timerObj = new Timer();
        TimerTask timerTaskObj = new TimerTask() {
            public void run() {
                //perform your action here
            }
        };
        timerObj.schedule(timerTaskObj, 0, 15000);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            LatLng coordinate = new LatLng(latitude, longitude);
                            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 19);
                            LatLng userLocation = new LatLng(latitude, longitude);
                            busMarker[0] =mMap.addMarker(new MarkerOptions().position(userLocation).title("User Location"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
                            mMap.animateCamera(yourLocation);
                            Date date = new Date();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
                            String currentDateTime = format.format(date);
                            createDriverLocation(
                                    "11",
                                    Double.toString(location.getLatitude()), Double.toString(location.getLongitude()),
                                    currentDateTime
                            );
                        }
                    }
                });
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(300000000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Toast.makeText(MapsActivity.this, "Location updating", Toast.LENGTH_SHORT).show();
                pgbar.setVisibility(View.INVISIBLE);
                busMarker[0].remove();
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    LatLng coordinate = new LatLng(latitude, longitude);
                    CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 19);
                    LatLng userLocation = new LatLng(latitude, longitude);
                    busMarker[0]=mMap.addMarker(new MarkerOptions().position(userLocation).title("User Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
                    mMap.animateCamera(yourLocation);
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
                    String currentDateTime = format.format(date);
                    createDriverLocation(
                            "11",
                            Double.toString(location.getLatitude()), Double.toString(location.getLongitude()),
                            currentDateTime
                    );
                    pgbar.setVisibility(View.VISIBLE);
                }
            }
        };

// Register the listener with the Location Manager to receive location updates
        try {
            fusedLocationClient.requestLocationUpdates(mLocationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                     /* Looper */);

        }
        catch (SecurityException e) {
            // lets the user know there is a problem with the gps
        }
    }

    private void createDriverLocation(

                    String driver_id,
                    String map_lat,
                    String map_long,
                    String datetime_added) {
        Repository repository = RetrofitClientInstance.getRetrofitInstance(EndPoints.BASE_URL).create(Repository.class);
        Call<DriverLocationResponseObject> call = repository.createDriverLocation(
                driver_id,
                map_lat,
                map_long,
                datetime_added
        );
        call.enqueue(new Callback<DriverLocationResponseObject>() {
            @Override
            public void onResponse(Call<DriverLocationResponseObject> call, retrofit2.Response<DriverLocationResponseObject> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Toast.makeText(MapsActivity.this, "Location Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Location could not be updated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DriverLocationResponseObject> call, Throwable t) {
                System.out.println();
                Toast.makeText(getBaseContext(), "" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }


}