package cg.example.blooddonationfrontend.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import cg.example.blooddonationfrontend.R;

public class AppointmentActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spinner hourSpinner = findViewById(R.id.hourSpinner);
        Spinner minuteSpinner = findViewById(R.id.minuteSpinner);
        MaterialButton makeAppointment = findViewById(R.id.makeAppointment);

        List<String> hours = new ArrayList<>();
        hours.add("8");
        hours.add("9");
        hours.add("10");
        hours.add("11");
        hours.add("12");
        hours.add("13");

        List<String> minutes = new ArrayList<>();
        minutes.add("00");
        minutes.add("10");
        minutes.add("20");
        minutes.add("30");
        minutes.add("40");
        minutes.add("50");

        ArrayAdapter<String> arrayAdapterHour = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hours);
        arrayAdapterHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(arrayAdapterHour);
        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO:
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO:
            }
        });

        ArrayAdapter<String> arrayAdapterMinute = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,minutes);
        arrayAdapterMinute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minuteSpinner.setAdapter(arrayAdapterMinute);
        minuteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO:
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO:
            }
        });

        makeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: make appointment
            }
        });
    }
}
