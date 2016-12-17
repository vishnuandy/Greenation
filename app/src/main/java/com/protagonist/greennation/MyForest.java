package com.protagonist.greennation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.protagonist.greennation.Adapter.ForestAdapter;
import com.protagonist.greennation.Model.Plant;
import com.protagonist.greennation.Model.UserPlant;
import com.protagonist.greennation.Task.Task_plantlists;
import com.protagonist.greennation.callbacks.Request_listplants;
import com.protagonist.greennation.helper.SessionManager;
import com.protagonist.greennation.json.Endpoints;
import com.protagonist.greennation.utils.Apputil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyForest extends AppCompatActivity implements Request_listplants {
    RecyclerView recyclerView;
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
    public void api_user_plant() {
        if (Apputil.checkInternetConnection()) {

            Endpoints.urlactionname = "list_my_plants";
            //Facebook_hasura_detail facebook_hasuradetail = sessionManager.get_Facebooklogin_hasuradetail();


            JSONObject params = new JSONObject();
            try {
                SessionManager session=new SessionManager();

                params.put("hasura_user_id", session.get_hasura_userid());


            } catch (JSONException e) {
                e.printStackTrace();
            }
            new Task_plantlists(this, params).execute();
        } else {
            //  Apputil.No_network_connection(AppTourActivity.this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initViews();

    }

    private void initViews() {
        FloatingActionMenu action = (FloatingActionMenu) findViewById(R.id.fab);

        FloatingActionButton seed = (FloatingActionButton) findViewById(R.id.seed);
        seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyForest.this, SeedActivity.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Plant> androidVersions = prepareData();
        ForestAdapter adapter = new ForestAdapter(getApplicationContext(), androidVersions);
        recyclerView.setAdapter(adapter);
        api_user_plant();
    }

    private ArrayList<Plant> prepareData() {

        ArrayList<Plant> android_version = new ArrayList<>();
       /* for (int i = 0; i < plant_names.length; i++) {
            Plant androidVersion = new Plant();
            androidVersion.setPlant_name(plant_names[i]);
            androidVersion.setPlant_image(plant_images[i]);
            android_version.add(androidVersion);
        }*/
        return android_version;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }/*
        getMenuInflater().inflate(R.menu.main_menu, menu);//Menu Resource, Menu
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                final Dialog dialog = new Dialog(this);
                View myContentsView = getLayoutInflater().inflate(R.layout.impactscore, null);
                dialog.setContentView(myContentsView);
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void oncreate_plantlist(ArrayList<UserPlant> plants) {
        ArrayList<Plant> androidVersions = prepareData();
        ForestAdapter adapter = new ForestAdapter(getApplicationContext(), androidVersions);
        recyclerView.setAdapter(adapter);

    }
}
