package com.c77.esteban.sensors;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.view.View;

public class ThrowDice extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_number);
        textView = (TextView) findViewById(R.id.tvDice);
        textView.setTextSize(20);
        changeDice(textView);
    }

    public void changeDice(View view) {
        String message; //= intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        int number = (int) Math.floor(Math.random()*(6)+1);
        switch (number) {
            case 1:
                message="The dice fall 1";
                break;
            case 2:
                message="The dice fall 2";
                break;
            case 3:
                message="The dice fall 3";
                break;
            case 4:
                message="The dice fall 4";
                break;
            case 5:
                message="The dice fall 5";
                break;
            case 6:
                message="The dice fall 6";
                break;
            default:
                message="The dice fall 0";
                break;
        }
        textView.setText(message);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.throw_dice, menu);
        return true;
    }
    
}
