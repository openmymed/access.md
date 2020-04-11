package me.kisoft.covid19.services;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.Symptom;
import me.kisoft.covid19.utils.RestClient;
import okhttp3.Response;

public class PatientServiceImpl implements PatientService {

    @Override
    public Patient login(String username, String password) {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            Log.e("JSON Login ", e.toString());
        }
        String json = jsonObject.toString();
        try (Response response = RestClient.post(RestClient.LOGIN_URL, json)) {
            Log.i("Login", String.valueOf(response.code()));//used for testing
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
        try (Response response = RestClient.post(RestClient.REGISTER_URL, json)) {
            Log.i("Register", String.valueOf(response.code()));//used for testing
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
    public List<Symptom> getSymptoms() {
        List<Symptom> symptoms = new ArrayList<>();
        Gson gson = new Gson();
        try (Response response = RestClient.get(RestClient.GET_SYMPTOMS_URL)) {
            Log.i("Symptoms Code", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
                JSONArray jsonArray = new JSONArray(response.body().string());
                for (int i = 0; i < jsonArray.length(); i++) {
                    Symptom symptom = gson.fromJson(jsonArray.get(i).toString(), Symptom.class);
                    symptoms.add(symptom);
                }
                return symptoms;
            } else {
                return null;
            }
        } catch (IOException | JSONException e) {
            Log.e("GET Symptoms", e.toString());
        }
        return null;
    }

    @Override
    public Boolean addSymptom(Symptom symptom) {
        return null;
    }

    @Override
    public Boolean answerQuestion(Question question) {
        return null;
    }

    @Override
    public Boolean createMedicalProfile(MedicalProfile profile) {
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        try (Response response = RestClient.put(RestClient.PROFILE_URL, json)){
            Log.i("MedicalProfile", "" + response.code());//used for testing
            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            Log.e("Medical Profile", e.toString());
        }
        return false;
    }

}
