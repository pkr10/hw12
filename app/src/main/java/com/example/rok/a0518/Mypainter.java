package com.example.rok.a0518;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by rok on 2017. 5. 18..
 */

public class Mypainter extends View{
    Bitmap mBitmap;
    Canvas mCanvas;
    Paint mPaint = new Paint();
    int checkable =0;
    int operation = 0;
    float[] matrixarray = {
            2f,0f,0f,0f,-25f,
            0f,2f,0f,0f,-25f,
            0f,0f,2f,0f,-25f,
            0f,0f,0f,1f,0f,
    };
    ColorMatrix matrix = new ColorMatrix(matrixarray);


    BlurMaskFilter blur =new BlurMaskFilter(100,BlurMaskFilter.Blur.INNER);
    public Mypainter(Context context) {
        super(context);
        mPaint.setColor(Color.BLACK);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE,null);




    }

    public Mypainter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.BLACK);



    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(mBitmap !=null){
            canvas.drawBitmap(mBitmap,0,0,null);
        }
        Log.d("rotate",String.valueOf(operation));


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);
        mCanvas.drawColor(Color.YELLOW);
        drawStamp();
    }
    private void drawStamp(){
        Bitmap img = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        mCanvas.drawBitmap(img,10,10,null);
    }
    int oldx = -1,oldy = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        Bitmap img = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Bitmap bigimg  = Bitmap.createScaledBitmap(img,(int)(img.getWidth()*1.5),(int)(img.getHeight()*1.5),false);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {


                if (checkable == 1) {
                    if (operation == 1) {
                        oldx = x;
                        oldy = y;
                        mCanvas.drawBitmap(img, oldx, oldy, mPaint);
                        invalidate();
                        mCanvas.rotate(30,mBitmap.getWidth()/2,mBitmap.getHeight()/2);


                    }
                    else if(operation == 2){

                        oldx = x;
                        oldy = y;
                        mCanvas.drawBitmap(img, 10, 10, mPaint);
                        invalidate();

                    }
                    else if(operation == 3){
                        oldx = x;
                        oldy = y;

                        mCanvas.drawBitmap(bigimg, oldx, oldy, mPaint);
                        invalidate();
                    }
                    else if(operation == 4){
                        oldx = x;
                        oldy = y;
                        mCanvas.drawBitmap(img, oldx, oldy, mPaint);

                        invalidate();
                        screw();
                    }
                    else {
                        oldx = x;
                        oldy = y;
                        mCanvas.drawBitmap(img, oldx, oldy, mPaint);
                        invalidate();
                    }
                }


                else{
                    oldx = x;
                    oldy = y;
                }
            }


        else if(event.getAction() == MotionEvent.ACTION_MOVE) {
            if(checkable ==0) {


                if (oldx != -1) {
                    mCanvas.drawLine(oldx, oldy, x, y, mPaint);
                    invalidate();
                    oldx = x;
                    oldy = y;
                }
            }
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){
           if(checkable ==0) {


               if (oldx != -1) {
                   mCanvas.drawLine(oldx, oldy, x, y, mPaint);
                   oldx = -1;
                   oldy = -1;
               }
           }
        }
        return true;
    }
    public void setCheckable(int checkable){
        this.checkable = checkable;

    }
    public void setOperation(int operation){
        this.operation = operation;
    }
    public void clear(){
        mBitmap.eraseColor(Color.WHITE);
        invalidate();


        operation =0;
    }


    public void screw(){
        float skewnum = 0.2f;

        
        mCanvas.skew(skewnum,0);
        skewnum = 5f;
    }
    public void bluring(MenuItem item,int value){
        if(value == 1){
            mPaint.setMaskFilter(blur);
            invalidate();
        }
        else{
            mPaint.setMaskFilter(null);
            invalidate();
        }
    }
    public void coloring(MenuItem item,int value){
        if(value == 1){
            mPaint.setColorFilter(new ColorMatrixColorFilter(matrix));
            invalidate();
        }
        else{
            mPaint.setColorFilter(null);
            invalidate();
        }
    }
    public void Bigpen(MenuItem item,int value){
        if(value == 1){
            mPaint.setStrokeWidth(5);
            invalidate();
        }
        else{
            mPaint.setStrokeWidth(1);
            invalidate();

        }
    }

    public void setRed(){
        mPaint.setColor(Color.RED);
    }
    public void setBlue(){
        mPaint.setColor(Color.BLUE);
    }
    public boolean Save(String file_name) {
        try {
            Toast.makeText(getContext(), "저장되었습니다!!", Toast.LENGTH_LONG).show();
            FileOutputStream out = new FileOutputStream(file_name);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", e.getMessage());
        } catch (IOException e) {
            Log.e("IOException", e.getMessage());
        }
        return false;
    }

    public void Open(String file_name) {
        try {
            String imgpath = file_name;
            Bitmap bm = BitmapFactory.decodeFile(imgpath);
            bm = Bitmap.createScaledBitmap(bm, bm.getWidth() / 2, bm.getHeight() / 2, false);
            mCanvas.drawBitmap(bm, mCanvas.getWidth() / 2 - bm.getWidth() / 2, mCanvas.getHeight() / 2 - bm.getHeight() / 2, mPaint);
            invalidate();
            Toast.makeText(getContext(), "load ok", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "파일이 없습니다", Toast.LENGTH_SHORT).show();
        }
    }






}
