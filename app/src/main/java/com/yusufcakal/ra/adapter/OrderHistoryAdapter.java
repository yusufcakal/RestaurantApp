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
import com.yusufcakal.ra.model.OrderHistory;
import com.yusufcakal.ra.model.Product;

import java.util.List;

/**
 * Created by Yusuf on 27.05.2017.
 */

public class OrderHistoryAdapter extends BaseAdapter {

    private Context context;
    private List<OrderHistory> orderHistoryList;
    private String date;
    private double totalPrice;
    private int status;

    public OrderHistoryAdapter(Context context, List<OrderHistory> orderHistoryList){
        this.context = context;
        this.orderHistoryList = orderHistoryList;
    }

    @Override
    public int getCount() {
        return orderHistoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderHistoryList.get(position);
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
            view = inflater.inflate(R.layout.order_history_layout, null);

        } else {
            view = (View) convertView;
        }

        date = orderHistoryList.get(position).getDate();
        totalPrice = orderHistoryList.get(position).getPriceTotal();
        status = orderHistoryList.get(position).getStatus();

        RelativeLayout rlOrderHistory = (RelativeLayout) view.findViewById(R.id.rlOrderHistory);
        TextView tvDate = (TextView) view.findViewById(R.id.tvOrderDate);
        TextView tvTotalPrice = (TextView) view.findViewById(R.id.tvOrderPriceTotal);

        if (status == 0){
            rlOrderHistory.setBackgroundColor(context.getResources().getColor(R.color.status0));
        }else if (status == 1){
            rlOrderHistory.setBackgroundColor(context.getResources().getColor(R.color.status1));
            tvDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            tvTotalPrice.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }else if (status == 2){
            rlOrderHistory.setBackgroundColor(context.getResources().getColor(R.color.status2));
            tvDate.setTextColor(context.getResources().getColor(R.color.colorWhite));
            tvTotalPrice.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }

        tvDate.setText(date);
        tvTotalPrice.setText(String.valueOf(totalPrice+ " TL "));

        return view;
    }
}
