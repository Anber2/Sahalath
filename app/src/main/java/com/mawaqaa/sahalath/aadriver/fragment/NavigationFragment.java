package com.mawaqaa.sahalath.aadriver.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aadriver.adapters.CustomSpinnerAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {

    Spinner mSpinnerStatus;
    private SupportMapFragment mSupportMapFragment;
    GoogleMap mGoogleMap;


    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        SahalathMainActivity.showUpButton();
        loadControl(view);
        setupMap();

        AppConstants.CURRENT_FRAGMENT = this;

        ArrayList<String> spinnerItem = new ArrayList<String>();
        spinnerItem.add("Delivered 1");
        spinnerItem.add("Delivered 2");
        spinnerItem.add("Delivered 3");
        spinnerItem.add("Delivered 4");
        spinnerItem.add("Dlivered 5");

        com.mawaqaa.sahalath.aadriver.adapters.CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(this.getActivity(), spinnerItem);
        mSpinnerStatus.setAdapter(customSpinnerAdapter);



        return view;
    }

    private void loadControl(View view) {
        mSpinnerStatus = (Spinner) view.findViewById(R.id.spinnerCustomStatus);
    }

    public void setupMap() {

        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {
                    if (googleMap != null) {

                        googleMap.getUiSettings().setAllGesturesEnabled(true);
                        mGoogleMap = googleMap;
//                      -> marker_latlng // MAKE THIS WHATEVER YOU WANT

//                       CameraPosition cameraPosition = new CameraPosition.Builder().target(marker_latlng).zoom(15.0f).build();
//                       CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
//                       googleMap.moveCamera(cameraUpdate);

                        if (ActivityCompat.checkSelfPermission(NavigationFragment.this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(NavigationFragment.this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        googleMap.setMyLocationEnabled(true);

                         GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
                            @Override
                            public void onMyLocationChange(Location location) {
                                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

                                if(googleMap != null){
                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 17.0f));
                                }
                            }
                        };
                         googleMap.setOnMyLocationChangeListener(myLocationChangeListener);



                   }

               }
           });


       }
   }


}
