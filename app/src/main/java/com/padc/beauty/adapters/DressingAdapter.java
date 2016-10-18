package com.padc.beauty.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.utils.SharedPreference;
import com.padc.beauty.views.holders.DressingViewHolder;
import com.padc.beauty.views.holders.FitnessandhealthViewHolder;

import java.util.List;

/**
 * Created by windows on 9/23/2016.
 */
public class DressingAdapter extends RecyclerView.Adapter<DressingViewHolder>  {

    private List<DressingVO> mDressingList;
    private LayoutInflater inflater;
    private DressingViewHolder.ControllerDressing mControllerDressing;
    SharedPreference   sharedPreference;

    public DressingAdapter(List<DressingVO> dressingList,DressingViewHolder.ControllerDressing controlleritem){
        inflater = LayoutInflater.from(BeautyApp.getContext());
        mDressingList = dressingList;
        mControllerDressing=controlleritem;
        sharedPreference = new SharedPreference();
    }

    @Override
    public DressingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_occasionaldress, parent, false);
        return new DressingViewHolder(itemView,mControllerDressing);
    }

    @Override
    public void onBindViewHolder(DressingViewHolder holder, int position) {
        holder.bindData(mDressingList.get(position));
    }

    @Override
    public int getItemCount() {
        return  mDressingList.size();
    }

    public void setNewData(List<DressingVO> newDressList) {
        mDressingList = newDressList;
        notifyDataSetChanged();//framework method
    }
}
