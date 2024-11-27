package ir.linuxian.wwd.customviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class MyRecyclerView extends RecyclerView {

    public OnScroolListener onScroolListener;
    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onScrolled(int dx, int dy ) {
        super.onScrolled(dx, dy);
        if(onScroolListener!=null)
            onScroolListener.onScroll(Objects.requireNonNull(getAdapter()).getItemCount());


    }

    public interface OnScroolListener{

        public void onScroll(int items);

    }

}
