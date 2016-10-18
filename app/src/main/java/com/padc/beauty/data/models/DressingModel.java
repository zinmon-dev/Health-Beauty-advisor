package com.padc.beauty.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.util.LongSparseArray;

import com.google.gson.Gson;
import com.padc.beauty.BeautyApp;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.events.DataEvent;
import com.padc.beauty.utils.BeautyAppConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by windows on 9/22/2016.
 */
public class DressingModel extends BaseModel {
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static DressingModel objInstance;
    private List<DressingVO> mDressingList;

    private DressingModel() {
        super();
        mDressingList = new ArrayList<>();
        dataAgent.loadDressings();
    }

    public static DressingModel getInstance() {
        if (objInstance == null) {
            objInstance = new DressingModel();
        }
        return objInstance;
    }

    public  void loadDressings()
    {
        dataAgent.loadDressings();
    }

    public List<DressingVO> getmDressingList() {

        return mDressingList;
    }

    public DressingVO getDressingbyType(String dressingtype) {
        for (DressingVO dressing : mDressingList) {
            if (dressing.getdresstype() == dressingtype)
                return dressing;
        }

        return null;
    }

    public DressingVO getDressingbyId(int dressingid) {
        for (DressingVO dressing : mDressingList) {
            if (dressing.getid()==dressingid)
                return dressing;
        }

        return null;
    }

    public void notifyDressingsLoaded(List<DressingVO> dressingList) {

        mDressingList = dressingList;
        saveDressings(mDressingList);
        //broadcastMealLoadedWithEventBus();

    }

    public void notifyErrorInLoadingFoodItems(String message) {

    }

