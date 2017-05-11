package com.yusufcakal.ra.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.yusufcakal.ra.R;
import com.yusufcakal.ra.adapter.GridAdapterDesk;
import com.yusufcakal.ra.interfaces.DeskList;
import com.yusufcakal.ra.interfaces.RefreshDeskStatus;
import com.yusufcakal.ra.model.Desk;
import com.yusufcakal.ra.model.Request;
import com.yusufcakal.ra.services.StaffService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StaffDeskActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        DeskList,
        SwipeRefreshLayout.OnRefreshListener{

    private TextView tvActionBar;
    private GridView gridView;
    private GridAdapterDesk gridAdapter;
    private List<Desk> deskListGlobal;
    private String url = "http://fatihsimsek.me:9090/desklist";
    private Desk deskRef;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeStaffLogin);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_desk);

        deskListGlobal = new ArrayList<>();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                    }
                                }
        );

        Request request = new Request(this, url, com.android.volley.Request.Method.GET);
        request.requestVolleyDeskList(this);

        gridView = (GridView) findViewById(R.id.gvDesk);
        gridView.setOnItemClickListener(this);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        tvActionBar = (TextView)getSupportActionBar().getCustomView().findViewById(R.id.tvActionBar);
        tvActionBar.setText(getResources().getString(R.string.staffDeskHeader));

        //startService(new Intent(StaffDeskActivity.this, StaffService.class));

    }

    @Override
    public void onRefresh() {
        deskListGlobal.clear();
        Request request = new Request(this, url, com.android.volley.Request.Method.GET);
        request.requestVolleyDeskList(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent ıntent = new Intent(this, DeskBasketActivity.class);
        ıntent.putExtra("orderId",deskListGlobal.get(position).getOrderId());
        startActivity(ıntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void getDesk(JSONObject result) {

        try {
            JSONArray deskList = result.getJSONArray("desklist");

            for (int i=0; i<deskList.length(); i++){
                JSONObject desk = (JSONObject) deskList.get(i);

                String name = desk.getString("name");
                String  status = desk.getString("status");
                String orderId = desk.getString("orderId");

                deskRef = new Desk(name, status, orderId);
                deskListGlobal.add(deskRef);
            }

            gridAdapter = new GridAdapterDesk(this, deskListGlobal);
            gridView.setAdapter(gridAdapter);

            swipeRefreshLayout.setRefreshing(false);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
