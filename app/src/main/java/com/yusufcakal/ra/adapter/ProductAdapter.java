package com.yusufcakal.ra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yusufcakal.ra.R;
import com.yusufcakal.ra.model.Product;

import java.util.List;

/**
 * Created by Yusuf on 3.05.2017.
 */

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;
    private Product product;

    public ProductAdapter(Context context, List<Product> productList){
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = new View(context);
            view = inflater.inflate(R.layout.customlist_product, null);

            product = productList.get(position);

            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            tvName.setText(product.getName());


        } else {
            view = (View) convertView;
        }

        return view;
    }
}
