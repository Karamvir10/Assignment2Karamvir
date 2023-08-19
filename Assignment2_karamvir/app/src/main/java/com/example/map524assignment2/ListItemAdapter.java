package com.example.map524assignment2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListItemAdapter extends BaseAdapter {
    ArrayList<Items> list;
    Context context;
    public ListItemAdapter(ArrayList<Items> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_layout,null);
        TextView itemName = view.findViewById(R.id.itemQtyAndName);
        TextView itemPrice = view.findViewById(R.id.itemPrice);
        itemName.setText(String.valueOf(list.get(i).name + "        "  + list.get(i).quant));
        itemPrice.setText(String.valueOf(list.get(i).price));
        return view;
    }
}
