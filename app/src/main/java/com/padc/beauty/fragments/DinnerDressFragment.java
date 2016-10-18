package com.padc.beauty.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.adapters.DressingAdapter;
import com.padc.beauty.data.models.DressingModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.data.vos.PersonalityDetailVO;
import com.padc.beauty.events.DataEvent;
import com.padc.beauty.utils.BeautyAppConstant;
import com.padc.beauty.views.holders.DressingViewHolder;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import de.greenrobot.event.EventBus;

/**
 * Created by windows on 9/16/2016.
 */
public class DinnerDressFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.rv_freedress)
    RecyclerView rvdress;

    @BindView(R.id.ll_dressingsearch)
    LinearLayout llserach;

    @BindView(R.id.iv_search)
    ImageView ivsearch;

    @BindView(R.id.et_searchdressing)
    EditText etsearch;

    @BindView(R.id.tp_serarch)
    TextInputLayout tpsearch;

    @BindView(R.id.fab)
    FloatingActionButton fabsearch;

    private DressingAdapter mDressAdapter;
    private DressingViewHolder.ControllerDressing controllerDressing;
    private List<DressingVO> mdressingList;

    public static DinnerDressFragment newInstance() {
        return new DinnerDressFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free_dress, container, false);
        ButterKnife.bind(this, view);
        int gridColumnSpanCount = getResources().getInteger(R.integer.tip_list_grid);
        rvdress.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        List<DressingVO> dressingList = DressingModel.getInstance().getmDressingList();
        mDressAdapter=new DressingAdapter(dressingList,controllerDressing);
        rvdress.setAdapter(mDressAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controllerDressing = (DressingViewHolder.ControllerDressing) context;
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

    public void onEventMainThread(DataEvent.DressingDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        //Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        List<DressingVO> newDressingList = event.getDressingList();
        mDressAdapter.setNewData(newDressingList);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                BeautyContract.DressingEntry.CONTENT_URI,
                null,
                BeautyContract.DressingEntry.COLUMN_TYPE + " = ?",
                new String[]{"free"},
                BeautyContract.DressingEntry.COLUMN_DRESSINGID + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<DressingVO> dressingList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                DressingVO dressing = DressingVO.parseFromCursor(data);
                dressing.setHairstyles(DressingModel.loadHairStyleByDressingID(dressing.getid()));
                dressing.setBodyshapes(DressingModel.loadBodyShapeByDressingID(dressing.getid()));
                dressing.setSkintypes(DressingModel.loadSkinTypeByDressingID(dressing.getid()));
                dressing.setSkincolors(DressingModel.loadSkinColorByDressingID(dressing.getid()));
                dressingList.add(dressing);
            } while (data.moveToNext());
        }

        Log.d(BeautyApp.TAG, "Retrieved Free Dressing : " + dressingList.size());
        mDressAdapter.setNewData(dressingList);
        mdressingList=dressingList;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(BeautyAppConstant.DRESSINGS_FREELIST_LOADER, null, this);
    }

    @OnClick(R.id.fab)
    public void tabsearch(){
       // Toast.makeText(BeautyApp.getContext(), "Fab search", Toast.LENGTH_SHORT).show();
        llserach.setVisibility(View.VISIBLE);
        ivsearch.setVisibility(View.VISIBLE);
        etsearch.setVisibility(View.VISIBLE);
        tpsearch.setVisibility(View.VISIBLE);
        fabsearch.setVisibility(View.INVISIBLE);
       // rvdress.setVisibility(View.INVISIBLE);

    }


    @OnClick(R.id.iv_search)
    public void OnSearch()
    {
        llserach.setVisibility(View.INVISIBLE);
        ivsearch.setVisibility(View.INVISIBLE);
        etsearch.setVisibility(View.INVISIBLE);
        llserach.setVisibility(View.INVISIBLE);
       // rvdress.setVisibility(View.VISIBLE);
        fabsearch.setVisibility(View.VISIBLE);
        List<DressingVO>  dressingList = new ArrayList<>();
        Boolean norecord=true;
        if(TextUtils.equals(etsearch.getText(),""))
        {

            Toast.makeText(BeautyApp.getContext(), R.string.price_msg, Toast.LENGTH_SHORT).show();
            mDressAdapter=new DressingAdapter(mdressingList,controllerDressing);
            rvdress.setAdapter(mDressAdapter);
        }
        else {
            for(DressingVO dressingVO:mdressingList) {
                if(TextUtils.equals(dressingVO.getprice(),etsearch.getText()) )
                {
                    DressingVO dressing=new DressingVO();
                    dressing.setImg_url(dressingVO.getimgurl());
                    dressing.setSkincolors(dressingVO.getSkincolors());
                    dressing.setHairstyles(dressingVO.getHairstyles());
                    dressing.setBodyshapes(dressingVO.getBodyshapes());
                    dressing.setSkintypes(dressingVO.getSkintypes());
                    dressing.setDescription(dressingVO.getDescription());
                    dressing.setShopname(dressingVO.getShopname());
                    dressing.setShopdirection(dressingVO.getShopdirection());
                    dressing.setPrice(dressingVO.getprice());
                    dressingList.add(dressing);
                    mDressAdapter=new DressingAdapter(dressingList,controllerDressing);
                    rvdress.setAdapter(mDressAdapter);
                    norecord=true;
                }
                else
                {
                    norecord=false;
                }
            }
        }
        if(norecord==false)
        {
            //Toast.makeText(getContext(),R.string.no_record,Toast.LENGTH_SHORT).show();
        }

    }


}
