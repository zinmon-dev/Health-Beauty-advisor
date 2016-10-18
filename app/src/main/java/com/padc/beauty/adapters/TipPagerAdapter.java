package com.padc.beauty.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.padc.beauty.utils.BeautyAppConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windows on 9/4/2016.
 */
public class TipPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentsTitles=new ArrayList<>();
    private BeautyAppConstant beautyAppConstant;

    public TipPagerAdapter(FragmentManager fm) {
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
