package com.protagonist.greennation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.protagonist.greennation.Model.CreateHyperLocalGarden;
import com.protagonist.greennation.Model.LeaderStatus;
import com.protagonist.greennation.Task.Task_createHyperLocalGarden;
import com.protagonist.greennation.Task.Task_createplant;
import com.protagonist.greennation.Task.Task_createprofile;
import com.protagonist.greennation.callbacks.Request_createHyperLocalGarden;
import com.protagonist.greennation.callbacks.Request_createPlant;
import com.protagonist.greennation.helper.SessionManager;
import com.protagonist.greennation.json.Endpoints;
import com.protagonist.greennation.utils.Apputil;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifTextView;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener, Request_createPlant, Request_createHyperLocalGarden {
    String plant_id = "";
    boolean gps_enabled = false;
    boolean network_enabled = false;
    Location net_loc = null, gps_loc = null, finalLoc = null;
    LocationManager locationManager;
    String strAdd = "";
    String provider;
    double lat;
    double longi;
    GifTextView water_pour;
    GifTextView fertilizer_pour;
    View clickedVieww;
    int water_count;
    int fertilizer_count;
    Button harvest;
    ImageView plant_stage;
    Animation animationFadeOut;
    Animation animationFadeIn;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_growth);
        fragmentManager = getSupportFragmentManager();
        animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        plant_stage = (ImageView) findViewById(R.id.plant_stage);
        harvest = (Button) findViewById(R.id.harvest);
        water_pour = (GifTextView) findViewById(R.id.water_gif);
        fertilizer_pour = (GifTextView) findViewById(R.id.fertilizer_gif);

        Intent i = getIntent();
        plant_id = i.getStringExtra("plant_id");

        statusCheck();
        getlocationdetails();
        if (plant_id != null) {
            api_create_myplant();
        } else {
            api_create_hyperlocal_gardening();


        }
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
       mMenuDialogFragment.setItemClickListener(this);
