package com.padc.beauty.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.DressingVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windows on 10/7/2016.
 */
public class BookMarkModel  extends BaseModel  {
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static BookMarkModel objInstance;
    private List<BookmarkVO> mBookmarkList;

    private BookMarkModel() {
        super();
        mBookmarkList = new ArrayList<>();
        //dataAgent.loadDressings();
    }

    public static BookMarkModel getInstance() {
        if (objInstance == null) {
            objInstance = new BookMarkModel();
        }
        return objInstance;
    }

    public List<BookmarkVO> getmBookmarkList() {
        return mBookmarkList;
    }

    public void notifyBookMarkLoaded(List<BookmarkVO> bookmarkList) {

        mBookmarkList = bookmarkList;
        saveBookMarks(mBookmarkList);
        //broadcastMealLoadedWithEventBus();

    }
    public static void saveBookMarks(List<BookmarkVO> bookmarkList) {
        Context context = BeautyApp.getContext();
        ContentValues[] bookmarkCVs = new ContentValues[bookmarkList.size()];
        Log.d(BeautyApp.TAG,"dressinglistsize"+bookmarkList.size());
        for (int index = 0; index < bookmarkList.size(); index++) {

            BookmarkVO bookmark = bookmarkList.get(index);
            bookmarkCVs[index] = bookmark.parseToContentValues();



        }
        Log.d(BeautyApp.TAG,"bookmarkcvs"+bookmarkCVs.length);
        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(BeautyContract.BookMarkEntry.CONTENT_URI, bookmarkCVs);

        Log.d(BeautyApp.TAG, "Bulk inserted into Bookmark table : " + insertedCount);
    }
}
