/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.droidcafewsettings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity handles radio buttons for choosing
 * a delivery method for an order, and EditText input controls.
 */
public class OrderActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    /**
     * Sets the content view to activity_order, and gets the intent and its
     * data.
     *
     * @param savedInstanceState Saved instance state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Get the intent and its data.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);

        // get shared preference
        sharedPref  = getPreferences(Context.MODE_PRIVATE);

        // Load data based on SharedPreferences
        EditText etName = (EditText) findViewById(R.id.name_text);
        EditText etAddress = (EditText) findViewById(R.id.address_text);
        EditText etPhone = (EditText) findViewById(R.id.phone_text);

        String name = sharedPref.getString("name", "");
        String address = sharedPref.getString("address", "");
        String phone = sharedPref.getString("phone", "");

        etName.setText(name);
        etAddress.setText(address);
        etPhone.setText(phone);

    }

    /**
     * Checks which radio button was clicked and displays a toast message to
     * show the choice.
     *
     * @param view The radio button view.
     */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    // Same day service
                    displayToast(
                            getString(R.string.same_day_messenger_service));
                break;
            case R.id.nextday:
                if (checked)
                    // Next day delivery
                    displayToast(getString(R.string.next_day_ground_delivery));
                break;
            case R.id.pickup:
                if (checked)
                    // Pick up
                    displayToast(getString(R.string.pick_up));
                break;
            default:
                // Do nothing.
                break;
        }
    }

    /**
     * Displays the actual message in a toast message.
     *
     * @param message Message to display.
     */
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void saveCustomerInfo(View view) {
        EditText etName = (EditText) findViewById(R.id.name_text);
        EditText etAddress = (EditText) findViewById(R.id.address_text);
        EditText etPhone = (EditText) findViewById(R.id.phone_text);


        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", etName.getText().toString());
        editor.putString("address",etAddress.getText().toString());
        editor.putString("phone",etPhone.getText().toString());
        editor.commit();
    }
}
