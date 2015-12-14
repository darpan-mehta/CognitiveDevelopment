package com.cogdev.cognitivedevelopment;
/**
 * Created by Sarah on 11/22/2015.
 * Implements canvas to trace shapes. Once shape is traced moves to next activity
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class DrawView extends View implements OnTouchListener {
    private static final String TAG = "DrawView";
    //variable to calculate accuracy
    int sideOne=0;
    int sideTwo=0;
    int sideThree=0;
    int sideFour=0;
    int numWrong=0;
    int total=0;

    //variable for media player
    private static MediaPlayer mpg;
    private static MediaPlayer mp;
    boolean voice = false;

    //variables for canvas
    List<Point> points = new ArrayList<Point>();
    Paint button = new Paint();
    Paint paint = new Paint();
    Paint newPaint = new Paint();

    //randomize shapes
    Random rand = new Random();
    int n = rand.nextInt(3);
    Bitmap a = BitmapFactory.decodeResource(getResources(), R.drawable.squaret);
    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.circlet);
    Bitmap c = BitmapFactory.decodeResource(getResources(), R.drawable.trianglet);

    public DrawView(Context context) {
        //set up ontouch canvas
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

        //set up paint color
        newPaint.setColor(Color.GREEN);
        newPaint.setAntiAlias(true);
        button.setColor(Color.TRANSPARENT);
        button.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int h = 1283;
        int w = 770;
        //if shape is square
        if (n == 0) {
            Bitmap scaled = Bitmap.createScaledBitmap(a, h, w, true);
            canvas.drawBitmap(scaled, -3, 0, null);
            for (Point point : points) {
                //button for media player
                if (point.x <= 1000 && point.y <= 250 && point.y >= 0 && point.x >= 800) {
                    canvas.drawRect(800, 0, 1000, 250, button);
                    voice=true;
                }
                //paint sides in green
                else if (point.x <= 850 && point.y <= 375 && point.y >= 300 && point.x >= 450) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideOne++;
                } else if (point.x <= 850 && point.y <= 550 && point.y >= 500 && point.x >= 450) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideTwo++;
                } else if (point.x <= 825 && point.y <= 540 && point.y >= 300 && point.x >= 775) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideThree++;
                } else if (point.x <= 575 && point.y <= 540 && point.y >= 300 && point.x >= 500) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideFour++;
                }
                //paint other screen in red
                else {
                    canvas.drawCircle(point.x, point.y, 25, paint);
                    numWrong++;
                }
                total++;
            }
        }
        //if shape is a circle
        else if (n == 1) {
            Bitmap scaled = Bitmap.createScaledBitmap(b, h, w, true);
            canvas.drawBitmap(scaled, -2, 0, null);
            for (Point point : points) {
                //button for media player
                if (point.x <= 1000 && point.y <= 250 && point.y >= 0 && point.x >= 800) {
                    canvas.drawRect(800, 0, 1000, 250, button);
                    voice=true;
                }
                //paint in green
                else if (((point.x-680)*(point.x-680)) + ((point.y-420)*(point.y-420)) < 27000 && ((point.x-660)*(point.x-660)) + ((point.y-440)*(point.y-440)) > 5000) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideOne++;
                }
                //paint in red
                else {
                    canvas.drawCircle(point.x, point.y, 25, paint);
                    numWrong++;
                }
                total++;
            }
        }
        //if the shape is a triangle
        else {
            Bitmap scaled = Bitmap.createScaledBitmap(c, h, w, true);
            canvas.drawBitmap(scaled, -1, 0, null);
            for (Point point : points) {
                //button for media player
                if (point.x <= 1000 && point.y <= 250 && point.y >= 0 && point.x >= 800) {
                    canvas.drawRect(800, 0, 1000, 250, button);
                    voice=true;
                }
                //paint sides in green
                else if (((point.x*2)-1200)<point.y && ((point.x*2)-1000)>point.y && point.y>200 && point.y<550) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideOne++;
                } else if (((point.x*(-2))+1500)<point.y && ((point.x*(-2))+1700)>point.y && point.y>200 && point.y<550) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideTwo++;
                } else if (point.x <= 850 && point.y <= 570 && point.y >= 500 && point.x >= 450) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideThree++;
                }
                //paint in red
                else {
                    canvas.drawCircle(point.x, point.y, 25, paint);
                    numWrong++;
                }
                total++;
            }
        }
    }
    public boolean onTouch(View view, MotionEvent event) {
        //choose sound based on shape
        if(n==0){
            mpg = MediaPlayer.create(view.getContext(), R.raw.treasuresquare);
        }
        else if(n==1){
            mpg = MediaPlayer.create(view.getContext(), R.raw.treasurecircle);
        }
        else {
            mpg = MediaPlayer.create(view.getContext(), R.raw.treasuretriangle);
        }
        mp = MediaPlayer.create(view.getContext(), R.raw.trace);

        //keep track of points
        Point point = new Point();
        point.x = event.getX();
        point.y = event.getY();
        points.add(point);

        //start sound on button
        if (voice == true && point.x <= 1000 && point.y <= 250 && point.y >= 0 && point.x >= 800){
            voice=false;
            try {
                if (mpg.isPlaying()) {
                    mpg.stop();
                    mpg.release();
                }
                mpg.start();
                while (mpg.isPlaying()){
                    Thread.yield(); //stop garbage collector from removing object until completion
                }
                mpg.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //if the shape is triangle
        if (n==0) {
            //calculate whether to go to next activity
            if (sideOne > 3 && sideTwo > 3 && sideThree > 3 && sideFour > 3 && numWrong < 50 && point.x <= 590 && point.y <= 430 && point.y >= 270 && point.x >= 440) {
                view.getContext().startActivity(new Intent(view.getContext(), FinalChest.class));
                points.clear();
                sideOne = 0;
                sideTwo = 0;
                sideThree = 0;
                numWrong = 0;
                total = 0;
            }
            //clear points if too inaccurate
            else if (numWrong > 51) {
                Toast.makeText(view.getContext(), "Try again", Toast.LENGTH_LONG).show();
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                    }
                    mp.start();
                    while (mp.isPlaying()){
                        Thread.yield(); //stop garbage collector from removing object until completion
                    }
                    mp.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                points.clear();
                sideOne = 0;
                sideTwo = 0;
                sideThree = 0;
                numWrong = 0;
                total = 0;
            }
        }
        //if the shape is circle
        else if (n==1) {
            //calculate whether to go to next activity
            if (sideOne > 10 && total>30 && numWrong < 50 && point.x <=600 && point.y <= 420 && point.y >= 280 && point.x >= 555) {
                view.getContext().startActivity(new Intent(view.getContext(), FinalChest.class));
                points.clear();
                sideOne = 0;
                numWrong = 0;
                total = 0;
            }
            //clear points if too inaccurate
            else if (numWrong > 51) {
                Toast.makeText(view.getContext(), "Try again", Toast.LENGTH_LONG).show();
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                    }
                    mp.start();
                    while (mp.isPlaying()){
                        Thread.yield(); //stop garbage collector from removing object until completion
                    }
                    mp.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                points.clear();
                sideOne = 0;
                numWrong = 0;
                total = 0;
            }
        }
        //if the shape is a triangle
        else {
            //calculate whether to go to next activity
            if (sideOne > 5 && sideTwo > 5 && sideThree > 5 && numWrong < 50 && point.x <=710 && point.y <= 420 && point.y >= 280 && point.x >= 580) {
                view.getContext().startActivity(new Intent(view.getContext(), FinalChest.class));
                points.clear();
                sideOne = 0;
                sideTwo = 0;
                sideThree = 0;
                numWrong = 0;
                total = 0;
            }
            //clear points if too inaccurate
            else if (numWrong > 51) {
                Toast.makeText(view.getContext(), "Try again", Toast.LENGTH_LONG).show();
                try {
                    if (mpg.isPlaying()) {
                        mp.stop();
                        mp.release();
                    }
                    mp.start();
                    while (mp.isPlaying()){
                        Thread.yield(); //stop garbage collector from removing object until completion
                    }
                    mp.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                points.clear();
                sideOne = 0;
                sideTwo = 0;
                sideThree = 0;
                numWrong = 0;
                total = 0;
            }
        }
        invalidate();
        return true;
    }
}

//Point class which produces objects with x and y coordinate
class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
