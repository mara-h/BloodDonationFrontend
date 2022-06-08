package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import cg.example.blooddonationfrontend.R;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_home);

        CardView qrScanCard = findViewById(R.id.qrScanCard);
        CardView logoutCard = findViewById(R.id.logoutCardAdmin);
        CardView appointmentCard = findViewById(R.id.appointmentCardAdmin);

        qrScanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomeActivity.this, CameraActivity.class));
            }
        });

        logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomeActivity.this, LoginActivity.class));
            }
        });

        appointmentCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AdminAppointmentsActivity.class));
            }
        });
    }
}
