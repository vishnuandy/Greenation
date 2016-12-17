/*
package com.protagonist.greennation;

import android.app.Dialog;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.protagonist.greennation.R.id.map;


public class VolunteerActivity extends FragmentActivity implements OnMapReadyCallback {
    LatLng currentlocation;
    private GoogleMap mMap;
    Button volunterbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        volunterbtn = (Button) findViewById(R.id.volunterbtn);

        // add button listener
        volunterbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(VolunteerActivity.this);
               View myContentsView = getLayoutInflater().inflate(R.layout.newvolunter, null);

                dialog.setContentView(myContentsView);
                dialog.setTitle("Title...");
                SegmentedGroup   gender = (SegmentedGroup) myContentsView.findViewById(R.id.SegmentedGroup);
                gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.button32:
                                Toast.makeText(VolunteerActivity.this, "One", Toast.LENGTH_SHORT).show();
                                return;
                            case R.id.button33:
                                Toast.makeText(VolunteerActivity.this, "two", Toast.LENGTH_SHORT).show();
                                return;

                            default:
                                // Nothing to do
                        }
                    }
                });
                // set the custom dialog components - text, image and button


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

        LatLng sydney = new LatLng(13.8323, 80.3232938);

        for (int i=0;i<=5;i++) {
                // Add a marker in Sydney and move the camera
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
                //marker.draggable(true);
// Changing marker icon
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.leaf));

                mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
                mMap.addMarker(marker);
                // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
              //  CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(sydney,3);
               // mMap.animateCamera(yourLocation);
               // mMap.animateCamera(CameraUpdateFactory.zoomTo(2.0f));

            }
        //mMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16.0f));


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
