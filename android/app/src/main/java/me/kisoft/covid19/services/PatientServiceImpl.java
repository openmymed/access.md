package me.kisoft.covid19.services;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Question;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PatientServiceImpl implements PatientService {
    final String WEBSITE_URL = "https://covid19-staging.kisoft.me/";
    final String LOGIN_URL = WEBSITE_URL + "login";
    final String REGISTER_URL = WEBSITE_URL + "patient/signup";
    final String PROFILE_URL = WEBSITE_URL + "patient/profile";
    final String CHANGE_PASSWORD = WEBSITE_URL + "user/password";
    final String CODE_URL = WEBSITE_URL + "patient/code";
    final String QUESTIONS_URL = WEBSITE_URL + "patient/question";
    final String ANSWER_URL = WEBSITE_URL + "patient/question/{question_id}/answer";//TODO remember to change question_id
    final String RECOMMENDATION_URL = WEBSITE_URL + "patient/recommendation";
    final String SYMPTOMS_URL = WEBSITE_URL + "patient/symptom";

    final MediaType JSON = MediaType.get("application/json; charset=utf-8");
   

    @Override
    public Patient login(String username, String password) {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = jsonObject.toString();
        try {
            Response response = post(LOGIN_URL, json);
            if (response.isSuccessful()) {
                String jsonRes = response.body().string();
                return gson.fromJson(jsonRes, Patient.class);
            } else {
                if (response.code() == 401 || response.code() == 403) {
                    return null;
                }
                return null;//what to return? if 500 or something else
            }
        } catch (IOException e) {
            Log.e("Login ", e.toString());
        }
        return null;
    }

    @Override
    public Boolean register(Patient patient) {
        Gson gson = new Gson();
        String json = gson.toJson(patient);
        try {
            Response response = post(REGISTER_URL, json);
            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            Log.e("Register ", e.toString());
        }
        return false;
    }

    @Override
    public List<Question> getQuestions() {
        return null;
    }

    @Override
    public Boolean createMedicalProfile(MedicalProfile profile) {
        return null;
    }

    //POST from OKHttp
    private Response post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    //GET from OKHttp
    private Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response;
        }
    }

    //PUT from OKHttp
    private Response put(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response;
        }
    }

}
