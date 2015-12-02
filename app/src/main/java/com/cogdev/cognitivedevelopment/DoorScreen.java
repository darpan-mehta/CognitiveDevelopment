package com.cogdev.cognitivedevelopment;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

public class DoorScreen extends AppCompatActivity {
    MediaPlayer mp;
    int[] soundsr = {R.raw.rightdoor1,R.raw.running1,R.raw.rdoorsound1};
    int[] soundsg = {R.raw.rightdoor1,R.raw.running1,R.raw.gdoorsound1};
    int[] soundsb = {R.raw.rightdoor1,R.raw.running1,R.raw.bdoorsound1};

    Random r = new Random();
    final int Low = 0;
    final int High = 2;
    int rndm = r.nextInt((High - Low)+1) + Low;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_screen);
        getSupportActionBar().hide();

        mp = MediaPlayer.create(getApplicationContext(), R.raw.introdoor);
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
            mp = MediaPlayer.create(getApplicationContext(), R.raw.introdoor);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageButton imageButtonRed = (ImageButton) findViewById(R.id.imageButtonRed);
        mp = MediaPlayer.create(getApplicationContext(), soundsr[rndm]);
        imageButtonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        rndm = r.nextInt((High - Low) + 1) + Low;
                        mp = MediaPlayer.create(getApplicationContext(), soundsr[rndm]);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton imageButtonGreen = (ImageButton) findViewById(R.id.imageButtonGreen);
        mp = MediaPlayer.create(getApplicationContext(), soundsg[rndm]);
        imageButtonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        rndm = r.nextInt((High - Low) + 1) + Low;
                        mp = MediaPlayer.create(getApplicationContext(), soundsg[rndm]);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton imageButtonBlue = (ImageButton) findViewById(R.id.imageButtonBlue);
        mp = MediaPlayer.create(getApplicationContext(), soundsb[rndm]);
        imageButtonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        rndm = r.nextInt((High - Low) + 1) + Low;
                        mp = MediaPlayer.create(getApplicationContext(), soundsb[rndm]);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_door_screen, menu);
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
