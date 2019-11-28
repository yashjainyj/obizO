package com.example.obizo.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.obizo.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;

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
                        .load("https://planetfashion.imgix.net/img/app/shopmedia/production/1/10-57-2563.jpg?w=992&auto=format")
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
                        .load("https://www.fbbonline.in/media/mcms/kidsethnic-01_15700913391029.jpg")
                        .into(viewHolder.imageViewBackground);
                break;
            default:
                viewHolder.textViewDescription.setText("Wedding Season");
                Glide.with(viewHolder.itemView)
                        .load("http://culturextourism.com/wp-content/uploads/2015/12/South-Indian-Dresses.jpg")
                        .into(viewHolder.imageViewBackground);
                break;

        }

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