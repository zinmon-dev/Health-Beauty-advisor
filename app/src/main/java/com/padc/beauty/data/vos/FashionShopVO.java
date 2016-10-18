package com.padc.beauty.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.beauty.BeautyApp;
import com.padc.beauty.data.persistence.BeautyContract;

import java.util.List;

/**
 * Created by windows on 9/20/2016.
 */
public class FashionShopVO {
    @SerializedName("shop-id")
    private long shopid;

    @SerializedName("shop-name")
    private String shop_name;

    @SerializedName("direction-to-shop")
    private String direction_to_shop;

    @SerializedName("full-address")
    private String full_address;

    @SerializedName("photos")
    private String photo;

    public long getshopid(){return shopid;}

    public String getshopname(){return shop_name;}

    public String getdirectiontoshop(){return direction_to_shop;}

    public String getfulladdr(){return full_address;}

    public String getPhoto(){return photo;}

    public static void saveFashionshops(List<FashionShopVO> fashionshoplist) {
        Context context = BeautyApp.getContext();
        ContentValues[] fashionShopsCVs = new ContentValues[fashionshoplist.size()];
        for (int index = 0; index < fashionshoplist.size(); index++) {
            FashionShopVO attraction = fashionshoplist.get(index);
            fashionShopsCVs[index] = attraction.parseToContentValues();

        }

        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(BeautyContract.FashionshopEntry.CONTENT_URI, fashionShopsCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into fashion table : " + insertedCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(BeautyContract.FashionshopEntry.COLUMN_SHOP_ID, shopid);
        cv.put(BeautyContract.FashionshopEntry.COLUMN_SHOP_NAME, shop_name);
        cv.put(BeautyContract.FashionshopEntry.COLUMN_DIRECTION_TO_SHOP, direction_to_shop);
        cv.put(BeautyContract.FashionshopEntry.COLUMN_ADDRESS, full_address);
        cv.put(BeautyContract.FashionshopEntry.COLUMN_PHOTO, photo);
        return cv;
    }

    public static FashionShopVO parseFromCursor(Cursor data) {
        FashionShopVO fashionshop = new FashionShopVO();
        fashionshop.shopid = data.getLong(data.getColumnIndex(BeautyContract.FashionshopEntry.COLUMN_SHOP_ID));
        fashionshop.shop_name = data.getString(data.getColumnIndex(BeautyContract.FashionshopEntry.COLUMN_SHOP_NAME));
        fashionshop.direction_to_shop = data.getString(data.getColumnIndex(BeautyContract.FashionshopEntry.COLUMN_DIRECTION_TO_SHOP));
        fashionshop.full_address = data.getString(data.getColumnIndex(BeautyContract.FashionshopEntry.COLUMN_ADDRESS));
        fashionshop.photo = data.getString(data.getColumnIndex(BeautyContract.FashionshopEntry.COLUMN_PHOTO));
        return fashionshop;
    }
}
