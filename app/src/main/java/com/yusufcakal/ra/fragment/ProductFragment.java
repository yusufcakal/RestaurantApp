package com.yusufcakal.ra.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yusufcakal.ra.R;
import com.yusufcakal.ra.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yusuf on 3.05.2017.
 */

public class ProductFragment extends Fragment{

    private View view;
    private ListView lvProduct;
    private ProductAdapter productAdapter;
    private List<String> stringList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product, container, false);

        stringList = new ArrayList<>();

        for (int i=0; i<20; i++){
            stringList.add("asd");
        }

        lvProduct = (ListView) view.findViewById(R.id.lvProduct);
        productAdapter = new ProductAdapter(getActivity(), stringList);
        lvProduct.setAdapter(productAdapter);

        return view;
    }

}
