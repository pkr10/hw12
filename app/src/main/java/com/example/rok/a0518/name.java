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
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by rok on 2017. 5. 18..
 */

public class name extends View{
    Rect rect;
    String operation ="";
    public name(Context context){
        super(context);
        this.setLayerType(LAYER_TYPE_SOFTWARE,null);
    }
    public name(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap img = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Bitmap Bigimage = Bitmap.createScaledBitmap(img,img.getWidth()*2,img.getHeight()*2,false);
        int cenX  = (canvas.getWidth() - Bigimage.getWidth())/2;
        int cenY  = (canvas.getHeight() - Bigimage.getHeight())/2;
        if(operation.equals("rotate")) {
            canvas.rotate(45, this.getWidth() / 2,getHeight() / 2);
//            float[] array = {
//                    2,0,0,0,-25f,
//                    0,2,0,0,-25f,
//                    0,0,2,0,-25f,
//                    0,0,0,2,0
//            };
//            ColorMatrix colorMatrix = new ColorMatrix(array);
//            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
//            paint.setColorFilter(filter);

        }
//        BlurMaskFilter blur = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);
//        paint.setMaskFilter(blur);
        float[] array = {
                2,0,0,0,-25f,
                0,2,0,0,-25f,
                0,0,2,0,-25f,
                0,0,0,2,0
        };
        ColorMatrix colorMatrix = new ColorMatrix(array);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(filter);
        canvas.drawBitmap(Bigimage,cenX,cenY,paint);

//        Paint paint = new Paint();
//        paint.setColor(Color.BLUE);
//        rect = new Rect(10,10,100,100);
//        canvas.drawRect(rect,paint);
//        int width = canvas.getWidth()/2- 45;
//        int height   = canvas.getHeight()/2;
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.YELLOW);
//        paint.setStrokeWidth(5);
//        canvas.drawRect(width,height,width+90,height+90,paint);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setTextSize(70);
//        canvas.drawText("Click me!!",300,300,paint);
//        Bitmap image = new BitmapFactory().decodeResource(getResources(),R.mipmap.ic_launcher);
//        canvas.drawBitmap(image,300,350,paint);
//        canvas.drawBitmap(image,400,150,paint);
//                Bitmap smallimage =Bitmap.createScaledBitmap(image,image.getWidth()*2,image.getHeight()*2,false);
//        canvas.drawBitmap(smallimage,100,100,null);


    }
    public void setOperation(String operation){
        this.operation = operation;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        if(x>=10&&x<=100&&y>=10&&y<=100){
            //red
            Toast.makeText(getContext(),"ReD",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(),"background",Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
