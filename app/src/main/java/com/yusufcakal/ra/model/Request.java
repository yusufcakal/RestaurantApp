package com.yusufcakal.ra.model;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.database.ValueEventListener;
import com.yusufcakal.ra.fragment.CategoryFragment;
import com.yusufcakal.ra.fragment.ProductDetailFragment;
import com.yusufcakal.ra.fragment.ProductFragment;
import com.yusufcakal.ra.interfaces.ChangeDeskStatus;
import com.yusufcakal.ra.interfaces.DeskList;
import com.yusufcakal.ra.interfaces.VolleyCallback;
import com.yusufcakal.ra.interfaces.VolleyTemp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yusuf on 26.04.2017.
 */

public class Request {

    private RequestQueue requestQueue;
    private Context context;
    private String url;
    private int requestMethod;

    public Request(Context context, String url, int requestMethod){
        this.url = url;
        this.context = context;
        this.requestMethod = requestMethod;
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
    }

    public void requestVolley(final VolleyCallback callback){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestMethod, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSucces(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public void requestVolleyAuth(final VolleyCallback callback, final String username, final String password){

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestMethod, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    callback.onSuccesAuth(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return new JSONObject(params).toString().getBytes();
            }
        };

        requestQueue.add(jsonObjectRequest);

    }

    public void requestVolleyDeskList(final DeskList deskList){

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestMethod, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                deskList.getDesk(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });

            requestQueue.add(jsonObjectRequest);

    }

    public void requestVolleyDeskList(final ChangeDeskStatus changeDeskStatus, final int orderId, final int status){

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestMethod, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                changeDeskStatus.changeStatus(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public byte[] getBody() {
                HashMap<String, Integer> params = new HashMap<String, Integer>();
                params.put("orderId", orderId);
                params.put("status", status);
                return new JSONObject(params).toString().getBytes();
            }
        };

        requestQueue.add(jsonObjectRequest);

    }

    public void requestVolleyTempDesk(final VolleyTemp callback, final int deskId){

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestMethod, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.getTempId(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public byte[] getBody() {
                HashMap<String, Integer> params = new HashMap<String, Integer>();
                params.put("deskid", deskId);
                return new JSONObject(params).toString().getBytes();
            }
        };

        requestQueue.add(jsonObjectRequest);

    }

}

