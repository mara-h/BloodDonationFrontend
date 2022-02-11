package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.Inet4Address;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.model.Globals;

public class AdminDonorResultsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.admin_donor_results);
        ImageButton backButton = findViewById(R.id.back_button);

        EditText firstName = findViewById(R.id.questionnaireFirstNameInput);
        EditText lastName = findViewById(R.id.questionnaireLastNameInput);
        EditText age = findViewById(R.id.questionnaireAgeInput);
        EditText cnp = findViewById(R.id.questionnaireCnpInput);
        EditText sex = findViewById(R.id.questionnaireSexInput);
        EditText bloodGroup = findViewById(R.id.questionnaireBloodGroupInput);
        EditText date = findViewById(R.id.questionnaireValid);

        firstName.setText("First name: " + Globals.questionnaireUser.getFirstName());
        lastName.setText("Last name: " + Globals.questionnaireUser.getLastName());
        age.setText("Age: " + Globals.questionnaireUser.getAge());
        cnp.setText("CNP: " + Globals.questionnaireUser.getCnp());
        sex.setText("Sex: " + Globals.questionnaireUser.getSex());
        String bg = Globals.questionnaireUser.getBloodGroup();
        switch(bg) {
            case "Aplus":
                bloodGroup.setText("Blood group: A+");
                break;
            case "Aminus":
                bloodGroup.setText("Blood group: A-");
                break;
            case "Bplus":
                bloodGroup.setText("Blood group: B+");
                break;
            case "Bminus":
                bloodGroup.setText("Blood group: B-");
                break;
            case "ABplus":
                bloodGroup.setText("Blood group: AB+");
                break;
            case "ABminus":
                bloodGroup.setText("Blood group: AB-");
                break;
            case "Oplus":
                bloodGroup.setText("Blood group: 0+");
                break;
            case "Ominus":
                bloodGroup.setText("Blood group: 0-");
                break;
            default:
                bloodGroup.setText("Blood group: UNKNOWN");
                break;
        }
        date.setText("Questionnaire date: " + Globals.currentQuestionnaire.getAdded_at());


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDonorResultsActivity.this, AdminHomeActivity.class));
            }
        });
    }
}
