package com.hello.myapp.myrecycleview;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,AdapterItem.EndlessListener{
public AdapterItem adapter;
public    ArrayList<String> myList;
    public SwipeRefreshLayout swipeRefreshLayout;
    public RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
        setupSwipe();
    }
    private  void setupSwipe(){
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    private void setupRecyclerView(){

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        myList= new ArrayList<>();

        for(int i =0 ; i <10; i ++){
            myList.add("my Item.no"+i);
        }

        adapter= new AdapterItem(myList);

        mRecyclerView.addItemDecoration(new ItemDeco(this));
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onRefresh() {
        Log.d("MainActivity_","onRefresh");
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);


            }
        },2000);
    }

    @Override
    protected void onStart() {
        super.onStart();




    }


    @Override
    public boolean onLoadMore(int position) {
        return false;
    }
}
