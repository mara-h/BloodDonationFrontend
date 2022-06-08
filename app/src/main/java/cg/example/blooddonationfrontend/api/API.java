package cg.example.blooddonationfrontend.api;

import java.util.List;
import java.util.UUID;

import cg.example.blooddonationfrontend.model.Appointment;
import cg.example.blooddonationfrontend.model.Enums;
import cg.example.blooddonationfrontend.model.Question;
import cg.example.blooddonationfrontend.model.Questionnaire;
import cg.example.blooddonationfrontend.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @POST("users/login")
    Call<User> checkUser (
            @Body User user
    );

    @POST("users")
    Call<ResponseBody> createUser(
            @Body User user
    );

    @GET("questions")
    Call<List<Question>> getQuestions();

    @POST("questionnaires")
    Call<Questionnaire> saveQuestionnaire(
            @Body Questionnaire questionnaire
    );

    @GET("questionnaires/{id}")
    Call<Questionnaire> getQuestionnaireData(
            @Path("id") String id
    );

    @GET("users/{id}")
    Call<User> getUserById(
            @Path("id") String id
    );

    @GET("appointments")
    Call<List<Appointment>> getAllAppointments();

    @GET("appointments/available")
    Call<List<String>> getAvailableHours();

    @POST("appointments")
    Call<Appointment> addAppointment(
            @Body Appointment appointment
    );


}
