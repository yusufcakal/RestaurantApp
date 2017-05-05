package com.yusufcakal.ra.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusufcakal.ra.R;
import com.yusufcakal.ra.model.Product;

/**
 * Created by Yusuf on 6.05.2017.
 */

public class ProductDetail extends Fragment{

    private View view;
    private Product product;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        return  view;
    }

}
