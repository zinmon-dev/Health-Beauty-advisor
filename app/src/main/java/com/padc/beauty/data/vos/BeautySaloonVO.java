package com.padc.beauty.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.padc.beauty.BeautyApp;
import com.padc.beauty.data.persistence.BeautyContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windows on 9/20/2016.
 */
public class BeautySaloonVO {

    @SerializedName("saloon-id")
    private long saloonid;

    @SerializedName("saloon-name")
    private String saloon_name;

    @SerializedName("direction-to-saloon")
    private String direction_to_saloon;

    @SerializedName("full-address")
    private String full_address;

    @SerializedName("photos")
    private String photo;

    @SerializedName("open daily")
    private String opendaily;

    @SerializedName("available-services")
    private List<ServiceVO> available_services;

    public long getsaloonid(){return saloonid;}

    public String getsaloonname(){return saloon_name;}

    public String getdirectiontosaloon(){return direction_to_saloon;}

    public String getfulladdr(){return full_address;}

    public String getPhoto(){return photo;}

    public String getOpendaily(){return opendaily;}

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public List<ServiceVO> getAvailable_services() {
        return available_services;
    }

    public static void saveBeautysalons(List<BeautySaloonVO> beautysalonList) {
        Context context = BeautyApp.getContext();
        ContentValues[] beautySalonsCVs = new ContentValues[beautysalonList.size()];
        for (int index = 0; index < beautysalonList.size(); index++) {
            BeautySaloonVO beauytsalon = beautysalonList.get(index);
            beautySalonsCVs[index] = beauytsalon.parseToContentValues();
            //Bulk insert into attraction_images.
            ServiceVO.saveServices(beautysalonList.get(index).getsaloonid(),beautysalonList.get(index).getAvailable_services());
        }

        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(BeautyContract.BeautySalonEntry.CONTENT_URI, beautySalonsCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into beauty salon table : " + insertedCount);
    }



    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(BeautyContract.BeautySalonEntry.COLUMN_ID, saloonid);
        cv.put(BeautyContract.BeautySalonEntry.COLUMN_SALON_NAME, saloon_name);
        cv.put(BeautyContract.BeautySalonEntry.COLUMN_DIRECTION_TO_SALON, direction_to_saloon);
        cv.put(BeautyContract.BeautySalonEntry.COLUMN_FULL_ADDRESS, full_address);
        cv.put(BeautyContract.BeautySalonEntry.COLUMN_PHOTO, photo);
        cv.put(BeautyContract.BeautySalonEntry.COLUMN_OPEN, opendaily);
        return cv;
    }

    public static BeautySaloonVO parseFromCursor(Cursor data) {
        BeautySaloonVO beautysalon = new BeautySaloonVO();
        beautysalon.saloonid = data.getLong(data.getColumnIndex(BeautyContract.BeautySalonEntry.COLUMN_ID));
        beautysalon.saloon_name = data.getString(data.getColumnIndex(BeautyContract.BeautySalonEntry.COLUMN_SALON_NAME));
        beautysalon.direction_to_saloon = data.getString(data.getColumnIndex(BeautyContract.BeautySalonEntry.COLUMN_DIRECTION_TO_SALON));
        beautysalon.full_address = data.getString(data.getColumnIndex(BeautyContract.BeautySalonEntry.COLUMN_FULL_ADDRESS));
        beautysalon.photo = data.getString(data.getColumnIndex(BeautyContract.BeautySalonEntry.COLUMN_PHOTO));
        beautysalon.opendaily = data.getString(data.getColumnIndex(BeautyContract.BeautySalonEntry.COLUMN_OPEN));
        return beautysalon;
    }


}
