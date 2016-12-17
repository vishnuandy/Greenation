package com.protagonist.greennation.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.protagonist.greennation.Model.UserPlant;
import com.protagonist.greennation.callbacks.Request_listplants;
import com.protagonist.greennation.extras.GreenationUtils;
import com.protagonist.greennation.network.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by makeshg on 17/12/16.
 */

public class Task_plantlists extends AsyncTask<Void, Void, ArrayList<UserPlant>> {
    private Request_listplants myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private JSONObject map ;
    public Task_plantlists(Request_listplants myComponent, JSONObject m) {
        this.map=m;
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }
    @Override
    protected ArrayList<UserPlant> doInBackground(Void... params)
    {
        Log.e("mapvalue",map.toString());
        ArrayList<UserPlant> listMovies = GreenationUtils.plantlist(requestQueue, map);
        return listMovies;
    }
    @Override
    protected void onPostExecute(ArrayList<UserPlant> otp) {
        if (myComponent != null) {
            if(otp!=null){
                myComponent.oncreate_plantlist(otp);
            }
        }
    }
}
