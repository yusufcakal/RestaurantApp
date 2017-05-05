package com.yusufcakal.ra.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.cengalabs.flatui.views.FlatButton;
import com.yusufcakal.ra.R;
import com.yusufcakal.ra.interfaces.VolleyCallback;
import com.yusufcakal.ra.model.Request;

public class MainActivity extends Activity implements View.OnClickListener{

    private Typeface tfBold, tfRegular;
    private FlatButton btnStaff, btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tfBold = Typeface.createFromAsset(getAssets(), "fonts/lato/Lato-Bold.ttf");
        tfRegular = Typeface.createFromAsset(getAssets(), "fonts/lato/Lato-Regular.ttf");

        btnStaff = (FlatButton) findViewById(R.id.btnStaff);
        btnOrder = (FlatButton) findViewById(R.id.btnOrder);
        btnStaff.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
        btnStaff.setTypeface(tfRegular);
        btnOrder.setTypeface(tfRegular);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnStaff)){
            startActivity(new Intent(this, StaffLoginActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }else if (v.equals(btnOrder)){
            startActivity(new Intent(this, UserActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO:App is finish
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
