package com.padc.beauty.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.views.items.ViewItemTipList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windows on 9/11/2016.
 */
public class SkinTypeAdapter extends BaseAdapter{

    private List<String> mSkintipList;
    private LayoutInflater mInflater;

    public SkinTypeAdapter(List<String> skintipList) {
        if (skintipList != null) {
            this.mSkintipList = skintipList;
        } else {
            this.mSkintipList = new ArrayList<>();
        }
        mInflater = LayoutInflater.from(BeautyApp.getContext());
    }

    @Override
    public int getCount() {
        return mSkintipList.size();
    }

    @Override
    public String getItem(int position) {
         return mSkintipList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.view_item_tip_category_list, parent, false);
        }

        if(convertView instanceof ViewItemTipList){
            ViewItemTipList viTip=(ViewItemTipList)convertView;
            viTip.setData(getItem(position));
        }

        return convertView;
    }
}
