package com.padc.beauty.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.data.vos.FashionShopVO;
import com.padc.beauty.data.vos.TipVO;

import com.padc.beauty.views.holders.BeautySaloonViewHolder;
import com.padc.beauty.views.holders.FitnessandhealthViewHolder;
import com.padc.beauty.views.holders.PersonalityViewHolder;

import java.util.List;

/**
 * Created by windows on 9/27/2016.
 */
public class FitnessandhealthAdapter  extends RecyclerView.Adapter<FitnessandhealthViewHolder>  {
    private List<TipVO> mTipList;
    private LayoutInflater inflater;
    private FitnessandhealthViewHolder.ControllerFitnessandHealth mControllerHealth;
    public FitnessandhealthAdapter(List<TipVO> tipList, FitnessandhealthViewHolder.ControllerFitnessandHealth controllerItem){
        inflater = LayoutInflater.from(BeautyApp.getContext());
        mTipList = tipList;
        mControllerHealth=controllerItem;

    }

    @Override
    public FitnessandhealthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_fitness_and_health, parent, false);
        return new FitnessandhealthViewHolder(itemView,mControllerHealth);
    }

    @Override
    public void onBindViewHolder(FitnessandhealthViewHolder holder, int position) {
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
