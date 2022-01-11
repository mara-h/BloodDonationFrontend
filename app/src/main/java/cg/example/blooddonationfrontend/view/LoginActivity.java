package cg.example.blooddonationfrontend.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.api.RetrofitClient;
import cg.example.blooddonationfrontend.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private EditText passwordText, emailText;
    private TextView registerField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        MaterialButton loginBtn = (MaterialButton)findViewById(R.id.loginBtn);
        registerField = (TextView)findViewById(R.id.registerField);



        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        findViewById(R.id.registerField).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    private void loginUser() {
        final String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        boolean errorFlag = false;

        if(email.isEmpty()) {
            emailText.setError("Email required");
            emailText.requestFocus();
            errorFlag = true;
        }

        if(password.isEmpty()) {
            passwordText.setError("Password required");
            passwordText.requestFocus();
            errorFlag = true;
        }

        if(!errorFlag) {
            User user = new User();
            user.setEmail(emailText.toString()); // check
            user.setPassword(passwordText.toString());
            this.makeLoginCall(user);
        }

    }

    private void makeLoginCall(User user) {
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .checkUser(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                boolean success;
                success = response.isSuccessful();

                int requestCode = response.code();

                if(success) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    if(requestCode == 500) {
                        Toast.makeText(LoginActivity.this, "Server error", Toast.LENGTH_LONG).show();
                    }
                    else {
                        if (requestCode == 400) {
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Authentication error. Email or password does not exist.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
