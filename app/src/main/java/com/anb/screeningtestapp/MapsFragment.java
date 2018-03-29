package com.anb.screeningtestapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.anb.screeningtestapp.Adapter.MapAdapter;
import com.anb.screeningtestapp.model.Event;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private MapView mapView;
    private RecyclerView recyclerMaps;
    private ArrayList<Event> listEvent;
    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_maps, container, false);
        listEvent = EventActivity.eventlist;
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mapView = mView.findViewById(R.id.maps);
        recyclerMaps = mView.findViewById(R.id.recyclerMaps);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        MapAdapter mapAdapter = new MapAdapter(getActivity(), listEvent);
        recyclerMaps.setHasFixedSize(true);
        recyclerMaps.setLayoutManager(mLayoutManager);
        recyclerMaps.setAdapter(mapAdapter);

        recyclerMaps.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(listEvent.get(newState).laT, listEvent.get(newState).lonG))
                        .title(listEvent.get(newState).nama));
                marker.showInfoWindow();
                CameraPosition cameraPosition = CameraPosition.builder().target(marker.getPosition()).zoom(14).bearing(0).tilt(0).build();
                mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        for (Event event : listEvent) {
            mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(event.laT, event.lonG)).title(event.nama));
        }

        CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(listEvent.get(0).laT, listEvent.get(0).lonG)).zoom(14).bearing(0).tilt(0).build();
        mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
