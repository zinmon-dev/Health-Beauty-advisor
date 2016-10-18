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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.adapters.AllTipListAdapter;
import com.padc.beauty.adapters.FitnessandhealthAdapter;
import com.padc.beauty.data.models.TipModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.events.DataEvent;
import com.padc.beauty.utils.BeautyAppConstant;
import com.padc.beauty.views.holders.BeautySaloonViewHolder;
import com.padc.beauty.views.holders.FitnessandhealthViewHolder;
import com.padc.beauty.views.holders.TipViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by windows on 9/27/2016.
 */
public class FitnessAndHealthFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>   {
    @BindView(R.id.rv_fitnessandhealth)
    RecyclerView rvfitnessandhealth;
    private long tipid;
    private FitnessandhealthAdapter mfitnessandhealthAdapter;
    private FitnessandhealthViewHolder.ControllerFitnessandHealth controllerFitnessandHealth;
    public static FitnessAndHealthFragment newInstance() {
        return new FitnessAndHealthFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controllerFitnessandHealth = (FitnessandhealthViewHolder.ControllerFitnessandHealth) context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fitness_and_health, container, false);
        ButterKnife.bind(this, view);

        List<TipVO> tipList = TipModel.getInstance().getmTipList();
        mfitnessandhealthAdapter = new FitnessandhealthAdapter(tipList,controllerFitnessandHealth);
        rvfitnessandhealth.setAdapter(mfitnessandhealthAdapter);

        int gridColumnSpanCount = getResources().getInteger(R.integer.tip_list_grid);
        rvfitnessandhealth.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                BeautyContract.TipEntry.CONTENT_URI,
                null,
                BeautyContract.TipEntry.COLUMN_CATEGORY + " = ?",
                new String[]{"fitness-and-health-related"},
                BeautyContract.TipEntry.COLUMN_TIPID + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<TipVO> tipList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                TipVO tip = TipVO.parseFromCursor(data);
                //tip.setImg_url(.loadAttractionImagesByTitle(attraction.getTitle()));
                tipList.add(tip);
            } while (data.moveToNext());
        }

        Log.d(BeautyApp.TAG, "Retrieved Skin Tips : " + tipList.size());
        mfitnessandhealthAdapter.setNewData(tipList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public void onEventMainThread(DataEvent.TipDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<TipVO> newTipList = event.getTipList();
        mfitnessandhealthAdapter.setNewData(newTipList);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(BeautyAppConstant.FitnessandHealthTIPS_LIST_LOADER, null, this);
    }
}
