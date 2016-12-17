package com.protagonist.greennation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.protagonist.greennation.Adapter.DataAdapter;
import com.protagonist.greennation.Model.Plant;

import java.util.ArrayList;

public class MyForest extends AppCompatActivity {

    private final String plant_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",

    };

    private final String plant_images[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initViews();

    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Plant> androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(), androidVersions);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Plant> prepareData() {

        ArrayList<Plant> android_version = new ArrayList<>();
        for (int i = 0; i < plant_names.length; i++) {
            Plant androidVersion = new Plant();
            androidVersion.setPlant_name(plant_names[i]);
            androidVersion.setPlant_image(plant_images[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }
}
