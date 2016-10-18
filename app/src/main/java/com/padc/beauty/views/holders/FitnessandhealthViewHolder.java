package com.padc.beauty.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.TipVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by windows on 9/27/2016.
 */


public class FitnessandhealthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener   {
    @BindView(R.id.tv_fitnessandhealth)
    TextView tvtitle;

    @BindView(R.id.iv_fitandhealth)
    ImageView ivfitnessandhealth;

    private TipVO mtip;
    private ControllerFitnessandHealth mController;


    public FitnessandhealthViewHolder(View itemView,ControllerFitnessandHealth controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController=controller;

    }

    public void bindData(TipVO tip) {
        mtip = tip;
        tvtitle.setText(tip.getTitle());
        String imageUrl =  tip.getImg_url();

        itemView.setOnClickListener(this);

        Glide.with(ivfitnessandhealth.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivfitnessandhealth);

    }
    @Override
    public void onClick(View view) {

        mController.onTapHealth(mtip);
    }

    public interface ControllerFitnessandHealth {
        void onTapHealth(TipVO healthrelatedTips);

    }


}
