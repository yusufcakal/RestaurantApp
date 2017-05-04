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

import java.util.List;

/**
 * Created by Yusuf on 3.05.2017.
 */

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<String> stringList;

    public ProductAdapter(Context context, List<String> stringList){
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
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

        } else {
            view = (View) convertView;
        }

        return view;
    }
}
