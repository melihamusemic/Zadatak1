package com.example.zadatak1.loginHandling;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class LoginRestClientUsage {
    JsonHttpTaskCompleteListener jsonHttpTaskCompleteListener;

    public LoginRestClientUsage(JsonHttpTaskCompleteListener jsonHttpTaskCompleteListener) {
        this.jsonHttpTaskCompleteListener = jsonHttpTaskCompleteListener;
    }

    public void getUsers() throws JSONException {
        LoginRestClient.get("users", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                jsonHttpTaskCompleteListener.onTaskCompleted(response);
            }
        });
    }
}

