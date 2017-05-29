package com.yusufcakal.ra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yusufcakal.ra.R;
import com.yusufcakal.ra.model.Product;

import java.util.List;

/**
 * Created by Yusuf on 12.05.2017.
 */

public class DeskOrderAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;
    private Product product;

    public DeskOrderAdapter(Context context, List<Product> productList){
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
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = new View(context);
            view = inflater.inflate(R.layout.custom_basket_list, null);

        } else {
            view = (View) convertView;
        }

        product = productList.get(position);

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        ImageView imProduct = (ImageView) view.findViewById(R.id.imProduct);
        TextView tvPiece = (TextView) view.findViewById(R.id.tvPiece);

        int piece = product.getPiece();
        double price = product.getPrice();

        try {
            Picasso.with(context)
                    .load(product.getImage())
                    .resize(120, 120)
                    .centerCrop()
                    .into(imProduct);
        }catch (Exception e){

        }

        tvName.setText(product.getName());
        tvPrice.setText(price + " TL");
        tvPiece.setText(String.valueOf(piece)+ " ADET");

        return view;
    }

}
