package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.api.RetrofitClient;
import cg.example.blooddonationfrontend.controller.InputValidator;


import cg.example.blooddonationfrontend.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends android.app.Activity {
    private EditText name, email, password, retypePassword, cnp, age;
    private TextView sexString;
    private ImageButton female, male;
    private MaterialButton register;
    private Spinner bloodGroupInput;
    private InputValidator inputValidator = new InputValidator();
    String Colector="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.registerNameInput);
        email = findViewById(R.id.registerEmailInput);
        password = findViewById(R.id.registerPasswordInput);
        retypePassword = findViewById(R.id.registerPasswordRetype);
        cnp = findViewById(R.id.cnpInput);
        age = findViewById(R.id.ageInput);
        female = findViewById(R.id.Female);
        male = findViewById(R.id.Male);
        register = findViewById(R.id.registerBtn);
        bloodGroupInput = (Spinner) findViewById(R.id.bloodGroupInput);
        sexString = (TextView) findViewById(R.id.sexString);

        female.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String display = "female";
                sexString.setText(display);
            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String display = "male";
                sexString.setText(display);
            }
        });

        List<String> bloodGroups = new ArrayList<>();
        bloodGroups.add("Select blood group");
        bloodGroups.add("A+");
        bloodGroups.add("A-");
        bloodGroups.add("B+");
        bloodGroups.add("B-");
        bloodGroups.add("AB+");
        bloodGroups.add("AB-");
        bloodGroups.add("0+");
        bloodGroups.add("0-");
        bloodGroups.add("UNKNOWN");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bloodGroups);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupInput.setAdapter(arrayAdapter);
        bloodGroupInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(adapterView.getItemAtPosition(position).equals("Select blood group")) {
                    Colector = "UNKNOWN";
                }
                else {
                    String item = adapterView.getItemAtPosition(position).toString();
                    Colector = adapterView.getItemAtPosition(position).toString();
                   // Colector+=item+"\n"; // ASA SAU colector = item?
                   // Toast.makeText(RegisterActivity.this, "Select blood group "+ item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Colector = "UNKNOWN";
            }
        });

        findViewById(R.id.registerBtn).setOnClickListener(view->registerUser());
        findViewById(R.id.registerBtn).setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

    }


    private void registerUser() {
        HashMap<EditText, String> inputs = new HashMap<>();

        String nameString = name.getText().toString().trim();
        String emailString = email.getText().toString().trim();
        String passwordString = password.getText().toString().trim();
        String retypePasswordString = retypePassword.getText().toString().trim();
        String cnpString = cnp.getText().toString().trim();
        String ageString = age.getText().toString().trim();
        String bloodGroupString = bloodGroupInput.getSelectedItem().toString();

        inputs.put(name, nameString);
        inputs.put(email, emailString);
        inputs.put(password, passwordString);
        inputs.put(retypePassword, retypePasswordString);
        inputs.put(cnp, cnpString);
        inputs.put(age, ageString);

        for (EditText field : inputs.keySet()) {
            if (inputs.get(field).isEmpty()) {
                inputValidator.setFieldError(field, "This field cannot be empty");
                return;
            }
        }

        if(sexString.getText().toString().equals("")) {
            sexString.setError("Please select your sex.");
        }

        if (!inputValidator.doStringsMatch(passwordString, retypePasswordString)) {
            inputValidator.setFieldError(retypePassword, "Passwords do not match");
            return;
        }

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .createUser(new User(nameString, emailString, passwordString, cnpString, ageString, sexString.getText().toString(), bloodGroupString));

        call.enqueue(new Callback<ResponseBody>() {

            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = "";
                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (s.equals("SUCCESS")) {
                    Toast.makeText(RegisterActivity.this, "Registered successfully. Please log in", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "User already exists.", Toast.LENGTH_LONG).show();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}
