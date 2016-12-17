package com.protagonist.greennation.json;


/**
 * Created by Windows on 02-03-2015.
 */

public class Endpoints {


    public static String urlname="https://auth.loggia70.hasura-app.io/";
    public static String hasura_urlname="https://data.oologic14.hasura-app.io/v1/template/";
    public static String urlactionname="";
    public static String urlFacebookauth="";

    public static String getCustomRequestUrl() {
        return  urlname+urlactionname;
    }

    public static String getHasuraRequestUrl() {
        return  hasura_urlname+urlactionname;
    }

    public static String getFacebookauth() {
        return  "https://auth.loggia70.hasura-app.io/facebook/authenticate?access_token="+urlFacebookauth;
    }


}
