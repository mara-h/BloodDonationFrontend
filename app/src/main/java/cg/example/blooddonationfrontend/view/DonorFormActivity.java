package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cg.example.blooddonationfrontend.R;

public class DonorFormActivity extends AppCompatActivity {
    int allQuestionsCount = 31;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_form);

        ImageButton backButton = findViewById(R.id.back_button);
        Button cupButtonOne = findViewById(R.id.CupButtonONE);
        Button cupButtonTwo = findViewById(R.id.CupButtonTWO);
        Button cupButtonThree = findViewById(R.id.CupButtonTHREE);
        Button yesButton = findViewById(R.id.YesButton);
        Button noButton = findViewById(R.id.noButton);

        TextView questionBody = findViewById(R.id.questionBody);




        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonorFormActivity.this, HomeActivity.class));
            }
        });
    }
}
