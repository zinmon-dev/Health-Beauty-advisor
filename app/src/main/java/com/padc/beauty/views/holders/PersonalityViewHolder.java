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
 * Created by windows on 9/26/2016.
 */
public class PersonalityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.tv_personality)
    TextView tvtitle;

    @BindView(R.id.iv_personality)
    ImageView ivpersonality;

    private TipVO mtip;
    private ControllerPersonalityItem mController;

    public PersonalityViewHolder(View itemView,ControllerPersonalityItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController=controller;
    }

    public void bindData(TipVO tip) {
        mtip = tip;
        tvtitle.setText(tip.getTitle());
        String imageUrl =  tip.getImg_url();

        Glide.with(ivpersonality.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivpersonality);

    }

    @Override
    public void onClick(View view) {
        mController.onTapPersonality(mtip);
    }
    public interface ControllerPersonalityItem {
        void onTapPersonality(TipVO tip);
    }

}
