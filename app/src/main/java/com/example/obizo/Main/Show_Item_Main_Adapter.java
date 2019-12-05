package com.example.obizo.Main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.obizo.Main.Product_Detail;
import com.example.obizo.R;
import com.example.obizo.seller.Item_data_model;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class Show_Item_Main_Adapter extends RecyclerView.Adapter<Show_Item_Main_Adapter.Adapter_ViewHolder> {

    private Context context;

    public Show_Item_Main_Adapter(Context context, List<Item_data_model> list) {
        this.context = context;
        this.list = list;
    }

    private List<Item_data_model> list;
    @NonNull
    @Override
    public Adapter_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items,viewGroup,false);
        Adapter_ViewHolder adapter_viewHolder=new Adapter_ViewHolder(view);
        return adapter_viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull Adapter_ViewHolder adapter_viewHolder, int i) {
        final Item_data_model item_data_model = list.get(i);
        adapter_viewHolder.name.setText(item_data_model.getItemName());
        adapter_viewHolder.price.setText("Rs."+item_data_model.getItemPrice());
        Glide.with(context).load(item_data_model.getImageUrl()).into(adapter_viewHolder.item_image);
        adapter_viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Product_Detail.class);
                intent.putExtra("item_id",item_data_model.getItemId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Adapter_ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
       TextView name,price;
        public Adapter_ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.book_img_id);
            name = itemView.findViewById(R.id.book_title_id);
            price = itemView.findViewById(R.id.price);
           // desc = itemView.findViewById(R.id.description);
            //category = itemView.findViewById(R.id.category);

        }
    }
}
