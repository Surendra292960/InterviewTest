package com.example.test.wepApi;
import com.example.test.model.Result;
import com.example.test.util.ApiUrls;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ImagesApi {

    @GET(ApiUrls.BASE_URL)
    Observable<Result> getImages();
}
