package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.test.databinding.ActivityMainBinding;
import com.example.test.model.Hit;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FullScreeActivity extends AppCompatActivity {
    private Hit images;
    private ImageView fullImage;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_scree);
        initViews();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            images = (Hit)bundle.getSerializable("Images");
            if(images!=null){
                Picasso.get().load(images.getLargeImageURL()).into(fullImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressbar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("Error : ", e.getMessage());
                    }
                });
            }
        }
    }

    private void initViews() {
        fullImage = findViewById(R.id.fullImage);
        progressbar = findViewById(R.id.progressbar);
    }
}
