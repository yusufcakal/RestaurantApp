package com.yusufcakal.ra.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatButton;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yusufcakal.ra.R;
import com.yusufcakal.ra.interfaces.ProductDetailBarChange;
import com.yusufcakal.ra.interfaces.VolleyCallback;
import com.yusufcakal.ra.interfaces.VolleyTemp;
import com.yusufcakal.ra.model.ProductBasket;
import com.yusufcakal.ra.model.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Yusuf on 13.05.2017.
 */

public class DetailPrıductFragment extends Fragment implements
        VolleyCallback,
        BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener,
        TextWatcher,
        View.OnClickListener{

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private View view;
    private int productIdExstra;
    private String urlDetail = "http://fatihsimsek.me:9090/detail/";
    private List<String> imageList;
    private SliderLayout sliderLayout;
    private TextView tvPrice;
    private EditText etPiece;
    private ProgressDialog progressDialog;
    private double price;
    private FlatButton btnAddBasket;
    private int piece, productID;
    private String android_id;
    private ProductDetailBarChange productDetailBarChange;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_product, container, false);

        android_id = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);

        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("basket").child(android_id);

        btnAddBasket = (FlatButton) view.findViewById(R.id.btnAddBasket);
        btnAddBasket.setOnClickListener(this);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Ürün Yükleniyor..");
        progressDialog.show();
        progressDialog.hide();

        //OnSuucese kaydet

        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        etPiece = (EditText) view.findViewById(R.id.etPiece);
        etPiece.addTextChangedListener(this);

        imageList = new ArrayList<>();
        productIdExstra = getArguments().getInt("id");

        urlDetail+=productIdExstra;

        Request request = new Request(getActivity(), urlDetail, com.android.volley.Request.Method.GET);
        request.requestVolley(this);

        return view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (count == 0){
            tvPrice.setText(price+ " TL");
        }else{
            piece = Integer.parseInt(String.valueOf(s));
            tvPrice.setText(String.valueOf(price*piece+ " TL"));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        if (piece==0){
            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("ADET")
                    .setContentText("Lütfen seçmiş olduğunuz ürüne adet bilgisi giriniz.")
                    .show();
        }else{
            ProductBasket productBasket = new ProductBasket(productID, piece);
            databaseReference.push().setValue(productBasket);
            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("EKLENDİ")
                    .setContentText("Ürün sepete eklendi.")
                    .show();
        }
        closeKeyword();
    }

    private void closeKeyword(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSucces(JSONObject result) {

        progressDialog.hide();

        JSONArray jsonArray = null;
        try {
            jsonArray = result.getJSONArray("detail");

            JSONObject productObject;
            JSONArray imageArray = null;
            String name = null;
            for (int i=0; i<jsonArray.length(); i++){
                productObject = (JSONObject) jsonArray.get(i);
                
                productID = productObject.getInt("productID");
                int star = productObject.getInt("stars");
                name = productObject.getString("name");
                String description = productObject.getString("description");
                price = productObject.getDouble("price");

                productDetailBarChange.onChangeBar(name);

                imageArray = productObject.getJSONArray("images");

                tvPrice.setText(price+" TL");
            }

            for (int j=0; j<imageArray.length(); j++){
                JSONObject imageObject = imageArray.getJSONObject(j);
                imageList.add(imageObject.getString("image"));
            }

            for (int k=0; k<imageList.size(); k++){
                TextSliderView textSliderView = new TextSliderView(getActivity());
                // initialize a SliderLayout
                textSliderView
                        .description(name)
                        .image(imageList.get(k))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                sliderLayout.addSlider(textSliderView);
            }

            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(3000);
            sliderLayout.addOnPageChangeListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccesAuth(JSONObject result) throws JSONException {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        productDetailBarChange = (ProductDetailBarChange) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        productDetailBarChange = null;
    }
}
