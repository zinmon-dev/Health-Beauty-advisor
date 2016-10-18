package com.padc.beauty.views.holders;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.utils.SharedPreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by windows on 10/2/2016.
 */
public class FitnessandhealthDtlViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.tv_title)
    TextView tvtitle;

    @BindView(R.id.iv_fitandhealth)
    ImageView ivfitnessandhealth;

    @BindView(R.id.tv_desc)
    TextView tvdesc;

    @BindView(R.id.iv_bookmark)
    ImageView ivbookmark;

    SharedPreference sharedPreference;
    private BookmarkVO mbookmark;

    private TipVO mtip;

    public FitnessandhealthDtlViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        sharedPreference =new SharedPreference();
    }



    public void bindData(TipVO tip) {
        mtip = tip;
        tvtitle.setText(tip.getTitle());
        tvdesc.setText(tip.getDescription());
        String imageUrl =  tip.getImg_url();

        Glide.with(ivfitnessandhealth.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivfitnessandhealth);

        if (checkFavoriteItem(mtip)) {
            // ivbookmark.setImageResource(R.drawable.ic_star_black_24dp);

            int bookmarkcolor= BeautyApp.getContext().getResources().getColor(R.color.primary);
            ivbookmark.setColorFilter(bookmarkcolor);

        } else {
            int bookmarkcolor=BeautyApp.getContext().getResources().getColor(R.color.text_black_ish);
            ivbookmark.setColorFilter(bookmarkcolor);
            //  holder.favoriteImg.setTag("grey");
        }

    }

    public boolean checkFavoriteItem(TipVO checkProduct) {
        boolean check = false;
        List<BookmarkVO> favorites = sharedPreference.getFavorites(BeautyApp.getContext());
        if (favorites != null) {
            for (BookmarkVO product : favorites) {
                //if (product.getid()==checkProduct.getid()) {
                if ((product.getBookmarkid()==checkProduct.getTipid()) && (TextUtils.equals(product.getBookmarkscreen(),checkProduct.getTipcategory()))) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @OnClick(R.id.iv_bookmark)
    public void onTabbookmark()
    {

        mbookmark = new BookmarkVO(mtip.getTipid(),mtip.getTitle(),mtip.getImg_url(),mtip.getTipcategory());
        sharedPreference.addtipFavorite(BeautyApp.getContext(),mtip,mbookmark);


        int bookmarkcolor=BeautyApp.getContext().getResources().getColor(R.color.primary);
        ivbookmark.setColorFilter(bookmarkcolor);

    }

}
