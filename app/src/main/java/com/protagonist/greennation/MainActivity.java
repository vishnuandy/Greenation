package com.protagonist.greennation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.protagonist.greennation.Task.Task_createplant;
import com.protagonist.greennation.Task.Task_createprofile;
import com.protagonist.greennation.callbacks.Request_createPlant;
import com.protagonist.greennation.helper.SessionManager;
import com.protagonist.greennation.json.Endpoints;
import com.protagonist.greennation.utils.Apputil;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener, Request_createPlant {
    String plant_id = "";
    private ContextMenuDialogFragment mMenuDialogFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_growth);
        fragmentManager = getSupportFragmentManager();
        Intent i = getIntent();
        plant_id = i.getStringExtra("plant_id");
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
       mMenuDialogFragment.setItemClickListener(this);
        api_create_myplant();
//        mMenuDialogFragment.setItemLongClickListener(this);
    }
    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.ic_close);

        MenuObject send = new MenuObject("Water the plant");
        send.setResource(R.drawable.menu_water);

        MenuObject like = new MenuObject("Feed your soil");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.menu_fertilizer);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("Track your plant");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.menu_location));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("Know about your plant");
        addFav.setResource(R.drawable.menu_info);

        MenuObject block = new MenuObject("Yet to come");
        block.setResource(R.drawable.back);


        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(block);
        return menuObjects;
    }

    public void api_create_myplant() {
        if (Apputil.checkInternetConnection()) {

            Endpoints.urlactionname = "create_my_plant";
            //Facebook_hasura_detail facebook_hasuradetail = sessionManager.get_Facebooklogin_hasuradetail();


            JSONObject params = new JSONObject();
            try {
                SessionManager session = new SessionManager();
                params.put("hasura_user_id", session.get_hasura_userid());
                params.put("plant_id", Integer.parseInt(plant_id));
                params.put("hyper_local_garden_id", 9999);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("api_create_user: ", params.toString());
            new Task_createplant(this, params).execute();
        } else {
            //  Apputil.No_network_connection(AppTourActivity.this);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            finish();
        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        if(position==1){
            Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
        }else if(position==2){
            Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
        }else if(position==3){
            Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
        }else if(position==4){
            Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
        }
        else if(position==5){
            Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void oncreate_plant(String plant) {
        if (plant != null || plant.isEmpty()) {
            Log.e("plant_result", plant);

        }
    }
    /*@Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }
*/}
