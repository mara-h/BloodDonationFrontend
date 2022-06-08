package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.api.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminAppointmentsActivity extends AppCompatActivity {

    List<String> hoursList = new ArrayList<>();
    TextView card830, card900, card930, card1000, card1030, card1100, card1130, card1200, card1230, card1300;
    ImageButton backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_appointment);

        backButton = findViewById(R.id.back_button);

        card830 = findViewById(R.id.card830);
        card900 = findViewById(R.id.card900);
        card930 = findViewById(R.id.card930);
        card1000 = findViewById(R.id.card1000);
        card1030 = findViewById(R.id.card1030);
        card1100 = findViewById(R.id.card1100);
        card1130 = findViewById(R.id.card1130);
        card1200 = findViewById(R.id.card1200);
        card1230 = findViewById(R.id.card1230);
        card1300 = findViewById(R.id.card1300);


        getAvailableHours();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminAppointmentsActivity.this, AdminHomeActivity.class));
            }
        });

    }


    private void getAvailableHours() {
        Call<List<String>> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getAvailableHours();

        call.enqueue(new Callback<List<String>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Boolean success;
                success = response.isSuccessful();
                int requestCode = response.code();
                if (success) {
                    List<String> appointmentList = response.body();

                    hoursList = appointmentList.stream().map(element -> element.toString().substring(1, 3) + ":" + element.toString().substring(3, 5)).collect(Collectors.toList());
                    System.out.println(hoursList);
                    Log.e("lista de ore", hoursList.toString());
                    setHours(hoursList);
                } else {
                    Toast.makeText(AdminAppointmentsActivity.this, "Problems encountered.", Toast.LENGTH_LONG).show();
                }
            }

            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(AdminAppointmentsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("failure: " + t.getMessage());
            }
        });

    }

    private void setHours(List<String> hoursList) {
        System.out.println("HourList" + hoursList);
        for (String el : hoursList) {
            System.out.println("Orele din lista:" + el);


            if (el.equals("08:30")) {
                card830.setTextColor(getResources().getColor(R.color.gray));
            }

            if (el.equals("09:00")) {
                card900.setTextColor(getResources().getColor(R.color.gray));
            }

            if (el.equals("09:30")) {
                card930.setTextColor(getResources().getColor(R.color.gray));
            }

            if (el.equals("10:00")) {
                card1000.setTextColor(getResources().getColor(R.color.gray));
            }

            if (el.equals("10:30")) {
                card1030.setTextColor(getResources().getColor(R.color.gray));
            }

            if (el.equals("11:00")) {
                card1100.setTextColor(getResources().getColor(R.color.gray));
            }

            if (el.equals("11:30")) {
                card1130.setTextColor(getResources().getColor(R.color.gray));
            }

            if (el.equals("12:00")) {
                card1200.setTextColor(getResources().getColor(R.color.gray));
            }

            if (el.equals("12:30")) {
                card1230.setTextColor(getResources().getColor(R.color.gray));
            }

            if (el.equals("13:00")) {
                card1300.setTextColor(getResources().getColor(R.color.gray));
            }
        }
    }

}
