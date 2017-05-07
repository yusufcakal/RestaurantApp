package com.yusufcakal.ra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatButton;
import com.squareup.picasso.Picasso;
import com.yusufcakal.ra.R;
import com.yusufcakal.ra.model.Product;

import java.util.List;

/**
 * Created by Yusuf on 3.05.2017.
 */

public class ProductAdapter extends BaseAdapter{

    private Context context;
    private List<Product> productList;
    private Product product;
    private ViewHolder viewHolder;

    static class ViewHolder{
        private TextView tvName, tvPrice;
        private ImageView imProduct, imStar;
        private int star;
        private double price;
        private FlatButton btnAddBasket;
    }

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
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = new View(context);
            view = inflater.inflate(R.layout.customlist_product, null);

        } else {
            view = (View) convertView;
        }

        product = productList.get(position);

        viewHolder = new ViewHolder();
        viewHolder.tvName = (TextView) view.findViewById(R.id.tvName);
        viewHolder.tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        viewHolder.imStar = (ImageView) view.findViewById(R.id.imStar);
        viewHolder.imProduct = (ImageView) view.findViewById(R.id.imProduct);
        viewHolder.btnAddBasket = (FlatButton) view.findViewById(R.id.btnAddBasket);

        viewHolder.btnAddBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Sepete Eklendi.", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.star = product.getStar();
        viewHolder.price = product.getPrice();

        try {
            Picasso.with(context)
                    .load(product.getImageList().get(0))
                    .resize(120, 120)
                    .centerCrop()
                    .into(viewHolder.imProduct);
        }catch (Exception e){

        }



        switch (viewHolder.star){
            case 0: viewHolder.imStar.setBackground(context.getResources().getDrawable(R.drawable.rating0)); break;
            case 1: viewHolder.imStar.setBackground(context.getResources().getDrawable(R.drawable.rating1)); break;
            case 2: viewHolder.imStar.setBackground(context.getResources().getDrawable(R.drawable.rating2)); break;
            case 3: viewHolder.imStar.setBackground(context.getResources().getDrawable(R.drawable.rating3)); break;
            case 4: viewHolder.imStar.setBackground(context.getResources().getDrawable(R.drawable.rating4)); break;
            case 5: viewHolder.imStar.setBackground(context.getResources().getDrawable(R.drawable.rating5)); break;
        }

        viewHolder.tvName.setText(product.getName());
        viewHolder.tvPrice.setText(viewHolder.price + " TL");

        view.setTag(viewHolder);

        return view;
    }
}
