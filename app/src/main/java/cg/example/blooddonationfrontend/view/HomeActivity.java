package cg.example.blooddonationfrontend.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.api.RetrofitClient;
import cg.example.blooddonationfrontend.model.Globals;
import cg.example.blooddonationfrontend.model.Question;
import cg.example.blooddonationfrontend.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageButton donorForm = findViewById(R.id.donorForm);
        ImageButton donorProfile = findViewById(R.id.donorProfile);
        ImageButton appointment = findViewById(R.id.appointment);
        //ImageButton donationInfo = findViewById(R.id.donationInfo);
        ImageButton logout = findViewById(R.id.logout);

        this.getQuestions();

        donorForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DonorFormActivity.class));
            }
        });

        donorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DonorProfileActivity.class));
            }
        });

//        donationInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, DonationInfoActivity.class));
//            }
//        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AppointmentActivity.class));
            }
        });
    }

    private void getQuestions() {
        Call<List<Question>> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getQuestions();

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                Boolean success;
                success = response.isSuccessful();
                
                if(success) {
                    List<Question> allQuestions = response.body();
                    Globals.setQuestions(allQuestions);
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }
}
