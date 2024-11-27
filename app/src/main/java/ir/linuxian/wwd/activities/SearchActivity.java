package ir.linuxian.wwd.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import ir.linuxian.wwd.R;
import ir.linuxian.wwd.fragments.DescFragment;
import ir.linuxian.wwd.fragments.FindFragment;
import ir.linuxian.wwd.fragments.SearchFragment;



public class SearchActivity extends AppCompatActivity {

    SearchFragment searchFragment;
    FragmentTransaction fragmentTransaction;
    int activityId;
    int fragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        initFactory();

        fragmentMGR(activityId ,fragmentId,searchFragment);



    }

    public void initFactory() {

        searchFragment = new SearchFragment();
        activityId = R.layout.activity_search;
        fragmentId = R.id.fragment_search;


    }

    public void fragmentMGR(int activity,int fragment, SearchFragment fragmentInstance) {

        setContentView(activity);


        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(fragment,fragmentInstance);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {


        backMGR(fragmentId,searchFragment);

    }

    private void backMGR(int fragmentId, SearchFragment fragmentInstance) {


        Fragment fragment = getSupportFragmentManager().findFragmentById(fragmentId);

        if(fragment instanceof DescFragment){

            Log.d("sldkfjlsdkj",fragment.toString());
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setReorderingAllowed(true);
            fragmentTransaction.replace(fragmentId, fragmentInstance);
            fragmentTransaction.commit();


        }else {
            Intent intent = new Intent(this,SelectActivity.class);
            startActivity(intent);
            this.finish();

            super.onBackPressed();


        }


    }
}