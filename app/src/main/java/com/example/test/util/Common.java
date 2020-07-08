package com.example.test.util;

import com.example.test.apiServiceCall.ApiClient;
import com.example.test.wepApi.ImagesApi;

public class Common {
    public static ImagesApi getAPI() {
        return ApiClient.getClient().create(ImagesApi.class);
    }

}
