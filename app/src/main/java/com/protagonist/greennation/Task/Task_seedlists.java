package com.protagonist.greennation.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.protagonist.greennation.Model.Seed;
import com.protagonist.greennation.Model.UserPlant;
import com.protagonist.greennation.callbacks.Request_listplants;
import com.protagonist.greennation.callbacks.Request_listseeds;
import com.protagonist.greennation.extras.GreenationUtils;
import com.protagonist.greennation.network.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by makeshg on 17/12/16.
 */

public class Task_seedlists extends AsyncTask<Void, Void, ArrayList<Seed>> {
    private Request_listseeds myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private JSONObject map;

    public Task_seedlists(Request_listseeds myComponent, JSONObject m) {
        this.map = m;
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected ArrayList<Seed> doInBackground(Void... params) {
        Log.e("mapvalue", map.toString());
        ArrayList<Seed> listMovies = GreenationUtils.seedlist(requestQueue, map);
        return listMovies;
    }

    @Override
    protected void onPostExecute(ArrayList<Seed> otp) {
        if (myComponent != null) {
            if (otp != null) {
                myComponent.oncreate_seedlist(otp);
            }
        }
    }
}
