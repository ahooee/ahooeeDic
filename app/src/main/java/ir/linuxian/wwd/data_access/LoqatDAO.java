package ir.linuxian.wwd.data_access;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import ir.linuxian.wwd.tables.Loqat;

@Dao
public interface LoqatDAO {

    @Query("SELECT id,word FROM loqat")
    Flowable<List<Loqat>> getAllWords();


    @Query("SELECT * FROM loqat WHERE  word LIKE (:pattern)  except SELECT * FROM loqat WHERE word LIKE '% %'  ")
    Flowable<List<Loqat>> getWords(String pattern);



    @Query("SELECT * FROM loqat WHERE  word LIKE :pattern AND length(word)=:length except SELECT * FROM loqat WHERE word LIKE '% %' ")
    Flowable<List<Loqat>> getWord(String pattern , Integer length);


    @Query("Select comment From loqat where id=:id")
    Flowable<String> getComment(int id);

//get words related to their first letters


    @Query("SELECT id,word FROM loqat WHERE (id>=:minId and id<:maxId)")
    Flowable<List<Loqat>> getWordsFast(int minId , int maxId);

    @Query("SELECT id,word FROM loqat WHERE (id>=:minId and id<:maxId) AND length(word)=:length")
    Flowable<List<Loqat>> getWordsFastLength(int minId , int maxId , int length);

    @Query("SELECT id,word FROM loqat WHERE (id>:minId and id<:maxId) AND word LIKE :letter")
    Flowable<List<Loqat>> getWordSlow(int minId , int maxId , String letter);



    @Insert
    void insert(Loqat loqat);

}


