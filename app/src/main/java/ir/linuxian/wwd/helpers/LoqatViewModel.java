package ir.linuxian.wwd.helpers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Flowable;
import ir.linuxian.wwd.tables.Loqat;

public class LoqatViewModel extends AndroidViewModel {

    public static LoqatRepo loqatRepo;


    public LoqatViewModel(@NonNull Application application) {
        super(application);
        loqatRepo = new LoqatRepo(application);
    }

    public Flowable<List<Loqat>> getAllLoqats(){

        return loqatRepo.getAllLoqats();
    }

    public Flowable<List<Loqat>> getLoqats(String pattern){

        return loqatRepo.getLoqats(pattern);
    }

    public Flowable<List<Loqat>> getLoqat(String pattern , Integer length){


        return loqatRepo.getLoqat(pattern , length);
    }

    public Flowable<String> getCommnet(int id){

    return loqatRepo.getComment(id);
}

    public Flowable<List<Loqat>> getLoqatsFast( int minId , int maxId){

        return loqatRepo.getLoqatsFast(minId,maxId);
}

    public Flowable<List<Loqat>> getLoqatsFastLength( int minId , int maxId , int length){

        return loqatRepo.getLoqatsFastLength(minId,maxId,length);
    }

    public Flowable<List<Loqat>> getLoqatsSlow(int minId , int maxId , String letter){

        return loqatRepo.getLoqatsSlow(minId,maxId,letter);
    }


}
