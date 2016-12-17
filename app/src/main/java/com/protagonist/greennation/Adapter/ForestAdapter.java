package com.protagonist.greennation.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.protagonist.greennation.Model.Plant;
import com.protagonist.greennation.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ForestAdapter extends RecyclerView.Adapter<ForestAdapter.ViewHolder> {
    private ArrayList<Plant> android;
    private Context context;

    public ForestAdapter(Context context, ArrayList<Plant> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public ForestAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForestAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(android.get(i).getPlant_name());
        viewHolder.price.setVisibility(View.GONE);
        Log.e("android_images", android.get(i).getPlant_image());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
                Log.e("loading error", exception.getLocalizedMessage() + "" + exception.getMessage());
            }
        });
        builder.build().load("http://lohitsascience.weebly.com/uploads/2/2/6/0/22607136/622994_orig.jpg").into(viewHolder.img_android);
        // Picasso.with(context).load(android.get(i).getPlant_image()).resize(240, 120).into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_android;
        private ImageView img_android;
        private TextView price;
        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView) view.findViewById(R.id.tv_plant_name);
            img_android = (ImageView) view.findViewById(R.id.img_plant);
            price = (TextView) view.findViewById(R.id.price);

        }
    }

}