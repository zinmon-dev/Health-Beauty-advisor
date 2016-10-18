package com.padc.beauty.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.adapters.BeautySaloonAdapter;
import com.padc.beauty.adapters.ServicesAdapter;
import com.padc.beauty.data.models.FashionShopandBeautySaloonModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.ServiceVO;
import com.padc.beauty.fragments.SalonDetailPagerFragment;
import com.padc.beauty.fragments.SaloonandFashionshopFragment;
import com.padc.beauty.utils.BeautyAppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 9/26/2016.
 */
public class BeautysalonDetailActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.iv_beautysaloon)
    ImageView ivBeautysaloon;

//    @BindView(R.id.my_recycler_view)
//    RecyclerView myrecyclerview;

    private static final String IE_ATTRACTION_NAME = "IE_SALON_ID";
    private static final String IE_BEAUTY_SALON_IMAGE = "IE_SALON_IMAGE";
    private static final String IE_BEAUTY_SALON_NAME = "IE_SALON_NAME";
    private static final String IE_BEAUTY_SALON_ADD = "IE_SALON_ADD";

    private int gridColumnSpanCount=1;
    private Long  mBeautysalonId;
    private BeautySaloonVO mBeautysalon;
    private Long mSalonid;
    private List<ServiceVO> mServices;
    private ServicesAdapter mServicesAdapter;

    public static Intent newIntent(long salonid, String photo, String beautysalonPhoto, String address) {
        Intent intent = new Intent(BeautyApp.getContext(), BeautysalonDetailActivity.class);
        intent.putExtra(IE_ATTRACTION_NAME,salonid);
        intent.putExtra(IE_BEAUTY_SALON_IMAGE,photo);
        intent.putExtra(IE_BEAUTY_SALON_NAME,beautysalonPhoto);
        intent.putExtra(IE_BEAUTY_SALON_ADD,address);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_salon_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        final String mServices_image = getIntent().getStringExtra(IE_BEAUTY_SALON_IMAGE);
        final String mSalon_name  = getIntent().getStringExtra(IE_BEAUTY_SALON_NAME);
        final String mFulladdress  = getIntent().getStringExtra(IE_BEAUTY_SALON_ADD);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendViaShareIntent(BeautyAppConstant.APP_NAME + "\n"+ mSalon_name +" - "  + mServices_image);
            }
        });

        mSalonid = getIntent().getLongExtra(IE_ATTRACTION_NAME,0);

        showdata(mServices_image);
        Log.d(BeautyApp.TAG, "Salon ID : " + mSalonid);

        List<ServiceVO> serviceList = FashionShopandBeautySaloonModel.getInstance().getServiceList();
        //showdata();
        mServicesAdapter = new ServicesAdapter(serviceList);
//        myrecyclerview.setAdapter(mServicesAdapter);
//        myrecyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), gridColumnSpanCount));
//        getSupportLoaderManager().initLoader(BeautyAppConstant.BEAUTY_SALON_DETAIL_LOADER, null, this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, SalonDetailPagerFragment.newInstance(mSalonid,mSalon_name,mFulladdress))
                    .commit();
        }
    }

    private void showdata(String mServices_image) {
        Glide.with(ivBeautysaloon.getContext())
                .load(mServices_image)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivBeautysaloon);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(BeautyApp.TAG, "Salon ID : " + mSalonid);
        return new CursorLoader(this,
                BeautyContract.SalonServicesEntry.buildBeautySalonUriWithId(mSalonid),
                null,
                null,/*BeautyContract.BeautySalonEntry.COLUMN_OPEN + " = ?",*/
                null,/*new String[]{"true"},*/
                null
        );
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

    private void bindData(ServiceVO mServices) {
        //tvServices.setText(mServices.getdescription());

        String imageUrl = mServices.getimgurl();
        Glide.with(ivBeautysaloon.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivBeautysaloon);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
