package com.yusufcakal.ra.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;
import com.yusufcakal.ra.R;
import java.util.List;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

public class CamActivity extends AppCompatActivity implements BarcodeRetriever {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeStaffLogin);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        getSupportActionBar().hide();

        BarcodeCapture barcodeCapture = (BarcodeCapture) getSupportFragmentManager().findFragmentById(R.id.barcode);
        barcodeCapture.setRetrieval(this);

    }

    @Override
    public void onRetrieved(final Barcode barcode) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CamActivity.this, barcode.displayValue, Toast.LENGTH_SHORT).show();
            }
        });


    }

    // for multiple callback
    @Override
    public void onRetrievedMultiple(final Barcode barcode, final List<BarcodeGraphic> barcodeGraphics) {
        /*
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CamActivity.this, barcode.displayValue, Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
        // when image is scanned and processed
    }

    @Override
    public void onRetrievedFailed(String reason) {
        // in case of failure
    }
}
