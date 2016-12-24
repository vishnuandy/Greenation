package com.protagonist.greennation.Task;

/**
 * Created by RajeshKumar on 12/22/2016.
 */

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.protagonist.greennation.callbacks.RequestGoogleauth;
import com.protagonist.greennation.extras.GreenationUtils;
import com.protagonist.greennation.network.VolleySingleton;

import java.util.HashMap;
/**
 * Created by Windows on 02-03-2015.
 */
public class TaskGoogleauth extends AsyncTask<Void, Void, String> {
    private RequestGoogleauth myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private HashMap<String , String> map ;
    public TaskGoogleauth(RequestGoogleauth myComponent, HashMap<String, String> m) {
        this.map=m;
        this.myComponent = myComponent;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }
    @Override
    protected String doInBackground(Void... params)
    {
        String listMovies = GreenationUtils.send_google_auth(requestQueue, map);
        return listMovies;
    }
    @Override
    protected void onPostExecute(String otp) {
        if (myComponent != null) {
            if(otp!=null){
                myComponent.ongoogle(otp);
            }
        }
    }
}