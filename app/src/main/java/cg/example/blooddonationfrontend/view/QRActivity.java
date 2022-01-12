package cg.example.blooddonationfrontend.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cg.example.blooddonationfrontend.R;

public class QRActivity extends AppCompatActivity {
    ImageView qrCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);

        qrCode = findViewById(R.id.qrCode);
    }

    //TODO: get qr code
}
