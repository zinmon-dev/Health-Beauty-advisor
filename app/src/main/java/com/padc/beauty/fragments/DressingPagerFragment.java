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
import com.padc.beauty.adapters.DressingPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by windows on 9/13/2016.
 */
public class DressingPagerFragment extends Fragment {

    @BindView(R.id.tl_dressing)
    TabLayout tldressings;

    @BindView(R.id.pager_dressings)
    ViewPager pagerDressings;

    private DressingPagerAdapter  mDressingPagerAdapter;

    public static DressingPagerFragment newInstance() {
        DressingPagerFragment fragment = new DressingPagerFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDressingPagerAdapter = new DressingPagerAdapter(getActivity().getSupportFragmentManager());
        mDressingPagerAdapter.addTab(WeddingDressFragment.newInstance(),getString(R.string.wedding_dressing));
        mDressingPagerAdapter.addTab(DinnerDressFragment.newInstance(),getString(R.string.dinner_dressing));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pager_dressing, container, false);
        ButterKnife.bind(this, rootView);

        pagerDressings.setAdapter(mDressingPagerAdapter);
        pagerDressings.setOffscreenPageLimit(mDressingPagerAdapter.getCount());
        tldressings.setupWithViewPager(pagerDressings);
        return rootView;
    }
}
