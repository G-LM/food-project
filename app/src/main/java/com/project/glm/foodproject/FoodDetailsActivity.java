package com.project.glm.foodproject;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by glm on 15/08/2017.
 */

public class FoodDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Common.isLandscapeOrientation(this) && Common.isLargeScreenLayout(this)) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            FoodDetailsFragment details = new FoodDetailsFragment();
            details.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }

}
