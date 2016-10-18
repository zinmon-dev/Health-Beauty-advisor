package com.padc.beauty.fragments;

import android.content.Intent;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.adapters.FitnessandHealthDetailAdapter;
import com.padc.beauty.data.models.TipModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.PersonalityDetailVO;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.events.DataEvent;
import com.padc.beauty.utils.BeautyAppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 9/14/2016.
 */

public class WorkoutDetailFragment extends Fragment  implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final String BARG_FTTNESSANDHEALTH_INDEX = "BARG_FITNESSANDHEALTH_INDEX";

    @BindView(R.id.rv_fitnessandhealthdtl)
    RecyclerView rvfitnessandhealth;

    private long tipid;
    private FitnessandHealthDetailAdapter mfitnessandhealthdtlAdapter;
    public static WorkoutDetailFragment newInstance(long Tipid){
        WorkoutDetailFragment workoutDetailFragment = new WorkoutDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(BARG_FTTNESSANDHEALTH_INDEX, Tipid);
        workoutDetailFragment.setArguments(bundle);
        return workoutDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle !=null){
            tipid = bundle.getLong(BARG_FTTNESSANDHEALTH_INDEX);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workoutdetail, container, false);
        ButterKnife.bind(this, rootView);

        List<TipVO> tipList = TipModel.getInstance().getmTipList();
        mfitnessandhealthdtlAdapter = new FitnessandHealthDetailAdapter(tipList);
        rvfitnessandhealth.setAdapter(mfitnessandhealthdtlAdapter);

        int gridColumnSpanCount = getResources().getInteger(R.integer.tip_list_grid);
        rvfitnessandhealth.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
      //  showdata();
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String selectionbyid =BeautyContract.TipEntry.COLUMN_CATEGORY + " = ? "+ " AND " + BeautyContract.TipEntry.COLUMN_TIPID +" =? ";

        return new CursorLoader(getContext(),
                BeautyContract.TipEntry.CONTENT_URI,
                null,
                selectionbyid,
                new String[]{"fitness-and-health-related",Long.toString(tipid) },
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
        mfitnessandhealthdtlAdapter.setNewData(tipList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public void onEventMainThread(DataEvent.TipDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<TipVO> newTipList = event.getTipList();
        mfitnessandhealthdtlAdapter.setNewData(newTipList);
    }
//    private void showdata()
//    {
//        cardio.setText(Html.fromHtml(getString(R.string.cardio_exercise)));
//        capoeria.setText(Html.fromHtml(getString(R.string.capoeria_exercise)));
//    }
@Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getActivity().getSupportLoaderManager().initLoader(BeautyAppConstant.FitnessandHealthDetailTIPS_LIST_LOADER, null, this);
}


}
