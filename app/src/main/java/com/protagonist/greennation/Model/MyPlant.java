package com.protagonist.greennation.Model;

/**
 * Created by Santhosh R on 18-12-2016.
 */

public class MyPlant {
    private String id;

    private long end_date;

    private String plant_pet_name;

    private String current_level;

    private String prev_level;

    private String plant_id;

    private String hyper_local_garden_id;

    private String start_date;

    private String hasura_user_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getEnd_date() {
        return end_date;
    }

    public void setEnd_date(long end_date) {
        this.end_date = end_date;
    }

    public String getPlant_pet_name() {
        return plant_pet_name;
    }

    public void setPlant_pet_name(String plant_pet_name) {
        this.plant_pet_name = plant_pet_name;
    }

    public String getCurrent_level() {
        return current_level;
    }

    public void setCurrent_level(String current_level) {
        this.current_level = current_level;
    }

    public String getPrev_level() {
        return prev_level;
    }

    public void setPrev_level(String prev_level) {
        this.prev_level = prev_level;
    }

    public String getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(String plant_id) {
        this.plant_id = plant_id;
    }

    public String getHyper_local_garden_id() {
        return hyper_local_garden_id;
    }

    public void setHyper_local_garden_id(String hyper_local_garden_id) {
        this.hyper_local_garden_id = hyper_local_garden_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getHasura_user_id() {
        return hasura_user_id;
    }

    public void setHasura_user_id(String hasura_user_id) {
        this.hasura_user_id = hasura_user_id;
    }

    @Override
    public String toString() {
        return "MyPlant [id = " + id + ", end_date = " + end_date + ", plant_pet_name = " + plant_pet_name + ", current_level = " + current_level + ", prev_level = " + prev_level + ", plant_id = " + plant_id + ", hyper_local_garden_id = " + hyper_local_garden_id + ", start_date = " + start_date + ", hasura_user_id = " + hasura_user_id + "]";
    }
}
