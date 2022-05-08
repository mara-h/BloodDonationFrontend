package cg.example.blooddonationfrontend.view;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;


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

        viewPager.setAdapter(new InfoPagerAdapter(getSupportFragmentManager(), getLifecycle()));

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
