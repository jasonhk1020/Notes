package com.thinkful.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SettingsActivity extends ActionBarActivity {
    private Button saveButton;
    private RadioGroup radioGroupBackground;
    private RadioGroup radioGroupForeground;

    public void onRadioButtonClicked(View view) {
        //get saved preferences
        SharedPreferences prefs = getSharedPreferences("colors",MODE_PRIVATE);

        //instantiate editor for preferences
        SharedPreferences.Editor editor = prefs.edit();

        //get reference to radio group background
        radioGroupBackground= (RadioGroup) findViewById(R.id.radioGroup_backgroundColor);

        //find the appropriate radio button selected and update the saved preferences accordingly
        switch(radioGroupBackground.getCheckedRadioButtonId()) {
            case R.id.settings_radioButton_backgroundColor1:
                editor.putString("BACKGROUND_COLOR", "RED");
                break;
            case R.id.settings_radioButton_backgroundColor2:
                editor.putString("BACKGROUND_COLOR", "GREEN");
                break;
            case R.id.settings_radioButton_backgroundColor3:
                editor.putString("BACKGROUND_COLOR", "WHITE");
                break;
        }

        //get reference to radio group background
        radioGroupForeground= (RadioGroup) findViewById(R.id.radioGroup_foregroundColor);

        //find the appropriate radio button selected and update the saved preferences accordingly
        switch(radioGroupForeground.getCheckedRadioButtonId()) {
            case R.id.settings_radioButton_foregroundColor1:
                editor.putString("FOREGROUND_COLOR", "YELLOW");
                break;
            case R.id.settings_radioButton_foregroundColor2:
                editor.putString("FOREGROUND_COLOR", "PURPLE");
                break;
            case R.id.settings_radioButton_foregroundColor3:
                editor.putString("FOREGROUND_COLOR", "GRAY");
                break;
            case R.id.settings_radioButton_foregroundColor4:
                editor.putString("FOREGROUND_COLOR", "BLACK");
                break;
        }

        //update the preferences
        editor.commit();
    }

    public void setRadioButtons() {
        //get saved preferences
        SharedPreferences prefs = getSharedPreferences("colors",MODE_PRIVATE);

        switch(prefs.getString("BACKGROUND_COLOR","WHITE")) {
            case "RED":
                RadioButton b1 = (RadioButton)findViewById(R.id.settings_radioButton_backgroundColor1);
                b1.setChecked(true);
                break;
            case "GREEN":
                RadioButton b2 = (RadioButton)findViewById(R.id.settings_radioButton_backgroundColor2);
                b2.setChecked(true);
                break;
            case "WHITE":
                RadioButton b3 = (RadioButton)findViewById(R.id.settings_radioButton_backgroundColor3);
                b3.setChecked(true);
                break;
        }

        switch(prefs.getString("FOREGROUND_COLOR","GRAY")) {
            case "YELLOW":
                RadioButton f1 = (RadioButton)findViewById(R.id.settings_radioButton_foregroundColor1);
                f1.setChecked(true);
                break;
            case "PURPLE":
                RadioButton f2 = (RadioButton)findViewById(R.id.settings_radioButton_foregroundColor2);
                f2.setChecked(true);
                break;
            case "GRAY":
                RadioButton f3 = (RadioButton)findViewById(R.id.settings_radioButton_foregroundColor3);
                f3.setChecked(true);
                break;
            case "BLACK":
                RadioButton f4 = (RadioButton)findViewById(R.id.settings_radioButton_foregroundColor4);
                f4.setChecked(true);
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setRadioButtons();

        //create save button
        saveButton = (Button)findViewById(R.id.settings_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {

            //if save button is clicked return to main activity
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("Color", "settings");
                setResult(RESULT_OK, intent);
                finish();
            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
