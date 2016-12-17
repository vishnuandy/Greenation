package com.protagonist.greennation.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by makeshg on 18/12/16.
 */

public class HyperLocals implements Parcelable {
    private String id;

    private String lon;

    private String garden_name;

    private String land_space;

    private String hasura_user_id;

    private String type_space;

    private String lat;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getGarden_name ()
    {
        return garden_name;
    }

    public void setGarden_name (String garden_name)
    {
        this.garden_name = garden_name;
    }

    public String getLand_space ()
    {
        return land_space;
    }

    public void setLand_space (String land_space)
    {
        this.land_space = land_space;
    }

    public String getHasura_user_id ()
    {
        return hasura_user_id;
    }

    public void setHasura_user_id (String hasura_user_id)
    {
        this.hasura_user_id = hasura_user_id;
    }


    public String getType_space ()
    {
        return type_space;
    }

    public void setType_space (String type_space)
    {
        this.type_space = type_space;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "HyperLocals [id = "+id+", lon = "+lon+", garden_name = "+garden_name+", land_space = "+land_space+", hasura_user_id = "+hasura_user_id+", type_space = "+type_space+", lat = "+lat+"]";
    }

    protected HyperLocals(Parcel in) {
        id = in.readString();
        lon = in.readString();
        garden_name = in.readString();
        land_space = in.readString();
        hasura_user_id = in.readString();
        type_space = in.readString();
        lat = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(lon);
        dest.writeString(garden_name);
        dest.writeString(land_space);
        dest.writeString(hasura_user_id);
        dest.writeString(type_space);
        dest.writeString(lat);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<HyperLocals> CREATOR = new Parcelable.Creator<HyperLocals>() {
        @Override
        public HyperLocals createFromParcel(Parcel in) {
            return new HyperLocals(in);
        }

        @Override
        public HyperLocals[] newArray(int size) {
            return new HyperLocals[size];
        }
    };
}