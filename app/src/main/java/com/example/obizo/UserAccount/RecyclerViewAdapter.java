package com.example.obizo.UserAccount;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.obizo.R;
import com.example.obizo.seller.Item_data_model;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Aws on 28/01/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Item_data_model> mData ;


    public RecyclerViewAdapter(Context mContext, List<Item_data_model> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Item_data_model item_data_model = mData.get(position);
        holder.tv_book_title.setText(mData.get(position).getItemName());
       // holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.price.setText(item_data_model.getItemPrice());
        Glide.with(mContext)
                .asBitmap()
                .load(item_data_model.getImageUrl())
                .into(holder.img_book_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_book_title,price;
        ImageView img_book_thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            price = itemView.findViewById(R.id.price);


        }
    }


}