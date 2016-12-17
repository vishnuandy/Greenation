package com.protagonist.greennation.utils;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.protagonist.greennation.MyApplication;

/**
 * Created by makeshg on 17/12/16.
 */

public class Apputil {

    public static void onUsersignedin(VolleyError error) {
        //update the Adapter to contain the new movies downloaded from the web
        //Log.e("user logout message", listMovies.toString());
        //mAdapter.setMovies(listMovies);
        String json = null;
        NetworkResponse response = error.networkResponse;
        Log.e("volley_error_response","volley_error_response"+response);
        if(response != null && response.data != null) {
            switch (response.statusCode) {
                case 500:

                    break;
                default:

//                    json = new String(response.data);
//                    json = trimMessage(json, "message");
//
//                    if(json != null)
//
//                        displayMessage(json);
                    break;
            }
        }

        //  Apputil.dismissdialog();
        if (error!=null) {
            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                // mTextError.setText(R.string.error_timeout);

            } else if (error instanceof AuthFailureError) {
                // mTextError.setText(R.string.error_auth_failure);
                //TODO
            } else if (error instanceof ServerError) {
                //  mTextError.setText(R.string.error_auth_failure);
                //TODO
            } else if (error instanceof NetworkError) {
                //  mTextError.setText(R.string.error_network);
                //TODO
            } else if (error instanceof ParseError) {
                //  mTextError.setText(R.string.error_parser);
                //TODO
            }
        }
    }


    public static Dialog connectionDialog;

    public static void showloading(Context context) {
        if( context != null ){
           /* connectionDialog = new Dialog(context, R.style.CustomAlertDialog);
            connectionDialog.setContentView(R.layout.dia_loading);
            connectionDialog.setCancelable(false);
            connectionDialog.show();*/
        }
    }

    /* checking the intrenet connection */
    public static boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
                /*Network connecting...*/
            return true;
        } else {
                /*oops!!! no network*/
            Log.d("TAG", "Internet Connection Not Present");
            return false;
        }
    }

    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

}
