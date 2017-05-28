package com.yusufcakal.ra.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yusufcakal.ra.R;
import com.yusufcakal.ra.adapter.GridAdapterDesk;
import com.yusufcakal.ra.adapter.OrderHistoryAdapter;
import com.yusufcakal.ra.interfaces.VolleyCallback;
import com.yusufcakal.ra.model.Desk;
import com.yusufcakal.ra.model.OrderHistory;
import com.yusufcakal.ra.model.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DeskOrdersActivity extends AppCompatActivity implements
        VolleyCallback,
        AdapterView.OnItemClickListener{

    private TextView tvActionBar;
    private ListView lvOrderHistory;
    private String orderId;
    private String url = "http://fatihsimsek.me:9090/deskOrders/";
    private OrderHistoryAdapter orderHistoryAdapter;
    private List<OrderHistory> orderHistoryList;
    private OrderHistory orderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeStaffLogin);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_orders);

        orderHistoryList = new ArrayList<>();
        orderHistoryAdapter = new OrderHistoryAdapter(this, orderHistoryList);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        tvActionBar = (TextView)getSupportActionBar().getCustomView().findViewById(R.id.tvActionBar);
        tvActionBar.setText(getResources().getText(R.string.orderHistory));

        lvOrderHistory = (ListView) findViewById(R.id.lvOrderHistory);
        lvOrderHistory.setOnItemClickListener(this);

        orderId = getIntent().getStringExtra("orderId");
        url+=orderId;

        Request request = new Request(this, url, com.android.volley.Request.Method.GET);
        request.requestVolley(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent ıntent = new Intent(this, DeskBasketActivity.class);
        ıntent.putExtra("orderId",orderHistoryList.get(position).getOrderId());
        ıntent.putExtra("status",orderHistoryList.get(position).getStatus());
        startActivity(ıntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onSucces(JSONObject result) {
        try {
            JSONArray deskList = result.getJSONArray("deskOrders");

            for (int i=0; i<deskList.length(); i++){
                JSONObject desk = (JSONObject) deskList.get(i);

                int orderId = desk.getInt("orderID");
                int  status = desk.getInt("status");
                String date = desk.getString("date"); // tempId gelicek
                Double totalPrice = desk.getDouble("total"); // tempId gelicek

                orderHistory = new OrderHistory(orderId, status, date, totalPrice);
                orderHistoryList.add(orderHistory);

            }

            lvOrderHistory.setAdapter(orderHistoryAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccesAuth(JSONObject result) throws JSONException {

    }

}
