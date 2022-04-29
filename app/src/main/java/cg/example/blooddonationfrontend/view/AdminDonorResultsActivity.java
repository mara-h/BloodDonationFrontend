package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
       // ImageView image = findViewById(R.id.imageResults);
       // TextView firstName = findViewById(R.id.questionnaireFirstNameInput);
        TextView name = findViewById(R.id.questionnaireNameInput);
        TextView age = findViewById(R.id.questionnaireAgeInput);
        TextView cnp = findViewById(R.id.questionnaireCnpInput);
        TextView sex = findViewById(R.id.questionnaireSexInput);
        TextView bloodGroup = findViewById(R.id.questionnaireBloodGroupInput);
        TextView date = findViewById(R.id.questionnaireValid);

      //  firstName.setText("First name: " + Globals.questionnaireUser.getFirstName());
        //lastName.setText("Last name: " + Globals.questionnaireUser.getLastName());
        name.setText(Globals.questionnaireUser.getFirstName() + " " + Globals.questionnaireUser.getLastName());
        age.setText(Globals.questionnaireUser.getAge());
        cnp.setText(Globals.questionnaireUser.getCnp());
        sex.setText(Globals.questionnaireUser.getSex());
        String bg = Globals.questionnaireUser.getBloodGroup();
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
        date.setText(Globals.currentQuestionnaire.getAdded_at());


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDonorResultsActivity.this, AdminHomeActivity.class));
            }
        });
    }
}
