package ir.linuxian.wwd.customviews;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Toast;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import ir.linuxian.wwd.R;

/* loaded from: classes.dex */
public class MyView extends View implements View.OnTouchListener {
    Paint paint;
    int position;
    int total;

    public MyView(Context context) {
        super(context);
        this.position = 0;
        this.total = 3590;
    }

    public MyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.position = 0;
        this.total = 3590;
        this.paint = new Paint();


    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int heigt = getHeight();
        this.paint.setColor(Color.parseColor("#22000000"));

        canvas.drawCircle((float) (width/2 ), (float) (heigt / 2), (float) (getWidth() / 2), this.paint);


        createtriangle(canvas, this.paint);
    }

    private void createtriangle(Canvas canvas, Paint paint) {
        for (int i = 0; i < this.total; i++) {
            int radius = getWidth() /2;
            if (i == this.position) {
                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.moveTo((float) (getWidth() / 2), (float) (getHeight() / 2));
                for (int j = 1; j < 449; j++) {
                    double width = (double) (getWidth() / 2);
                    double d = (double) radius;
                    double d2 = (double) (i + j);
                    Double.isNaN(d2);
                    double cos = Math.cos((d2 * 3.141592653589793d) / 1800.0d);
                    Double.isNaN(d);
                    Double.isNaN(width);
                    float f = (float) (width + (d * cos));
                    double height = (double) (getHeight() / 2);
                    double d3 = (double) radius;
                    double d4 = (double) (i + j);
                    Double.isNaN(d4);
                    double sin = Math.sin((d4 * 3.141592653589793d) / 1800.0d);
                    Double.isNaN(d3);
                    Double.isNaN(height);
                    path.lineTo(f, (float) (height + (d3 * sin)));
                }
                //paint.setColor(-16776961);
                paint.setColor(Color.parseColor("#bb00ff31"));
                //paint.setColor(Color.parseColor("#aa5cff7c"));
                canvas.drawPath(path, paint);
                paint.setColor(Color.parseColor("#99888888"));
                paint.setTextSize(25.0f);
                paint.setTextAlign(Paint.Align.CENTER);
                StringBuilder sb = new StringBuilder();
                sb = sb.append(Integer.toBinaryString(i*256/total));

                /*
                double width2 = (double) (getWidth() / 2);
                double d5 = (double) radius;
                double d6 = (double) i;
                Double.isNaN(d6);
                double cos2 = Math.cos((d6 * 3.141592653589793d) / 180.0d);
                Double.isNaN(d5);
                Double.isNaN(width2);
                //sb.append((int) (width2 + (d5 * cos2)));
                sb.append("...");
                double height2 = (double) (getHeight() / 2);
                double d7 = (double) radius;
                double d8 = (double) i;
                Double.isNaN(d8);
                double sin2 = Math.sin((d8 * 3.141592653589793d) / 180.0d);
                Double.isNaN(d7);
                Double.isNaN(height2);
                sb.append((Integer.toBinaryString((int) (height2 + (d7 * sin2)))));
                sb.append("");


                 */

                canvas.drawText(sb.toString(), (float) (getWidth()/2), (float) ((getHeight()/4)+(getHeight()/7)), paint);
                sb = new StringBuilder();
                sb = sb.append(Integer.toBinaryString((total-i)*256/total));
                canvas.drawText(sb.toString(), (float) (getWidth()/2), (float) ((getHeight())-(2*getHeight()/7)), paint);

                sb = new StringBuilder();
                sb = sb.append(Integer.toBinaryString(i*64/total));

                canvas.drawText(sb.toString(), (float) (getWidth()/2), (float) (getHeight()-(getHeight()/10)), paint);
                sb = new StringBuilder();
                sb = sb.append(Integer.toBinaryString((total-i)*64/total));
                canvas.drawText(sb.toString(), (float) (getWidth()/2), (float) (getHeight()/4), paint);

                sb = new StringBuilder();
                sb = sb.append(Integer.toBinaryString(i*512/total));

                canvas.drawText(sb.toString(), (float) (getWidth()/2), (float) (getHeight()/2+(getHeight()/14)), paint);






            }
        }
    }

    @Override // android.view.View
    public void startAnimation(Animation animation) {
        super.startAnimation(animation);
        animation.setDuration(0);
        animation.setRepeatCount(-1);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() { // from class: ir.linuxian.win10progressbar.MyView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation2) {
                Toast.makeText(MyView.this.getContext(), "شروع", Toast.LENGTH_SHORT).show();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation2) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation2) {
                MyView.this.position+=15;
                if (MyView.this.position >= MyView.this.total) {
                    MyView.this.position = 0;
                }
            }
        });
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        startAnimation(new Animation() { // from class: ir.linuxian.win10progressbar.MyView.2
            @Override // android.view.animation.Animation
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                MyView.this.invalidate();
            }
        });
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        int mEvent = event.getAction();
        Path path = new Path();
        Canvas canvas = new Canvas();
        if (mEvent == 0) {
            path.moveTo(v.getX(), v.getY());
            Toast.makeText(getContext(), "down", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (mEvent == 1) {
            path.lineTo(getX(), getY());
            path.close();
            this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            canvas.drawPath(path, this.paint);
        }
        return false;
    }
}