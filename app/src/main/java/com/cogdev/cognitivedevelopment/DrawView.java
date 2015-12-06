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
    private static final String TAG = "DrawView";
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
        Point point = new Point();
        point.x = event.getX();
        point.y = event.getY();
        points.add(point);
        invalidate();
        Log.d(TAG, "point: " + point);
        if (voice == true){
            voice=false;
            MediaPlayer mp = MediaPlayer.create(view.getContext(), R.raw.introdoor);
            try {
                if (mp.isPlaying()) {
                    mp.stop();
                    mp.release();
                }
                mp = MediaPlayer.create(view.getContext(), R.raw.introdoor);
                mp.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (n==0) {
            if (sideOne > 10 && sideTwo > 10 && sideThree > 10 && sideFour > 10 && numWrong < 50) {
                Toast.makeText(view.getContext(), "Congrats. You traced the shape.", Toast.LENGTH_LONG).show();
                view.getContext().startActivity(new Intent(view.getContext(), ShipCounting.class));
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
            if (sideOne > 10 && total>200 && numWrong < 50) {
                Toast.makeText(view.getContext(), "Congrats. You traced the shape.", Toast.LENGTH_LONG).show();
                view.getContext().startActivity(new Intent(view.getContext(), ShipCounting.class));
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
            if (sideOne > 10 && sideTwo > 10 && sideThree > 20 && numWrong < 50) {
                Toast.makeText(view.getContext(), "Congrats. You traced the shape.", Toast.LENGTH_LONG).show();
                view.getContext().startActivity(new Intent(view.getContext(), ShipCounting.class));
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