    private void broadcastMealLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.DressingDataLoadedEvent("extra-in-broadcast", mDressingList));
    }

    public static void saveDressings(List<DressingVO> dressingList) {
        Context context = BeautyApp.getContext();
        ContentValues[] dressingCVs = new ContentValues[dressingList.size()];
        Log.d(BeautyApp.TAG,"dressinglistsize"+dressingList.size());
        for (int index = 0; index < dressingList.size(); index++) {

            DressingVO dressing = dressingList.get(index);
            dressingCVs[index] = dressing.parseToContentValues();

            //Bulk insert into attraction_images.
            saveDressingSkinType(dressing.getid(), dressing.getSkintypes());
            saveDressingBodyShape(dressing.getid(), dressing.getBodyshapes());
            saveDressingHairStyle(dressing.getid(), dressing.getHairstyles());
            saveDressingSkinColor(dressing.getid(), dressing.getSkintypes());

        }
        Log.d(BeautyApp.TAG,"tipcvs"+dressingCVs.length);
        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(BeautyContract.DressingEntry.CONTENT_URI, dressingCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into Dressing table : " + insertedCount);
    }

    private static void saveDressingSkinType(Integer dressingid, String[] skintypes) {
        ContentValues[] dressingskintypeCVs = new ContentValues[skintypes.length];
        for (int index = 0; index < skintypes.length; index++) {
            String skintype = skintypes[index];

            ContentValues cv = new ContentValues();
            cv.put(BeautyContract.DressingSkinTypeEntry.COLUMN_DRESSINGID, dressingid);
            cv.put(BeautyContract.DressingSkinTypeEntry.COLUMN_SKINTYPE,skintype+" skin type");

            dressingskintypeCVs[index] = cv;
        }

        Context context = BeautyApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(BeautyContract.DressingSkinTypeEntry.CONTENT_URI, dressingskintypeCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into dressing skintype table : " + insertCount);
    }

    private static void saveDressingSkinColor(Integer dressingid, String[] skincolors) {
        ContentValues[] dressingsskincolorCVs = new ContentValues[skincolors.length];
        for (int index = 0; index < skincolors.length; index++) {
            String skincolor = skincolors[index];

            ContentValues cv = new ContentValues();
            cv.put(BeautyContract.DressingSkinColorEntry.COLUMN_DRESSINGID, dressingid);
            cv.put(BeautyContract.DressingSkinColorEntry.COLUMN_SKINCOLOR,skincolor+" skin tone");

            dressingsskincolorCVs[index] = cv;
        }

        Context context = BeautyApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(BeautyContract.DressingSkinColorEntry.CONTENT_URI, dressingsskincolorCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into dressing skincolor table : " + insertCount);
    }

    private static void saveDressingBodyShape(Integer dressingid, String[] bodyshapes) {
        ContentValues[] dressingsbodyshapeCVs = new ContentValues[bodyshapes.length];
        for (int index = 0; index < bodyshapes.length; index++) {
            String bodyshape = bodyshapes[index];

            ContentValues cv = new ContentValues();
            cv.put(BeautyContract.DressingBodyShapeEntry.COLUMN_DRESSINGID, dressingid);
            cv.put(BeautyContract.DressingBodyShapeEntry.COLUMN_BODYSHAPE,bodyshape+" body type");

            dressingsbodyshapeCVs[index] = cv;
        }

        Context context = BeautyApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(BeautyContract.DressingBodyShapeEntry.CONTENT_URI, dressingsbodyshapeCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into dressing bodyshape table : " + insertCount);
    }

    private static void saveDressingHairStyle(Integer dressingid, String[] hairstyles) {
        ContentValues[] tipshairstyleCVs = new ContentValues[hairstyles.length];
        for (int index = 0; index < hairstyles.length; index++) {
            String hairstyle = hairstyles[index];

            ContentValues cv = new ContentValues();
            cv.put(BeautyContract.DressingHairStyleEntry.COLUMN_DRESSINGID, dressingid);
            cv.put(BeautyContract.DressingHairStyleEntry.COLUMN_HAIRSTYLE,hairstyle+" hair style");

            tipshairstyleCVs[index] = cv;
        }

        Context context = BeautyApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(BeautyContract.DressingHairStyleEntry.CONTENT_URI, tipshairstyleCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into Dressing hairstyle table : " + insertCount);
    }

    public static String[] loadHairStyleByDressingID(long Dressingid) {
        Context context = BeautyApp.getContext();
        ArrayList<String> hairstyles = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(BeautyContract.DressingHairStyleEntry.buildHairStyleUriWithDressingID(Dressingid),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                hairstyles.add(cursor.getString(cursor.getColumnIndex(BeautyContract.DressingHairStyleEntry.COLUMN_HAIRSTYLE)));
            } while (cursor.moveToNext());
        }

        String[] hairstyleArray = new String[hairstyles.size()];
        hairstyles.toArray(hairstyleArray);
        return hairstyleArray;
    }

    public static String[] loadSkinTypeByDressingID(long Dressingid) {
        Context context = BeautyApp.getContext();
        ArrayList<String> skintypes = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(BeautyContract.DressingSkinTypeEntry.buildSkinTypeUriWithDressingID(Dressingid),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                skintypes.add(cursor.getString(cursor.getColumnIndex(BeautyContract.DressingSkinTypeEntry.COLUMN_SKINTYPE)));
            } while (cursor.moveToNext());
        }

        String[] skintypeArray = new String[skintypes.size()];
        skintypes.toArray(skintypeArray);
        return skintypeArray;
    }

    public static String[] loadSkinColorByDressingID(long Dressingid) {
        Context context = BeautyApp.getContext();
        ArrayList<String> skincolors = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(BeautyContract.DressingSkinColorEntry.buildSkinColorUriWithDressingID(Dressingid),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                skincolors.add(cursor.getString(cursor.getColumnIndex(BeautyContract.DressingSkinColorEntry.COLUMN_SKINCOLOR)));
            } while (cursor.moveToNext());
        }

        String[] skincolorArray = new String[skincolors.size()];
        skincolors.toArray(skincolorArray);
        return skincolorArray;
    }

    public static String[] loadBodyShapeByDressingID(long Dressingid) {
        Context context = BeautyApp.getContext();
        ArrayList<String> bodyshapes = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(BeautyContract.DressingBodyShapeEntry.buildBodyShapeUriWithDressingID(Dressingid),
                null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                bodyshapes.add(cursor.getString(cursor.getColumnIndex(BeautyContract.DressingBodyShapeEntry.COLUMN_BODYSHAPE)));
            } while (cursor.moveToNext());
        }

        String[] bodyshapeArray = new String[bodyshapes.size()];
        bodyshapes.toArray(bodyshapeArray);
        return bodyshapeArray;
    }

    public void addFavorite(DressingVO dressing) {

        BookmarkVO bookmarkvo=new BookmarkVO(dressing.getid(),dressing.getShopname(),dressing.getimgurl(),dressing.getdresstype());
        List<BookmarkVO> currentbookmarklist=new ArrayList<>();
        currentbookmarklist.add(bookmarkvo);
        BookMarkModel.getInstance().notifyBookMarkLoaded(currentbookmarklist);

    }


}
