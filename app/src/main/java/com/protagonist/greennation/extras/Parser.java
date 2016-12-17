package com.protagonist.greennation.extras;

import android.util.Log;

import com.google.gson.Gson;
import com.protagonist.greennation.Model.Seed;
import com.protagonist.greennation.Model.UserPlant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by makeshg on 17/12/16.
 */

public class Parser {
    public static final String KEY_INFO="info";
    public static ArrayList<UserPlant> parsePlantList(String response) {
        Log.e("ArrayList res",""+response);
        ArrayList<UserPlant> interviewlist=new ArrayList<UserPlant>();
        if (response != null && response.length() > 0) {
            try {
                //JSONObject categoryobj = new JSONObject(response);
                JSONArray arrayMovies = new JSONArray(response);
                for (int i=0;i<arrayMovies.length();i++) {
                    JSONObject interviewobj=arrayMovies.getJSONObject(i);
                    Log.e("response value",interviewobj.toString());
                    Gson gson = new Gson();
                    UserPlant obj=null;
                    obj = gson.fromJson(interviewobj.toString(), UserPlant.class);
                    interviewlist.add(obj);
                }
            } catch (JSONException e) {
            }
        }
        return interviewlist;
    }

    public static ArrayList<Seed> parseSeedList(String response) {
        Log.e("ArrayList res", "" + response);
        ArrayList<Seed> interviewlist = new ArrayList<Seed>();
        if (response != null && response.length() > 0) {
            try {
                //JSONObject categoryobj = new JSONObject(response);
                JSONArray arrayMovies = new JSONArray(response);
                for (int i = 0; i < arrayMovies.length(); i++) {
                    JSONObject interviewobj = arrayMovies.getJSONObject(i);
                    Log.e("response value", interviewobj.toString());
                    Gson gson = new Gson();
                    Seed obj = null;
                    obj = gson.fromJson(interviewobj.toString(), Seed.class);
                    interviewlist.add(obj);
                }
            }catch (JSONException e)
            {
            }
        }
        return interviewlist;
    }
}