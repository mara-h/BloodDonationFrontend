package cg.example.blooddonationfrontend.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.model.Globals;

public class AppointmentViewActivity extends AppCompatActivity {

    String hour, day;
    String dayToDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_appointment);

        TextView appointmentTime = findViewById(R.id.momentulProgramarii);
        //TODO: get hour and minute

        day = Globals.currentAppointment.getDayOfAppointment();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse(day);
            sdf.applyPattern("dd.MM.yyyy");
            dayToDisplay = sdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        hour = Globals.currentAppointment.getHourOfAppointment();

        String hourToDisplay = hour.substring(1, 3) + ":" + hour.substring(3, 5);

        appointmentTime.setText(dayToDisplay + ", ora " + hourToDisplay);

        // appointmentTime.setText(day);


    }


}
