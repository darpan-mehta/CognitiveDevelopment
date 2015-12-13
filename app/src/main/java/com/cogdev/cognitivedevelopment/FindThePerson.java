package com.cogdev.cognitivedevelopment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class FindThePerson extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_the_person);
        getSupportActionBar().hide();

        //Sound implementation is the same structure as ShipCounting.java - Darpan

        mp = MediaPlayer.create(getApplicationContext(), R.raw.friendintro);
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
            mp = MediaPlayer.create(getApplicationContext(), R.raw.friendintro);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageButton imageButtonPeople = (ImageButton) findViewById(R.id.imageButtonPeople);
        imageButtonPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                    }
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.friendyes);
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            goToFindTheChest();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton imageButtonDolphin = (ImageButton) findViewById(R.id.imageButtonDolphin);
        imageButtonDolphin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();

                    }
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.friendno1);
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton imageButtonWalrus = (ImageButton) findViewById(R.id.imageButtonWalrus);
        imageButtonWalrus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();

                    }
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.friendno);
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton imageButtonPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();

                    }
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.friendintro);
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
        getMenuInflater().inflate(R.menu.menu_find_the_person, menu);
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

    //Sends to next book page
    public void goToFindTheChest() {
        Intent intent = new Intent(this, FindTheChest.class);
        startActivity(intent);

        //Animation to make app seem more like a story book
        //Files are in the anim folder
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
