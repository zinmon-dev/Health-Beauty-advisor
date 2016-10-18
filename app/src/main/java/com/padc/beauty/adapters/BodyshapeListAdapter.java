package com.padc.beauty.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.views.items.ViewItemCountry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aung on 7/15/16.
 */
public class BodyshapeListAdapter extends BaseAdapter {

    private List<String> mBodyshapeList;
    private LayoutInflater mInflater;

    public BodyshapeListAdapter(List<String> countryList) {
        if (countryList != null) {
            this.mBodyshapeList = countryList;
        } else {
            this.mBodyshapeList = new ArrayList<>();//to prevent null pointer exception when execute  mBodyshapeList.size()
        }
        mInflater = LayoutInflater.from(BeautyApp.getContext());
    }

    @Override
    public int getCount() {
        return mBodyshapeList.size();
    }

    @Override
    public String getItem(int position) {
        return mBodyshapeList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.view_item_bodyshape, parent, false);
        }
        //instanceof >>check type of  root
        if (convertView instanceof ViewItemCountry) {
            ViewItemCountry viCountry = (ViewItemCountry) convertView;
            viCountry.setData(getItem(position));
        }

        return convertView;
    }
}