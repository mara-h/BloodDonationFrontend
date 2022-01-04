package cg.example.blooddonationfrontend.api;

import cg.example.blooddonationfrontend.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {
    @POST("users")
    Call<ResponseBody> createUser (
            @Body User user
    );
}
