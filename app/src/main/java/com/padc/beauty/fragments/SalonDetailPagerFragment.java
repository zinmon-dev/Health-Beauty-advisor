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
 * Created by Asus on 10/2/2016.
 */
public class SalonDetailPagerFragment extends Fragment {
    @BindView(R.id.tl_tips)
    TabLayout tltips;

    @BindView(R.id.pager_tips)
    ViewPager pagerTips;

    private TipPagerAdapter mTipsPagerAdapter;
    private static Long mSalon_id;
    private static String mBeauty_Salon_name;
    private static String mSalon_add;

    public static SalonDetailPagerFragment newInstance(Long mSalonid, String mSalon_name,String address){
        SalonDetailPagerFragment salonDetailPagerFragment = new SalonDetailPagerFragment();
        mSalon_id = mSalonid;
        mBeauty_Salon_name = mSalon_name;
        mSalon_add = address;
        return salonDetailPagerFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTipsPagerAdapter = new TipPagerAdapter(getActivity().getSupportFragmentManager());
        mTipsPagerAdapter.addTab(ServicesFragment.newInstance(mSalon_id),getString(R.string.saloon_services));
        mTipsPagerAdapter.addTab(AboutusFragment.newInstance(mBeauty_Salon_name,mSalon_add),getString(R.string.aboutus));
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
