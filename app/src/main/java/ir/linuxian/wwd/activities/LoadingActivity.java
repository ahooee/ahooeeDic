package ir.linuxian.wwd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;

import io.reactivex.Flowable;
import ir.linuxian.wwd.R;
import ir.linuxian.wwd.helpers.DBLoader;
import ir.linuxian.wwd.helpers.LoqatViewModel;
import ir.linuxian.wwd.tables.Loqat;

public class LoadingActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        if(sharedPreferences.getBoolean("loaded",false)) {
            goToSA();
        }else {
            DBLoader dbLoader = new DBLoader(new DBLoader.DBLoadListener() {
                @Override
                public void onLoad() {


                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("loaded",true);
                    editor.apply();
                    goToSA();

                }
            });

            Flowable<String> flowable = new LoqatViewModel(getApplication()).getCommnet(1);

            dbLoader.load_db(flowable);
        }

    }

    private void goToSA(){

        Intent intent = new Intent(LoadingActivity.this,SelectActivity.class);
        startActivity(intent);
        LoadingActivity.this.finish();
    }
}