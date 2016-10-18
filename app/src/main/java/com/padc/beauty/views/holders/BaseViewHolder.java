package com.padc.beauty.views.holders;

import java.util.PriorityQueue;

/**
 * Created by windows on 9/24/2016.
 */
public class BaseViewHolder {

    private String skintypes="";
    private String skincolors="";

    private String showskintype(String[] skintype)
    {

        for(int index=0;index<skintype.length;index++)
        {

            skintypes = skintype[index] + "Skin Type"+"\n" ;
        }
        return skintypes;
    }
}
