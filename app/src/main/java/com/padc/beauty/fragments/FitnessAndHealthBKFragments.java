package com.padc.beauty.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.R;
import com.padc.beauty.adapters.TipPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 9/11/2016.
 */
public class FitnessAndHealthBKFragments extends Fragment {
    @BindView(R.id.tl_tips)
    TabLayout tltips;

    @BindView(R.id.pager_tips)
    ViewPager pagerTips;

    private TipPagerAdapter mTipsPagerAdapter;
    public static FitnessAndHealthBKFragments newInstance(){
        FitnessAndHealthBKFragments fitnessAndHealthFragments = new FitnessAndHealthBKFragments();
        return fitnessAndHealthFragments;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTipsPagerAdapter = new TipPagerAdapter(getActivity().getSupportFragmentManager());
        mTipsPagerAdapter.addTab(WorkoutFragment.newInstance(),getString(R.string.workout));
        mTipsPagerAdapter.addTab(DietFragment.newInstance(),getString(R.string.diet));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_temp_fitness_and_health, container, false);
        ButterKnife.bind(this, rootView);

        pagerTips.setAdapter(mTipsPagerAdapter);
        pagerTips.setOffscreenPageLimit(mTipsPagerAdapter.getCount());
        tltips.setupWithViewPager(pagerTips);
        return rootView;
    }
}
