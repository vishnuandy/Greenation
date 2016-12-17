package com.protagonist.greennation.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.protagonist.greennation.callbacks.Request_createprofile;
import com.protagonist.greennation.extras.GreenationUtils;
import com.protagonist.greennation.network.VolleySingleton;


import org.json.JSONObject;

/**
 * Created by makeshg on 17/12/16.
 */

public class Task_createprofile extends AsyncTask<Void, Void, String> {
    private Request_createprofile myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private JSONObject map ;
    public Task_createprofile(Request_createprofile myComponent, JSONObject m) {
        this.map=m;
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }
    @Override
    protected String doInBackground(Void... params)
    {
        Log.e("mapvalue",map.toString());
        String listMovies = GreenationUtils.send_Fb_auth(requestQueue, map);
        return listMovies;
    }
    @Override
    protected void onPostExecute(String otp) {
        if (myComponent != null) {
            if(otp!=null){
                myComponent.oncreate_profile(otp);
            }
        }
    }
}
