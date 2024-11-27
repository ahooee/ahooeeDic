package ir.linuxian.wwd.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyProgressBar extends View implements View.OnTouchListener{
    Paint paint;
    float top;
     float bottom;
    public  OnProgressListener progressListener;

    public MyProgressBar(Context context) {
        super(context);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
        this.setOnTouchListener(this);


    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GRAY);
        canvas.drawRect(0,0,(float) getWidth(),getHeight(),paint);
        paint.setColor(Color.parseColor("#0071ec"));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(0,top,(float) getWidth(),(float) bottom+top,10,10,paint);
        paint.setColor(Color.parseColor("#000000"));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(0,top,(float) getWidth(),(float) bottom+top,10,10,paint);




    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


        int mAction = motionEvent.getAction();

        if(mAction==MotionEvent.ACTION_DOWN){

            top=motionEvent.getY();
            if(top>(view.getHeight()-bottom))
                top=view.getHeight()-bottom;
            else if(top<0)
                top=0;

            if(progressListener!=null)
                progressListener.OnProgressChanged(top/(view.getHeight()-bottom),true);

            invalidate();



            return true;
        }


        if(mAction==MotionEvent.ACTION_MOVE){


            top =motionEvent.getY();
            if(top>(view.getHeight()-bottom))
                top=(view.getHeight()-bottom);
            else if(top<0)
                top=0;

            if(progressListener!=null)
                progressListener.OnProgressChanged(top/(view.getHeight()-bottom),true);


            Log.d("current height"," = "+top);
            Log.d("height",view.getHeight()+"");

            invalidate();


            return true;

        }
        if(mAction==MotionEvent.ACTION_UP){

            top =motionEvent.getY();
            if(top>(view.getHeight()-bottom))
                top=(view.getHeight()-bottom);
            else if(top<0)
                top=0;

            if(progressListener!=null)
            progressListener.OnProgressChanged(top/(view.getHeight()-bottom),false);


            return false;
        }




        return false;
    }

    public interface OnProgressListener{

        void OnProgressChanged(float current_loc , boolean touched);
    }

    public void setTop(float top) {
        top = top*getHeight();
        if(top>(getHeight()-bottom))
            top=(getHeight()-bottom);
        else if(top<0)
            top=0;
        this.top = top;
        Log.d("getHeights=",getHeight()+"");


        invalidate();
    }

    public void setBottom(float bottom) {
        this.bottom = bottom*getHeight();
        if(this.bottom<getWidth()/2f)
            this.bottom = getWidth()/2f;


        Log.d("bottom",this.bottom+"botoom="+bottom);
        Log.d("Top+Bottom",top+bottom+"");
    }


}
