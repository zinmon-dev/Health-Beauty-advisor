package com.padc.beauty.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aung on 6/24/16.
 */
public class PersonalityDetailVO {
    @SerializedName("personality_id")
    private long tipid;

    @SerializedName("detail")
    private List<PerDetailVO> persondtlVO;

    public PersonalityDetailVO(Long Tipid,List<PerDetailVO> PersondtlVO) {
        this.tipid=Tipid;
        this.persondtlVO=PersondtlVO;
    }

    public long getTipid() {
        return tipid;
    }

    public List<PerDetailVO> getPersondtlVO() {
        return persondtlVO;
    }
}
