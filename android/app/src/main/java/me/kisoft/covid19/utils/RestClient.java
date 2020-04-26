/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.utils;

/**
 * @author tareq
 */

import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {
    static final String BASE_URL = "https://covid19-staging.kisoft.me/";

    public static final String LOGIN_URL = BASE_URL + "login";
    public static final String REGISTER_URL = BASE_URL + "patient/signup";
    public static final String PROFILE_URL = BASE_URL + "patient/profile";
    public static final String CHANGE_PASSWORD = BASE_URL + "user/password";
    public static final String SECURITY_CODE_URL = BASE_URL + "patient/code";
    public static final String QUESTIONS_URL = BASE_URL + "patient/question";
    public static final String ANSWER_URL = BASE_URL + "patient/question/%s/answer";
    public static final String DOCTOR_URL = BASE_URL + "patient/doctor";
    public static final String RECOMMENDATION_URL = BASE_URL + "patient/recommendation";
    public static final String GET_ICPC_URL = BASE_URL + "symptom/codes";
    public static final String GET_NOTIFICATION = BASE_URL + "notification";
    public static final String POST_SYMPTOMS_URL = BASE_URL + "patient/symptom";
    public static final String POST_VITALS = BASE_URL + "patient/vitals";
    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    static ClearableCookieJar cookieJar;
    static OkHttpClient CLIENT;

    public static void init(Context context) {
        cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        CLIENT = new OkHttpClient.Builder().cookieJar(cookieJar).build();
    }

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

        Response response = CLIENT.newCall(request).execute();
        return response;

    }

    //PUT from OKHttp
    public static Response put(String url, String json) throws IOException {

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        try (Response response = CLIENT.newCall(request).execute()) {
            return response;
        }
    }

    //DELETE from OKHttp
    public static Response delete(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            return response;
        }
    }

}
