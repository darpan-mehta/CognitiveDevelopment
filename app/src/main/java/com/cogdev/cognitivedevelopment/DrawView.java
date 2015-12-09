package com.cogdev.cognitivedevelopment;
/**
 * Created by Sarah on 11/22/2015.
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
    private static MediaPlayer mpg;
    private static final String TAG = "DrawView";
    int position=0;
    int sideOne=0;
    int sideTwo=0;
    int sideThree=0;
    int sideFour=0;
    int numWrong=0;
    int total=0;
    boolean voice = false;
    List<Point> points = new ArrayList<Point>();
    Paint button = new Paint();
    Paint paint = new Paint();
    Paint newPaint = new Paint();
    Random rand = new Random();
    int n = rand.nextInt(3);
    Bitmap a = BitmapFactory.decodeResource(getResources(), R.drawable.squaret);
    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.circlet);
    Bitmap c = BitmapFactory.decodeResource(getResources(), R.drawable.trianglet);

    public DrawView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

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
        if (n == 0) {
            Bitmap scaled = Bitmap.createScaledBitmap(a, h, w, true);
            canvas.drawBitmap(scaled, -3, 0, null);
            for (Point point : points) {
                if (point.x <= 1000 && point.y <= 250 && point.y >= 0 && point.x >= 800) {
                    canvas.drawRect(800,0,1000,250,button);
                    voice=true;
                } else if (point.x <= 850 && point.y <= 375 && point.y >= 300 && point.x >= 450) {
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
                } else {
                    canvas.drawCircle(point.x, point.y, 25, paint);
                    numWrong++;
                }
                total++;
            }
        }
        else if (n == 1) {
            Bitmap scaled = Bitmap.createScaledBitmap(b, h, w, true);
            canvas.drawBitmap(scaled, -2, 0, null);
            for (Point point : points) {
                if (point.x <= 1000 && point.y <= 250 && point.y >= 0 && point.x >= 800) {
                    canvas.drawRect(800, 0, 1000, 250, button);
                    voice=true;
                } else if (((point.x-680)*(point.x-680)) + ((point.y-420)*(point.y-420)) < 27000 && ((point.x-660)*(point.x-660)) + ((point.y-440)*(point.y-440)) > 5000) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideOne++;
                } else {
                    canvas.drawCircle(point.x, point.y, 25, paint);
                    numWrong++;
                }
                total++;
            }
        }
        else {
            Bitmap scaled = Bitmap.createScaledBitmap(c, h, w, true);
            canvas.drawBitmap(scaled, -1, 0, null);
            for (Point point : points) {
                if (point.x <= 1000 && point.y <= 250 && point.y >= 0 && point.x >= 800) {
                    canvas.drawRect(800, 0, 1000, 250, button);
                    voice=true;
                } else if (((point.x*2)-1200)<point.y && ((point.x*2)-1000)>point.y && point.y>200 && point.y<550) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideOne++;
                } else if (((point.x*(-2))+1500)<point.y && ((point.x*(-2))+1700)>point.y && point.y>200 && point.y<550) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideTwo++;
                } else if (point.x <= 850 && point.y <= 570 && point.y >= 500 && point.x >= 450) {
                    canvas.drawCircle(point.x, point.y, 25, newPaint);
                    sideThree++;
                    sideFour=100;
                } else {
                    canvas.drawCircle(point.x, point.y, 25, paint);
                    numWrong++;
                }
                total++;
            }
        }
    }
    public boolean onTouch(View view, MotionEvent event) {
        // if(event.getAction() != MotionEvent.ACTION_DOWN)
        // return super.onTouchEvent(event);
        mpg = MediaPlayer.create(view.getContext(), R.raw.introdoor);
        Point point = new Point();
        point.x = event.getX();
        point.y = event.getY();
        points.add(point);
        Log.d(TAG, "point: " + point);
        if (voice == true && point.x <= 1000 && point.y <= 250 && point.y >= 0 && point.x >= 800){
            voice=false;
            try {
                if (mpg.isPlaying()) {
                    mpg.stop();
                    mpg.release();
                }
                mpg.start();
                while (mpg.isPlaying()){
                    Thread.yield();
                }
                mpg.release();
                //mpg.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                //    public void onCompletion(MediaPlayer mpg) {
                //        mpg.release();
                //    }
                //});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (n==0) {
            if (sideOne > 3 && sideTwo > 3 && sideThree > 3 && sideFour > 3 && numWrong < 50 && point.x <= 590 && point.y <= 430 && point.y >= 270 && point.x >= 440) {
                //Toast.makeText(view.getContext(), "Congrats. You traced the shape.", Toast.LENGTH_LONG).show();
                view.getContext().startActivity(new Intent(view.getContext(), FinalChest.class));
                points.clear();
                sideOne = 0;
                sideTwo = 0;
                sideThree = 0;
                sideFour = 0;
                numWrong = 0;
                total = 0;
            } else if (numWrong > 51) {
                Toast.makeText(view.getContext(), "Try again", Toast.LENGTH_LONG).show();
                points.clear();
                sideOne = 0;
                sideTwo = 0;
                sideThree = 0;
                sideFour = 0;
                numWrong = 0;
                total = 0;
            }
        }
        else if (n==1) {
            if (sideOne > 10 && total>30 && numWrong < 50 && point.x <=600 && point.y <= 420 && point.y >= 280 && point.x >= 555) {
                //Toast.makeText(view.getContext(), "Congrats. You traced the shape.", Toast.LENGTH_LONG).show();
                view.getContext().startActivity(new Intent(view.getContext(), FinalChest.class));
                points.clear();
                sideOne = 0;
                numWrong = 0;
                total = 0;
            } else if (numWrong > 51) {
                Toast.makeText(view.getContext(), "Try again", Toast.LENGTH_LONG).show();
                points.clear();
                sideOne = 0;
                numWrong = 0;
                total = 0;
            }
        }
        else {
            if (sideOne > 5 && sideTwo > 5 && sideThree > 5 && numWrong < 50 && point.x <=710 && point.y <= 420 && point.y >= 280 && point.x >= 580) {
                //Toast.makeText(view.getContext(), "Congrats. You traced the shape.", Toast.LENGTH_LONG).show();
                view.getContext().startActivity(new Intent(view.getContext(), FinalChest.class));
                points.clear();
                sideOne = 0;
                sideTwo = 0;
                sideThree = 0;
                sideFour = 0;
                numWrong = 0;
                total = 0;
            } else if (numWrong > 51) {
                Toast.makeText(view.getContext(), "Try again", Toast.LENGTH_LONG).show();
                points.clear();
                sideOne = 0;
                sideTwo = 0;
                sideThree = 0;
                sideFour = 0;
                numWrong = 0;
                total = 0;
            }
        }
        invalidate();
        return true;
    }
}


class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
