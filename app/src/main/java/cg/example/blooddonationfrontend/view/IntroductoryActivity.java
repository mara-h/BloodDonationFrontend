package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import java.util.Timer;
import java.util.TimerTask;
import cg.example.blooddonationfrontend.R;

public class IntroductoryActivity extends AppCompatActivity {

    ImageView logo, text;
    LottieAnimationView lottieAnimationView;
    Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        logo = findViewById(R.id.img);
        text = findViewById(R.id.text);
        lottieAnimationView = findViewById(R.id.lottie);

        logo.animate().translationY(-1600).setDuration(3000).setStartDelay(4000);
        text.animate().translationY(-1600).setDuration(3000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(3000).setStartDelay(4000);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroductoryActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 6000);

    }
}
