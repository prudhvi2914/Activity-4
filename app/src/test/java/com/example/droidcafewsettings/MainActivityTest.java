package com.example.droidcafewsettings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.Menu;
import android.view.View;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
    private OrderActivity orderActivity;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();

        orderActivity = Robolectric.buildActivity(OrderActivity.class).create().resume().get();

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
        //   Test that the app remembers the information the user entered on the Shopping Cart page.
        //1.go to orders page
        //2.add some data
        //2a.click the button to save
        //3.go back to the page reenter the orderpage
        //4.check the data entered using equals
        //5.check the data is still equals the last entered data

        FloatingActionButton shoppingcartbtn = (FloatingActionButton) activity.findViewById(R.id.fab);
        shoppingcartbtn.performClick();


//        sharedPref = ShadowPreferenceManager.getDefaultSharedPreferences(Robolectric.application.getApplicationContext());
//        sharedPref.edit().putString("userName", "").commit();



        //1.add some data
        EditText Name = (EditText) orderActivity.findViewById(R.id.name_text);Name.setText("pokemon");
        EditText address = (EditText) orderActivity.findViewById(R.id.address_text);address.setText("universe boss");
        EditText Phone = (EditText) orderActivity.findViewById(R.id.phone_text);Phone.setText("12345678");
        EditText Note = (EditText) orderActivity.findViewById(R.id.note_text);Note.setText("cartoon");
        RadioButton checked = (RadioButton) orderActivity. findViewById(R.id.sameday);
        checked.setChecked(true);


        //2.save the details by clicking the button
        Button save = (Button) orderActivity.findViewById(R.id.saveButton);
        save.callOnClick();

//3.go back and re-enter the orderspage
        orderActivity.onBackPressed();
        shoppingcartbtn.performClick();

//4.check the data using assert equals
        assertEquals("pokemon",Name.getText().toString());
        assertEquals("universe boss",address.getText().toString());
        assertEquals("12345678",Phone.getText().toString());
        assertEquals("cartoon",Note.getText().toString());
        assertTrue(checked.isChecked());

//
//        SharedPreferences sharedPref ;
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString("name", etName.getText().toString());
//        editor.putString("address",etAddress.getText().toString());
//        editor.putString("phone",etPhone.getText().toString());
//        editor.commit();
    }


}



