package cg.example.blooddonationfrontend.api;

import java.util.List;

import cg.example.blooddonationfrontend.model.Question;
import cg.example.blooddonationfrontend.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
