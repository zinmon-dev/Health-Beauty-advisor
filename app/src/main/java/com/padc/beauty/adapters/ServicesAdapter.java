package com.padc.beauty.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.ServiceVO;
import com.padc.beauty.views.holders.BeautySaloonViewHolder;
import com.padc.beauty.views.holders.ServicesViewHolder;

import java.util.List;

/**
 * Created by Asus on 9/29/2016.
 */
public class ServicesAdapter extends RecyclerView.Adapter<ServicesViewHolder>   {
    private List<ServiceVO> mServicesList;
    private LayoutInflater inflater;
    private ServicesViewHolder.ControllerBeautysalonItem mControllerBeautysalonItem;

    public ServicesAdapter(List<ServiceVO> servicesList){
        inflater = LayoutInflater.from(BeautyApp.getContext());
        mServicesList = servicesList;
    }
    @Override
    public ServicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View itemView = inflater.inflate(R.layout.view_item_beautysalon_detail, parent, false);
        View itemView = inflater.inflate(R.layout.fragment_salon_services, parent, false);
        return new ServicesViewHolder(itemView,mControllerBeautysalonItem);
    }

    @Override
    public void onBindViewHolder(ServicesViewHolder holder, int position) {
        holder.bindServicesData(mServicesList.get(position));
    }


    @Override
    public int getItemCount() {
        return  mServicesList.size();
    }

    public void setNewData(List<ServiceVO> newBeautySaloonList) {
        mServicesList = newBeautySaloonList;
        notifyDataSetChanged();//framework method
    }
}
