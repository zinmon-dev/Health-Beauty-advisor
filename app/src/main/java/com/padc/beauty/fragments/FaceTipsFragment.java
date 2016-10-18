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
 * Created by windows on 9/5/2016.
 */
public class FaceTipsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>  {

//    @BindView(R.id.sp_tip_list)
//    Spinner sptiplist;

    @BindView(R.id.rv_facetype)
    RecyclerView rvfacetype;

    private FaceTipAdapter mFaceTipListAdapter;
    private AllTipListAdapter mTipListAdapter;
    private List<TipVO> mtipList;
    private  int gridColumnSpanCount;

    public static FaceTipsFragment newInstance(){
        FaceTipsFragment faceTipsFragment=new FaceTipsFragment();

        return faceTipsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] tipListArray = getResources().getStringArray(R.array.face_tip_list);
        List<String> tipList = new ArrayList<>(Arrays.asList(tipListArray));

        mFaceTipListAdapter = new FaceTipAdapter(tipList);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_face_tips, container, false);
        ButterKnife.bind(this, rootView);

//        sptiplist.setAdapter(mFaceTipListAdapter);
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
//                    String[] facetypes=tipVO.getFacetypes();
//                    for(int ind=0;ind<facetypes.length;ind++){
//                        String facetype=facetypes[ind]+" Face";
//                        if(TextUtils.equals(facetype,spinnertext) )
//                        {
//                            TipVO tip=new TipVO();
//                            tip.setDescription(tipVO.getDescription());
//                            tip.setImg_url(tipVO.getImg_url());
//                            tip.setTitle(tipVO.getTitle());
//                            tipList.add(tip);
//                            mTipListAdapter = new AllTipListAdapter(tipList);
//                            rvfacetype.setAdapter(mTipListAdapter);
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
        rvfacetype.setAdapter(mTipListAdapter);

        gridColumnSpanCount = getResources().getInteger(R.integer.tip_list_grid);
        rvfacetype.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));


        return rootView;
    }
//
//    @OnItemSelected(R.id.sp_tip_list)
//    public void OnSelectedSpinner(){
//        String spinnertext=sptiplist.getSelectedItem().toString();
//        Boolean norecord=true;
//        //sptiplist.setAdapter
//        // tvskintiptitle.setText(sptiplist.getSelectedItem().toString());
//        String facetype=BeautyApp.getContext().getString(R.string.allfacetype).toString();
//        if(TextUtils.equals(spinnertext,facetype)){
//           // List<TipVO> tipList = TipModel.getInstance().getmTipList();
//            //mTipListAdapter = new AllTipListAdapter(tipList);
//            //mTipListAdapter.setNewData(tipList);
//            //rvfacetype.setAdapter(mTipListAdapter);
//            //rvfacetype.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
//        }
//        else
//        {
//            List<TipVO>  tipList = new ArrayList<>();
//            for(TipVO tipVO:mtipList) {
//                String[] facetypes=tipVO.getFacetypes();
//                for(int i=0;i<facetypes.length;i++){
//                    String skincolor=facetypes[i]+" Face";
//                    if(TextUtils.equals(skincolor,spinnertext) )
//                    {
//                        TipVO tip=new TipVO();
//                        tip.setDescription(tipVO.getDescription());
//                        tip.setImg_url(tipVO.getImg_url());
//                        tip.setTitle(tipVO.getTitle());
//                        tipList.add(tip);
//                        mTipListAdapter = new AllTipListAdapter(tipList);
//                        rvfacetype.setAdapter(mTipListAdapter);
//                        //Toast.makeText(BeautyApp.getContext(), "Equal"+skincolor, Toast.LENGTH_SHORT).show();
//                        norecord=true;
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
//
//
//                Toast.makeText(getContext(),R.string.no_record,Toast.LENGTH_SHORT).show();
//
//
//            }
//        }
//
//        // Toast.makeText(getContext(),"Spinner selected Data"+spinnertext,Toast.LENGTH_SHORT).show();
//    }

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
        String selectionbyid =BeautyContract.TipEntry.COLUMN_CATEGORY + " IN( " + " ?  "+ "," +" ? "+")";
//        String selectionbyid =BeautyContract.TipEntry.COLUMN_CATEGORY + " = ? "+ " AND " + BeautyContract.TipEntry.COLUMN_CATEGORY +" =? ";
//        String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
//                + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
//                + Contacts.DISPLAY_NAME + " != '' ))";
        return new CursorLoader(getContext(),
                BeautyContract.TipEntry.CONTENT_URI,
                null,
                selectionbyid,
                new String[]{"face-related","hair-related"},
                BeautyContract.TipEntry.COLUMN_TIPID + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<TipVO> tipList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                TipVO tip = TipVO.parseFromCursor(data);
                //tip.setImages(AttractionVO.loadAttractionImagesByTitle(attraction.getTitle()));
                tip.setSkincolors(TipModel.loadSkinColorByTipID(tip.getTipid()));
                tip.setSkintypes(TipModel.loadSkinTypeByTipID(tip.getTipid()));
                tip.setBodyshapes(TipModel.loadBodyShapeByTipID(tip.getTipid()));
                tip.setFacetypes(TipModel.loadFaceTypeByTipID(tip.getTipid()));
                tipList.add(tip);
            } while (data.moveToNext());
        }

        Log.d(BeautyApp.TAG, "Retrieved Face Tips : " + tipList.size());
        mTipListAdapter.setNewData(tipList);
        mtipList=tipList;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(BeautyAppConstant.FACETIPS_LIST_LOADER, null, this);
    }
}