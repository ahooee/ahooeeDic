package ir.linuxian.wwd.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ir.linuxian.wwd.data_access.LoqatDAO;
import ir.linuxian.wwd.tables.Loqat;

@Database(entities = {Loqat.class},version = 1,exportSchema = false)
public abstract class LoqatDataBase extends RoomDatabase {

    public abstract LoqatDAO loqat_dao();

    private static volatile LoqatDataBase INSTANCE;

    private static ExecutorService loqatDataBaseService = Executors.newSingleThreadExecutor();

    public static LoqatDataBase getLoqatDataBase(final Context context){

        if (INSTANCE == null) {

            synchronized (LoqatDataBase.class){

                INSTANCE = Room.databaseBuilder(context,LoqatDataBase.class,"loqat").createFromAsset("database/deh_db.db").build();

            }

        }

        return INSTANCE;

    }

}
