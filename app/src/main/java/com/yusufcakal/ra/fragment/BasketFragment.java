package com.yusufcakal.ra.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yusufcakal.ra.R;
import com.yusufcakal.ra.interfaces.VolleyCallback;
import com.yusufcakal.ra.model.ProductBasket;
import com.yusufcakal.ra.model.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yusuf on 5.05.2017.
 */

public class BasketFragment extends Fragment{

    private View view;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<ProductBasket> productBaskets;
    private ProductBasket productBasket;
    private TextView tvBasketNull;
    private ListView lvBasket;
    private ProgressDialog progressDialog;
    private String url = "http://fatihsimsek.me:9090/detail/";
    private String android_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_basket, container, false);

        android_id = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Sepet Yükleniyor..");
        progressDialog.show();

        tvBasketNull = (TextView) view.findViewById(R.id.tvBasketNull);
        lvBasket = (ListView) view.findViewById(R.id.lvBasket);

        productBaskets = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("basket").child(android_id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.hide();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    productBasket = new ProductBasket();
                    productBasket = snapshot.getValue(ProductBasket.class);
                    productBaskets.add(productBasket);
                }

                for (ProductBasket basket : productBaskets) {
                    Toast.makeText(getActivity(), basket.getPiece()+"", Toast.LENGTH_SHORT).show();
                }

                if (productBaskets.size() == 0){
                    tvBasketNull.setVisibility(View.VISIBLE);
                    lvBasket.setVisibility(View.GONE);
                }else{
                    tvBasketNull.setVisibility(View.GONE);
                    lvBasket.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.hide();

            }
        });


        return view;
    }

}
