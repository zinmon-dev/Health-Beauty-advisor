package com.padc.beauty.data.models;

import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.FashionShopVO;
import com.padc.beauty.data.vos.PerDetailVO;
import com.padc.beauty.data.vos.PersonalityDetailVO;
import com.padc.beauty.data.vos.ServiceVO;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.events.DataEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by windows on 9/21/2016.
 */
public class FashionShopandBeautySaloonModel extends BaseModel {
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static FashionShopandBeautySaloonModel objInstance;


    private List<FashionShopVO> mFashionShopList;
    private List<BeautySaloonVO> mBeautySaloonList;
    private List<ServiceVO> mServiceList;



    private FashionShopandBeautySaloonModel() {
        super();
        mFashionShopList = new ArrayList<>();
        mBeautySaloonList = new ArrayList<>();
        mServiceList = new ArrayList<>();
        dataAgent.loadFashionshopandBeautysaloons();
    }

    public static FashionShopandBeautySaloonModel getInstance() {
        if (objInstance == null) {
            objInstance = new FashionShopandBeautySaloonModel();
        }
        return objInstance;
    }

    public  void loadFoods()
    {
        dataAgent.loadFashionshopandBeautysaloons();
    }

    public List<FashionShopVO> getFashionShopList() {
        return mFashionShopList;
    }
    public List<BeautySaloonVO> getBeautySaloonList() {
        return mBeautySaloonList;
    }
    public List<ServiceVO> getServiceList() {
        return mServiceList;
    }

    public FashionShopVO getFashionShopById(int fashionshopid) {
        for (FashionShopVO fashionshop : mFashionShopList) {
            if (fashionshop.getshopid()==fashionshopid)
                return fashionshop;
        }

        return null;
    }

    public BeautySaloonVO getBeautySaloonById(int beautysaloonid) {
        for (BeautySaloonVO beautysaloon : mBeautySaloonList) {
            if (beautysaloon.getsaloonid()==beautysaloonid)
                return beautysaloon;
        }

        return null;
    }

    public void notifyAttractionsLoaded(List<FashionShopVO> fashionshopList, List<BeautySaloonVO> beautysaloonList) {
        //Notify that the data is ready - using LocalBroadcast
        mFashionShopList = fashionshopList;
        mBeautySaloonList = beautysaloonList;
        mServiceList=mBeautySaloonList.get(0).getAvailable_services();

        FashionShopVO.saveFashionshops(mFashionShopList);
        BeautySaloonVO.saveBeautysalons(mBeautySaloonList);

        //broadcastMealLoadedWithEventBus();
        //broadcastAttractionLoadedWithLocalBroadcastManager();
    }

    public void notifyErrorInLoadingFashionshopItems(String message) {

    }

    private void broadcastMealLoadedWithEventBus() {
        //EventBus.getDefault().post(new DataEvent.FahionShopandBeautySaloonDataLoadedEvent("extra-in-broadcast", mFashionShopList,mBeautySaloonList,mServiceList));
        EventBus.getDefault().post(new DataEvent.FahionShopandBeautySaloonDataLoadedEvent("extra-in-broadcast", mFashionShopList,mBeautySaloonList));
    }

    public void setStoredData(List<FashionShopVO> fashionshopsList) {
        mFashionShopList = fashionshopsList;
    }
    public void setStoredBeautysalonData(List<BeautySaloonVO> beautysalonList) {
        mBeautySaloonList = beautysalonList;
    }
    public void setStoredServicesData(List<ServiceVO> mServices){

    }


    public void addFavorite(BeautySaloonVO beautysaloon) {

        BookmarkVO bookmarkvo=new BookmarkVO(beautysaloon.getsaloonid(),beautysaloon.getsaloonname(),beautysaloon.getPhoto(),beautysaloon.getfulladdr());
        List<BookmarkVO> currentbookmarklist=new ArrayList<>();
        currentbookmarklist.add(bookmarkvo);
        BookMarkModel.getInstance().notifyBookMarkLoaded(currentbookmarklist);

    }
}
