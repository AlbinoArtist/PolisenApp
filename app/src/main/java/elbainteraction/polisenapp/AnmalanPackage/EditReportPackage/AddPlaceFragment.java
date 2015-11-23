package elbainteraction.polisenapp.AnmalanPackage.EditReportPackage;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import elbainteraction.polisenapp.R;

public  class AddPlaceFragment extends Fragment implements OnMapReadyCallback {
    public AddPlaceFragment AddPlacementFragment(){
        return this;
    }
    public static AddPlaceFragment newInstance() {
        AddPlaceFragment fragment = new AddPlaceFragment();
        return fragment;
    }

    private static View view;
    private MarkerOptions markerOptions;
    private boolean isToggled;
    /**
     * Note that this may be null if the Google Play services APK is not
     * available.
     */

    private static GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        isToggled = false;
        if (container == null) {
            return null;
        }
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = (CoordinatorLayout) inflater.inflate(R.layout.fragment2_add_event, container, false);
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.location_map);
            mapFragment.getMapAsync(this);
        } catch (InflateException e) {

        /* map is already there, just return view as it is */
        }
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.pin_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isToggled = !isToggled;
                ImageView pin = (ImageView)view.findViewById(R.id.middle_screen_pin);
                if(pin.getVisibility()==View.GONE){
                    mMap.clear();
                    pin.setVisibility(View.VISIBLE);
                }
                else{
                    view.findViewById(R.id.middle_screen_pin).setVisibility(View.GONE);
                    mMap.clear();
                    markerOptions = new MarkerOptions().position(new LatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude)).draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    mMap.addMarker(markerOptions);
                }

            }
        });

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
    }


    public Double getLatitude(){
        return markerOptions.getPosition().latitude;
    }

    public Double getLongitude(){
        return markerOptions.getPosition().longitude;
    }

    public boolean isToggled(){
        return isToggled;
    }


}