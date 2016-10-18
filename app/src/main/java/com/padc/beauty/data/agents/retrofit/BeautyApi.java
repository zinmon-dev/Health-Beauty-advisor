package com.padc.beauty.data.agents.retrofit;

import com.padc.beauty.data.responses.DressingListResponse;
import com.padc.beauty.data.responses.FashionShopandBeautySaloonListResponse;
import com.padc.beauty.data.responses.TipListResponse;
import com.padc.beauty.utils.BeautyAppConstant;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by windows on 9/22/2016.
 */
public interface BeautyApi {
    @FormUrlEncoded
    @POST(BeautyAppConstant.API_GET_DRESSING_LIST)
    Call<DressingListResponse> loadDressings(
            @Field(BeautyAppConstant.PARAM_ACCESS_TOKEN) String param);

    @FormUrlEncoded
    @POST(BeautyAppConstant.API_GET_FASHIONSHOPANDBEAUTY_LIST)
    Call<FashionShopandBeautySaloonListResponse> loadFashionshopandBeautysaloons(
            @Field(BeautyAppConstant.PARAM_ACCESS_TOKEN) String param);

    @FormUrlEncoded
    @POST(BeautyAppConstant.API_GET_TIP_LIST)
    Call<TipListResponse> loadTips(
            @Field(BeautyAppConstant.PARAM_ACCESS_TOKEN) String param);
}
