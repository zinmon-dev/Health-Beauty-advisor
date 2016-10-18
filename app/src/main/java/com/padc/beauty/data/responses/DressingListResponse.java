package com.padc.beauty.data.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.beauty.data.vos.DressingVO;

import java.util.ArrayList;

/**
 * Created by windows on 9/22/2016.
 */
public class DressingListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("dressings")
    private ArrayList<DressingVO> dressingList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<DressingVO> getDressingList() {
        return dressingList;
    }
}
