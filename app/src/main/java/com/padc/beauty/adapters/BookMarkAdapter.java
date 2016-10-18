package com.padc.beauty.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.FashionShopVO;
import com.padc.beauty.views.holders.BookMarkViewHolder;
import com.padc.beauty.views.holders.FashionShopViewHolder;

import java.util.List;

/**
 * Created by windows on 10/7/2016.
 */
public class BookMarkAdapter extends RecyclerView.Adapter<BookMarkViewHolder> {
    private List<BookmarkVO> mBookMarkList;
    private LayoutInflater inflater;

    public BookMarkAdapter(List<BookmarkVO> bookmarkList){
        inflater = LayoutInflater.from(BeautyApp.getContext());
        mBookMarkList = bookmarkList;
    }


    @Override
    public BookMarkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_bookmark, parent, false);
        return new BookMarkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookMarkViewHolder holder, int position) {
        holder.bindData(mBookMarkList.get(position));
    }

    @Override
    public int getItemCount() {
        return  mBookMarkList.size();
    }

    public void setNewData(List<BookmarkVO> newBookMarkList) {
        mBookMarkList = newBookMarkList;
        notifyDataSetChanged();//framework method
    }
}
