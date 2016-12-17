package com.protagonist.greennation.Model;

/**
 * Created by makeshg on 18/12/16.
 */

public class User {
    int hasura_id;
    String auth_token;

    public int getHasura_id() {
        return hasura_id;
    }

    public void setHasura_id(int hasura_id) {
        this.hasura_id = hasura_id;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

}
