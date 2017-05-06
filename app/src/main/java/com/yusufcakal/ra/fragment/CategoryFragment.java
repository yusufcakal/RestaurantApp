package com.yusufcakal.ra.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.yusufcakal.ra.R;
import com.yusufcakal.ra.adapter.GridAdapterCategory;
import com.yusufcakal.ra.interfaces.CategoryCallback;
import com.yusufcakal.ra.interfaces.VolleyCallback;
import com.yusufcakal.ra.model.Category;
import com.yusufcakal.ra.model.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yusuf on 3.05.2017.
 */

public class CategoryFragment extends Fragment implements AdapterView.OnItemClickListener, VolleyCallback{

    private View view;
    private GridView gridView;
    private GridAdapterCategory gridAdapterCategory;
    private List<Category> categoryList;
    private CategoryCallback categoryCallback;
    private String urlCategory = "http://fatihsimsek.me:9090/category";
    private ProgressDialog progressDialog;
    private Category category;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);

        categoryList = new ArrayList<>();

        Request request = new Request(getActivity(), urlCategory, com.android.volley.Request.Method.GET);
        request.requestVolley(this);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Yükleniyor.");
        progressDialog.show();

        gridView = (GridView) view.findViewById(R.id.gvCat);
        gridView.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        categoryCallback.call(new ProductFragment(), position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        categoryCallback = (CategoryCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        gridAdapterCategory = null;
    }

    @Override
    public void onSucces(JSONObject result) {
        JSONArray jsonArray = null;
        try {
            jsonArray = result.getJSONArray("categories");
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String catId = jsonObject.getString("categoryId");
                String name = jsonObject.getString("name");
                String image = jsonObject.getString("image");

                category = new Category(catId, name, image);
                categoryList.add(category);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        gridAdapterCategory = new GridAdapterCategory(getActivity(), categoryList);
        gridView.setAdapter(gridAdapterCategory);

        progressDialog.hide();
    }

    @Override
    public void onSuccesAuth(JSONObject result) {

    }

}
