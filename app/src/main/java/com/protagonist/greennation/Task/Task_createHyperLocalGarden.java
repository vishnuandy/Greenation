package com.protagonist.greennation.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.protagonist.greennation.callbacks.Request_createHyperLocalGarden;
import com.protagonist.greennation.callbacks.Request_createLeaderBoard;
import com.protagonist.greennation.extras.GreenationUtils;
import com.protagonist.greennation.network.VolleySingleton;

import org.json.JSONObject;

/**
 * Created by makeshg on 17/12/16.
 */

public class Task_createHyperLocalGarden extends AsyncTask<Void, Void, String> {
    private Request_createHyperLocalGarden myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private JSONObject map;

    public Task_createHyperLocalGarden(Request_createHyperLocalGarden myComponent, JSONObject m) {
        this.map = m;
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.e("mapvalue", map.toString());
        String listMovies = GreenationUtils.send_my_plant(requestQueue, map);
        return listMovies;
    }

    @Override
    protected void onPostExecute(String otp) {
        if (myComponent != null) {
            if (otp != null) {
                myComponent.oncreate_hyperlocal_garden(otp);
            }
        }
    }
}
