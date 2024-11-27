package ir.linuxian.wwd.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import ir.linuxian.wwd.Adapters.RVAdapter;
import ir.linuxian.wwd.Adapters.SimpleRVAdapter;
import ir.linuxian.wwd.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescFragment extends Fragment {


    RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DescFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DescFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescFragment newInstance(String param1, String param2) {
        DescFragment fragment = new DescFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_desc, container, false);



        recyclerView = view.findViewById(R.id.rc0);

        recyclerView.setAdapter(new SimpleRVAdapter(getArguments().getString("matn").replaceAll("<BR>","\n")));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //textView.setText(getArguments().getString("matn").replaceAll("<BR>","\n"));



/*
        textView.setFocusable(true);

        textView.setTextIsSelectable(true);


 */


        // Inflate the layout for this fragment
        return view;
    }
}