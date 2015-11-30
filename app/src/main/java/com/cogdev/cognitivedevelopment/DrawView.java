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

public class DrawView extends View implements OnTouchListener {
    private static final String TAG = "DrawView";
    int numBlue=0;
    int numRed=0;
    int total=0;
    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();
    Paint newPaint = new Paint();

    public DrawView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

        newPaint.setColor(Color.RED);
        newPaint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.square);
        canvas.drawBitmap(b,50,200,null);
        for (Point point : points) {
            if (point.x<=574.7184 && point.y<=400 && point.y>=300 && point.x>=185){
                canvas.drawCircle(point.x, point.y, 25, newPaint);
                numRed++;
            }
            else if(point.x<=600 && point.y<=740 && point.y>=300 && point.x>=570){
                canvas.drawCircle(point.x, point.y, 25, newPaint);
                numRed++;
            }
            else {
                canvas.drawCircle(point.x, point.y, 25, paint);
                numBlue++;
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
        int ratio;
        if (total == 0){
            ratio=0;
        }
        else {
            ratio=numRed/total;
        }
        invalidate();
        Log.d(TAG, "ratio: " + ratio);
        Log.d(TAG, "point: " + point);
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
