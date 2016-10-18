package com.padc.beauty.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.FashionShopVO;
import com.padc.beauty.views.holders.BeautySaloonViewHolder;
import com.padc.beauty.views.holders.FashionShopViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by windows on 9/24/2016.
 */
public class FashionShopAdapter extends RecyclerView.Adapter<FashionShopViewHolder>  {
    private List<FashionShopVO> mFashionShopList;
    private LayoutInflater inflater;
    ArrayList<FashionShopVO> arraylist;

    public FashionShopAdapter(List<FashionShopVO> fashionshopList){
        inflater = LayoutInflater.from(BeautyApp.getContext());
        mFashionShopList = fashionshopList;

    }

    @Override
    public FashionShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_fashionshop, parent, false);
        return new FashionShopViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FashionShopViewHolder holder, int position) {
        holder.bindData(mFashionShopList.get(position));
    }

    @Override
    public int getItemCount() {
        return  mFashionShopList.size();
    }

    public void setNewData(List<FashionShopVO> newFashionShopList) {
        mFashionShopList = newFashionShopList;
        notifyDataSetChanged();//framework method
        arraylist = new ArrayList<FashionShopVO>();
        arraylist.addAll(mFashionShopList);
    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        mFashionShopList.clear();
        if (charText.length() == 0) {
            mFashionShopList.addAll(arraylist);

        } else {
            for (FashionShopVO postDetail : arraylist) {
                if (charText.length() != 0 && postDetail.getshopname().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mFashionShopList.add(postDetail);
                }

                else if (charText.length() != 0 && postDetail.getshopname().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mFashionShopList.add(postDetail);
                }
            }
        }
        notifyDataSetChanged();
    }
}
