package com.anb.screeningtestapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anb.screeningtestapp.Adapter.MapAdapter;
import com.anb.screeningtestapp.model.Event;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback, ViewPager.OnPageChangeListener {

    private GoogleMap mGoogleMap;
    private MapView mapView;
    private ViewPager mapsPager;
    private ArrayList<Event> listEvent;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        listEvent = EventActivity.eventlist;
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mapView = mView.findViewById(R.id.maps);
        mapsPager = mView.findViewById(R.id.mapsPager);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
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

        mapsPager.setAdapter(new MapAdapter(getActivity(), listEvent));
        mapsPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(listEvent.get(position).laT, listEvent.get(position).lonG))
                .title(listEvent.get(position).nama));
        marker.showInfoWindow();
        CameraPosition cameraPosition = CameraPosition.builder().target(marker.getPosition()).zoom(14).bearing(0).tilt(0).build();
        mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
