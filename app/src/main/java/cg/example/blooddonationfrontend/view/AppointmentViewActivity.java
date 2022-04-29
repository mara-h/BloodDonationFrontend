package cg.example.blooddonationfrontend.view;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.model.Globals;

public class AppointmentViewActivity extends AppCompatActivity {

    String hour, day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_appointment);

        TextView appointmentTime = findViewById(R.id.momentulProgramarii);

        //TODO: get hour and minute

//        day = Globals.currentAppointment.getDayOfAppointment();
//        hour = Globals.currentAppointment.getHourOfAppointment();
//        appointmentTime.setText(day+", ora "+hour);
    }



}
