package com.protagonist.greennation;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;
import com.protagonist.greennation.Adapter.ForestAdapter;
import com.protagonist.greennation.Adapter.SeedAdapter;
import com.protagonist.greennation.Model.Plant;
import com.protagonist.greennation.Model.Seed;
import com.protagonist.greennation.Task.Task_plantlists;
import com.protagonist.greennation.Task.Task_seedlists;
import com.protagonist.greennation.callbacks.Request_listseeds;
import com.protagonist.greennation.helper.SessionManager;
import com.protagonist.greennation.json.Endpoints;
import com.protagonist.greennation.utils.Apputil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SeedActivity extends AppCompatActivity implements Request_listseeds {
    private final String plant_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",

    };

    private final String plant_images[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",

    };
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initViews();

    }

    private void initViews() {
        FloatingActionMenu action = (FloatingActionMenu) findViewById(R.id.fab);

        com.github.clans.fab.FloatingActionButton seed = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.seed);
        final com.github.clans.fab.FloatingActionButton programFab1 = new com.github.clans.fab.FloatingActionButton(this);
        programFab1.setButtonSize(com.github.clans.fab.FloatingActionButton.SIZE_MINI);
        action.addMenuButton(programFab1);
        action.setClosedOnTouchOutside(true);
        programFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Intent intent = new Intent(MyForest.this, SeedActivity.class);
                startActivity(intent);*/
            }
        });
        action.setVisibility(View.GONE);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Seed> seeds = new ArrayList<>();
        SeedAdapter adapter = new SeedAdapter(SeedActivity.this, seeds);
        recyclerView.setAdapter(adapter);
        api_user_plant();
    }

    public void api_user_plant() {
        if (Apputil.checkInternetConnection()) {
            SessionManager session = new SessionManager();

            Endpoints.urlactionname = "list_plants_master";
            //Facebook_hasura_detail facebook_hasuradetail = sessionManager.get_Facebooklogin_hasuradetail();


            JSONObject params = new JSONObject();
    /*        try {

                params.put("hasura_user_id", session.get_hasura_userid());


            } catch (JSONException e) {
                e.printStackTrace();
            }*/
            new Task_seedlists(this, params).execute();
        } else {
            //  Apputil.No_network_connection(AppTourActivity.this);
        }
    }

    private ArrayList<Plant> prepareData() {


        ArrayList<Plant> android_version = new ArrayList<>();
        for (int i = 0; i < plant_names.length; i++) {
            Plant androidVersion = new Plant();
            androidVersion.setPlant_name(plant_names[i]);
            androidVersion.setPlant_image(plant_images[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }

    @Override
    public void oncreate_seedlist(ArrayList<Seed> plants) {
        if (plants != null) {
            SeedAdapter adapter = new SeedAdapter(SeedActivity.this, plants);
            recyclerView.setAdapter(adapter);
        }
    }
}
