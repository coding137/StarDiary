package com.hello.myapp.myrecycleview;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloud on 2017-06-15.
 */

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    ArrayList<String> myStringList;
    EndlessListener endlessListener;
    public  void setEndlessListener(EndlessListener endlessListener){
        this.endlessListener= endlessListener;
    }

    public AdapterItem(ArrayList<String> myStringList) {

       this. myStringList= new ArrayList<>();
        this.myStringList = myStringList;

    }



    @Override
    public int getItemViewType(int position) {
        return myStringList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{
         final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.tvItem);

        }
    }




    @Override
    public AdapterItem.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        // 이곳에서 Layout 설정
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterItem.ViewHolder holder, int position) {
        String text = myStringList.get(position);
        if(position == getItemCount()-1){
            if(endlessListener !=null){
                endlessListener.onLoadMore(position);
            }
        }
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {

        if(myStringList==null){
            return  0;

        }else{
            return myStringList.size();

        }
    }


    public  interface  EndlessListener{

        boolean onLoadMore(int position);
    }

}
