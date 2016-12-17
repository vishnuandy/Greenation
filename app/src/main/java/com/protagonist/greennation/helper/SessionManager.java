package com.protagonist.greennation.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.protagonist.greennation.MyApplication;

/**
 * Created by makeshg on 18/12/16.
 */

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    private static final String PREF_NAME = "greenation";
    int PRIVATE_MODE = 0;

    /* Facebooklogin_hasuradetail */
    public String KEY_login = "KEY_login";
    public String HASURA_ID = "HASURA_ID" ;
    public String HASURA_AUTH_TOKEN = "HASURA_AUTH_TOKEN";





    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public SessionManager() {
        pref = MyApplication.getAppContext().getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_login, false);
    }

    public void create_Facebooklogin_hasuradetail(int user_id,String auth_token) {
        editor.putBoolean(KEY_login, true);
        editor.putInt(HASURA_ID, user_id);
        editor.putString(HASURA_AUTH_TOKEN, auth_token);
        editor.commit();
    }



    public String get_hasura_auth_token() {
        return pref.getString(HASURA_AUTH_TOKEN,"");
    }


    public int get_hasura_userid() {
        return pref.getInt(HASURA_ID,0);
    }


}

