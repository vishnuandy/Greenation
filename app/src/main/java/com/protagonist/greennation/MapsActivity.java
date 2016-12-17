/*
package com.protagonist.greennation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.protagonist.greennation.R.id.map;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    LatLng currentlocation;
    private GoogleMap mMap;
    Button volunterbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        volunterbtn = (Button) findViewById(R.id.volunterbtn);

        // add button listener
        volunterbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(MapsActivity.this);
               View myContentsView = getLayoutInflater().inflate(R.layout.newvolunter, null);

                dialog.setContentView(myContentsView);
                dialog.setTitle("Title...");
                SegmentedGroup   gender = (SegmentedGroup) myContentsView.findViewById(R.id.SegmentedGroup);
                gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.button32:
                                Toast.makeText(MapsActivity.this, "One", Toast.LENGTH_SHORT).show();
                                return;
                            case R.id.button33:
                                Toast.makeText(MapsActivity.this, "two", Toast.LENGTH_SHORT).show();
                                return;

                            default:
                                // Nothing to do
                        }
                    }
                });
                // set the custom dialog components - text, image and button
                Button   gobtn = (Button) myContentsView.findViewById(R.id.volunterbtn);
                gobtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i= new Intent(MapsActivity.this, VolunteerActivity.class);
                        startActivity(i);
                    }
                });


                dialog.show();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

       */
/* GPSTracker gps = new GPSTracker(this);
        if(gps.canGetLocation()){
            currentlocation=new LatLng(gps.getLatitude(),gps.getLongitude());

            gps.stopUsingGPS();
        }*//*

        }


    */
/**
 * Manipulates the map once available.
 * This callback is triggered when the map is ready to be used.
 * This is where we can add markers or lines, add listeners or move the camera. In this case,
 * we just add a marker near Sydney, Australia.
 * If Google Play services is not installed on the device, the user will be prompted to install
 * it inside the SupportMapFragment. This method will only be triggered once the user has
 * installed Google Play services and returned to the app.
 *//*

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
            for (int i=0;i<=0;i++) {
                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(13.8323, 80.3232938);
                if (i==0)
                {
                    sydney = new LatLng(13.83456, 80.456678);
                }
                if (i==1)
                {
                    sydney = new LatLng(13.83456, 80.456678);
                }
                if (i==2)
                {
                    sydney = new LatLng(13.902121, 80.637263);
                }
                if (i==3)
                {
                    sydney = new LatLng(13.334234, 80.2323);
                }
                if (i==4)
                {
                    sydney = new LatLng(13.3432342, 80.432423423);
                }
// create marker
                MarkerOptions marker = new MarkerOptions().position(sydney).title("protagonist");
                marker.draggable(true);
// Changing marker icon
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.leaf));

                mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
                mMap.addMarker(marker);
                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker markerDragStart) {
                        // TODO Auto-generated method stub
                        if (BuildConfig.DEBUG)
                            Log.i("Marker drag", "start");
                    }

                    @Override
                    public void onMarkerDragEnd(Marker markerDragEnd) {
                        if (BuildConfig.DEBUG)
                            Log.i("Marker drag", "end");
                    }

                    @Override
                    public void onMarkerDrag(Marker markerDrag) {
                        if (BuildConfig.DEBUG)
                            Log.i("Marker drag", "draging");
                    }
                });
                // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(sydney,1);
                mMap.animateCamera(yourLocation);

            }



       // if (currentlocation!=null)
       // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentlocation,32));
    }





    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            //TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            //tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }

}
*/
