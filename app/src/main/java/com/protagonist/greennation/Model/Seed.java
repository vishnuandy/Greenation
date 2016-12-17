package com.protagonist.greennation.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Santhosh R on 18-12-2016.
 */
public class Seed implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Seed> CREATOR = new Parcelable.Creator<Seed>() {
        @Override
        public Seed createFromParcel(Parcel in) {
            return new Seed(in);
        }

        @Override
        public Seed[] newArray(int size) {
            return new Seed[size];
        }
    };
    private String stage_plant_emerge;
    private String stage_fruit_growth;
    private String stage_flowering;
    private String suitable_regions;
    private long suitable_climate_start;
    private String stage_sprout_start;
    private String plant_name;
    private String id;
    private String plant_lifetime;
    private String price;
    private String oxygen_creation_level;
    private long suitable_climate_end;
    private String description;
    private String stage_full_grown_plant;
    private String plant_img;

    protected Seed(Parcel in) {
        stage_plant_emerge = in.readString();
        stage_fruit_growth = in.readString();
        stage_flowering = in.readString();
        suitable_regions = in.readString();
        suitable_climate_start = in.readLong();
        stage_sprout_start = in.readString();
        plant_name = in.readString();
        id = in.readString();
        plant_lifetime = in.readString();
        price = in.readString();
        oxygen_creation_level = in.readString();
        suitable_climate_end = in.readLong();
        description = in.readString();
        stage_full_grown_plant = in.readString();
        plant_img = in.readString();
    }

    public String getStage_plant_emerge() {
        return stage_plant_emerge;
    }

    public void setStage_plant_emerge(String stage_plant_emerge) {
        this.stage_plant_emerge = stage_plant_emerge;
    }

    public String getStage_fruit_growth() {
        return stage_fruit_growth;
    }

    public void setStage_fruit_growth(String stage_fruit_growth) {
        this.stage_fruit_growth = stage_fruit_growth;
    }

    public String getStage_flowering() {
        return stage_flowering;
    }

    public void setStage_flowering(String stage_flowering) {
        this.stage_flowering = stage_flowering;
    }

    public String getSuitable_regions() {
        return suitable_regions;
    }

    public void setSuitable_regions(String suitable_regions) {
        this.suitable_regions = suitable_regions;
    }

    public long getSuitable_climate_start() {
        return suitable_climate_start;
    }

    public void setSuitable_climate_start(long suitable_climate_start) {
        this.suitable_climate_start = suitable_climate_start;
    }

    public String getStage_sprout_start() {
        return stage_sprout_start;
    }

    public void setStage_sprout_start(String stage_sprout_start) {
        this.stage_sprout_start = stage_sprout_start;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlant_lifetime() {
        return plant_lifetime;
    }

    public void setPlant_lifetime(String plant_lifetime) {
        this.plant_lifetime = plant_lifetime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOxygen_creation_level() {
        return oxygen_creation_level;
    }

    public void setOxygen_creation_level(String oxygen_creation_level) {
        this.oxygen_creation_level = oxygen_creation_level;
    }

    public long getSuitable_climate_end() {
        return suitable_climate_end;
    }

    public void setSuitable_climate_end(long suitable_climate_end) {
        this.suitable_climate_end = suitable_climate_end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStage_full_grown_plant() {
        return stage_full_grown_plant;
    }

    public void setStage_full_grown_plant(String stage_full_grown_plant) {
        this.stage_full_grown_plant = stage_full_grown_plant;
    }

    public String getPlant_img() {
        return plant_img;
    }

    public void setPlant_img(String plant_img) {
        this.plant_img = plant_img;
    }

    @Override
    public String toString() {
        return "Seed [stage_plant_emerge = " + stage_plant_emerge + ", stage_fruit_growth = " + stage_fruit_growth + ", stage_flowering = " + stage_flowering + ", suitable_regions = " + suitable_regions + ", suitable_climate_start = " + suitable_climate_start + ", stage_sprout_start = " + stage_sprout_start + ", plant_name = " + plant_name + ", id = " + id + ", plant_lifetime = " + plant_lifetime + ", price = " + price + ", oxygen_creation_level = " + oxygen_creation_level + ", suitable_climate_end = " + suitable_climate_end + ", description = " + description + ", stage_full_grown_plant = " + stage_full_grown_plant + ", plant_img = " + plant_img + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stage_plant_emerge);
        dest.writeString(stage_fruit_growth);
        dest.writeString(stage_flowering);
        dest.writeString(suitable_regions);
        dest.writeLong(suitable_climate_start);
        dest.writeString(stage_sprout_start);
        dest.writeString(plant_name);
        dest.writeString(id);
        dest.writeString(plant_lifetime);
        dest.writeString(price);
        dest.writeString(oxygen_creation_level);
        dest.writeLong(suitable_climate_end);
        dest.writeString(description);
        dest.writeString(stage_full_grown_plant);
        dest.writeString(plant_img);
    }
}