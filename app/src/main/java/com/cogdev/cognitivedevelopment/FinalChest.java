package com.cogdev.cognitivedevelopment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

public class FinalChest extends AppCompatActivity {
    MediaPlayer mp;

    private Timer inactivityTimer;
    private MyTimerTask inactivityTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_chest);
        getSupportActionBar().hide();

        mp = MediaPlayer.create(getApplicationContext(), R.raw.treasurefinal);
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
            mp = MediaPlayer.create(getApplicationContext(), R.raw.treasurefinal);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }



        timerToFinalScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_final_chest, menu);
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


    //Shows the final screen for 5 seconds
    class MyTimerTask extends TimerTask {
        @Override
        //Starts timer and sends user to the next book page when completed
        public void run(){
            Intent intent = new Intent(FinalChest.this, EndScreen.class);
            startActivity(intent);

            //Animation to make app seem more like a story book
            //Files are in the anim folder
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        }
    }

    //Sets the timer time
    public void timerToFinalScreen() {
        inactivityTimer = new Timer();
        inactivityTask = new MyTimerTask();
        //Starts a timer that blacks out the screen after 90 seconds unless the user interacts though either voice or touch
        inactivityTimer.schedule(inactivityTask, 5000);
    }
}
