package com.padc.beauty.data.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.FashionShopVO;


import java.util.ArrayList;

/**
 * Created by windows on 9/20/2016.
 */
public class FashionShopandBeautySaloonListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("fashion-shops")
    private ArrayList<FashionShopVO> fashionshopList;

    @SerializedName("beauty-saloons")
    private ArrayList<BeautySaloonVO> beautySaloonList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<FashionShopVO> getFashionshopList() {
        return fashionshopList;
    }

    public ArrayList<BeautySaloonVO> getBeautySaloonList() {
        return beautySaloonList;
    }


}
