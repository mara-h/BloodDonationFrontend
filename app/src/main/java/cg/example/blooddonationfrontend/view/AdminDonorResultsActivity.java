package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.Inet4Address;

import cg.example.blooddonationfrontend.R;

public class AdminDonorResultsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_donor_results);
        ImageButton backButton = findViewById(R.id.back_button);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDonorResultsActivity.this, AdminHomeActivity.class));
            }
        });
    }
}
