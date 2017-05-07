package com.yusufcakal.ra.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatButton;
import com.google.android.gms.vision.barcode.Barcode;
import com.yusufcakal.ra.Manifest;
import com.yusufcakal.ra.R;
import com.yusufcakal.ra.interfaces.VolleyCallback;
import com.yusufcakal.ra.model.Request;

import static com.google.android.gms.internal.zzt.TAG;

public class MainActivity extends Activity implements View.OnClickListener{

    private Typeface tfBold, tfRegular;
    private FlatButton btnStaff, btnOrder;
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;
    private static final String TAG = "TAG";

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

            int permissionChech = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
            if (permissionChech != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST);
                startCamActivity();
            }else{
                Log.i(TAG, "Kamera izni zaten verilmişti.");
                startCamActivity();
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null){
                Barcode barcode = data.getParcelableExtra("barcode");
                Toast.makeText(this, barcode.displayValue+"", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void startCamActivity(){
        startActivityForResult(new Intent(this, CamActivity.class), REQUEST_CODE); //UserActivity
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
