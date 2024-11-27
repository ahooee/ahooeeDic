package ir.linuxian.wwd.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import io.reactivex.disposables.Disposable;
import ir.linuxian.wwd.Adapters.RVAdapter;
import ir.linuxian.wwd.R;
import ir.linuxian.wwd.customviews.MyProgressBar;
import ir.linuxian.wwd.customviews.MyProgressDialog;
import ir.linuxian.wwd.helpers.RecyclerViewTouchHandler;
import ir.linuxian.wwd.helpers.SearchHelper;
import ir.linuxian.wwd.tables.Loqat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    Handler handler;
    RecyclerView recyclerView;
    ImageView imageView;
    MyProgressDialog progress;
    List<Loqat> loqatList ;
    RVAdapter rvAdapter;
    MyProgressBar myProgressBar;
    Disposable disposable;
    SearchHelper searchHelper;
    int id = 0;
    int WORD_LENGTH = 0;
    int count_Level = 0;
    String searchText = "";

    boolean onTouched = false;
    boolean contiueSearch = false;
    SearchView searchView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // d empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        // Inflate the layout for this fragment

        View view = createView(container,inflater);

        initVars(view);




        return view;
    }

    public View createView(ViewGroup container , LayoutInflater inflater) {

        return  inflater.inflate(R.layout.fragment_search, container, false);

    }

    public void initVars(View view) {




        Toast.makeText(getContext(),"shorushod!!!!",Toast.LENGTH_SHORT).show();

        handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(myProgressBar.getVisibility()==View.VISIBLE)
                    if(!onTouched)
                        myProgressBar.setVisibility(View.GONE);

            }
        };


        searchHelper = new SearchHelper(getContext(), new SearchHelper.DoSearchListener() {
            @Override
            public void onStartSearch() {
                progress.show();

            }

            @Override
            public void onSearch(List<Loqat> loqats) {

                if(WORD_LENGTH<=0)
                loqatList.clear();



                    Log.d("search", searchText);
                    if (searchText.length() > 1) {
                        for (Loqat loqat : loqats) {
                            if (loqat.getWord().startsWith(searchText))
                                loqatList.add(loqat);
                        }
                    } else {

                        loqatList.addAll(loqats);


                }

            }

            @Override
            public void onStopSearch(String finalText) {


                


                    rvAdapter.notifyDataSetChanged();

                    if (loqatList.size() > 0) {
                        imageView.setVisibility(View.GONE);
                    } else {
                        imageView.setBackgroundResource(R.drawable.noresult);
                        imageView.setVisibility(View.VISIBLE);
                    }
                    progress.dismiss();
                    Toast.makeText(getContext(), loqatList.size() + "TT", Toast.LENGTH_SHORT).show();



            }
        }, new SearchHelper.DoGetCommentListener() {
            @Override
            public void onStartSearch() {
                Log.d("ldskjflksadjflkj",loqatList.toString());



            }

            @Override
            public void onSearch(String comment) {
                Log.d("ldskjflksadjflkj",loqatList.toString());



            }





            @Override
            public void onStopSearch(String comment) {
                Log.d("ldskjflksadjflkj",loqatList.toString());

                Bundle bundle = new Bundle();
                bundle.putString("matn",comment);

                FragmentTransaction fragmentTransaction;
                fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.replace(R.id.fragment_search, DescFragment.class, bundle);
                fragmentTransaction.commit();

                Toast.makeText(getContext(),"alalalala"+recyclerView.getHeight(),Toast.LENGTH_SHORT).show();


            }
        });


        progress = new MyProgressDialog(getContext(), android.R.style.Theme_Panel, new MyProgressDialog.ClickListener() {
            @Override
            public void OnClick() {

                progress.dismiss();
                if(disposable!=null)
                    disposable.dispose();
            }
        });   //ProgressDialog

        progress.setCancelable(false);


        if(loqatList==null)
            loqatList = new ArrayList<>();



        myProgressBar = view.findViewById(R.id.MyPBar0);

        searchView = view.findViewById(R.id.ed0);

        recyclerView = view.findViewById(R.id.recycler0);

         imageView = view.findViewById(R.id.im0);

         if(imageView!=null) {
             searchView.setIconified(false);

             imageView.requestFocus();
             imageView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     searchView.setIconified(false);

                     imageView.requestFocus();                 }
             });

         }

        rvAdapter = new RVAdapter(loqatList);










        recyclerView.addOnItemTouchListener(new RecyclerViewTouchHandler(getContext(), new RecyclerViewTouchHandler.TouchListener() {
            @Override
            public void onItemTouch(View view, int position) {



                if(rvAdapter.getLoqatList().get(position).getId()!=null) {
                    id = rvAdapter.getLoqatList().get(position).getId();
                    searchHelper.getComment(id);

                }
            }
        }));




        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(rvAdapter);



        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                Log.d("stateSCR","="+newState);

                if(newState>0)
                    handler.removeCallbacks(runnable);
                else
                    handler.postDelayed(runnable, 3000);


                if (myProgressBar.getVisibility() == View.GONE)
                    myProgressBar.setVisibility(View.VISIBLE);


            }


            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                float i = ((LinearLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager())).findLastVisibleItemPosition()-((LinearLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager())).findFirstVisibleItemPosition()+1;

                if(rvAdapter.getItemCount()>0)
                    myProgressBar.setBottom( (1f/rvAdapter.getItemCount()*i));
                else
                    myProgressBar.setBottom(recyclerView.getWidth());

                if(!onTouched) {
                    float a = ((LinearLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager())).findFirstVisibleItemPosition();
                    myProgressBar.setTop(a/(rvAdapter.getItemCount()-1));
                    Log.d("itemcount=",rvAdapter.getItemCount()+"");
                    Log.d("HeightHa",  recyclerView.getHeight()+"");
                    Log.d("item0=",a+"");

                }
            }
        });



        myProgressBar.progressListener = new MyProgressBar.OnProgressListener() {
            @Override
            public void OnProgressChanged(float current_loc , boolean touched) {


                onTouched = touched ;

                if(!touched)
                    handler.postDelayed(runnable,3000);

                recyclerView.scrollToPosition((int)(current_loc*(rvAdapter.getItemCount()-1)));
                Log.d("current_loc",current_loc+"");
                Log.d("ItemCount",rvAdapter.getItemCount()+"");
                Log.d("curr*item",(int)(current_loc*(rvAdapter.getItemCount()-1))+"");
                Log.d("current_loc",current_loc+"");

            }
        };



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }



            @Override
            public boolean onQueryTextChange(String query) {


                //search(newText);
                /*
                if(newText.length()>1){

                    searchInHeap(newText);

                }


                 */

                if(WORD_LENGTH>0){
                    loqatList.clear();
                    for(int i=0;i<query.length();i++)
                        doSearch(query.charAt(i)+"",WORD_LENGTH);
                }else {
                    doSearch(query, WORD_LENGTH);
                }

                return false;
            }
        });


    }

    public void doSearch(String query, int length) {

        if(query.length()==0){
            loqatList.clear();
            imageView.setBackgroundResource(R.drawable.inputsomething);
            imageView.setVisibility(View.VISIBLE);
            rvAdapter.notifyDataSetChanged();
            return;

        }

        String localSearchText = searchText;
        searchText = query;



        if(false && (localSearchText.length()>0&&query.length()>0) && query.substring(0,query.length()-1).equals(localSearchText)){
            Log.d("que else if",localSearchText+"   "+query);

           // searchInHeap(query , 0);

        } else {

            Log.d("queelse",localSearchText+"="+localSearchText.length()+"   "+query+"="+query.length());

            if(length>0)

            disposable = searchHelper.search(query , length);




        }



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("destroy","");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("destroyed","");

    }

    private void searchInHeap(String query , int length) {


        List<Loqat> loqats = new ArrayList<>(loqatList);


       query =query.replaceAll("ی","ي" );

        loqatList.clear();

        for (Loqat loqat : loqats){
            if(loqat.getWord().startsWith(query))
                loqatList.add(loqat);
        }

        rvAdapter.notifyDataSetChanged();
    }

}