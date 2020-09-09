package com.example.e_doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TypeselectActivity extends AppCompatActivity implements  GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, OnMapReadyCallback {


    GPSTracker gpsTracker;
    GoogleMap mMap;

    private FirebaseFirestore db;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeselect);

        gpsTracker = new GPSTracker(TypeselectActivity.this);
        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...!");
        progressDialog.setCancelable(false);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        progressDialog.show();
        Location current = gpsTracker.getLocation();
        final Location start = new Location("start");
        start.setLatitude(current.getLatitude());
        start.setLongitude(current.getLongitude());
        mMap = googleMap;
        mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.style_json));
        db.collection("edoctor")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Location end = new Location("end");
                                end.setLatitude(document.getDouble("latitude"));
                                end.setLongitude(document.getDouble("longitude"));
                                double distance = (start.distanceTo(end)) / 1000;
                                if (distance <= 10) {
                                    LatLng loc = new LatLng(document.getDouble("latitude"), document.getDouble("longitude"));
                                    mMap.addMarker(new MarkerOptions()
                                            .position(loc)
                                            .title(document.getString("name"))
                                            .snippet(document.getId())
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.orng)));
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_LONG).show();
                            Log.d("Data", "Error getting documents: ", task.getException());
                        }
                    }
                });
        mMap.getUiSettings().setMapToolbarEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(current.getLatitude(), current.getLongitude()), 15));
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                buildAlertMessage(TypeselectActivity.this, marker.getPosition(), marker.getTitle(), marker.getSnippet());
                return true;
            }
        });

    }

    private void buildAlertMessage(final TypeselectActivity view, final LatLng position, final String name, final String docID) {
        LayoutInflater inflater=LayoutInflater.from(view);
        View alertLayout = inflater.inflate(R.layout.custom_dailog, null);
        final TextView tvname = alertLayout.findViewById(R.id.tv_placeName);
        final MapView mapView=alertLayout.findViewById(R.id.mapDialog);
        mapView.onCreate(null);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.addMarker(new MarkerOptions()
                        .position(position)
                        .title(name)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.orng)));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,14));
                googleMap.getUiSettings().setMapToolbarEnabled(false);
            }
        });
        tvname.setText(name);
        final AlertDialog.Builder builder=new AlertDialog.Builder(view);
        builder.setView(alertLayout)
                .setCancelable(false)
                .setPositiveButton("Navigation", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri.Builder builder = new Uri.Builder();
                        builder.scheme("https")
                                .authority("www.google.com").appendPath("maps").appendPath("dir").appendPath("").appendQueryParameter("api", "1")
                                .appendQueryParameter("destination", position.latitude + "," + position.longitude);
                        String url = builder.build().toString();
                        Log.d("Directions", url);
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        view.startActivity(i);
                    }

                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog dialog=builder.create();
        dialog.show();
    }


    public void next(View view) {

        Intent intent = new Intent(TypeselectActivity.this, UserHomeActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }
}