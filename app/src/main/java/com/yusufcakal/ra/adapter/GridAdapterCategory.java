package com.yusufcakal.ra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yusufcakal.ra.R;
import com.yusufcakal.ra.model.Category;

import java.util.List;

/**
 * Created by Yusuf on 3.05.2017.
 */

public class GridAdapterCategory extends BaseAdapter {

    private Context context;
    private List<Category> categoryList;
    private Category category;

    public GridAdapterCategory(Context context, List<Category> categoryList){
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
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
            view = inflater.inflate(R.layout.custom_catgrid, null);

        } else {
            view = (View) convertView;
        }

        ImageView imCat = (ImageView) view.findViewById(R.id.imCat);
        TextView tvCat = (TextView) view.findViewById(R.id.tvCategory);
        category = categoryList.get(position);

        String image = category.getImage();
        String name = category.getName();

        Picasso.with(context).load(image).resize(175, 175).centerCrop().into(imCat);
        tvCat.setText(name);

        return view;
    }
}
