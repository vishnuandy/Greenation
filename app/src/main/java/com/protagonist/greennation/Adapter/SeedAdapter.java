package com.protagonist.greennation.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.protagonist.greennation.GPSTracker;
import com.protagonist.greennation.MainActivity;
import com.protagonist.greennation.Model.Plant;
import com.protagonist.greennation.Model.Seed;
import com.protagonist.greennation.R;
import com.protagonist.greennation.SeedActivity;
import com.protagonist.greennation.utils.Apputil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SeedAdapter extends RecyclerView.Adapter<SeedAdapter.ViewHolder> {
    private ArrayList<Seed> android;
    private Activity context;

    public SeedAdapter(Activity context, ArrayList<Seed> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public SeedAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeedAdapter.ViewHolder viewHolder, final int i) {


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                View myContentsView = context.getLayoutInflater().inflate(R.layout.buy_seed, null);
                Button buy = (Button) myContentsView.findViewById(R.id.buy_seed);
                TextView description = (TextView) myContentsView.findViewById(R.id.description);
                description.setText(android.get(i).getDescription());
                final RadioButton growit_myself = (RadioButton) myContentsView.findViewById(R.id.grow_myself);
                final RadioButton growit_for_me = (RadioButton) myContentsView.findViewById(R.id.help_grow);

                final String plant_id = android.get(i).getId();
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (growit_myself.isChecked()) {

                            Intent i = new Intent(context, MainActivity.class);
                            context.startActivity(i);
                            //lat 13.0570374
                            //long 80.25959319999993
                            Toast.makeText(context, "Thank you for your purchase", Toast.LENGTH_LONG).show();
                        } else if (growit_for_me.isChecked()) {
                            Intent i = new Intent(context, MainActivity.class);
                            i.putExtra("plant_id", plant_id);
                            context.startActivity(i);
                            Toast.makeText(context, "Thank you for your purchase", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Please select atleast one option ", Toast.LENGTH_LONG).show();

                        }



                    }
                });
                dialog.setContentView(myContentsView);
                dialog.show();
            }
        });
        viewHolder.tv_android.setText(android.get(i).getPlant_name());
        String rupee = context.getResources().getString(R.string.rupee);
        String price_value = rupee + android.get(i).getPrice();
        viewHolder.price.setText(price_value);
        Log.e("android_images", android.get(i).getPlant_name());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
                Log.e("loading error", exception.getLocalizedMessage() + "" + exception.getMessage());
            }
        });
        builder.build().load(android.get(i).getPlant_img()).into(viewHolder.img_android);
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