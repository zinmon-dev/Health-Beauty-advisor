package com.padc.beauty.data.models;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.events.DataEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by windows on 9/24/2016.
 */
public class TipModel extends BaseModel{
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static TipModel objInstance;
    private List<TipVO> mTipList;

    private TipModel() {
        super();
        mTipList = new ArrayList<>();
        dataAgent.loadTips();
    }

    public static TipModel getInstance() {
        if (objInstance == null) {
            objInstance = new TipModel();
        }
        return objInstance;
    }

    public  void loadTips()
    {
        dataAgent.loadTips();
    }

    public List<TipVO> getmTipList() {
        return mTipList;
    }

    public TipVO getTipsbyCategory(String tipcategory) {
        for (TipVO tip : mTipList) {
            if (tip.getTipcategory() == tipcategory)
                return tip;
        }

        return null;
    }

    public TipVO getDressingbyId(int tipid) {
        for (TipVO tip : mTipList) {
            if (tip.getTipid()==tipid)
                return tip;
        }

        return null;
    }
    public TipVO getTipsById(Long tipId) {
        Log.d(BeautyApp.TAG,"Reach tip condition");
        for (TipVO tip : mTipList) {
            if (tip.getTipid()== tipId) {
                return tip;
            }
        }
        return null;
    }

    public static String[] loadSkinTypeByTipID(long Tipid) {
        Context context = BeautyApp.getContext();
        ArrayList<String> skintypes = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(BeautyContract.TipSkinTypeEntry.buildSkinTypeUriWithTipID(Tipid),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                skintypes.add(cursor.getString(cursor.getColumnIndex(BeautyContract.TipSkinTypeEntry.COLUMN_SKINTYPE)));
            } while (cursor.moveToNext());
        }

        String[] skintypeArray = new String[skintypes.size()];
        skintypes.toArray(skintypeArray);
        return skintypeArray;
    }

    public static String[] loadSkinColorByTipID(long Tipid) {
        Context context = BeautyApp.getContext();
        ArrayList<String> skincolors = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(BeautyContract.TipSkinColorEntry.buildSkinColorUriWithTipID(Tipid),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                skincolors.add(cursor.getString(cursor.getColumnIndex(BeautyContract.TipSkinColorEntry.COLUMN_SKINCOLOR)));
            } while (cursor.moveToNext());
        }

        String[] skincolorArray = new String[skincolors.size()];
        skincolors.toArray(skincolorArray);
        return skincolorArray;
    }

    public static String[] loadBodyShapeByTipID(long Tipid) {
        Context context = BeautyApp.getContext();
        ArrayList<String> bodyshapes = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(BeautyContract.TipBodyShapeEntry.buildBodyShapeUriWithTipID(Tipid),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                bodyshapes.add(cursor.getString(cursor.getColumnIndex(BeautyContract.TipBodyShapeEntry.COLUMN_BODYSHAPE)));
            } while (cursor.moveToNext());
        }

        String[] bodyshapeArray = new String[bodyshapes.size()];
        bodyshapes.toArray(bodyshapeArray);
        return bodyshapeArray;
    }

    public static String[] loadFaceTypeByTipID(long Tipid) {
        Context context = BeautyApp.getContext();
        ArrayList<String> facetypes = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(BeautyContract.TipFaceTypeEntry.buildFaceTypeUriWithTipID(Tipid),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                facetypes.add(cursor.getString(cursor.getColumnIndex(BeautyContract.TipFaceTypeEntry.COLUMN_FACETYPE)));
            } while (cursor.moveToNext());
        }

        String[] facetypeArray = new String[facetypes.size()];
        facetypes.toArray(facetypeArray);
        return facetypeArray;
    }



    public void notifyTipssLoaded(List<TipVO> tipList) {

        mTipList = tipList;

        TipVO.saveTips(mTipList);
        // broadcastMealLoadedWithEventBus();

    }

    public void notifyErrorInLoadingTipItems(String message) {

    }


    private void broadcastMealLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.TipDataLoadedEvent("extra-in-broadcast", mTipList));
    }

    public void addFavorite(TipVO tip) {

        BookmarkVO bookmarkvo=new BookmarkVO(tip.getTipid(),tip.getTitle(),tip.getImg_url(),tip.getTipcategory());
        List<BookmarkVO> currentbookmarklist=new ArrayList<>();
        currentbookmarklist.add(bookmarkvo);
        BookMarkModel.getInstance().notifyBookMarkLoaded(currentbookmarklist);

    }

}