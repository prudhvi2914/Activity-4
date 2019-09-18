package com.example.droidcafewsettings;

import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.view.Menu;
import android.widget.Button;

import static org.hamcrest.CoreMatchers.equalTo;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowToast;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)


public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void activityIsNotNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void testButtonClickShouldShowToast() throws Exception {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        ImageView view = (ImageView) activity.findViewById(R.id.froyo);
        assertNotNull(view);
        view.performClick();
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("You ordered a FroYo.") );
    }


}
