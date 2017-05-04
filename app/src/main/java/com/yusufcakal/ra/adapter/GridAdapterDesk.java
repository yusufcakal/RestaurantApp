package com.yusufcakal.ra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusufcakal.ra.R;

import java.util.List;

/**
 * Created by Yusuf on 2.05.2017.
 */

public class GridAdapterDesk extends BaseAdapter {

    private Context context;
    private List<Integer> ıntegerList;

    public GridAdapterDesk(Context context, List<Integer> ıntegerList){
        this.context = context;
        this.ıntegerList = ıntegerList;
    }

    @Override
    public int getCount() {
        return ıntegerList.size();
    }

    @Override
    public Object getItem(int position) {
        return ıntegerList.get(position);
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
            view = inflater.inflate(R.layout.custom_grid, null);

            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rlDesk);

            /*

            if (statusDesk.get(position) == 0){
                relativeLayout.setBackgroundColor();
            }

            */


            TextView tvDesk = (TextView) view.findViewById(R.id.tvDesk);
            tvDesk.setText(String.valueOf(position));

        } else {
            view = (View) convertView;
        }

        return view;
    }
}
