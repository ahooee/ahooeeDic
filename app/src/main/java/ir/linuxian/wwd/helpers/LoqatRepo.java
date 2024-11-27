package ir.linuxian.wwd.helpers;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Flowable;
import ir.linuxian.wwd.data_access.LoqatDAO;
import ir.linuxian.wwd.database.LoqatDataBase;
import ir.linuxian.wwd.tables.Loqat;

public class LoqatRepo {



    LoqatDAO loqatDAO;

    public LoqatRepo(Application application){

        LoqatDataBase loqatDataBase = LoqatDataBase.getLoqatDataBase(application);

        loqatDAO = loqatDataBase.loqat_dao();
    }


    public Flowable<List<Loqat>> getAllLoqats(){

        return loqatDAO.getAllWords();
    }

    public Flowable<List<Loqat>> getLoqats(String pattern){

        return loqatDAO.getWords(pattern);

    }


    public Flowable<List<Loqat>> getLoqat(String pattern , Integer length){


        return loqatDAO.getWord(pattern , length);
    }

    public Flowable<String> getComment(int id){

        return loqatDAO.getComment(id);
    }

public Flowable<List<Loqat>> getLoqatsFast(int minId , int maxId){

        return loqatDAO.getWordsFast(minId,maxId);
}

    public Flowable<List<Loqat>> getLoqatsFastLength(int minId , int maxId , int length){

        return loqatDAO.getWordsFastLength(minId,maxId,length);
    }

    public Flowable<List<Loqat>> getLoqatsSlow(int minId , int maxId , String letter){

        return loqatDAO.getWordSlow(minId,maxId,letter);
    }

}
