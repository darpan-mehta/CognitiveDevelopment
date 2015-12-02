package com.cogdev.cognitivedevelopment;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ShipCounting extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_counting);
        getSupportActionBar().hide();

        mp = MediaPlayer.create(getApplicationContext(), R.raw.parrotintro);
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
            mp = MediaPlayer.create(getApplicationContext(), R.raw.parrotintro);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ship_counting, menu);
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

    public void toastCorrect(View view){

        Toast.makeText(getApplicationContext(), "Ayy Matey, That's Correct!",
                Toast.LENGTH_SHORT).show();
    }

    public void toastIncorrect(View view){

        Toast.makeText(getApplicationContext(), "RRRRRR, That's Not Correct!",
                Toast.LENGTH_SHORT).show();
    }
}
