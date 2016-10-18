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
 * Created by Asus on 9/13/2016.
 */
public class SaloonandFashionshopFragment extends Fragment {
    @BindView(R.id.tl_tips)
    TabLayout tltips;

    @BindView(R.id.pager_tips)
    ViewPager pagerTips;

    private TipPagerAdapter mTipsPagerAdapter;
    public static SaloonandFashionshopFragment newInstance(){
        SaloonandFashionshopFragment saloonandFashionshopFragment = new SaloonandFashionshopFragment();
        return saloonandFashionshopFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTipsPagerAdapter = new TipPagerAdapter(getActivity().getSupportFragmentManager());
        mTipsPagerAdapter.addTab(BeautysaloonFragment.newInstance(),getString(R.string.saloon));
        mTipsPagerAdapter.addTab(FashionshopFragment.newInstance(),getString(R.string.fashionshop));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saloon_and_fashionshop, container, false);
        ButterKnife.bind(this, rootView);

        pagerTips.setAdapter(mTipsPagerAdapter);
        pagerTips.setOffscreenPageLimit(mTipsPagerAdapter.getCount());
        tltips.setupWithViewPager(pagerTips);
        return rootView;
    }
}
