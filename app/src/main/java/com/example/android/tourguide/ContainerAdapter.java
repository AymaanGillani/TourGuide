package com.example.android.tourguide;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ContainerAdapter extends FragmentPagerAdapter {

    public ContainerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DetailsFragment();
        } else if (position == 1){
            return new HelpUsOutFragment();
        } else {
            return new MapFragment();
        }
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:return "Details";
            case 1:return "Help us out";
            case 2:return "Map & Directions";
            default:return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