//        mMenuDialogFragment.setItemLongClickListener(this);
    }
    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.ic_close);


        MenuObject send = new MenuObject("Water the plant");
        send.setResource(R.drawable.ic_pot_png);

        MenuObject like = new MenuObject("Feed your soil");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_seeds_png);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("Track your plant");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_location_png));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("Know about your plant");
        addFav.setResource(R.drawable.ic_info_png);



        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        return menuObjects;

    }

    public void locationdetails() {
        gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        //   Log.e("network_enable",""+network_enabled);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (gps_enabled)
            gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (network_enabled)
            net_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (gps_loc != null && net_loc != null) {
            if (gps_loc.getAccuracy() >= net_loc.getAccuracy())
                finalLoc = gps_loc;
            else
                finalLoc = net_loc;
        } else {
            if (gps_loc != null) {
                finalLoc = net_loc;
            } else if (net_loc != null) {
                finalLoc = gps_loc;
            }
        }
        if (finalLoc != null) {
            lat = finalLoc.getLatitude();
            longi = finalLoc.getLongitude();

            //getCompleteAddressString(lat, longi);
        } else {
            statusCheck();
        }
    }

    public void getlocationdetails() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Creating an empty criteria object
        Criteria criteria = new Criteria();
        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);
        if (provider != null && !provider.equals("")) {
            // Get the location from the given provider
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            locationManager.requestLocationUpdates(provider, 20000, 1, (LocationListener) this);
            if (location != null)
                if (TextUtils.isEmpty(strAdd)) {
                    //    onLocationChanged(location);
                }
        } else {
            // Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }

    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
           /* buildAlertMessageNoGps();*/
            //lat 13.0570374
            //long 80.25959319999993
            lat = 13.0570374;
            longi = 80.25959319999993;

        }
    }

    public void api_create_hyperlocal_gardening() {
        if (Apputil.checkInternetConnection()) {

            Endpoints.urlactionname = "create_hyper_local_garden";
            //Facebook_hasura_detail facebook_hasuradetail = sessionManager.get_Facebooklogin_hasuradetail();


            JSONObject params = new JSONObject();
            try {
                SessionManager session = new SessionManager();
                params.put("hasura_user_id", session.get_hasura_userid());
                params.put("garden_name", "garden");
                params.put("lat", String.valueOf(lat));
                params.put("lon", "" + String.valueOf(longi));
                params.put("land_space", 5000);
                params.put("type_space", 1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("api_create_user: ", params.toString());
            new Task_createHyperLocalGarden(this, params).execute();
        } else {
            //  Apputil.No_network_connection(AppTourActivity.this);
        }
    }

    public void api_create_myplant_hyperlocal(String id) {
        if (Apputil.checkInternetConnection()) {

            Endpoints.urlactionname = "create_my_plant";
            //Facebook_hasura_detail facebook_hasuradetail = sessionManager.get_Facebooklogin_hasuradetail();


            JSONObject params = new JSONObject();
            try {
                SessionManager session = new SessionManager();
                params.put("hasura_user_id", session.get_hasura_userid());
                params.put("plant_id", Integer.parseInt(plant_id));
                params.put("hyper_local_garden_id", id);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("api_create_user: ", params.toString());
            new Task_createplant(this, params).execute();
        } else {
            //  Apputil.No_network_connection(AppTourActivity.this);
        }
    }
    public void api_create_myplant() {
        if (Apputil.checkInternetConnection()) {

            Endpoints.urlactionname = "create_my_plant";
            //Facebook_hasura_detail facebook_hasuradetail = sessionManager.get_Facebooklogin_hasuradetail();


            JSONObject params = new JSONObject();
            try {
                SessionManager session = new SessionManager();
                params.put("hasura_user_id", session.get_hasura_userid());
                params.put("plant_id", Integer.parseInt(plant_id));
                params.put("hyper_local_garden_id", 9999);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("api_create_user: ", params.toString());
            new Task_createplant(this, params).execute();
        } else {
            //  Apputil.No_network_connection(AppTourActivity.this);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            finish();
        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        clickedVieww = clickedView;
        if(position==1){
//            Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
//            if(fertilizer_pour.getVisibility()!=View.VISIBLE) {
//                clickedVieww.setEnabled(false);
            if (water_count == fertilizer_count) {

                water_count = water_count + 1;
                water_pour.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 10 seconds
                        water_pour.setVisibility(View.GONE);
//                        clickedVieww.setEnabled(true);

                    }
                }, 3000);
            } else {
                Toast.makeText(MainActivity.this, "Please feed your before pour water", Toast.LENGTH_SHORT).show();
            }
//            }
        }else if(position==2){
//            Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
//            if(water_pour.getVisibility()!=View.VISIBLE){
//                clickedVieww.setEnabled(false);
            if (fertilizer_count + 1 == water_count) {

                fertilizer_count = fertilizer_count + 1;
                fertilizer_pour.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 10 seconds
                        fertilizer_pour.setVisibility(View.GONE);

                        if (fertilizer_count == 1) {
                            plant_stage.startAnimation(animationFadeOut);
                            plant_stage.setImageDrawable(getResources().getDrawable(R.drawable.two));
                            plant_stage.startAnimation(animationFadeIn);
                        } else if (fertilizer_count == 2) {
                            plant_stage.startAnimation(animationFadeOut);
                            plant_stage.setImageDrawable(getResources().getDrawable(R.drawable.three));
                            plant_stage.startAnimation(animationFadeIn);
                        }
//                    else if(fertilizer_count==3){
//                        plant_stage.startAnimation(animationFadeOut);
//                        plant_stage.setImageDrawable(getResources().getDrawable( R.drawable.three));
//                        plant_stage.startAnimation(animationFadeIn);
//                    }
                        if (water_count == 2 && fertilizer_count == 2) {
                            harvest.setVisibility(View.VISIBLE);
                        }

//                    clickedVieww.setEnabled(true);
                    }
                }, 3000);
//            }
            } else {
                Toast.makeText(MainActivity.this, "Please pour water before feed your soil", Toast.LENGTH_SHORT).show();

            }

        }else if(position==3){
            Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
        }else if(position==4){
            Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
        }
        else if(position==5){
            Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void oncreate_plant(String plant) {
        if (plant != null || plant.isEmpty()) {
            Log.e("plant_result", plant);

        }
    }

    @Override
    public void oncreate_hyperlocal_garden(String hyperlocal) {
        if (hyperlocal != null || !hyperlocal.isEmpty()) {
            try {
                //JSONObject categoryobj = new JSONObject(response);
                JSONArray arrayMovies = new JSONArray(hyperlocal);
                ArrayList<CreateHyperLocalGarden> interviewlist = new ArrayList<CreateHyperLocalGarden>();
                for (int i = 0; i < arrayMovies.length(); i++) {
                    JSONObject interviewobj = arrayMovies.getJSONObject(i);
                    Log.e("response value", interviewobj.toString());
                    Gson gson = new Gson();
                    CreateHyperLocalGarden obj = null;
                    obj = gson.fromJson(interviewobj.toString(), CreateHyperLocalGarden.class);
                    interviewlist.add(obj);

                }


                api_create_myplant_hyperlocal(interviewlist.get(0).getId());


            } catch (JSONException e) {
            }

        }
    }
    /*@Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }
*/}
