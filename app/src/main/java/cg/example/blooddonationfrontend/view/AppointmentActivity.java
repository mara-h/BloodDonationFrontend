package cg.example.blooddonationfrontend.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.api.RetrofitClient;
import cg.example.blooddonationfrontend.model.Appointment;
import cg.example.blooddonationfrontend.model.Enums;
import cg.example.blooddonationfrontend.model.Globals;
import cg.example.blooddonationfrontend.model.Question;
import cg.example.blooddonationfrontend.model.Questionnaire;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentActivity extends AppCompatActivity {

    String Colector = "";
    String itemMinute = "";
    String itemHour = "";
    List<String> hoursList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_appointment);
        MaterialButton makeAppointment = findViewById(R.id.makeAppointment);
        ImageButton backButton = findViewById(R.id.back_button);

        getAvailableHours();

        makeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = "h" + itemHour.substring(0, 2) + itemHour.substring(3, 5);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String dateOfAppointment = formatter.format(date);
                Appointment appointment = new Appointment();

                appointment.setUser(Globals.currentUser.getId());
                appointment.setQuestionnaire(Globals.currentQuestionnaire.getId());
                appointment.setDayOfAppointment(dateOfAppointment);
                appointment.setHourOfAppointment(time);

                addAppointment(appointment);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppointmentActivity.this, HomeActivity.class));
            }
        });
    }

    private void addAppointment(Appointment newAppointment) {
        Call<Appointment> call = RetrofitClient
                .getInstance()
                .getAPI()
                .addAppointment(newAppointment);
        call.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                Boolean success;
                success = response.isSuccessful();
                int requestCode = response.code();
                if (success) {
                    Appointment crtAppointment = response.body();
                    Globals.setCurrentAppointment(crtAppointment);
                    startActivity(new Intent(AppointmentActivity.this, AppointmentViewActivity.class));
                } else {
                    Toast.makeText(AppointmentActivity.this, "Problems encountered.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {
                Toast.makeText(AppointmentActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("failure: " + t.getMessage());
            }
        });
    }

    private void setSpinnerHours(List<String> hoursList) {
        Spinner hourSpinner = findViewById(R.id.hourSpinner);
        ArrayAdapter<String> arrayAdapterHour = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hoursList);
        arrayAdapterHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(arrayAdapterHour);
        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                itemHour = adapterView.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO:
                Toast.makeText(AppointmentActivity.this, "Time not selected.", Toast.LENGTH_LONG).show();
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

                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                    Date date = new Date();
                    String time = formatter.format(date);

                    String hour = time.substring(0,2);
                    String minute = time.substring(3,5);
                    List<String> hoursList2 = new ArrayList<>();


                    if(hour.equals("13") && minute.equals("00")) {
                        hoursList2.add("13:00");
                    } else if(hour.equals("12")) {
                        if(minute.compareTo("30")>=0) {
                            hoursList2.add("12:30");
                            hoursList2.add("13:00");
                        }
                        if(minute.equals("00")) {
                            hoursList2.add("12:00");
                            hoursList2.add("12:30");
                            hoursList2.add("13:00");
                        }
                    } else if(hour.equals("11")) {
                        if(minute.compareTo("30")>=0) {
                            hoursList2.add("11:30");
                            hoursList2.add("12:00");
                            hoursList2.add("12:30");
                            hoursList2.add("13:00");
                        }

                        if(minute.equals("00")) {
                            hoursList2.add("11:00");
                            hoursList2.add("11:30");
                            hoursList2.add("12:00");
                            hoursList2.add("12:30");
                            hoursList2.add("13:00");
                        }
                    } else if (hour.equals("10")) {
                        if(minute.compareTo("30")>=0) {
                            hoursList2.add("10:30");
                            hoursList2.add("11:00");
                            hoursList2.add("11:30");
                            hoursList2.add("12:00");
                            hoursList2.add("12:30");
                            hoursList2.add("13:00");
                        }
                        if(minute.equals("00")) {
                            hoursList2.add("10:00");
                            hoursList2.add("10:30");
                            hoursList2.add("11:00");
                            hoursList2.add("11:30");
                            hoursList2.add("12:00");
                            hoursList2.add("12:30");
                            hoursList2.add("13:00");
                        }

                    } else if (hour.equals("09")) {
                        if(minute.compareTo("30")>=0) {
                            hoursList2.add("09:30");
                            hoursList2.add("10:00");
                            hoursList2.add("10:30");
                            hoursList2.add("11:00");
                            hoursList2.add("11:30");
                            hoursList2.add("12:00");
                            hoursList2.add("12:30");
                            hoursList2.add("13:00");
                        }

                        if(minute.equals("00")){
                            hoursList2.add("09:00");
                            hoursList2.add("09:30");
                            hoursList2.add("10:00");
                            hoursList2.add("10:30");
                            hoursList2.add("11:00");
                            hoursList2.add("11:30");
                            hoursList2.add("12:00");
                            hoursList2.add("12:30");
                            hoursList2.add("13:00");
                        }

                    } else if (hour.equals("08")) {
                        hoursList2.add("08:30");
                        hoursList2.add("09:00");
                        hoursList2.add("09:30");
                        hoursList2.add("10:00");
                        hoursList2.add("10:30");
                        hoursList2.add("11:00");
                        hoursList2.add("11:30");
                        hoursList2.add("12:00");
                        hoursList2.add("12:30");
                        hoursList2.add("13:00");
                    }

                    setSpinnerHours(hoursList2);
                } else {
                    Toast.makeText(AppointmentActivity.this, "Problems encountered.", Toast.LENGTH_LONG).show();
                }
            }
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(AppointmentActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("failure: " + t.getMessage());
            }
        });
    }



}
