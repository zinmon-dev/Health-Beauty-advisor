package com.padc.beauty.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.padc.beauty.R;
import com.padc.beauty.utils.BeautyAppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Asus on 10/2/2016.
 */
public class AboutusFragment extends Fragment {
    private static String mBeauty_salon_name;
    private static String mFulladdress;

    @BindView(R.id.tv_salon_name)
    TextView beautysalonname;

    @BindView(R.id.tv_address)
    TextView address;

    public static AboutusFragment newInstance(String mSalon_name, String address){
        AboutusFragment aboutusFragment = new AboutusFragment();
        mBeauty_salon_name = mSalon_name;
        mFulladdress = address;
        return aboutusFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_salon_aboutus, container, false);
        ButterKnife.bind(this, rootView);
        beautysalonname.setText(mBeauty_salon_name);
        address.setText(mFulladdress);
        return rootView;
    }


    @OnClick(R.id.btn_booking)
    public void onTapBook(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+BeautyAppConstant.CUSTOMER_SUPPORT_PHONE));
        startActivity(callIntent);
    }
}
