package com.padc.beauty.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.data.vos.PersonalityDetailVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by windows on 10/1/2016.
 */
public class PersonalityDetailPagerAdapter extends PagerAdapter {

    private List<PersonalityDetailVO> mpersonalitydetailvo;
    private LayoutInflater mInflater;

    public PersonalityDetailPagerAdapter(List<PersonalityDetailVO> personalityDetailVOs) {
        if (personalityDetailVOs == null) {
            mpersonalitydetailvo = new ArrayList<>();
        } else {
            mpersonalitydetailvo = new ArrayList<>(personalityDetailVOs);
        }
        mInflater = LayoutInflater.from(BeautyApp.getContext());
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
