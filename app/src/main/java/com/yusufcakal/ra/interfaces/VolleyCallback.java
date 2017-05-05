package com.yusufcakal.ra.interfaces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yusuf on 1.05.2017.
 */

public interface VolleyCallback {

    void onSucces(JSONObject result);
    void onSuccesAuth(JSONObject result) throws JSONException;

}
