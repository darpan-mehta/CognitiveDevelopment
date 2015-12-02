package com.cogdev.cognitivedevelopment;
/**
 * Created by Sarah on 11/22/2015.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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
    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();
    Paint newPaint = new Paint();

    public DrawView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

        newPaint.setColor(Color.GREEN);
        newPaint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.squaret);
        int h = 1280; // Height in pixels
        int w = 770; // Width in pixels
        Bitmap scaled = Bitmap.createScaledBitmap(b, h, w, true);
        canvas.drawBitmap(scaled,0,0,null);
        for (Point point : points) {
            if (point.x<=850 && point.y<=200 && point.y>=150 && point.x>=450){
                canvas.drawCircle(point.x, point.y, 25, newPaint);
                sideOne++;
            }
            else if (point.x<=850 && point.y<=600 && point.y>=550 && point.x>=450){
                canvas.drawCircle(point.x, point.y, 25, newPaint);
                sideTwo++;
            }
            else if(point.x<=860 && point.y<=740 && point.y>=200 && point.x>=800){
                canvas.drawCircle(point.x, point.y, 25, newPaint);
                sideThree++;
            }
            else if(point.x<=450 && point.y<=740 && point.y>=200 && point.x>=400){
                canvas.drawCircle(point.x, point.y, 25, newPaint);
                sideFour++;
            }
            else {
                canvas.drawCircle(point.x, point.y, 25, paint);
                numWrong++;
            }
            // Log.d(TAG, "Painting: "+point);
            total++;
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
        Log.d(TAG, "total: " + total);
        Log.d(TAG, "point: " + point);
        Log.d(TAG, "wrong: " + numWrong);
        if (total>2500 && sideOne>160 && sideTwo>125 && sideThree>150 && sideFour>300){
            Toast.makeText(view.getContext(), "Congrats. You traced the shape.",Toast.LENGTH_LONG).show();
            points.clear();
            sideOne=0;
            sideTwo=0;
            sideThree=0;
            sideFour=0;
            numWrong=0;
            total=0;
        }
        if (numWrong>100){
            Toast.makeText(view.getContext(), "Try again",Toast.LENGTH_LONG).show();
            points.clear();
            sideOne=0;
            sideTwo=0;
            sideThree=0;
            sideFour=0;
            numWrong=0;
            total=0;
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
