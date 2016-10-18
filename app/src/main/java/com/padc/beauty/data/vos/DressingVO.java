package com.padc.beauty.data.vos;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.beauty.data.persistence.BeautyContract;

/**
 * Created by windows on 9/22/2016.
 */
public class DressingVO {
    @SerializedName("dressing-id")
    private int id;

    @SerializedName("dressing-type")
    private String dressingtype;

    @SerializedName("image")
    private String img_url;

    @SerializedName("description")
    private String description;

    @SerializedName("estimate-price")
    private String price;

    @SerializedName("rating")
    private String rating;

    @SerializedName("for-skin-colors")
    private String[] skincolors;

    @SerializedName("for-body-shapes")
    private String[] bodyshapes;

    @SerializedName("for-hair-styles")
    private String[] hairstyles;

    @SerializedName("for-skin-types")
    private String[] skintypes;

    @SerializedName("shop-id")
    private long shopid;

    @SerializedName("shop-name")
    private String shopname;

    @SerializedName("direction-to-shop")
    private String shopdirection;

    @SerializedName("promotion")
    private String promotion;

    public int getid(){return id;}

    public String getdresstype(){return dressingtype;}

    public String getimgurl(){return img_url;}

    public String getDescription(){return description;}

    public String getprice(){return price;}

    public String getrating(){return rating;}

    public String[] getSkincolors(){
        return skincolors;
    }

    public String[] getSkintypes(){
        return skintypes;
    }

    public String[] getHairstyles(){
        return hairstyles;
    }

    public String[] getBodyshapes(){
        return bodyshapes;
    }

    public long getshopid(){return shopid;}

    public String getShopname(){return shopname;}

    public String getShopdirection(){return shopdirection;}

    public String getPromotion(){return promotion;}

    public void setId(int id) {
        this.id = id;
    }

    public void setDressingtype(String dressingtype) {
        this.dressingtype = dressingtype;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setSkincolors(String[] skincolors) {
        this.skincolors = skincolors;
    }

    public void setBodyshapes(String[] bodyshapes) {
        this.bodyshapes = bodyshapes;
    }

    public void setHairstyles(String[] hairstyles) {
        this.hairstyles = hairstyles;
    }

    public void setSkintypes(String[] skintypes) {
        this.skintypes = skintypes;
    }

    public void setShopid(long shopid) {
        this.shopid = shopid;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public void setShopdirection(String shopdirection) {
        this.shopdirection = shopdirection;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public  ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(BeautyContract.DressingEntry.COLUMN_DRESSINGID,id);
        cv.put(BeautyContract.DressingEntry.COLUMN_TYPE,dressingtype);
        cv.put(BeautyContract.DressingEntry.COLUMN_IMAGE, img_url);
        cv.put(BeautyContract.DressingEntry.COLUMN_DESC, description);
        cv.put(BeautyContract.DressingEntry.COLUMN_PRICE, price);
        cv.put(BeautyContract.DressingEntry.COLUMN_RATING, rating);
        cv.put(BeautyContract.DressingEntry.COLUMN_SHOPID, shopid);
        cv.put(BeautyContract.DressingEntry.COLUMN_SHOPNAME, shopname);
        cv.put(BeautyContract.DressingEntry.COLUMN_DIRECTIONTOSHOP, shopdirection);
        cv.put(BeautyContract.DressingEntry.COLUMN_PROMOTION, promotion);
        return cv;
    }

    public static DressingVO parseFromCursor(Cursor data) {
        DressingVO dressing = new DressingVO();
        dressing.id = data.getInt(data.getColumnIndex(BeautyContract.DressingEntry.COLUMN_DRESSINGID));
        dressing.dressingtype = data.getString(data.getColumnIndex(BeautyContract.DressingEntry.COLUMN_TYPE));
        dressing.img_url = data.getString(data.getColumnIndex(BeautyContract.DressingEntry.COLUMN_IMAGE));
        dressing.description = data.getString(data.getColumnIndex(BeautyContract.DressingEntry.COLUMN_DESC));
        dressing.price = data.getString(data.getColumnIndex(BeautyContract.DressingEntry.COLUMN_PRICE));
        dressing.rating = data.getString(data.getColumnIndex(BeautyContract.DressingEntry.COLUMN_RATING));
        dressing.shopid = data.getLong(data.getColumnIndex(BeautyContract.DressingEntry.COLUMN_SHOPID));
        dressing.shopname = data.getString(data.getColumnIndex(BeautyContract.DressingEntry.COLUMN_SHOPNAME));
        dressing.shopdirection = data.getString(data.getColumnIndex(BeautyContract.DressingEntry.COLUMN_DIRECTIONTOSHOP));
        dressing.promotion = data.getString(data.getColumnIndex(BeautyContract.DressingEntry.COLUMN_PROMOTION));

        return dressing;
    }
}
