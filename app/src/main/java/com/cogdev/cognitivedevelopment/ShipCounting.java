package com.cogdev.cognitivedevelopment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ShipCounting extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_counting);
        getSupportActionBar().hide();

        //Intro set to play when it starts - Darpan
        mp = MediaPlayer.create(getApplicationContext(), R.raw.parrotintro);
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release(); //to catch and release the player so there's not multiple soundclips playing
            }
            mp = MediaPlayer.create(getApplicationContext(), R.raw.parrotintro);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Simple sound play - Darpan
        ImageButton button3 = (ImageButton) findViewById(R.id.imageButton3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                    }
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.parrot3);
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton button5 = (ImageButton) findViewById(R.id.imageButton5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                    }
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.parrot5);
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            goToPeople();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton button7 = (ImageButton) findViewById(R.id.imageButton7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                    }
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.parrot7);
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton buttonplay = (ImageButton) findViewById(R.id.imageButtonPlay);
        buttonplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

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

    //Toasts used for testing
    public void toastCorrect(View view){

        Toast.makeText(getApplicationContext(), "Ayy Matey, That's Correct!",
                Toast.LENGTH_SHORT).show();
    }

    public void toastIncorrect(View view){

        Toast.makeText(getApplicationContext(), "RRRRRR, That's Not Correct!",
                Toast.LENGTH_SHORT).show();
    }

    //Send to next page
    public void goToPeople() {
        Intent intent = new Intent(this, FindThePerson.class);
        startActivity(intent);

        //Animation to make app seem more like a story book
        //Files are in the anim folder
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
