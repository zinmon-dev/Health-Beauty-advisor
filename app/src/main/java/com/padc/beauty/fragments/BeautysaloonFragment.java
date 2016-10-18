package com.padc.beauty.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.adapters.BeautySaloonAdapter;
import com.padc.beauty.data.models.FashionShopandBeautySaloonModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.BeautySaloonVO;

import com.padc.beauty.events.DataEvent;
import com.padc.beauty.utils.BeautyAppConstant;
import com.padc.beauty.views.holders.BeautySaloonViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Asus on 9/13/2016.
 */
public class BeautysaloonFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    @BindView(R.id.rv_beautysaloon)
    RecyclerView rvbeautysaloon;

    private int gridColumnSpanCount=2;

    private BeautySaloonAdapter mBeautySaloonAdapter;
    private List<BeautySaloonVO> beautysalonList;
    private BeautySaloonViewHolder.ControllerBeautysalonItem controllerBeautysalonItem;

    public static BeautysaloonFragment newInstance(){
        BeautysaloonFragment beautysaloonFragment = new BeautysaloonFragment();
        return beautysaloonFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controllerBeautysalonItem = (BeautySaloonViewHolder.ControllerBeautysalonItem) context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beautysaloon, container, false);
        ButterKnife.bind(this, rootView);

        List<BeautySaloonVO> beautysaloonList = FashionShopandBeautySaloonModel.getInstance().getBeautySaloonList();
        //showdata();
        mBeautySaloonAdapter = new BeautySaloonAdapter(beautysaloonList,controllerBeautysalonItem);
        rvbeautysaloon.setAdapter(mBeautySaloonAdapter);
        rvbeautysaloon.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEventMainThread(DataEvent.FahionShopandBeautySaloonDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        List<BeautySaloonVO> newBeautySaloonList = event.getBeautySaloonList();
        mBeautySaloonAdapter.setNewData(newBeautySaloonList);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(BeautyAppConstant.BEAUTY_SALON_LIST_LOADER, null, this);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                BeautyContract.BeautySalonEntry.CONTENT_URI,
                null,
                null, /*BeautyContract.BeautySalonEntry.COLUMN_OPEN + " = ?",*/
                null, /*new String[]{"true"},*/
                BeautyContract.BeautySalonEntry.COLUMN_ID + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        beautysalonList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                BeautySaloonVO beautysalon = BeautySaloonVO.parseFromCursor(data);
                beautysalonList.add(beautysalon);
            } while (data.moveToNext());
        }

        Log.d(BeautyApp.TAG, "Retrieved beauty salon ASC : " + beautysalonList.size());
        mBeautySaloonAdapter.setNewData(beautysalonList);

        FashionShopandBeautySaloonModel.getInstance().setStoredBeautysalonData(beautysalonList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private List<BeautySaloonVO> filterByCategory(String category) {
        List<BeautySaloonVO> beautySaloonVOs = new ArrayList<>();
        for(BeautySaloonVO beautySaloonVO:beautysalonList) {
            if(TextUtils.equals(beautySaloonVO.getsaloonname(), category)) {
                beautySaloonVOs.add(beautySaloonVO);
            }
        }

        return beautySaloonVOs;
    }
}
