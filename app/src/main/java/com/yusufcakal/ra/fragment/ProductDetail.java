package com.yusufcakal.ra.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatButton;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.yusufcakal.ra.R;
import com.yusufcakal.ra.interfaces.VolleyCallback;
import com.yusufcakal.ra.model.Product;
import com.yusufcakal.ra.model.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Yusuf on 6.05.2017.
 */

public class ProductDetail extends Fragment implements VolleyCallback,
        BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener, TextWatcher, View.OnClickListener {

    private View view;
    private int catId;
    private String url = "http://fatihsimsek.me:9090/product/";
    private List<String> imageList;
    private SliderLayout sliderLayout;
    private TextView tvPrice;
    private EditText etPiece;
    private ProgressDialog progressDialog;
    private double price;
    private FlatButton btnAddBasket;
    private int productId;
    private int piece;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        btnAddBasket = (FlatButton) view.findViewById(R.id.btnAddBasket);
        btnAddBasket.setOnClickListener(this);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Yükleniyor..");
        progressDialog.show();

        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        etPiece = (EditText) view.findViewById(R.id.etPiece);
        etPiece.addTextChangedListener(this);

        imageList = new ArrayList<>();
        catId = getArguments().getInt("id");
        url+=catId;
        Request request = new Request(getActivity(), url, com.android.volley.Request.Method.GET);
        request.requestVolley(this);

        return  view;
    }

    @Override
    public void onSucces(JSONObject result) {

        progressDialog.hide();

        JSONArray jsonArray = null;
        try {
            jsonArray = result.getJSONArray("products");
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                productId = jsonObject.getInt("productID");
                int star = jsonObject.getInt("stars");
                int categoryId = jsonObject.getInt("category");
                String name = jsonObject.getString("name");
                String description = jsonObject.getString("description");
                price = jsonObject.getDouble("price");

                tvPrice.setText(price+" TL");

                JSONArray imageArray = jsonObject.getJSONArray("images");

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

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccesAuth(JSONObject result) throws JSONException {
        //TODO : Not Using
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {}
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    @Override
    public void onPageSelected(int position) {}
    @Override
    public void onPageScrollStateChanged(int state) {}


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
        Toast.makeText(getActivity(),"Sepete Eklendi." , Toast.LENGTH_SHORT).show();
    }

}

