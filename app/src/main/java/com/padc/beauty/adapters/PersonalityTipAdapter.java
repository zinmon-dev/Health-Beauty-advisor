package com.padc.beauty.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.views.holders.AllTipsViewHolder;
import com.padc.beauty.views.holders.PersonalityViewHolder;

import java.util.List;

/**
 * Created by windows on 9/26/2016.
 */
public class PersonalityTipAdapter extends   RecyclerView.Adapter<PersonalityViewHolder>{
    private List<TipVO> mTipList;
    private LayoutInflater inflater;

    private  PersonalityViewHolder.ControllerPersonalityItem mController;

    public PersonalityTipAdapter(List<TipVO> TipList, PersonalityViewHolder.ControllerPersonalityItem controllerItem){
        inflater = LayoutInflater.from(BeautyApp.getContext());
        mTipList = TipList;
        mController=controllerItem;
    }

    @Override
    public PersonalityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_personality, parent, false);
        return new PersonalityViewHolder(itemView,mController);
    }

    @Override
    public void onBindViewHolder(PersonalityViewHolder holder, int position) {
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
