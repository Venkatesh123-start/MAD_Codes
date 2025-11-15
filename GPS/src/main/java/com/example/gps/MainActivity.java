package com.example.gps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull; // Import this
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView latitudeText, longitudeText;
    private LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitudeText = findViewById(R.id.textView2);
        longitudeText = findViewById(R.id.textView3);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Check for permissions and request if not granted
        if (checkLocationPermission()) {
            startLocationUpdates(); // Start updates if permission is already granted
        } else {
            requestLocationPermission(); // Request permission
        }
    }

    private boolean checkLocationPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_LOCATION);
    }

    private void startLocationUpdates() {
        // This check is necessary again because this method might be called from onCreate
        // where the check might have just passed, or from onRequestPermissionsResult
        if (!checkLocationPermission()) {
            // This should not happen if logic is correct, but as a safeguard
            return;
        }

        // Use try-catch for security exception
        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);

            if (provider != null && !provider.equals("")) {
                Location location = locationManager.getLastKnownLocation(provider);
                // Request updates: (provider, minTimeMs, minDistanceM, listener)
                locationManager.requestLocationUpdates(provider, 2000, 1, this); // Changed to 2 sec for faster testing

                if (location != null) {
                    onLocationChanged(location);
                } else {
                    Toast.makeText(this, "Searching for location...", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No location provider found!", Toast.LENGTH_LONG).show();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(this, "Permission error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        // Updated text setting
        latitudeText.setText("Latitude : " + location.getLatitude());
        longitudeText.setText("Longitude : " + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Toast.makeText(this, "Please enable GPS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        // You could restart updates here if needed
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Deprecated and not often used
    }

    // --- THIS IS THE MISSING METHOD ---
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was GRANTED
                startLocationUpdates();
            } else {
                // Permission was DENIED
                Toast.makeText(this, "Permission denied. Location cannot be shown.", Toast.LENGTH_LONG).show();
                latitudeText.setText("Latitude : Permission Denied");
                longitudeText.setText("Longitude : Permission Denied");
            }
        }
    }

    // Stop listening for updates when the app is paused to save battery
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    // Resume listening when the app is resumed
    @Override
    protected void onResume() {
        super.onResume();
        if (checkLocationPermission()) {
            startLocationUpdates();
        }
    }
}