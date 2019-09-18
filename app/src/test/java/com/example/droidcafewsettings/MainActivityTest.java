package com.example.droidcafewsettings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatImageView;
import android.view.Menu;
import android.widget.Button;

import static org.hamcrest.CoreMatchers.equalTo;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.ShadowToast;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)


public class MainActivityTest {

    private MainActivity activity;
    private final static String FILENAME = "filename";
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    private Context context;
    private String etName;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();


        context = ApplicationProvider.getApplicationContext();

        sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        // Ensure no shared preferences have leaked from previous tests.
        assertThat(sharedPreferences.getAll()).hasSize(0);
    }

    @Test
    public void activityIsNotNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void testButtonClickShouldShowToast() throws Exception {
        //1.get the button id
        //2click the button
        //3.check the toast
        //4.compare it with expected value


        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        //get the id of image view
        ImageView view = (ImageView) activity.findViewById(R.id.froyo);
        //check the view is not null
        assertNotNull(view);
        //click the image view
        view.performClick();
        //comparing actual vs expected
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("You ordered a FroYo.") );
    }


    @Test
    public void cartPageRemembersUSerInfo() throws Exception {

//
     //   Test that the app remembers the information the user entered on the Shopping Cart page.
        //1.test the sharedpreferences are empty

        //2.add some data
        //3.check the data entered equals
        //4.close the app and enter the order activity again
        //5.check the data is still equals the last entered data

        editor.commit();

      SharedPreferences anotherSharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
           assertTrue(anotherSharedPreferences.getString("string", "prudhvi")).isEqualTo(etName.getText().toString());


           SharedPreferences sharedPreferences = Robolectric.application.getSharedPreferences("you_custom_pref_name", Context.MODE_PRIVATE);
           sharedPreferences.edit().putString("name", "prudhvi").commit();
        assertThat(Shadow(etName).getText().toString(), equalTo("prudhvi") );



    }


}
