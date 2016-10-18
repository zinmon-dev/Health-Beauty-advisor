package com.padc.beauty.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.FashionShopVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by windows on 9/24/2016.
 */
public class BookMarkViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_bookmark)
    TextView tvbookmark;

    @BindView(R.id.iv_bookmark)
    ImageView ivbookmark;

    private BookmarkVO mbookmark;

    public BookMarkViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(BookmarkVO bookmark) {
        mbookmark = bookmark;

        tvbookmark.setText(bookmark.getBookmarktitle());

        String imageUrl =  bookmark.getBookmarkimage();

        Glide.with(ivbookmark.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivbookmark);
    }
}