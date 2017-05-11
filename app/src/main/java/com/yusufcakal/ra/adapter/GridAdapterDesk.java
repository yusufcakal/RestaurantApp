package com.yusufcakal.ra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusufcakal.ra.R;
import com.yusufcakal.ra.model.Desk;

import java.util.List;

/**
 * Created by Yusuf on 2.05.2017.
 */

public class GridAdapterDesk extends BaseAdapter {

    private Context context;
    private List<Desk> deskList;
    private Desk desk;

    public GridAdapterDesk(Context context, List<Desk> deskList){
        this.context = context;
        this.deskList = deskList;
    }

    @Override
    public int getCount() {
        return deskList.size();
    }

    @Override
    public Object getItem(int position) {
        return deskList.get(position);
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
        } else {
            view = (View) convertView;
        }

        desk = deskList.get(position);

        RelativeLayout rlDesk = (RelativeLayout) view.findViewById(R.id.rlDesk);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);

        if (desk.getStatus().equals("0")){
            rlDesk.setBackgroundColor(context.getResources().getColor(R.color.status0));
        }else if (desk.getStatus().equals("1")){
            rlDesk.setBackgroundColor(context.getResources().getColor(R.color.status1));
        }else if (desk.getStatus().equals("2")){
            rlDesk.setBackgroundColor(context.getResources().getColor(R.color.status2));
        }

        tvName.setText(desk.getName());

        return view;
    }
}
