package com.yusufcakal.ra.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.yusufcakal.ra.R;
import com.yusufcakal.ra.adapter.GridAdapterDesk;

import java.util.ArrayList;
import java.util.List;

public class StaffDeskActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private TextView tvActionBar;
    private GridView gridView;
    private GridAdapterDesk gridAdapter;
    private List<Integer> ıntegerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeStaffLogin);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_desk);

        ıntegerList = new ArrayList<>();

        for (int i=0; i<20; i++){
            ıntegerList.add(i);
        }

        gridView = (GridView) findViewById(R.id.gvDesk);
        gridView.setOnItemClickListener(this);

        gridAdapter = new GridAdapterDesk(this, ıntegerList);
        gridView.setAdapter(gridAdapter);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        tvActionBar = (TextView)getSupportActionBar().getCustomView().findViewById(R.id.tvActionBar);
        tvActionBar.setText(getResources().getString(R.string.staffDeskHeader));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
    }
}
