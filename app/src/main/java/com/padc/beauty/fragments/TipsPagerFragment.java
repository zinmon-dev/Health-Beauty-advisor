package com.padc.beauty.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.activities.SpecialtipActivity;
import com.padc.beauty.adapters.TipPagerAdapter;
import com.padc.beauty.utils.BeautyAppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by windows on 9/4/2016.
 */
public class TipsPagerFragment extends Fragment  {
    @BindView(R.id.tl_tips)
    TabLayout tltips;

    @BindView(R.id.pager_tips)
    ViewPager pagerTips;

    @BindView((R.id.fab_tips))
    FloatingActionButton fabtips;

    private TipPagerAdapter mTipsPagerAdapter;


    public static TipsPagerFragment newInstance() {
        TipsPagerFragment fragment = new TipsPagerFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mTipsPagerAdapter = new TipPagerAdapter(getActivity().getSupportFragmentManager());
        mTipsPagerAdapter.addTab(FaceTipsFragment.newInstance(),getString(R.string.face_tips));
        mTipsPagerAdapter.addTab(SkinTipsFragment.newInstance(),getString(R.string.skin_tones));
        mTipsPagerAdapter.addTab(BodyShapeTipsFragment.newInstance(),getString(R.string.body_tips));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pager_tips, container, false);

        ButterKnife.bind(this, rootView);

        pagerTips.setAdapter(mTipsPagerAdapter);
        pagerTips.setOffscreenPageLimit(mTipsPagerAdapter.getCount());
        tltips.setupWithViewPager(pagerTips);

        return rootView;
    }


    @OnClick(R.id.fab_tips)
    public void onTapSpecificTips(View view) {
        //Toast.makeText(getContext(),"specific tips",Toast.LENGTH_SHORT).show();
        Intent intentDetail = new Intent(getActivity(),SpecialtipActivity.class);
        startActivity(intentDetail);
    }


}

