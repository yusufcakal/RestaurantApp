package com.yusufcakal.ra.model;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Yusuf on 26.04.2017.
 */

public class VolleyClient extends Application {

    private static final String TAG = VolleyClient.class.getSimpleName();
    private static VolleyClient volleyClient;
    private RequestQueue requestQueue;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    private VolleyClient(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }

    public static synchronized VolleyClient getInstance(Context context) {
        if (volleyClient == null) {
            volleyClient = new VolleyClient(context);
        }
        return volleyClient;
    }

    public <T> void addToRequestQueue(Request<T> tRequest, String tag) { //Tag if emmpty default tag be
        tRequest.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(tRequest);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

}
