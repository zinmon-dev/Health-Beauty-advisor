package com.padc.beauty.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.views.holders.BeautySaloonViewHolder;

import java.util.List;

/**
 * Created by windows on 9/23/2016.
 */
public class BeautySaloonAdapter extends RecyclerView.Adapter<BeautySaloonViewHolder>   {
    private List<BeautySaloonVO> mBeautySaloonList;
    private LayoutInflater inflater;
    private BeautySaloonViewHolder.ControllerBeautysalonItem mControllerBeautysalonItem;

    public BeautySaloonAdapter(List<BeautySaloonVO> beautysaloonList,BeautySaloonViewHolder.ControllerBeautysalonItem controllerBeautysalonItem){
        inflater = LayoutInflater.from(BeautyApp.getContext());
        mBeautySaloonList = beautysaloonList;
        mControllerBeautysalonItem = controllerBeautysalonItem;
    }
    @Override
    public BeautySaloonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_beautysaloon, parent, false);
        return new BeautySaloonViewHolder(itemView,mControllerBeautysalonItem);
    }

    @Override
    public void onBindViewHolder(BeautySaloonViewHolder holder, int position) {
        holder.bindData(mBeautySaloonList.get(position));
    }

    @Override
    public int getItemCount() {
        return  mBeautySaloonList.size();
    }

    public void setNewData(List<BeautySaloonVO> newBeautySaloonList) {
        mBeautySaloonList = newBeautySaloonList;
        notifyDataSetChanged();//framework method
    }
}
