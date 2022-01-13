package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.model.Globals;

public class DonorProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);
        ImageButton backButton = findViewById(R.id.back_button);
        EditText firstName = findViewById(R.id.registerFirstNameInput);
        EditText lastName = findViewById(R.id.registerLastNameInput);
        EditText email = findViewById(R.id.registerEmailInput);
        EditText age = findViewById(R.id.ageInput);
        EditText cnp = findViewById(R.id.cnpInput);
        EditText sex = findViewById(R.id.sexInput);
        EditText bloodGroup = findViewById(R.id.bloodGroupInput);


        firstName.setText(Globals.currentUser.getFirstName());
        lastName.setText(Globals.currentUser.getLastName());
        email.setText(Globals.currentUser.getEmail());
        age.setText(Globals.currentUser.getAge());
        cnp.setText(Globals.currentUser.getCnp());
        sex.setText(Globals.currentUser.getSex());
        String bg = Globals.currentUser.getBloodGroup();
        switch(bg) {
            case "Aplus":
                bloodGroup.setText("A+");
                break;
            case "Aminus":
                bloodGroup.setText("A-");
                break;
            case "Bplus":
                bloodGroup.setText("B+");
                break;
            case "Bminus":
                bloodGroup.setText("B-");
                break;
            case "ABplus":
                bloodGroup.setText("AB+");
                break;
            case "ABminus":
                bloodGroup.setText("AB-");
                break;
            case "Oplus":
                bloodGroup.setText("0+");
                break;
            case "Ominus":
                bloodGroup.setText("0-");
                break;
            default:
                bloodGroup.setText("UNKNOWN");
                break;
        }


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonorProfileActivity.this, HomeActivity.class));
            }
        });

    }
}
