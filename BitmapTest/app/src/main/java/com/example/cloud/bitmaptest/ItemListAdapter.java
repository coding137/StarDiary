package com.example.cloud.bitmaptest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cloud on 2017-03-09.
 */

public class ItemListAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private ArrayList<Item> itemsList;

    public ItemListAdapter(Context context, int layout, ArrayList<Item> itemsList) {
        this.context = context;
        this.layout = layout;
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private  class  ViewHolder{
        ImageView imageView;
        TextView textTopic,textContents;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View row =view;
        ViewHolder holder = new ViewHolder();

        if(row ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.textTopic = (TextView) row.findViewById(R.id.topic);
            holder.textContents = (TextView)row.findViewById(R.id.contents);
            holder.imageView = (ImageView) row.findViewById(R.id.itemImg);
            row.setTag(holder);
        }else {
            holder = (ViewHolder) row.getTag();
        }

        Item item = itemsList.get(position);
        holder.textTopic.setText(item.getTopic());
        holder.textContents.setText(item.getContents());
        byte[] itemImage = item.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0 ,itemImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
