package com.padc.beauty.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.padc.beauty.R;
import com.padc.beauty.adapters.BodyshapeListAdapter;
import com.padc.beauty.adapters.SkinTypeAdapter;
import com.padc.beauty.adapters.FaceTipAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class SpecialtipActivityFragment extends Fragment {
    @BindView(R.id.rdgrp_face)
    RadioGroup rdface;

    @BindView(R.id.rdo_skin)
    RadioGroup rdskin;

    @BindView(R.id.sp_bodyshapequest)
    Spinner spBodyShapeList;

//    @BindView(R.id.lbl_tips_title)
//    TextView lblTipsTitle;

    private BodyshapeListAdapter mBodyshapeListAdapter;
    private SkinTypeAdapter mSkinTipListAdapter;
    private FaceTipAdapter mFaceTipAdapter;

    //private FragmentChangeListener mChangeFragment;

    public static SpecialtipActivityFragment newInstance() {
        SpecialtipActivityFragment fragment = new SpecialtipActivityFragment();
        return fragment;
    }
    public SpecialtipActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] bodyshapeListArray = getResources().getStringArray(R.array.body_shape);
        List<String> bodyshapeList = new ArrayList<>(Arrays.asList(bodyshapeListArray));
        mBodyshapeListAdapter = new BodyshapeListAdapter(bodyshapeList);

//        String[]faceshapeListArray = getResources().getStringArray(R.array.face_tip_list);
//        List<String> faceshapeList = new ArrayList<>(Arrays.asList(faceshapeListArray));
//        mFaceTipAdapter = new FaceTipAdapter(faceshapeList);
//
//        String[]skintipListArray = getResources().getStringArray(R.array.Skin_Type_tip_list);
//        List<String> skintipList = new ArrayList<>(Arrays.asList(skintipListArray));
//        mSkinTipListAdapter = new SkinTypeAdapter(skintipList);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_specialtip, container, false);
        ButterKnife.bind(this, rootView);
        spBodyShapeList.setAdapter(mBodyshapeListAdapter);
//        spFaceList.setAdapter(mFaceTipAdapter);
//        spSkinList.setAdapter(mSkinTipListAdapter);
        //lblTipsTitle.setText("Special Tips for you");
        return rootView;
    }

    @OnClick(R.id.btn_ok)
    public void onTapOk(Button btnOk) {
        Fragment fr=new ShowResultFragment();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);
    }

}
