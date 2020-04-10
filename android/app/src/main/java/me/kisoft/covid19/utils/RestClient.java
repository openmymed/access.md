/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.utils;

/**
 *
 * @author tareq
 */
import java.io.IOException;
import okhttp3.OkHttpClient;
public class RestClient {
    static final String BASE_URL = "https://covid19-staging.kisoft.me/";
    static final  OkHttpClient CLIENT = new OkHttpClient();
    
    //POST from OKHttp
    public static Response post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = CLIENT.newCall(request).execute();
        return response;
    }

    //GET from OKHttp
    public static Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            return response;
        }
    }

    //PUT from OKHttp
    public static  Response put(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        try (Response response = CLIENT.newCall(request).execute()) {
            return response;
        }
    }
}
