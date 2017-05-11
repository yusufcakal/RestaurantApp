package com.yusufcakal.ra.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatButton;
import com.yusufcakal.ra.R;
import com.yusufcakal.ra.adapter.DeskOrderAdapter;
import com.yusufcakal.ra.interfaces.ChangeDeskStatus;
import com.yusufcakal.ra.interfaces.DeskList;
import com.yusufcakal.ra.model.Product;
import com.yusufcakal.ra.model.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DeskBasketActivity extends AppCompatActivity implements
        DeskList,
        View.OnClickListener,
        ChangeDeskStatus
{

    private TextView tvActionBar;
    private String orderId;
    private ListView lvBasket;
    private DeskOrderAdapter deskOrderAdapter;
    private List<Product> productList;
    private String url = "http://fatihsimsek.me:9090/baskets/";
    private String urlChangeStatus = "http://fatihsimsek.me:9090/changestatus";
    private String name, image;
    private int piece;
    private double price;
    private Product product;
    private FlatButton btnOrderVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeStaffLogin);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_basket);

        lvBasket= (ListView) findViewById(R.id.lvBasket);
        productList = new ArrayList<>();

        btnOrderVerify = (FlatButton) findViewById(R.id.btnOrderVerify);
        btnOrderVerify.setOnClickListener(this);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        tvActionBar = (TextView)getSupportActionBar().getCustomView().findViewById(R.id.tvActionBar);
        tvActionBar.setText(getResources().getString(R.string.staffDeskBasketHeader));
        tvActionBar.setTextAppearance(this, android.R.style.TextAppearance_Large);
        tvActionBar.setTextColor(Color.WHITE);

        orderId = getIntent().getStringExtra("orderId");
        url+=orderId;

        Request request = new Request(this, url, com.android.volley.Request.Method.GET);
        request.requestVolleyDeskList(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, StaffDeskActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void getDesk(JSONObject result) {

        try {
            JSONArray desks = result.getJSONArray("baskets");
            for (int i=0; i<desks.length(); i++){
                JSONObject desk = (JSONObject) desks.get(i);
                name = desk.getString("name");
                image = desk.getString("image");
                piece = desk.getInt("piece");
                price = desk.getDouble("total");

                product = new Product(piece, name, image, price);
                productList.add(product);

            }

            deskOrderAdapter = new DeskOrderAdapter(this, productList);
            lvBasket.setAdapter(deskOrderAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnOrderVerify)){
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
            sweetAlertDialog.setTitleText("ONAYLANDI");
            sweetAlertDialog.setContentText("Sipariş Onaylandı");
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            Request request = new Request(getApplicationContext(), urlChangeStatus, com.android.volley.Request.Method.POST);
                            request.requestVolleyDeskList(DeskBasketActivity.this, Integer.valueOf(orderId), 2);
                        }
                    });
            sweetAlertDialog.show();
        }
    }

    @Override
    public void changeStatus(JSONObject result) {
        try {
            boolean flag = result.getBoolean("result");
            if (flag){
                startActivity(new Intent(this, StaffDeskActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
