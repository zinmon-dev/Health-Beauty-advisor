package com.padc.beauty.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.padc.beauty.adapters.FaceTipAdapter;
import com.padc.beauty.utils.BeautyAppConstant;

/**
 * Created by windows on 9/9/2016.
 */
public class BaseFragment extends Fragment {

    public FaceTipAdapter mTipListAdapter;
    private BeautyAppConstant beautyAppConstant;

    String[] tipListArray;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
