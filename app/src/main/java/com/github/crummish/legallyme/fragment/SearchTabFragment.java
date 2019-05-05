package com.github.crummish.legallyme.fragment;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import com.github.crummish.legallyme.activity.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class SearchTabFragment extends BaseTitledFragment {
    private MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.search_tab_title), getString(R.string.search_tab_subtitle));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_maps, null, false);
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                //googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setCompassEnabled(true);
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                LatLng loc = new LatLng(36.846962, -76.285369);
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(loc));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(loc).zoom(16).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                //Adding dummy info markers
                LatLng norfolk_circuit_court = new LatLng(36.846445, -76.285299);
                googleMap.addMarker(new MarkerOptions().position(norfolk_circuit_court)
                        .title("Norfolk Circuit Court Clerk's Office"));
                LatLng DMV_select_norfolk = new LatLng(36.844666, -76.285637);
                googleMap.addMarker(new MarkerOptions().position(DMV_select_norfolk)
                        .title("DMV Select"));
                LatLng DMV_select_portsmouth = new LatLng(336.832869, -76.297188);
                googleMap.addMarker(new MarkerOptions().position(DMV_select_portsmouth)
                        .title("DMV Select"));
                LatLng DMV_norfolk = new LatLng(36.902605, -76.254085);
                googleMap.addMarker(new MarkerOptions().position(DMV_norfolk)
                        .title("Virginia Department of Motor Vehicles"));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}

