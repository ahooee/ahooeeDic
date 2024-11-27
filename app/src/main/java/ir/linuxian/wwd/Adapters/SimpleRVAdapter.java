package ir.linuxian.wwd.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.linuxian.wwd.R;

public class SimpleRVAdapter extends RecyclerView.Adapter<SimpleRVAdapter.SimpleVH> {

    String matn;

    public SimpleRVAdapter(String matn){

        this.matn = matn;

    }

    @NonNull
    @Override
    public SimpleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desc_item_layout,parent,false);
        return new SimpleVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleVH holder, int position) {

        holder.setText(this.matn);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class SimpleVH extends RecyclerView.ViewHolder{

        TextView textView;
        public SimpleVH(@NonNull View itemView) {
            super(itemView);
             textView = itemView.findViewById(R.id.tv0);

        }
        public void  setText(String text){

            textView.setText(text);

        }


    }


}
