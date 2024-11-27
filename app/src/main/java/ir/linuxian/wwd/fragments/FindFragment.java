package ir.linuxian.wwd.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;

import ir.linuxian.wwd.R;

public class FindFragment extends SearchFragment{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public View createView(ViewGroup container, LayoutInflater inflater) {

        View view = inflater.inflate(R.layout.fragment_find,container,false);

        AppCompatSpinner spinner = view.findViewById(R.id.spinn0);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.string_length, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                FindFragment.super.WORD_LENGTH = i+2;
                FindFragment.super.count_Level = WORD_LENGTH;



                        Toast.makeText(getContext(), textView.getText()+" "+i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(getContext(), adapterView.getCount(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}