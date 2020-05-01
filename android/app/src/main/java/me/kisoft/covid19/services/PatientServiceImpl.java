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

import me.kisoft.covid19.models.Answer;
import me.kisoft.covid19.models.Doctor;
import me.kisoft.covid19.models.ICPCEntry;
import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.models.Notification;
import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.SecurityCode;
import me.kisoft.covid19.models.Symptom;
import me.kisoft.covid19.models.Vitals;
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
    public Boolean postVitals(Vitals vitals) {
        Gson gson = new Gson();
        String json = gson.toJson(vitals);
        try (Response response = RestClient.post(RestClient.POST_VITALS_URL, json)) {
            Log.i("PostVitals", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            Log.e("PostVitals ", e.toString());
        }
        return false;
    }

    @Override
    public List<Notification> getNotification() {
        List<Notification> notifications = new ArrayList<>();
        Gson gson = new Gson();
        try (Response response = RestClient.get(RestClient.GET_NOTIFICATION)) {
            Log.i("GET Notification", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
                JSONArray jsonArray = new JSONArray(response.body().string());
                for (int i = 0; i < jsonArray.length(); i++) {
                    Notification notification = gson.fromJson(jsonArray.get(i).toString(), Notification.class);
                    notifications.add(notification);
                }
                return notifications;
            }else{
                return null;
            }
        } catch (IOException | JSONException e) {
            Log.e("GET Notification", e.toString());
        }
        return null;
    }

    @Override
    public Boolean changePassword(String oldPassword, String newPassword) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("oldPassword", oldPassword);
            jsonObject.put("newPassword", newPassword);
        } catch (JSONException e) {
            Log.e("JSON Login ", e.toString());
        }
        String json = jsonObject.toString();
        try (Response response = RestClient.post(RestClient.CHANGE_PASSWORD_URL, json)) {
            Log.i("Change Password", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
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
    public MedicalProfile getMedicalProfile() {
        Gson gson = new Gson();
        try (Response response = RestClient.get(RestClient.PROFILE_URL)) {
            Log.i("GET Medical Profile", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
                MedicalProfile profile = gson.fromJson(response.body().string(),MedicalProfile.class);
                return profile;
            } else {
                return null;
            }
        } catch (IOException e) {
            Log.e("GET Medical Profile", e.toString());
        }
        return null;
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

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();

        //  Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = builder.create();

        try (Response response = RestClient.get(RestClient.QUESTIONS_URL)) {
            Log.i("Question Code", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
                JSONArray jsonArray = new JSONArray(response.body().string());
                for (int i = 0; i < jsonArray.length(); i++) {
                    Question question = gson.fromJson(jsonArray.get(i).toString(), Question.class);
                    questions.add(question);
                }
                return questions;
//            } else {
//                if (response.code() == 401)
//                    throw new UnauthorizedException();
            }
        } catch (IOException | JSONException e) {//
            Log.e("GET Questions", e.toString());
        }
        return questions;
    }

    @Override
    public Boolean answerQuestion(Answer answer, Long questionId) {
        Gson gson = new Gson();
        String json = gson.toJson(answer);
        try (Response response = RestClient.put(String.format(RestClient.ANSWER_URL,questionId), json)) {
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

    @Override
    public Doctor getDoctor() {
        Gson gson = new Gson();
        try (Response response = RestClient.get(RestClient.DOCTOR_URL)) {
            Log.i("GET DOCTOR", String.valueOf(response.code()));//used for testing
            if (response.isSuccessful()) {
               Doctor doctor = gson.fromJson(response.body().string(),Doctor.class);
               return doctor;
            } else {
                return null;
            }
        } catch (IOException e) {
            Log.e("GET DOCTOR", e.toString());
        }
        return null;
    }

}
