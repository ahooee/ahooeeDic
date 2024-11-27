package ir.linuxian.wwd.helpers;

import android.app.Application;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.internal.operators.flowable.FlowableWindow;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ir.linuxian.wwd.tables.Loqat;

public class DBLoader {

    Disposable disposable;
    DBLoadListener dbLoadListener;
    public DBLoader( DBLoadListener dbLoadListener){
        this.dbLoadListener = dbLoadListener;
    }

    public void load_db(Flowable<String> flowable){

        disposable = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .doOnSubscribe(subscription->{



        }).doFinally(()->{

                dbLoadListener.onLoad();

        }).subscribe(loqats->{

            disposable.dispose();
                },throwable -> {});

    }

    public interface DBLoadListener{

        void onLoad();

    }

}
