package com.padc.beauty.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.beauty.BeautyApp;
import com.padc.beauty.data.persistence.BeautyContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windows on 9/24/2016.
 */
public class TipVO {
    @SerializedName("tip-id")
    private long tipid;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String img_url;

    @SerializedName("description")
    private String description;

    @SerializedName("tip-category")
    private String tipcategory;

    @SerializedName("for-face-types")
    private String[] facetypes;

    @SerializedName("for-skin-colors")
    private String[] skincolors;

    @SerializedName("for-body-shapes")
    private String[] bodyshapes;

//    @SerializedName("for-hair-color")
//    private String[] hairstyles;

    @SerializedName("for-skin-types")
    private String[] skintypes;

    public long getTipid() {
        return tipid;
    }

    public String getTipcategory() {
        return tipcategory;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String[] getFacetypes() {
        return facetypes;
    }

    public String[] getSkincolors() {
        return skincolors;
    }

    public String[] getBodyshapes() {
        return bodyshapes;
    }

 //   public String[] getHairstyles() {
   //     return hairstyles;
   // }

    public void setTipid(long tipid) {
        this.tipid = tipid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTipcategory(String tipcategory) {
        this.tipcategory = tipcategory;
    }

    public void setFacetypes(String[] facetypes) {
        this.facetypes = facetypes;
    }

    public void setSkincolors(String[] skincolors) {
        this.skincolors = skincolors;
    }

    public void setBodyshapes(String[] bodyshapes) {
        this.bodyshapes = bodyshapes;
    }

    public void setSkintypes(String[] skintypes) {
        this.skintypes = skintypes;
    }

    public String[] getSkintypes() {
        return skintypes;
    }

    public static void saveTips(List<TipVO> tipList) {
        Context context = BeautyApp.getContext();
        ContentValues[] tipCVs = new ContentValues[tipList.size()];
        Log.d(BeautyApp.TAG,"tiplistsize"+tipList.size());
        for (int index = 0; index < tipList.size(); index++) {

            TipVO tip = tipList.get(index);
            tipCVs[index] = tip.parseToContentValues();

            //Bulk insert into attraction_images.
            TipVO.saveTipSkinColor(tip.getTipid(), tip.getSkincolors());
            TipVO.saveTipBodyShape(tip.getTipid(), tip.getBodyshapes());
          //  TipVO.saveTipHairColor(tip.getTipid(), tip.getHairstyles());
            TipVO.saveTipSkinType(tip.getTipid(), tip.getSkintypes());
            TipVO.saveTipFaceType(tip.getTipid(), tip.getFacetypes());
        }
        Log.d(BeautyApp.TAG,"tipcvs"+tipCVs.length);
        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(BeautyContract.TipEntry.CONTENT_URI, tipCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into Tips table : " + insertedCount);
    }

    private static void saveTipSkinType(Long tipid, String[] skintypes) {
        ContentValues[] tipskintypeCVs = new ContentValues[skintypes.length];
        for (int index = 0; index < skintypes.length; index++) {
            String skintype = skintypes[index];

            ContentValues cv = new ContentValues();
            cv.put(BeautyContract.TipSkinTypeEntry.COLUMN_TIPID, tipid);
            cv.put(BeautyContract.TipSkinTypeEntry.COLUMN_SKINTYPE,skintype);

            tipskintypeCVs[index] = cv;
        }

        Context context = BeautyApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(BeautyContract.TipSkinTypeEntry.CONTENT_URI, tipskintypeCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into skintype table : " + insertCount);
    }

    private static void saveTipSkinColor(Long tipid, String[] skincolors) {
        ContentValues[] tipsskincolorCVs = new ContentValues[skincolors.length];
        for (int index = 0; index < skincolors.length; index++) {
            String skincolor = skincolors[index];

            ContentValues cv = new ContentValues();
            cv.put(BeautyContract.TipSkinColorEntry.COLUMN_TIPID, tipid);
            cv.put(BeautyContract.TipSkinColorEntry.COLUMN_SKINCOLOR,skincolor);

            tipsskincolorCVs[index] = cv;
        }

        Context context = BeautyApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(BeautyContract.TipSkinColorEntry.CONTENT_URI, tipsskincolorCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into skincolor table : " + insertCount);
    }

    private static void saveTipBodyShape(Long tipid, String[] bodyshapes) {
        ContentValues[] tipsbodyshapeCVs = new ContentValues[bodyshapes.length];
        for (int index = 0; index < bodyshapes.length; index++) {
            String bodyshape = bodyshapes[index];

            ContentValues cv = new ContentValues();
            cv.put(BeautyContract.TipBodyShapeEntry.COLUMN_TIPID, tipid);
            cv.put(BeautyContract.TipBodyShapeEntry.COLUMN_BODYSHAPE,bodyshape);

            tipsbodyshapeCVs[index] = cv;
        }

        Context context = BeautyApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(BeautyContract.TipBodyShapeEntry.CONTENT_URI, tipsbodyshapeCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into bodyshape table : " + insertCount);
    }

    private static void saveTipHairColor(Long tipid, String[] haircolors) {
        ContentValues[] tipshaircolorCVs = new ContentValues[haircolors.length];
        for (int index = 0; index < haircolors.length; index++) {
            String haircolor = haircolors[index];

            ContentValues cv = new ContentValues();
            cv.put(BeautyContract.TipHairColorEntry.COLUMN_TIPID, tipid);
            cv.put(BeautyContract.TipHairColorEntry.COLUMN_HAIRCOLOR,haircolor);

            tipshaircolorCVs[index] = cv;
        }

        Context context = BeautyApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(BeautyContract.TipHairColorEntry.CONTENT_URI, tipshaircolorCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into haircolor table : " + insertCount);
    }

    private static void saveTipFaceType(Long tipid, String[] facetypes) {

        ContentValues[] tipsfacetypeCVs = new ContentValues[facetypes.length];
        for (int index = 0; index < facetypes.length; index++) {
            String facetype = facetypes[index];

            ContentValues cv = new ContentValues();
            cv.put(BeautyContract.TipFaceTypeEntry.COLUMN_TIPID, tipid);
            cv.put(BeautyContract.TipFaceTypeEntry.COLUMN_FACETYPE,facetype);

            tipsfacetypeCVs[index] = cv;
        }

        Context context = BeautyApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(BeautyContract.TipFaceTypeEntry.CONTENT_URI, tipsfacetypeCVs);
        Log.d(BeautyApp.TAG, "Bulk inserted into facetype table : " + insertCount);


    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(BeautyContract.TipEntry.COLUMN_TIPID, tipid);
        cv.put(BeautyContract.TipEntry.COLUMN_TITLE, title);
        cv.put(BeautyContract.TipEntry.COLUMN_IMAGE, img_url);
        cv.put(BeautyContract.TipEntry.COLUMN_DESC, description);
        cv.put(BeautyContract.TipEntry.COLUMN_CATEGORY, tipcategory);
        return cv;
    }

    public static TipVO parseFromCursor(Cursor data) {
        TipVO tip = new TipVO();
        tip.tipid=data.getLong(data.getColumnIndex(BeautyContract.TipEntry.COLUMN_TIPID));
        tip.tipcategory=data.getString(data.getColumnIndex(BeautyContract.TipEntry.COLUMN_CATEGORY));
        tip.title = data.getString(data.getColumnIndex(BeautyContract.TipEntry.COLUMN_TITLE));
        tip.description = data.getString(data.getColumnIndex(BeautyContract.TipEntry.COLUMN_DESC));
        tip.img_url = data.getString(data.getColumnIndex(BeautyContract.TipEntry.COLUMN_IMAGE));

        return tip;
    }


}
