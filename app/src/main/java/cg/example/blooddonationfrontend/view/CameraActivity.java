package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import java.util.UUID;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.api.RetrofitClient;
import cg.example.blooddonationfrontend.model.Globals;
import cg.example.blooddonationfrontend.model.Question;
import cg.example.blooddonationfrontend.model.Questionnaire;
import cg.example.blooddonationfrontend.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_camera);
        Button answerCamera = findViewById(R.id.answerCamera);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CameraActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                        id = result.getText();
                        findViewById(R.id.answerCamera).setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
        answerCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQuestionnaireData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    private void getQuestionnaireData() {

        Call<Questionnaire> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getQuestionnaireData(id);

        call.enqueue(new Callback<Questionnaire>() {
            public void onResponse(Call<Questionnaire> call, Response<Questionnaire> response) {
                Boolean success;
                success = response.isSuccessful();
                int requestCode = response.code();
                if (success) {
                    Questionnaire thisQuestionnaire = response.body();
                    Globals.setCurrentQuestionnaire(thisQuestionnaire);
                    UUID userId = thisQuestionnaire.getUserId();
                    getUserData(userId.toString());

                } else {
                    Toast.makeText(CameraActivity.this, "Eroare la obținerea chestionarului.", Toast.LENGTH_LONG).show();
                }
            }
            public void onFailure(Call<Questionnaire> call, Throwable t) {
                Toast.makeText(CameraActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("failure: " + t.getMessage());
            }
        });
    }

    private void getUserData(String userId) {
        Call<User> call = RetrofitClient
                .getInstance()
                .getAPI()
                .getUserById(userId);

        call.enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                Boolean success;
                success = response.isSuccessful();
                int requestCode = response.code();

                if(success) {
                    User questionnaireUser = response.body();
                    Globals.setQuestionnaireUser(questionnaireUser);
                    startActivity(new Intent(CameraActivity.this, AdminDonorResultsActivity.class));
                } else {
                    Toast.makeText(CameraActivity.this, "Error la obținerea datelor din chestioanr.", Toast.LENGTH_LONG).show();
                }

            }

            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(CameraActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("failure: " + t.getMessage());
            }
        });
    }


}



