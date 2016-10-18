package com.padc.beauty.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.FashionShopVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by windows on 9/24/2016.
 */
public class FashionShopViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_fashioshopname)
    TextView tvname;

    @BindView(R.id.tv_fashionshopaddr)
    TextView tvaddr;

    @BindView(R.id.iv_fashionshop)
    ImageView ivfashionshop;

    private FashionShopVO mfashionshop;

    public FashionShopViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(FashionShopVO fashionshop) {
        mfashionshop = fashionshop;

        tvname.setText(fashionshop.getshopname());
        tvaddr.setText(fashionshop.getfulladdr());
        String imageUrl =  fashionshop.getPhoto();

        Glide.with(ivfashionshop.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivfashionshop);
    }
}