package com.padc.beauty.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.views.holders.AllTipsViewHolder;

import java.util.List;

/**
 * Created by windows on 9/25/2016.
 */
public class AllTipListAdapter extends RecyclerView.Adapter<AllTipsViewHolder>{
    private List<TipVO> mTipList;
    private LayoutInflater inflater;

    public AllTipListAdapter(List<TipVO> TipList){
        inflater = LayoutInflater.from(BeautyApp.getContext());
        mTipList = TipList;
    }
    @Override
    public AllTipsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_tips, parent, false);
        return new AllTipsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AllTipsViewHolder holder, int position) {
        holder.bindData(mTipList.get(position));
    }

    @Override
    public int getItemCount() {
        return  mTipList.size();
    }

    public void setNewData(List<TipVO> newTipList) {
        mTipList = newTipList;
        notifyDataSetChanged();//framework method
    }
}
