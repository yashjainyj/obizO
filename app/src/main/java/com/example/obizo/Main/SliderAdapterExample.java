package com.example.obizo.Main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.obizo.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;
    Dialog dialog;
    TextView titletv,message;
    Button submit;
    ImageView close;
    public  SliderAdapterExample(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slide_layout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        //viewHolder.textViewDescription.setText("This is slider item " + position);

        switch (position) {
            case 0:
                viewHolder.textViewDescription.setText("Mens Wear");
                Glide.with(viewHolder.itemView)
                        .load("https://i.pinimg.com/originals/50/6a/1a/506a1a570f3e42e5e63858f393efbd87.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                viewHolder.textViewDescription.setText("Womens Wear");
                Glide.with(viewHolder.itemView)
                        .load("https://www.utsavpedia.com/wp-content/uploads/2014/10/httpfullonsms.comtrendingwp-contentuploads201407teej.jpg.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                viewHolder.textViewDescription.setText("Kids Wear");
                Glide.with(viewHolder.itemView)
                        .load("https://www.bunosilo.com/media/catalog/product/cache/1/image/1200x/040ec09b1e35df139433887a97daa66f/t/t/tt19.41.028.1_a.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            default:
                viewHolder.textViewDescription.setText("Home Decoratives");
                Glide.with(viewHolder.itemView)
                        .load("https://5.imimg.com/data5/RR/EC/MU/SELLER-3297603/terracotta-ganesha-homedecor-best-options-for-gift-items-500x500.jpg")
                        .into(viewHolder.imageViewBackground);
                break;

        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.positive_popup_layput);
                close  = dialog.findViewById(R.id.close);
                titletv = dialog.findViewById(R.id.titletv);
                message = dialog.findViewById(R.id.message);
                submit = dialog.findViewById(R.id.btnaccept);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ShowItemMain.class);
                        context.startActivity(intent);

                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 4;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}