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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.adapters.AllTipListAdapter;
import com.padc.beauty.adapters.FaceTipAdapter;
import com.padc.beauty.data.models.TipModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.events.DataEvent;
import com.padc.beauty.utils.BeautyAppConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import de.greenrobot.event.EventBus;

/**
 * Created by windows on 9/6/2016.
 =======
 import android.os.Bundle;
 import android.support.annotation.Nullable;
 import android.support.v4.app.Fragment;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;

 import com.padc.beauty.R;

 /**
 * Created by windows on 9/8/2016.

 */
public class SkinTipsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.rv_skintype)
    RecyclerView rvskintype;

//    @BindView(R.id.sp_tip_list)
//    Spinner sptiplist;

    private FaceTipAdapter mFaceTipListAdapter;
    private AllTipListAdapter mTipListAdapter;
    private List<TipVO> mtipList;

    public static SkinTipsFragment newInstance(){
        SkinTipsFragment skinTipsFragment=new SkinTipsFragment();
        return skinTipsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] tipListArray = getResources().getStringArray(R.array.Skin_Tone_tip_list);
        List<String> tipList = new ArrayList<>(Arrays.asList(tipListArray));

        mFaceTipListAdapter = new FaceTipAdapter(tipList);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_skintype_tips, container, false);
        ButterKnife.bind(this, rootView);
//       sptiplist.setAdapter(mFaceTipListAdapter);
//
//        sptiplist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String spinnertext=sptiplist.getSelectedItem().toString();
//                String norecord="true";
//                //sptiplist.setAdapter
//                // tvskintiptitle.setText(sptiplist.getSelectedItem().toString());
//                List<TipVO>  tipList = new ArrayList<>();
//                for(TipVO tipVO:mtipList) {
//                    String[] skincolors=tipVO.getSkincolors();
//                    for(int ind=0;ind<skincolors.length;ind++){
//                        String skincolor=skincolors[ind]+" Skin Tone";
//                        if(TextUtils.equals(skincolor,spinnertext) )
//                        {
//                            TipVO tip=new TipVO();
//                            tip.setDescription(tipVO.getDescription());
//                            tip.setImg_url(tipVO.getImg_url());
//                            tip.setTitle(tipVO.getTitle());
//                            tipList.add(tip);
//                            mTipListAdapter = new AllTipListAdapter(tipList);
//                            rvskintype.setAdapter(mTipListAdapter);
//                            norecord="true";
//                            //  Toast.makeText(BeautyApp.getContext(), "Equal"+skincolor, Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            norecord="false";
//                        }
//                    }
//
//                }
//                if(TextUtils.equals(norecord,"false"))
//                {
//                    Toast.makeText(getContext(),R.string.no_record,Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        List<TipVO> tipList = TipModel.getInstance().getmTipList();
        mTipListAdapter = new AllTipListAdapter(tipList);
        rvskintype.setAdapter(mTipListAdapter);

        int gridColumnSpanCount = getResources().getInteger(R.integer.tip_list_grid);
        rvskintype.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));


        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(BeautyAppConstant.SKINTIPS_LIST_LOADER, null, this);
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


//    @OnItemSelected(R.id.sp_tip_list)
//    public void OnSelectedSpinner(){
//        String spinnertext=sptiplist.getSelectedItem().toString();
//        //sptiplist.setAdapter
//       // tvskintiptitle.setText(sptiplist.getSelectedItem().toString());
//        Boolean norecord=true;
//
//            List<TipVO>  tipList = new ArrayList<>();
//            for(TipVO tipVO:mtipList) {
//                String[] skincolors=tipVO.getSkincolors();
//                for(int i=0;i<skincolors.length;i++){
//                    String skincolor=skincolors[i]+" Skin Tone";
//                    if(TextUtils.equals(skincolor,spinnertext) )
//                    {
//                        TipVO tip=new TipVO();
//                        tip.setDescription(tipVO.getDescription());
//                        tip.setImg_url(tipVO.getImg_url());
//                        tip.setTitle(tipVO.getTitle());
//                        tipList.add(tip);
//                        mTipListAdapter = new AllTipListAdapter(tipList);
//                        rvskintype.setAdapter(mTipListAdapter);
//                        norecord=true;
//                        //  Toast.makeText(BeautyApp.getContext(), "Equal"+skincolor, Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        norecord=false;
//                    }
//                }
//
//            }
//            if(norecord==false)
//            {
//                Toast.makeText(getContext(),R.string.no_record,Toast.LENGTH_SHORT).show();
//
//            }
//
//        }
//       // Toast.makeText(getContext(),"Spinner selected Data"+spinnertext,Toast.LENGTH_SHORT).show();
//    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                BeautyContract.TipEntry.CONTENT_URI,
                null,
                BeautyContract.TipEntry.COLUMN_CATEGORY + " = ?",
                new String[]{"skin-related"},
                BeautyContract.TipEntry.COLUMN_TIPID + " ASC");

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<TipVO> tipList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                TipVO tip = TipVO.parseFromCursor(data);
                tip.setSkincolors(TipModel.loadSkinColorByTipID(tip.getTipid()));
                tip.setSkintypes(TipModel.loadSkinTypeByTipID(tip.getTipid()));
                tip.setBodyshapes(TipModel.loadBodyShapeByTipID(tip.getTipid()));
                tip.setFacetypes(TipModel.loadFaceTypeByTipID(tip.getTipid()));
                tipList.add(tip);
            } while (data.moveToNext());
        }

        Log.d(BeautyApp.TAG, "Retrieved Skin Tips : " + tipList.size());
        mtipList=tipList;
        mTipListAdapter.setNewData(tipList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public void onEventMainThread(DataEvent.TipDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<TipVO> newTipList = event.getTipList();
        mTipListAdapter.setNewData(newTipList);
    }
}