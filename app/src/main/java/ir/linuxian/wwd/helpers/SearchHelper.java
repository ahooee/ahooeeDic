package ir.linuxian.wwd.helpers;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ir.linuxian.wwd.tables.Loqat;

public class SearchHelper {



    String  comments ;

    Context context;
    Disposable disposable;
    DoSearchListener doSearchListener;
    DoGetCommentListener doGetCommentListener;
    final int AlphabetId[] = {1,3161,51255,71720,78914,95420,101338,111675,117810,130802,144793,159427,162086,
            172558,179499,180471,191931,203525,208750,210157,214476,214998,231942,237194,244473,255874,269793,
            277677,284563,319119,332793,338653,343421,347657};

    int min = 0 ;
    int max = 0 ;

    public SearchHelper(Context context, DoSearchListener doSearchListener , DoGetCommentListener doGetCommentListener){

        this.context = context;

        this.doSearchListener = doSearchListener;

        this.doGetCommentListener = doGetCommentListener;


    }


    public Disposable searchw(String text){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder = stringBuilder//.append("%")
                .append(text).append("%")
        ;


        text = stringBuilder.toString();


        LoqatViewModel loqatViewModel = new LoqatViewModel((Application) context.getApplicationContext());


       final String finalText = text;
        disposable = loqatViewModel.getLoqats(text).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((Consumer<? super Subscription>) subscription-> {

                    doSearchListener.onStartSearch();

                }).doFinally(()->{

                    doSearchListener.onStopSearch(finalText);

                })


                .subscribe(loqats->{


                    doSearchListener.onSearch(loqats);

                    disposable.dispose();




                },throwable -> {

                    Log.d("Flowable Error",throwable.getMessage());


                });

        return disposable;

    }

    public  Disposable search(String text , int length){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder = stringBuilder//.append("%")
                .append(text).append("%")
        ;


        text = stringBuilder.toString();


        String finalText = text;
        disposable = getLoqatFunc(text , length).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((Consumer<? super Subscription>) subscription-> {

                    doSearchListener.onStartSearch();

                }).doFinally(()->{

                    doSearchListener.onStopSearch(finalText);

                })


                .subscribe(loqats->{


                    doSearchListener.onSearch(loqats);

                    disposable.dispose();




                },throwable -> {

                    Log.d("Flowable Error",throwable.getMessage());


                });

return disposable;

    }


    private Flowable<List<Loqat>> getLoqatFunc(String text , int length) {



        LoqatViewModel loqatViewModel = new LoqatViewModel((Application) context.getApplicationContext());
        if (text.length() == 1) {
            Log.d("poooch", (text.length() > 0) + "");
            return loqatViewModel.getAllLoqats();
        }


        switch (text.charAt(0)) {

            case 'آ':
                min = 0;
                max = 1;
                break;
            case 'ا':
                min = 1;
                max = 2;
                break;
            case 'ب':
                min = 2;
                max = 3;
                break;
            case 'پ':
                min = 3;
                max = 4;
                break;
            case 'ت':
                min = 4;
                max = 5;
                break;
            case 'ث':
                min = 5;
                max = 6;
                break;
            case 'ج':
                min = 6;
                max = 7;
                break;
            case 'چ':
                min = 7;
                max = 8;
                break;
            case 'ح':
                min = 8;
                max = 9;
                break;
            case 'خ':
                min = 9;
                max = 10;
                break;
            case 'د':
                min = 10;
                max = 11;
                break;
            case 'ذ':
                min = 11;
                max = 12;
                break;
            case 'ر':
                min = 12;
                max = 13;
                break;
            case 'ز':
                min = 13;
                max = 14;
                break;
            case 'ژ':
                min = 14;
                max = 15;
                break;
            case 'س':
                min = 15;
                max = 16;
                break;
            case 'ش':
                min = 16;
                max = 17;
                break;
            case 'ص':
                min = 17;
                max = 18;
                break;
            case 'ض':
                min = 18;
                max = 19;
                break;
            case 'ط':
                min = 19;
                max = 20;
                break;
            case 'ظ':
                min = 20;
                max = 21;
                break;
            case 'ع':
                min = 21;
                max = 22;
                break;
            case 'غ':
                min = 22;
                max = 23;
                break;
            case 'ف':
                min = 23;
                max = 24;
                break;
            case 'ق':
                min = 24;
                max = 25;
                break;
            case 'ک':
                min = 25;
                max = 26;
                break;
            case 'گ':
                min = 26;
                max = 27;
                break;
            case 'ل':
                min = 27;
                max = 28;
                break;
            case 'م':
                min = 28;
                max = 29;
                break;
            case 'ن':
                min = 29;
                max = 30;
                break;
            case 'ه':
                min = 30;
                max = 31;
                break;
            case 'و':
                min = 31;
                max = 32;
                break;
            case 'ي':
                min = 32;
                max = 33;
                break;
            case 'ی':
                min = 32;
                max = 33;
                break;


        }


        if (length > 0){

            return loqatViewModel.getLoqatsFastLength(AlphabetId[min], AlphabetId[max] , length);

    }else {
            return loqatViewModel.getLoqatsFast(AlphabetId[min], AlphabetId[max]);

        }
    }


    public Disposable getComment(int id){


        LoqatViewModel loqatViewModel = new LoqatViewModel((Application) context.getApplicationContext());


        disposable = loqatViewModel.getCommnet(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((Consumer<? super Subscription>) subscription-> {

                    doGetCommentListener.onStartSearch();

                }).doFinally(()->{

            doGetCommentListener.onStopSearch(comments);


        })


                .subscribe(comment->{

                    comments = comment;

                    doGetCommentListener.onSearch(comment);


                    disposable.dispose();







                },throwable -> {

                    Log.d("Flowable Error",throwable.getMessage());


                });

            return disposable;
        }









    public interface DoSearchListener{

         void onStartSearch();

         void onSearch(List<Loqat> loqats);

         void onStopSearch(String finalText);




    }

public interface DoGetCommentListener{

     void onStartSearch();

     void onSearch(String comment);

     void onStopSearch(String comment);




}




}
