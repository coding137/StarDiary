package com.example.cloud.bitmaptest;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Cloud on 2017-03-09.
 */

public class Itemlist extends AppCompatActivity{
    GridView gridView;
    ArrayList<Item> list;
    ItemListAdapter adapter= null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();

        adapter = new ItemListAdapter(this,R.layout.items,list);
        gridView.setAdapter(adapter);

        //get Data Everything

        Cursor cursor =MainActivity.sqLiteHelper.getData("SELECT * FROM MEMO");
        list.clear();
        while(cursor.moveToNext()){
           int id = cursor.getInt(0);
            String topic = cursor.getString(1);
            String contents =cursor.getString(2);
            byte[] image= cursor.getBlob(3);

            list.add(new Item(id,topic,contents,image));
        }
        adapter.notifyDataSetChanged();
    }
}
