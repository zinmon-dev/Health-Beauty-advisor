package com.padc.beauty.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.R;

import butterknife.ButterKnife;

/**
 * Created by Asus on 9/10/2016.
 */
public class ShowResultFragment extends Fragment {
    public static ShowResultFragment newInstance() {
        ShowResultFragment fragment = new ShowResultFragment();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_show_result, container, false);
        //return inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }
}
