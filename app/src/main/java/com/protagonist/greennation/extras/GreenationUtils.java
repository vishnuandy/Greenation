package com.protagonist.greennation.extras;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.protagonist.greennation.Model.Seed;
import com.protagonist.greennation.Model.UserPlant;
import com.protagonist.greennation.json.Endpoints;
import com.protagonist.greennation.json.Requestor;


import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by makeshg on 17/12/16.
 */

public class GreenationUtils {
 /*   public static String send_Fb_profile(RequestQueue requestQueue, JSONObject map) {
        String response = Requestor.getting_response_data_post(requestQueue, Endpoints.getCustomRequestUrl(), map);
        Log.e("Is_response","Is_response::: "+response);
        return response;
    }*/
 public static String send_Fb_auth(RequestQueue requestQueue, JSONObject map) {
     Log.e("mapvalue",""+ Endpoints.getCustomRequestUrl());

     String response = Requestor.getting_Facebookauth(requestQueue, Endpoints.getCustomRequestUrl(), map);
     Log.e("Is_response","Is_response::: "+response);
     return response;
 }

    public static String send_my_plant(RequestQueue requestQueue, JSONObject map) {
        Log.e("mapvalue", "" + Endpoints.getHasuraRequestUrl());

        String response = Requestor.getting_postdata(requestQueue, Endpoints.getHasuraRequestUrlChange(), map);
        Log.e("Is_response", "Is_response::: " + response);
        return response;
    }
    public static ArrayList<UserPlant> plantlist(RequestQueue requestQueue, JSONObject map) {
        Log.e("mapvalue",""+ Endpoints.getCustomRequestUrl());

        String response = Requestor.get_api(requestQueue, Endpoints.getHasuraRequestUrl(), map);
        Log.e("Is_response","Is_response::: "+response);
        ArrayList<UserPlant> plants=Parser.parsePlantList(response);
        return plants;
    }

    public static ArrayList<Seed> seedlist(RequestQueue requestQueue, JSONObject map) {
        Log.e("mapvalue", "" + Endpoints.getCustomRequestUrl());

        String response = Requestor.get_api(requestQueue, Endpoints.getHasuraRequestUrl(), map);
        Log.e("Is_response", "Is_response::: " + response);
        ArrayList<Seed> plants = Parser.parseSeedList(response);
        return plants;
    }
}
