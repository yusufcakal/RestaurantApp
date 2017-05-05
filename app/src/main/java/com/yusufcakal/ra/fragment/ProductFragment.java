package com.yusufcakal.ra.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yusufcakal.ra.R;
import com.yusufcakal.ra.adapter.GridAdapterCategory;
import com.yusufcakal.ra.adapter.ProductAdapter;
import com.yusufcakal.ra.interfaces.VolleyCallback;
import com.yusufcakal.ra.model.Category;
import com.yusufcakal.ra.model.Product;
import com.yusufcakal.ra.model.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yusuf on 3.05.2017.
 */

public class ProductFragment extends Fragment implements VolleyCallback{

    private View view;
    private ListView lvProduct;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private String url = "http://fatihsimsek.me:9090/product/";
    private int id;
    private Product product;
    private ProgressDialog progressDialog;
    private TextView tvProductNull;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product, container, false);

        id = getArguments().getInt("id");
        url+=id;

        Request request = new Request(getActivity(), url, com.android.volley.Request.Method.GET);
        request.requestVolley(this);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Ürünler Yükleniyor..");
        progressDialog.show();

        tvProductNull = (TextView) view.findViewById(R.id.tvProductNull);

        productList = new ArrayList<>();
        lvProduct = (ListView) view.findViewById(R.id.lvProduct);

        return view;
    }

    @Override
    public void onSucces(JSONObject result) {

        JSONArray jsonArray = null;
        try {
            jsonArray = result.getJSONArray("products");
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int productId = jsonObject.getInt("productID");
                int star = jsonObject.getInt("stars");
                int categoryId = jsonObject.getInt("category");
                String name = jsonObject.getString("name");
                String description = jsonObject.getString("description");
                double price = jsonObject.getDouble("price");

                product = new Product(productId, star, categoryId, name, description, price);
                productList.add(product);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        productAdapter = new ProductAdapter(getActivity(), productList);
        lvProduct.setAdapter(productAdapter);

        if (productList.size() == 0){
            tvProductNull.setVisibility(View.VISIBLE);
        }

        progressDialog.hide();
    }

    @Override
    public void onSuccesAuth(JSONObject result) throws JSONException {

    }
}
