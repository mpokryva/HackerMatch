package com.hackermatcher.hackermatcher.Backend;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * Created by Miki on 1/20/2018.
 */

public class CircleView extends View {

    private Paint mTextPaint;
    private float mTextHeight;
    private Paint mPiePaint;
    private Paint mShadowPaint;
    private float mScaleFactor = 1;
    private ScaleGestureDetector mScaleDetector;
    private int numCircles;
    private Random rand;
    private ArrayList<Circle> circles;
    private final int CENTER_RADIUS = 150;
    private GestureDetector mGestureDetector;
    private float xTranslate;
    private float yTranslate;
    private int cx;
    private int cy;
    private HashMap<Hacker, Double> hackerScoreMap;
    private HashMap<Circle, Hacker> circleHackerMap;
    private float diffX;
    private float diffY;



    public CircleView(Context context, HashMap<Hacker, Double> hackerMap) {
        super(context);
        init();
        this.hackerScoreMap = hackerMap;
        mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
        mGestureDetector = new GestureDetector(getContext(), new GestureListener());
        rand = new Random();
        circles = new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((HackerMatchActivity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        cx = width / 2;
        cy = height / 2;
//        circles.add(centerCircle);
        circleHackerMap = new HashMap<>();
        for (Hacker hacker : hackerMap.keySet()) {
            float x = 1;
            float y = 2;
            float radius = hackerMap.get(hacker).floatValue() * (Circle.MAX_RADIUS - Circle.MIN_RADIUS) + Circle.MIN_RADIUS;
            Circle circle = new Circle(x, y, radius);
            circles.add(circle);
            circleHackerMap.put(circle, hacker);
        }
        circles.get(0).setX(cx);
        circles.get(0).setY(cy);
        circles.get(0).setComputed(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);
        mGestureDetector.onTouchEvent(ev);
        return true;
    }

    private void recompute() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((HackerMatchActivity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        for (int i = 0; i < circles.size(); i++) {
            Circle[] c = new Circle[circles.size()];
            circles.get(i).computePosition1(circles.toArray(c), cx, cy);
        }
    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLUE);
        if (mTextHeight == 0) {
            mTextHeight = mTextPaint.getTextSize();
        } else {
            mTextPaint.setTextSize(mTextHeight);
        }

        mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPiePaint.setStyle(Paint.Style.STROKE);
        mPiePaint.setStrokeWidth(5);
        mPiePaint.setTextSize(mTextHeight);

        mShadowPaint = new Paint(0);
        mShadowPaint.setColor(0xff101010);
        mShadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));

    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
//        if (center != null && prevCenter != null) {
//
//            float diffX = (center.x - prevCenter.x);
//            float diffY = (center.y - prevCenter.y);
//            canvas.translate(diffX, diffY);
//        }
        canvas.scale(mScaleFactor, mScaleFactor);
        canvas.translate(xTranslate, yTranslate);
        diffX += xTranslate;
        diffY += yTranslate;
        recompute();
        for (int i = 0; i < circles.size(); i++) {
            int multiplier = 100;
//            float x = rand.nextFloat() * multiplier;
//            float y = rand.nextFloat() * multiplier;

            canvas.drawCircle(circles.get(i).getX(), circles.get(i).getY(), circles.get(i).getRadius(), mPiePaint);

        }
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
//            float x = detector.getFocusX();
//            float y = detector.getFocusY();
//            if (center != null) {
//                float diffX = (center.x - x);
//                diffX = diffX * mScaleFactor - diffX;
//                float diffY = (center.y - y);
//                diffY = diffY * mScaleFactor - diffY;
//                prevCenter = center;
//                center = new PointF(center.x - diffX, center.y - diffY);
//            } else {
//                center = new PointF(x, y);
//                prevCenter = null;
//            }
            invalidate();
            return true;
        }


    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TAG, "Scrolling!");
            xTranslate = e2.getX() - e1.getX();
            yTranslate = e2.getY() - e1.getY();
            Log.d(TAG, "" + xTranslate);
            invalidate();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            for (int i = 0; i < circles.size(); i ++) {
                Circle circle = circles.get(i);
                if (doesIntersect(circle, e.getX(), e.getY(), mScaleFactor, diffX, diffY)) {
                    Hacker hacker = circleHackerMap.get(circle);
                    double score = hackerScoreMap.get(hacker);
                    Toast.makeText(getContext(),  hacker.getName() +", " + score, Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        }
    }

    private double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    private boolean doesIntersect(Circle circle, double x, double y, float zoom, float diffX, float diffY) {
        float circleX = (circle.getX() * zoom) + xTranslate;
        float circleY = (circle.getY() * zoom) + yTranslate;
        double dist = getDistance(circleX, circleY, x, y);
        return dist < circle.getRadius();
    }
}
