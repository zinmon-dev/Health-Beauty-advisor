package com.padc.beauty.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.activities.PersonalityDetailActivity;
import com.padc.beauty.adapters.AllTipListAdapter;
import com.padc.beauty.adapters.PersonalityTipAdapter;
import com.padc.beauty.data.models.TipModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.events.DataEvent;
import com.padc.beauty.utils.BeautyAppConstant;
import com.padc.beauty.views.holders.PersonalityViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by windows on 9/17/2016.
 */
public class PersonalityListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>  {

    @BindView(R.id.rv_personality)
    RecyclerView rvpersonality;
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    private PersonalityTipAdapter mpersonalitytipAdapter;
    private PersonalityViewHolder.ControllerPersonalityItem mcontroller;

    public static PersonalityListFragment newInstance() {
        return new PersonalityListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personality_list, container, false);
        ButterKnife.bind(this, view);

        List<TipVO> tipList = TipModel.getInstance().getmTipList();
        mpersonalitytipAdapter = new PersonalityTipAdapter(tipList,mcontroller);
        rvpersonality.setAdapter(mpersonalitytipAdapter);

        int gridColumnSpanCount = getResources().getInteger(R.integer.tip_list_grid);
        rvpersonality.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
    }
//    @OnClick(R.id.ll_good_personality)
//    public void onTapPersonality(){
//        Intent intent = PersonalityDetailActivity.newIntent();
//        startActivity(intent);
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontroller = (PersonalityViewHolder.ControllerPersonalityItem) context;
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
                BeautyContract.TipEntry.COLUMN_CATEGORY + " = ? ",
                new String[]{"personality-related"},
                BeautyContract.TipEntry.COLUMN_TIPID + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<TipVO> tipList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                TipVO tip = TipVO.parseFromCursor(data);
                //tip.setImages(AttractionVO.loadAttractionImagesByTitle(attraction.getTitle()));
                tipList.add(tip);
            } while (data.moveToNext());
        }

        Log.d(BeautyApp.TAG, "Retrieved Personality : " + tipList.size());
        mpersonalitytipAdapter.setNewData(tipList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(BeautyAppConstant.PERSONALITY_LIST_LOADER, null, this);
    }

    public void onEventMainThread(DataEvent.TipDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<TipVO> newTipList = event.getTipList();
        mpersonalitytipAdapter.setNewData(newTipList);
    }

}
