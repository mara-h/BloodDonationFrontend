package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    TextView date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.admin_donor_results);

        ImageButton backButton = findViewById(R.id.back_button);
        TextView name = findViewById(R.id.questionnaireNameInput);
        TextView age = findViewById(R.id.questionnaireAgeInput);
        TextView cnp = findViewById(R.id.questionnaireCnpInput);
        TextView sex = findViewById(R.id.questionnaireSexInput);
        TextView bloodGroup = findViewById(R.id.questionnaireBloodGroupInput);
        date = findViewById(R.id.questionnaireValid);

        name.setText(Globals.questionnaireUser.getFirstName() + " " + Globals.questionnaireUser.getLastName());
        age.setText(Globals.questionnaireUser.getAge());
        cnp.setText(Globals.questionnaireUser.getCnp());
        //sex.setText(Globals.questionnaireUser.getSex());
        if(Globals.questionnaireUser.getSex().equals("male")){
            sex.setText("masculin");
        } else {
            sex.setText("feminin");
        }


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
                bloodGroup.setText("Necunscut");
                break;
        }

        //date.setText(Globals.currentQuestionnaire.getAdded_at());
        //Log.e("added at: ", Globals.currentQuestionnaire.getAdded_at());

        changeHour();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDonorResultsActivity.this, AdminHomeActivity.class));
            }
        });
    }

    public void changeHour() {
        String qDate = Globals.currentQuestionnaire.getAdded_at();
        String hour = qDate.substring(0,2);
        String minute = qDate.substring(3,5);
        String setDate = "";
        Log.e("hour", hour);

        if(hour.equals("00")) {
            hour = "03";
            setDate = hour + ":" + minute;
        }
         else if(hour.equals("01")) {
            hour = "04";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("02")) {
            hour = "05";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("03")) {
            hour = "06";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("04")) {
            hour = "07";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("05")) {
            hour = "08";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("06")) {
            hour = "09";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("07")) {
            hour = "10";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("08")) {
            hour = "11";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("09")) {
            hour = "12";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("10")) {
            hour = "13";
            Log.e("hour in if1", hour);
            setDate = hour + ":" + minute;
            Log.e("setDate: ", setDate);
        }
        else if(hour.equals("11")) {
            hour = "14";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("12")) {
            hour = "15";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("13")) {
            hour = "16";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("14")) {
            hour = "17";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("15")) {
            hour = "18";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("16")) {
            hour = "19";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("17")) {
            hour = "20";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("18")) {
            hour = "21";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("19")) {
            hour = "22";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("20")) {
            hour = "23";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("21")) {
            hour = "00";
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("22")) {
            hour = "01";
            Log.e("hour in if2", hour);
            setDate = hour + ":" + minute;
        }
        else if(hour.equals("23")) {
            hour = "02";
            Log.e("hour in if3", hour);
            setDate = hour + ":" + minute;
        }

        date.setText(setDate);
    }
}
