package com.padc.beauty.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windows on 9/13/2016.
 */
public class DressingPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentsTitles=new ArrayList<>();

    public DressingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mFragmentsTitles.get(position);
    }

    public void addTab(Fragment fragment,String title)
    {
        mFragments.add(fragment);
        mFragmentsTitles.add(title);
    }
}
