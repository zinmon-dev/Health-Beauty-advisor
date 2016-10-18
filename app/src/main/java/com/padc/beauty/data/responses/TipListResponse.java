package com.padc.beauty.data.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.FashionShopVO;
import com.padc.beauty.data.vos.TipVO;

import java.util.ArrayList;

/**
 * Created by windows on 9/24/2016.
 */
public class TipListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("tips")
    private ArrayList<TipVO> tipList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<TipVO> getTipList() {
        return tipList;
    }

}
