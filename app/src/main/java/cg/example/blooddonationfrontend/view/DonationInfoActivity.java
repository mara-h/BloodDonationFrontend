package cg.example.blooddonationfrontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;


import cg.example.blooddonationfrontend.R;

public class DonationInfoActivity extends AppCompatActivity {
    ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_donation_info);

        viewPager = findViewById(R.id.vpInfo);
       // DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);
       // SpringDotsIndicator dotsIndicator = findViewById(R.id.spring_dots_indicator);
        WormDotsIndicator dotsIndicator = findViewById(R.id.worm_dots_indicator);
        viewPager.setAdapter(new InfoPagerAdapter(getSupportFragmentManager(), getLifecycle()));
        dotsIndicator.setViewPager2(viewPager);
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationInfoActivity.this, HomeActivity.class));
            }
        });

    }


//    @Override
//    public void onBackPressed() {
//        if(viewPager.getCurrentItem() ==0) {
//            super.onBackPressed();
//        } else {
//            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
//        }
//    }


}
