package com.protagonist.greennation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.gson.Gson;
import com.protagonist.greennation.Model.User;
import com.protagonist.greennation.Task.TaskGoogleauth;
import com.protagonist.greennation.callbacks.RequestGoogleauth;
import com.protagonist.greennation.helper.SessionManager;
import com.protagonist.greennation.json.Endpoints;
import com.protagonist.greennation.utils.Apputil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, RequestGoogleauth, GoogleApiClient.OnConnectionFailedListener {
    //Signin button
    private SignInButton signInButton;
    String token = null;
    //Signing Options
    SessionManager sessionManager = new SessionManager();
    private GoogleSignInOptions gso;

    //google api client
    private GoogleApiClient mGoogleApiClient;

    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;
    private static final String TAG = "RetrieveAccessToken";

    final String SCOPES = "https://www.googleapis.com/auth/plus.login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("232093444901-2rmfjnt3rah9c2ls31ogt239u06v2kol.apps.googleusercontent.com")
                .requestEmail()
                .build();

        //Initializing signinbutton
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //Setting onclick listener to signing button
        signInButton.setOnClickListener(this);
    }

    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {
            //Calling signin
            signIn();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin

            handleSignInResult(result);

        }
    }
    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            //Displaying name and email

            Log.e("getdisplay_name", acct.getDisplayName());
            Log.e("getemail", acct.getEmail());
            Log.e("getprofileimage", "" + acct.getPhotoUrl());
            Log.e("getIdToken", "" + acct.getIdToken());

          //  new RetrieveTokenTask().execute( acct.getEmail());
           token=acct.getIdToken();
          //  Log.e("token_value",acct.getServerAuthCode());
           api_verifyGoogleauth();



        /*    */
        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    public void api_verifyGoogleauth() {
        if (Apputil.checkInternetConnection()) {
//            Endpoints.urlFacebookauth = "EAACzMqNgiNUBACSL4B3Hhq2ZAbqMSoimHxrJENmiQYMbUOUkrIaQqB8EoWIIlTdcQo8oic8AvS5v8pQ8IbGJPKgHYHrnkYsTZC3rMUvVwwgx7soofqC2yQTbCR4Jufib66gZBDd88TYnoKekuWQ45v6fUXhwZA0ZD";
            Endpoints.urlFacebookauth = token;
            HashMap<String, String> params = new HashMap<String, String>();
            new TaskGoogleauth(this, params).execute();
        } else {
            Toast.makeText(this,"No Internet connection",Toast.LENGTH_SHORT).show();
          //  Apputil.No_network_connection(AppTourActivity.this);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void ongoogle(String google_is) {
        Log.e("google_is", "google_is: " + google_is);
        User google_hasuradetail = new User();
        if (!Apputil.isEmpty(google_is)) {
            try {
                JSONObject google_object = new JSONObject(google_is);
                Gson gson = new Gson();
                google_hasuradetail = gson.fromJson(google_object.toString(), User.class);
                sessionManager.create_Facebooklogin_hasuradetail(google_hasuradetail.getHasura_id(),google_hasuradetail.getAuth_token());
                if (google_object.getString("new_user").equalsIgnoreCase("true")) {
                    Log.e("new_user", "new_user");
               /*     api_create_user();*/
                } else {
                    Log.e("old_user", "old_user id is: " + google_object.getString("hasura_id"));
/*
                    api_get_user(google_object.getString("hasura_id"));
*/
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
