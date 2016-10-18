package com.padc.beauty.events;

import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.data.vos.FashionShopVO;
import com.padc.beauty.data.vos.ServiceVO;
import com.padc.beauty.data.vos.TipVO;

import java.util.List;

/**
 * Created by windows on 9/22/2016.
 */
public class DataEvent {
    public static  class DressingDataLoadedEvent{
        private String extraMessage;
        private List<DressingVO> DressingList;


        public DressingDataLoadedEvent(String extraMessage, List<DressingVO> dressingList) {
            this.extraMessage = extraMessage;
            this.DressingList = dressingList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<DressingVO> getDressingList() {
            return DressingList;
        }
    }

    public static class FahionShopandBeautySaloonDataLoadedEvent{

        private String extraMessage;
        private List<BeautySaloonVO> BeautySaloonList;
        private List<FashionShopVO> FashionShopList;
        private List<ServiceVO> ServiceList;


        //public FahionShopandBeautySaloonDataLoadedEvent(String extraMessage, List<FashionShopVO> fashionshopList, List<BeautySaloonVO> beautysaloonList, List<ServiceVO> serviceList) {
        public FahionShopandBeautySaloonDataLoadedEvent(String extraMessage, List<FashionShopVO> fashionshopList, List<BeautySaloonVO> beautysaloonList) {
            this.extraMessage = extraMessage;
            this.FashionShopList = fashionshopList;
            this.BeautySaloonList=beautysaloonList;
           // this.ServiceList=serviceList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<BeautySaloonVO> getBeautySaloonList() {
            return BeautySaloonList;
        }

        public List<ServiceVO> getServiceList() {
            return ServiceList;
        }

        public List<FashionShopVO> getFashionShopList() {
            return FashionShopList;
        }
    }

    public static  class TipDataLoadedEvent{
        private String extraMessage;
        private List<TipVO> TipList;


        public TipDataLoadedEvent(String extraMessage, List<TipVO> tipList) {
            this.extraMessage = extraMessage;
            this.TipList = tipList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<TipVO> getTipList() {
            return TipList;
        }
    }

    public static class BookMarkDataLoadedEvent{
        private List<BookmarkVO> bookmarkList;
        public BookMarkDataLoadedEvent(List<BookmarkVO> bookmList) {

            this.bookmarkList = bookmList;
        }

        public List<BookmarkVO> getbookmarkList() {
            return bookmarkList;
        }
    }

}