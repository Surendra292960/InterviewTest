package com.example.test;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.test.adapter.ImageAdapter;
import com.example.test.databinding.ActivityMainBinding;
import com.example.test.model.Hit;
import com.example.test.model.Result;
import com.example.test.util.Common;
import com.example.test.util.NetworkUtil;
import com.example.test.util.ProgressBarUtil;
import com.example.test.util.BaseActivity;
import com.example.test.wepApi.ImagesApi;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    private RecyclerView image_grid_recycler;
    private ProgressBarUtil progressBarUtil;
    private ImagesApi mService;
    private List<Hit> allHits;
    private ActivityMainBinding binding;
    private ImageAdapter adapter;
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBarUtil = new ProgressBarUtil(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if(NetworkUtil.checkInternetConnection(MainActivity.this)) {
            doNetworkCall();
        }
        else {
            showAlert(getResources().getString(R.string.no_internet));
        }
    }


/*    private void initViews() {
        progressBarUtil = new ProgressBarUtil(this);
        image_grid_recycler = findViewById(R.id.image_grid_recycler);
    }*/

    private void doNetworkCall() {
        mService = Common.getAPI();
        progressBarUtil.showProgress();
        mService.getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {
                        allHits = new ArrayList<>(result.getHits());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ShowToast("Error "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        progressBarUtil.hideProgress();
                        // Updates UI with data
                        displayImage(allHits);
                    }
                });

    }

    @SuppressLint("WrongConstant")
    private void displayImage(List<Hit> images) {
        GridLayoutManager gridLayoutManager ;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 20, 20, 20);
            gridLayoutManager.setOrientation(LinearLayout.VERTICAL);
            gridLayoutManager.canScrollHorizontally();
        }else{
            gridLayoutManager = new GridLayoutManager(MainActivity.this,3);
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
            params.setMargins(20, 20, 20, 20);
            gridLayoutManager.setOrientation(LinearLayout.VERTICAL);
            gridLayoutManager.canScrollVertically();
        }

        binding.recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new ImageAdapter(this,images);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
