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
import com.yusufcakal.ra.model.ProductBasket;
import com.yusufcakal.ra.model.ProductBasketDetail;

import java.util.List;

/**
 * Created by Yusuf on 6.05.2017.
 */

public class BasketAdapter extends BaseAdapter {

    private Context context;
    private List<ProductBasketDetail> productBasketDetails;

    public BasketAdapter(Context context, List<ProductBasketDetail> productBasketDetails) {
        this.context = context;
        this.productBasketDetails = productBasketDetails;
    }

    @Override
    public int getCount() {
        return productBasketDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return productBasketDetails.get(position);
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
            view = inflater.inflate(R.layout.customlist_basket, null);

        } else {
            view = (View) convertView;
        }

        ImageView imProduct = (ImageView) view.findViewById(R.id.imProduct);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        TextView tvPiece = (TextView) view.findViewById(R.id.tvPiece);

        ProductBasketDetail productBasketDetail = productBasketDetails.get(position);

        try {
            Picasso.with(context)
                    .load(productBasketDetail.getImageList().get(0))
                    .resize(120, 120)
                    .centerCrop()
                    .into(imProduct);
        }catch (Exception e){

        }

        tvName.setText(productBasketDetail.getName());
        tvPrice.setText((String.valueOf(productBasketDetail.getPrice()+ " TL")));
        tvPiece.setText(String.valueOf(productBasketDetail.getPiece() + " Adet"));

        return view;
    }



}
