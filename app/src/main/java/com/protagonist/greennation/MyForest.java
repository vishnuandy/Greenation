package com.protagonist.greennation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.protagonist.greennation.Adapter.ForestAdapter;
import com.protagonist.greennation.Model.LeaderStatus;
import com.protagonist.greennation.Model.Plant;
import com.protagonist.greennation.Model.UserPlant;
import com.protagonist.greennation.Task.Task_createLeaderStatus;
import com.protagonist.greennation.Task.Task_createplant;
import com.protagonist.greennation.Task.Task_plantlists;
import com.protagonist.greennation.callbacks.Request_createLeaderBoard;
import com.protagonist.greennation.callbacks.Request_listplants;
import com.protagonist.greennation.helper.SessionManager;
import com.protagonist.greennation.json.Endpoints;
import com.protagonist.greennation.utils.Apputil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyForest extends AppCompatActivity implements Request_listplants, Request_createLeaderBoard {
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

    public void api_check_leaderboard_status() {
        if (Apputil.checkInternetConnection()) {

            Endpoints.urlactionname = "get_leader_board";
            //Facebook_hasura_detail facebook_hasuradetail = sessionManager.get_Facebooklogin_hasuradetail();


            JSONObject params = new JSONObject();
            try {
                SessionManager session = new SessionManager();
                params.put("hasura_user_id", session.get_hasura_userid());


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("api_create_user: ", params.toString());
            new Task_createLeaderStatus(this, params).execute();
        } else {
            //  Apputil.No_network_connection(AppTourActivity.this);
        }
    }
    public void api_user_plant() {
        if (Apputil.checkInternetConnection()) {
            SessionManager session = new SessionManager();

            Endpoints.urlactionname = "list_my_plants?hasura_user_id=" + session.get_hasura_userid();
            //Facebook_hasura_detail facebook_hasuradetail = sessionManager.get_Facebooklogin_hasuradetail();


            JSONObject params = new JSONObject();
            try {

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
        ArrayList<UserPlant> plantlist = new ArrayList<>();
        ForestAdapter adapter = new ForestAdapter(getApplicationContext(), plantlist);
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
                api_check_leaderboard_status();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void oncreate_plantlist(ArrayList<UserPlant> plants) {
        if (plants != null) {
            ForestAdapter adapter = new ForestAdapter(getApplicationContext(), plants);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void oncreate_leaderboard(String leaderboard) {
        if (leaderboard != null || !leaderboard.isEmpty()) {
            try {
                //JSONObject categoryobj = new JSONObject(response);
                JSONArray arrayMovies = new JSONArray(leaderboard);
                ArrayList<LeaderStatus> interviewlist = new ArrayList<LeaderStatus>();
                for (int i = 0; i < arrayMovies.length(); i++) {
                    JSONObject interviewobj = arrayMovies.getJSONObject(i);
                    Log.e("response value", interviewobj.toString());
                    Gson gson = new Gson();
                    LeaderStatus obj = null;
                    obj = gson.fromJson(interviewobj.toString(), LeaderStatus.class);
                    interviewlist.add(obj);

                }
                final Dialog dialog = new Dialog(this);
                View myContentsView = getLayoutInflater().inflate(R.layout.impactscore, null);
                TextView badge = (TextView) myContentsView.findViewById(R.id.badge);
                ImageView close = (ImageView) myContentsView.findViewById(R.id.close);

                TextView description = (TextView) myContentsView.findViewById(R.id.description);
                description.setText(interviewlist.get(0).getMessage());
                badge.setText(interviewlist.get(0).getLeaderboard_level_name());
                dialog.setContentView(myContentsView);
                dialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            } catch (JSONException e) {
            }

        }
    }
}
