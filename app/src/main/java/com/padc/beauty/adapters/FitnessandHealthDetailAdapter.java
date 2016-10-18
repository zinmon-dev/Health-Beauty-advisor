package com.padc.beauty.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.views.holders.FitnessandhealthDtlViewHolder;
import com.padc.beauty.views.holders.FitnessandhealthViewHolder;

import java.util.List;

/**
 * Created by windows on 10/2/2016.
 */
public class FitnessandHealthDetailAdapter extends RecyclerView.Adapter<FitnessandhealthDtlViewHolder> {
    private List<TipVO> mTipList;
    private LayoutInflater inflater;


    public FitnessandHealthDetailAdapter(List<TipVO> tipList){
        inflater = LayoutInflater.from(BeautyApp.getContext());
        mTipList = tipList;


    }

    @Override
    public FitnessandhealthDtlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_fitness_health_detail, parent, false);
        return new FitnessandhealthDtlViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FitnessandhealthDtlViewHolder holder, int position) {
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
