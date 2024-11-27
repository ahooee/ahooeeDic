package ir.linuxian.wwd.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.linuxian.wwd.R;
import ir.linuxian.wwd.tables.Loqat;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AhooeeVH> {

    public List<Loqat> getLoqatList() {
        return loqatList;
    }

    List<Loqat> loqatList ;

    class AhooeeVH extends RecyclerView.ViewHolder{

        TextView textView;

        public AhooeeVH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv0);

        }

        public void RVsetText(String text){

            textView.setText(text);
            textView.setTextSize(26);

        }

    }



    public RVAdapter(List<Loqat> loqatList){

        this.loqatList = loqatList;



    }

    @NonNull
    @Override
    public AhooeeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fehrest_item_layout,parent,false);


        return new AhooeeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AhooeeVH holder, int position) {

        holder.RVsetText(loqatList.get(position).getWord());

    }

    @Override
    public int getItemCount() {
        return loqatList.size();
    }

}
