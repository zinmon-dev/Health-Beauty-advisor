package com.padc.beauty.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.adapters.BookMarkAdapter;
import com.padc.beauty.adapters.FashionShopAdapter;
import com.padc.beauty.data.models.BookMarkModel;
import com.padc.beauty.data.models.FashionShopandBeautySaloonModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.FashionShopVO;
import com.padc.beauty.events.DataEvent;
import com.padc.beauty.utils.BeautyAppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by windows on 10/7/2016.
 */
public class BookmarkFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    @BindView(R.id.rv_bookmark)
    RecyclerView rvbookmark;

    private int gridColumnSpanCount=1;

    private BookMarkAdapter mBookMarkAdapter;
    public static BookmarkFragment newInstance(){
        BookmarkFragment bookmarkFragment = new BookmarkFragment();
        return bookmarkFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bookmark, container, false);
        ButterKnife.bind(this, rootView);
        rvbookmark.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        List<BookmarkVO> bookmarkList = BookMarkModel.getInstance().getmBookmarkList();
        //showdata();
        mBookMarkAdapter = new BookMarkAdapter(bookmarkList);
        rvbookmark.setAdapter(mBookMarkAdapter);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEventMainThread(DataEvent.BookMarkDataLoadedEvent event) {

        //Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        List<BookmarkVO> newFashionShopList = event.getbookmarkList();
        mBookMarkAdapter.setNewData(newFashionShopList);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(BeautyAppConstant.BOOKMARK_LIST_LOADER, null, this);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                BeautyContract.BookMarkEntry.CONTENT_URI,
                null,
                null,
                null,
                BeautyContract.BookMarkEntry.COLUMN_BOOKMARKID + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<BookmarkVO> bookmarkList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                BookmarkVO bookmark = BookmarkVO.parseFromCursor(data);
                bookmarkList.add(bookmark);
            } while (data.moveToNext());
        }

        Log.d(BeautyApp.TAG, "Retrieved bookmark ASC : " + bookmarkList.size());
        mBookMarkAdapter.setNewData(bookmarkList);


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
