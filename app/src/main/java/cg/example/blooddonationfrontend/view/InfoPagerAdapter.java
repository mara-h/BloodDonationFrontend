package cg.example.blooddonationfrontend.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class InfoPagerAdapter extends FragmentStateAdapter {

    InfoFragment page1, page2, page3, page4, page5, page6;

    public InfoPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        page1 = new InfoFragment();
        page1.setCurrentPos(0);

        page2 = new InfoFragment();
        page2.setCurrentPos(1);

        page3 = new InfoFragment();
        page3.setCurrentPos(2);

        page4 = new InfoFragment();
        page4.setCurrentPos(3);

        page5 = new InfoFragment();
        page5.setCurrentPos(4);

        page6 = new InfoFragment();
        page6.setCurrentPos(5);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return page1;
            case 1:
                return page2;
            case 2:
                return page3;
            case 3:
                return page4;
            case 4:
                return page5;
            case 5:
                return page6;
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return 6;
    }



}
