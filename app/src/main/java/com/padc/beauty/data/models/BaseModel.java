package com.padc.beauty.data.models;

import com.padc.beauty.data.agents.BeautyDataAgent;
import com.padc.beauty.data.agents.retrofit.RetrofitDataAgent;

import de.greenrobot.event.EventBus;

/**
 * Created by Asus on 8/7/2016.
 */

public abstract class BaseModel {
    protected BeautyDataAgent dataAgent;

    public BaseModel(){
        initDataAgent();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    private void initDataAgent() {
        dataAgent = RetrofitDataAgent.getInstance();
    }

    public void onEventMainThread(Object obj) {

    }
}
