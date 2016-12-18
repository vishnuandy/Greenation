package com.protagonist.greennation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.protagonist.greennation.Model.User;
import com.protagonist.greennation.Task.Task_createprofile;
import com.protagonist.greennation.callbacks.Request_createprofile;
import com.protagonist.greennation.helper.SessionManager;
import com.protagonist.greennation.json.Endpoints;
import com.protagonist.greennation.utils.Apputil;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity implements Request_createprofile{
    TextView signup;
    TextView username;


    SessionManager sessionManager = new SessionManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=(TextView) findViewById(R.id.signup);
        username=(EditText) findViewById(R.id.username);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Apputil.isEmpty(username.getText().toString()))
                {
                    api_create_user(username.getText().toString());
                }

            }
        });

    }


    public void api_create_user(String username) {
        if (Apputil.checkInternetConnection()) {

            Endpoints.urlactionname = "signup";
            //Facebook_hasura_detail facebook_hasuradetail = sessionManager.get_Facebooklogin_hasuradetail();


            JSONObject params = new JSONObject();
            try {
                params.put("username", username);
                params.put("password", "jsmith1234");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("api_create_user: ", params.toString());
            new Task_createprofile(this, params).execute();
        } else {
            //  Apputil.No_network_connection(AppTourActivity.this);
        }
    }

    @Override
    public void oncreate_profile(String profile_is) {
        if (!Apputil.isEmpty(profile_is)) {
            User Loggedinuser;
            Log.e("profile_is", "profile_is" + profile_is);
            try {
                JSONObject object = new JSONObject(profile_is);
/*
                JSONObject object1 = new JSONObject(String.valueOf(object.getJSONArray("returning").get(0)));
*/

                Gson gson = new Gson();
                Loggedinuser = gson.fromJson(object.toString(), User.class);
                sessionManager.create_Facebooklogin_hasuradetail(Loggedinuser.getHasura_id(), Loggedinuser.getAuth_token());

                Intent intent = new Intent(getApplicationContext(), MyForest.class);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("exception_error", e.getMessage());
            }
        }
    }
}
