package com.c77.esteban.sensors;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.c77.esteban.sensors.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void giveNumber(View view) {
        // Do something in response to button2
        Intent intent = new Intent(this, ThrowDice.class);
        startActivity(intent);
    }

    public void showAcelerometer(View view) {
        // Do something in response to button2
        Intent intent = new Intent(this, ShowAccelerometer.class);
        startActivity(intent);
    }

    public void showMagnetometer(View view) {
        // Do something in response to button3
        Intent intent = new Intent(this, ShowMagnetometer.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
//                openSearch();
                return true;
            case R.id.action_settings:
//                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
