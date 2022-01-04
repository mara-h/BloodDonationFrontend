package cg.example.blooddonationfrontend.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import cg.example.blooddonationfrontend.R;


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

        if(email.isEmpty()) {
            emailText.setError("Email required");
            emailText.requestFocus();
        }

        if(password.isEmpty()) {
            passwordText.setError("Password required");
            passwordText.requestFocus();
        }

    }
}
