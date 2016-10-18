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
public class ServiceVO {
    @SerializedName("service-id")
    private long serviceid;

    @SerializedName("service-title")
    private String service_title;

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String description;
    private long saloonid;

    public long getserviceid(){return serviceid;}

    public String getservicetitle(){return service_title;}

    public String getimgurl(){return image;}

    public String getdescription(){return description;}

    public void setImage(String image) {
        this.image = image;
    }

    static void saveServices(long saloonid, List<ServiceVO> available_services){
        Context context = BeautyApp.getContext();
        ContentValues[] servicesCVs = new ContentValues[available_services.size()];
        for (int index = 0; index < available_services.size(); index++) {
            ServiceVO services = available_services.get(index);
            servicesCVs[index] = services.parseToContentValues(saloonid);
        }
        int insertedCount = context.getContentResolver().bulkInsert(BeautyContract.SalonServicesEntry.CONTENT_URI, servicesCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into services table : " + insertedCount);
    }
    private ContentValues parseToContentValues(long saloonid) {
        ContentValues cv = new ContentValues();
        cv.put(BeautyContract.SalonServicesEntry.COLUMN_SERVICE_ID, serviceid);
        cv.put(BeautyContract.SalonServicesEntry.COLUMN_SALON_ID, saloonid);
        cv.put(BeautyContract.SalonServicesEntry.COLUMN_SERVICE_TITLE, service_title);
        cv.put(BeautyContract.SalonServicesEntry.COLUMN_IMAGE, image);
        cv.put(BeautyContract.SalonServicesEntry.COLUMN_DESCRIPTION, description);
        return cv;
    }

    public static ServiceVO parseFromCursor(Cursor data) {
        ServiceVO services = new ServiceVO();
        services.serviceid = data.getLong(data.getColumnIndex(BeautyContract.SalonServicesEntry.COLUMN_SERVICE_ID));
        services.saloonid = data.getLong(data.getColumnIndex(BeautyContract.SalonServicesEntry.COLUMN_SALON_ID));
        services.service_title = data.getString(data.getColumnIndex(BeautyContract.SalonServicesEntry.COLUMN_SERVICE_TITLE));
        services.image = data.getString(data.getColumnIndex(BeautyContract.SalonServicesEntry.COLUMN_IMAGE));
        services.description = data.getString(data.getColumnIndex(BeautyContract.SalonServicesEntry.COLUMN_DESCRIPTION));
        return services;
    }
}
