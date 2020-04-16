package me.kisoft.covid19.services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.kisoft.covid19.models.ICPCEntry;
import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.SecurityCode;
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
        Log.e("",json);
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
    public List<ICPCEntry> getICPC() {
        List<ICPCEntry> icpcEntries = new ArrayList<>();
        Gson gson = new Gson();
        try (Response response = RestClient.get(RestClient.GET_ICPC_URL)) {
            Log.i("ICPC Code", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
                JSONArray jsonArray = new JSONArray(response.body().string());
                for (int i = 0; i < jsonArray.length(); i++) {
                    ICPCEntry icpcEntry = gson.fromJson(jsonArray.get(i).toString(), ICPCEntry.class);
                    icpcEntries.add(icpcEntry);
                }
                return icpcEntries;
            } else {
                return null;
            }
        } catch (IOException | JSONException e) {
            Log.e("GET ICPC", e.toString());
        }
        return null;
    }

    @Override
    public Boolean createMedicalProfile(MedicalProfile profile) {
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        try (Response response = RestClient.put(RestClient.PROFILE_URL, json)) {
            Log.i("MedicalProfile", String.valueOf(response.code()));//used for testing
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

    @Override
    public Boolean addSymptom(Symptom symptom) {
        //POST
        Gson gson = new Gson();
        String json = gson.toJson(symptom);
        try (Response response = RestClient.post(RestClient.POST_SYMPTOMS_URL, json)) {
            Log.i("Add Symptom", String.valueOf(response.code()));//used for testing
            Log.i("Add Symptom", response.body().string());//used for testing
            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            Log.e("Add Symptom ", e.toString());
        }
        return false;
    }

    //not tested yet.
    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        Gson gson = new Gson();
        try (Response response = RestClient.get(RestClient.QUESTIONS_URL)) {
            Log.i("Question Code", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
                JSONArray jsonArray = new JSONArray(response.body().string());
                for (int i = 0; i < jsonArray.length(); i++) {
                    Question question = gson.fromJson(jsonArray.get(i).toString(), Question.class);
                    questions.add(question);
                }
                return questions;
            }
        } catch (IOException | JSONException e) {
            Log.e("GET Questions", e.toString());
        }
        return questions;
    }

    //not implemented.
    @Override
    public Boolean answerQuestion(Question question) {
        Gson gson = new Gson();
        String json = gson.toJson(question);
        try (Response response = RestClient.put("change url later", json)) { //todo change url later
            Log.i("Answer Question", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            Log.e("Answer Question", e.toString());
        }
        return false;
    }

    @Override
    public SecurityCode getSecurityCode() {
        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        //  Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = builder.create();
        try (Response response = RestClient.get(RestClient.SECURITY_CODE_URL)) {
            Log.i("Security Code", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
                String jsonRes = response.body().string();
                SecurityCode securityCode = gson.fromJson(jsonRes, SecurityCode.class);
                return securityCode;
            } else {
                return null;
            }
        } catch (IOException e) {
            Log.e("GET Security Code", e.toString());
        }
        return null;
    }

}
