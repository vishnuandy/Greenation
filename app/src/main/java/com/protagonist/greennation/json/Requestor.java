package com.protagonist.greennation.json;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.protagonist.greennation.helper.SessionManager;
import com.protagonist.greennation.utils.Apputil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Windows on 02-03-2015.
 */
public class Requestor {

    public static final String TAG = "MyTag";
   // static SessionManager sessionManager = new SessionManager();
//    String get_hasura_auth_token = sessionManager.get_hasura_auth_token();

  /* public static String getting_Facebookauth(RequestQueue requestQueue, String url, final HashMap<String,String> m) {
        Log.e("map value data",m.toString());
        requestQueue.cancelAll(TAG);
        String response = null;
        RequestFuture<String> requestFuture = RequestFuture.newFuture();
        StringRequest request=new StringRequest(Request.Method.GET, url, requestFuture, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Apputil.onUsersignedin(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                return m;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        requestQueue.add(request);
        try {

            request.setTag(TAG);
            request.setRetryPolicy(new DefaultRetryPolicy(30000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            response = requestFuture.get(30000, TimeUnit.MILLISECONDS);
//            request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } catch (InterruptedException e) {
            Log.e("InterruptedException","InterruptedException"+e.getMessage());

        } catch (ExecutionException e) {
            Log.e("ExecutionException","ExecutionException"+e.getMessage());

        } catch (TimeoutException e) {
             Log.e("TimeoutException", "TimeoutException" + e.getMessage());
        }
        return response;
    }*/
  public static String getting_Googleauth(RequestQueue requestQueue, String url, final HashMap<String,String> m) {
      Log.e("map value data",m.toString());
      requestQueue.cancelAll(TAG);
      String response = null;
      RequestFuture<String> requestFuture = RequestFuture.newFuture();
      StringRequest request=new StringRequest(Request.Method.GET, url, requestFuture, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Apputil.onUsersignedin(error);
          }
      }){
          @Override
          protected Map<String, String> getParams() {
              return m;
          }
          @Override
          public Map<String, String> getHeaders() throws AuthFailureError {
              Map<String,String> params = new HashMap<String, String>();
              params.put("Content-Type","application/x-www-form-urlencoded");
              return params;
          }
      };
      requestQueue.add(request);
      try {
          request.setTag(TAG);
          request.setRetryPolicy(new DefaultRetryPolicy(30000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
          response = requestFuture.get(30000, TimeUnit.MILLISECONDS);
//            request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
      } catch (InterruptedException e) {
          Log.e("InterruptedException","InterruptedException"+e.getMessage());
      } catch (ExecutionException e) {
          Log.e("ExecutionException","ExecutionException"+e.getMessage());
      } catch (TimeoutException e) {
          Log.e("TimeoutException", "TimeoutException" + e.getMessage());
      }
      return response;
  }

    public static String getting_postdata(RequestQueue requestQueue, String url, final JSONObject m) {
      Log.e("map_value_data", m.toString());
      requestQueue.cancelAll(TAG);
      String response = null;
      RequestFuture<String> requestFuture = RequestFuture.newFuture();

      StringRequest request = new StringRequest(Request.Method.POST, url, requestFuture, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Apputil.onUsersignedin(error);
          }
      })

      {
          @Override
          public byte[] getBody() throws AuthFailureError {
              String str = m.toString();
              return str.getBytes();
          }

          @Override
          public String getBodyContentType() {
              return "application/json; charset=utf-8";
          }

          @Override
          public Map<String, String> getHeaders() throws AuthFailureError {
              Map<String, String> params = new HashMap<String, String>();
              params.put("Content-Type", "application/json");
              SessionManager session = new SessionManager();
              if (session.get_hasura_auth_token() != null) {
                  params.put("Authorization", "Bearer " + session.get_hasura_auth_token());

              }
              Log.e("getHeaders", "getHeaders: " + params);
              return params;
          }

      };

      requestQueue.add(request);
      try {

          request.setTag(TAG);
          request.setRetryPolicy(new DefaultRetryPolicy(300000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
          response = requestFuture.get(300000, TimeUnit.MILLISECONDS);
//            request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
      } catch (InterruptedException e) {
          Log.e("InterruptedException", "InterruptedException" + e.getMessage());

      } catch (ExecutionException e) {
          Log.e("ExecutionException", "ExecutionException" + e.getMessage());

      } catch (TimeoutException e) {
          Log.e("TimeoutException", "TimeoutException" + e.getMessage());
      }
      return response;
  }

    public static String getting_Facebookauth(RequestQueue requestQueue, String url, final JSONObject m) {
        Log.e("map_value_data",m.toString());
        requestQueue.cancelAll(TAG);
        String response = null;
        RequestFuture<String> requestFuture = RequestFuture.newFuture();

        StringRequest request=new StringRequest(Request.Method.POST, url, requestFuture, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Apputil.onUsersignedin(error);
            }
        })

        {
            @Override
            public byte[] getBody() throws AuthFailureError {
                String str = m.toString();
                return str.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                SessionManager session=new SessionManager();
              /*  if(session.get_hasura_auth_token()!=null)
                {
                    params.put("Authorization","Bearer "+session.get_hasura_auth_token());

                }*/
                Log.e("getHeaders", "getHeaders: " + params);
                return params;
            }

        };

        requestQueue.add(request);
        try {

            request.setTag(TAG);
            request.setRetryPolicy(new DefaultRetryPolicy(300000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            response = requestFuture.get(300000, TimeUnit.MILLISECONDS);
//            request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } catch (InterruptedException e) {
            Log.e("InterruptedException", "InterruptedException" + e.getMessage());

        } catch (ExecutionException e) {
            Log.e("ExecutionException", "ExecutionException" + e.getMessage());

        } catch (TimeoutException e) {
            Log.e("TimeoutException", "TimeoutException" + e.getMessage());
        }
        return response;
    }

    public static String get_api(RequestQueue requestQueue, String url, final JSONObject m) {
        Log.e("map_value_data", m.toString());
        requestQueue.cancelAll(TAG);
        String response = null;
        RequestFuture<String> requestFuture = RequestFuture.newFuture();

        StringRequest request = new StringRequest(Request.Method.GET, url, requestFuture, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Apputil.onUsersignedin(error);
            }
        })

        {
            @Override
            public byte[] getBody() throws AuthFailureError {
                String str = m.toString();
                return str.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                SessionManager session = new SessionManager();
                if(session.get_hasura_auth_token()!=null)
                {
                    params.put("Authorization","Bearer "+session.get_hasura_auth_token());

                }
                Log.e("getHeaders","getHeaders: "+params);
                return params;
            }

        };

        requestQueue.add(request);
        try {

            request.setTag(TAG);
            request.setRetryPolicy(new DefaultRetryPolicy(300000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            response = requestFuture.get(300000, TimeUnit.MILLISECONDS);
//            request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } catch (InterruptedException e) {
            Log.e("InterruptedException","InterruptedException"+e.getMessage());

        } catch (ExecutionException e) {
            Log.e("ExecutionException","ExecutionException"+e.getMessage());

        } catch (TimeoutException e) {
            Log.e("TimeoutException", "TimeoutException" + e.getMessage());
        }
        return response;
    }

}
