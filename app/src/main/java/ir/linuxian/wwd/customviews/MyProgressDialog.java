package ir.linuxian.wwd.customviews;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

import ir.linuxian.wwd.R;



public class MyProgressDialog extends ProgressDialog{

    ClickListener clickListener;
    public MyProgressDialog(Context context) {
        super(context);
    }




    public MyProgressDialog(Context context, int theme , ClickListener clickListener) {
        super(context, theme );
       this. clickListener = clickListener;
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.progress_dialog);
      //  getWindow().setLayout(182, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textView = findViewById(R.id.tv1);

        textView.setOnClickListener(view -> {

            if(clickListener!=null)
            clickListener.OnClick();

        });



    }

 public    interface ClickListener{

         void OnClick();
    }
}
