package com.yusufcakal.ra.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatButton;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yusufcakal.ra.R;
import com.yusufcakal.ra.adapter.BasketAdapter;
import com.yusufcakal.ra.interfaces.VolleyCallback;
import com.yusufcakal.ra.interfaces.VolleyTemp;
import com.yusufcakal.ra.model.ProductBasket;
import com.yusufcakal.ra.model.ProductBasketDetail;
import com.yusufcakal.ra.model.Request;
//import com.yusufcakal.ra.model.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yusuf on 5.05.2017.
 */

public class BasketFragment extends Fragment implements
        ValueEventListener,
        View.OnClickListener,
        VolleyTemp {

    private View view;
    private List<ProductBasket> productBaskets;
    private ProductBasket productBasket;
    private TextView tvBasketNull;
    private ListView lvBasket;
    private ProgressDialog progressDialog;
    private String url = "http://fatihsimsek.me:9090/detail/";
    private String urlTempDesk = "http://fatihsimsek.me:9090/tempdesk";
    private String android_id;
    private List<ProductBasketDetail> productBasketDetails;
    private ProductBasketDetail productBasketDetail;
    private FlatButton btnOrder;
    private boolean userFlag;
    private int deskID=1;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_basket, container, false);

        android_id = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);

        Request requestTemp = new Request(getActivity(), urlTempDesk, com.android.volley.Request.Method.POST);
        requestTemp.requestVolleyTempDesk(this, deskID);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Sepet Yükleniyor..");
        progressDialog.show();
        progressDialog.hide();

        tvBasketNull = (TextView) view.findViewById(R.id.tvBasketNull);
        lvBasket = (ListView) view.findViewById(R.id.lvBasket);
        btnOrder = (FlatButton) view.findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(this);

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("userBasket", Context.MODE_PRIVATE);
        userFlag = sharedpreferences.getBoolean("user",false);
        if (userFlag){
            btnOrder.setText("SİPARİŞ VER");
        }

        productBaskets = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("basket").child(android_id);
        databaseReference.addValueEventListener(this);

        return view;
    }

    @Override
    public void getTempId(JSONObject tempId) {
        Toast.makeText(getActivity(), String.valueOf(tempId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnOrder) && userFlag){
            Toast.makeText(getActivity(), "Sipariş Verildi Onay Bekliyor.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            productBasket = new ProductBasket();
            productBasket.setPiece(snapshot.getValue(ProductBasket.class).getPiece());
            productBasket.setProductId(snapshot.getValue(ProductBasket.class).getProductId());
            productBaskets.add(productBasket);
        }

        try {
            Toast.makeText(getActivity(), productBaskets.size()+"", Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

}
