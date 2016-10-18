package com.padc.beauty.data.agents.retrofit;

import com.padc.beauty.data.agents.BeautyDataAgent;
import com.padc.beauty.data.models.DressingModel;
import com.padc.beauty.data.models.FashionShopandBeautySaloonModel;
import com.padc.beauty.data.models.TipModel;
import com.padc.beauty.data.responses.DressingListResponse;
import com.padc.beauty.data.responses.FashionShopandBeautySaloonListResponse;
import com.padc.beauty.data.responses.TipListResponse;
import com.padc.beauty.utils.BeautyAppConstant;
import com.padc.beauty.utils.CommonInstances;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aung on 7/9/16.
 */



public class RetrofitDataAgent implements BeautyDataAgent {
    private static RetrofitDataAgent objInstance;

    private final BeautyApi theApi;
    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BeautyAppConstant.FASHIONSHOP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(BeautyApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadDressings() {
        Call<DressingListResponse> loadDressingCall = theApi.loadDressings(BeautyAppConstant.ACCESS_TOKEN);
        loadDressingCall.enqueue(new Callback<DressingListResponse>() {
            @Override
            public void onResponse(Call<DressingListResponse> call, Response<DressingListResponse> response) {
                DressingListResponse dressingListResponse=response.body();
                if(dressingListResponse==null)
                {
                    DressingModel.getInstance().notifyErrorInLoadingFoodItems(response.message());
                }
                else
                {
                    DressingModel.getInstance().notifyDressingsLoaded(dressingListResponse.getDressingList());
                }
            }

            @Override
            public void onFailure(Call<DressingListResponse> call, Throwable throwable) {

                DressingModel.getInstance().notifyErrorInLoadingFoodItems(throwable.getMessage());
            }
        });
    }

    @Override
    public void loadFashionshopandBeautysaloons() {
        Call<FashionShopandBeautySaloonListResponse> loadFashionshopandbeautysaloonCall = theApi.loadFashionshopandBeautysaloons(BeautyAppConstant.ACCESS_TOKEN);
        loadFashionshopandbeautysaloonCall.enqueue(new Callback<FashionShopandBeautySaloonListResponse>() {
            @Override
            public void onResponse(Call<FashionShopandBeautySaloonListResponse> call, Response<FashionShopandBeautySaloonListResponse> response) {
                FashionShopandBeautySaloonListResponse beautysaloonandfashionListResponse=response.body();
                if(beautysaloonandfashionListResponse==null)
                {
                    FashionShopandBeautySaloonModel.getInstance().notifyErrorInLoadingFashionshopItems(response.message());
                }
                else
                {
                    FashionShopandBeautySaloonModel.getInstance().notifyAttractionsLoaded(beautysaloonandfashionListResponse.getFashionshopList(),beautysaloonandfashionListResponse.getBeautySaloonList());

                }
            }

            public void onFailure(Call<FashionShopandBeautySaloonListResponse> call, Throwable throwable) {

                FashionShopandBeautySaloonModel.getInstance().notifyErrorInLoadingFashionshopItems(throwable.getMessage());
            }
        });
    }

    @Override
    public void loadTips() {
        Call<TipListResponse> loadTipsCall = theApi.loadTips(BeautyAppConstant.ACCESS_TOKEN);
        loadTipsCall.enqueue(new Callback<TipListResponse>() {
            @Override
            public void onResponse(Call<TipListResponse> call, Response<TipListResponse> response) {
                TipListResponse tipListResponse=response.body();
                if(tipListResponse==null)
                {
                    TipModel.getInstance().notifyErrorInLoadingTipItems(response.message());
                }
                else
                {
                    TipModel.getInstance().notifyTipssLoaded(tipListResponse.getTipList());
                }
            }

            @Override
            public void onFailure(Call<TipListResponse> call, Throwable throwable) {

                TipModel.getInstance().notifyErrorInLoadingTipItems(throwable.getMessage());
            }
        });
    }
}
