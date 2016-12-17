package com.protagonist.greennation.callbacks;

import com.protagonist.greennation.Model.Seed;
import com.protagonist.greennation.Model.UserPlant;

import java.util.ArrayList;

/**
 * Created by makeshg on 17/12/16.
 */

public interface Request_listseeds {
    public void oncreate_seedlist(ArrayList<Seed> plants);
}
