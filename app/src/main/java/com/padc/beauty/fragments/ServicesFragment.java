package com.padc.beauty.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.adapters.ServicesAdapter;
import com.padc.beauty.data.models.FashionShopandBeautySaloonModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.ServiceVO;
import com.padc.beauty.utils.BeautyAppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 10/2/2016.
 */
public class ServicesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static Long mSalonid;
    private List<ServiceVO> mServices;
    private ServicesAdapter mServicesAdapter;
    @BindView(R.id.my_recycler_view)
    RecyclerView myrecyclerview;
    public static int gridColumnSpanCount=1;

    public static ServicesFragment newInstance(Long mSalon_id){
        ServicesFragment servicesFragment = new ServicesFragment();
        mSalonid = mSalon_id;
        return servicesFragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(BeautyAppConstant.BEAUTY_SALON_LIST_LOADER, null, this);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_services_view, container, false);
        ButterKnife.bind(this, rootView);
        List<ServiceVO> serviceList = FashionShopandBeautySaloonModel.getInstance().getServiceList();
        //showdata();
        mServicesAdapter = new ServicesAdapter(serviceList);
        myrecyclerview.setAdapter(mServicesAdapter);
        myrecyclerview.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(BeautyApp.TAG, "Salon ID : " + mSalonid);
        return new CursorLoader(getContext(),
                BeautyContract.SalonServicesEntry.buildBeautySalonUriWithId(mSalonid),
                null,
                null, /*BeautyContract.BeautySalonEntry.COLUMN_OPEN + " = ?",*/
                null, /*new String[]{"true"},*/
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(BeautyApp.TAG, "Salon ID : " + mSalonid);

//        if (data != null && data.moveToFirst()) {
//            mServices = ServiceVO.parseFromCursor(data);
//            mServices.setImage(mServices.getimgurl());
//            Log.d(BeautyApp.TAG,"cursor data count"+data.getColumnCount());
//            bindData(mServices);
//        }


        mServices = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                ServiceVO services = ServiceVO.parseFromCursor(data);

                mServices.add(services);
            } while (data.moveToNext());
        }

        Log.d(BeautyApp.TAG, "Retrieved services table ASC : " + mServices.size());
        mServicesAdapter.setNewData(mServices);

        FashionShopandBeautySaloonModel.getInstance().setStoredServicesData(mServices);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
