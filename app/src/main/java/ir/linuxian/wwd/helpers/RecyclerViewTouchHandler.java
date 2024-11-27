package ir.linuxian.wwd.helpers;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewTouchHandler implements RecyclerView.OnItemTouchListener {

    GestureDetector gestureDetector;
    TouchListener touchListener;

    public RecyclerViewTouchHandler(Context context , TouchListener touchListener){

        this.touchListener = touchListener;


        this.gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                Log.d("salllll",e.toString());
                return true;
            }
        });



    }




    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        View view = rv.findChildViewUnder(e.getX(),e.getY());

        if(touchListener!=null && gestureDetector.onTouchEvent(e) && view!=null)
            touchListener.onItemTouch(view,rv.getChildAdapterPosition(view));


        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface TouchListener{

        public void onItemTouch(View view , int position);

    }
}
