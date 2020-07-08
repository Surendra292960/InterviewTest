package com.example.test.adapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test.FullScreeActivity;
import com.example.test.R;
import com.example.test.databinding.ImagesLayoutBinding;
import com.example.test.model.Hit;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private static Context mCtx;
    private List<Hit> imagesList;

    public ImageAdapter(Context mCtx, List<Hit> imagesList) {
        this.mCtx = mCtx;
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ImagesLayoutBinding imagesLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.images_layout, viewGroup, false);
        return new ViewHolder(imagesLayoutBinding);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder employeeViewHolder, int i) {
        Hit hit = imagesList.get(i);
        if(hit!=null){
            ViewHolder.imagesLayoutBinding.setHit(hit);
            ViewHolder.imagesLayoutBinding.setImageUrl(hit.getLargeImageURL());
            ViewHolder.imagesLayoutBinding.setItemClickListener(this::onItemClicked);
        }
    }

    @Override
    public int getItemCount() {
        return imagesList ==null?0:imagesList.size();
    }

    public void onItemClicked(Hit hit) {
        Intent intent = new Intent(mCtx, FullScreeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Images", hit);
        intent.putExtras(bundle);
        mCtx.startActivity(intent);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public static ImagesLayoutBinding imagesLayoutBinding;
        public ViewHolder(@NonNull ImagesLayoutBinding imagesLayoutBinding) {
            super(imagesLayoutBinding.getRoot());
            this.imagesLayoutBinding = imagesLayoutBinding;
        }
    }
}