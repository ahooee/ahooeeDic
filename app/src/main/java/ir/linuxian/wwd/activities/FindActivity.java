package ir.linuxian.wwd.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import ir.linuxian.wwd.R;
import ir.linuxian.wwd.fragments.FindFragment;
import ir.linuxian.wwd.fragments.SearchFragment;

public class FindActivity extends SearchActivity {


    @Override
    public void initFactory() {

        super.activityId = R.layout.activity_find;
        super.fragmentId = R.id.fragment_search;
        super.searchFragment = new FindFragment();
    }
}