package com.padc.beauty.views.holders;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.data.vos.ServiceVO;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.utils.SharedPreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by windows on 9/24/2016.
 */
public class BeautySaloonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_beautysaloonname)
    TextView tvname;

    @BindView(R.id.tv_beautysaloonaddr)
    TextView tvaddr;

    @BindView(R.id.tv_openhr)
    TextView tvOpenhr;

    @BindView(R.id.iv_beautysaloon)
    ImageView ivbeautysaloon;

    @BindView(R.id.iv_bookmark)
    ImageView ivbookmark;

    private BeautySaloonVO mbeauty;
    private BookmarkVO mbookmark;
    private ControllerBeautysalonItem mController;
    private ServiceVO mServices;
    SharedPreference sharedPreference;

    public BeautySaloonViewHolder(View itemView, ControllerBeautysalonItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
        sharedPreference =new SharedPreference();
    }

    public void bindData(BeautySaloonVO beautysaloon) {
        mbeauty = beautysaloon;

        tvname.setText(beautysaloon.getsaloonname());
        tvaddr.setText(beautysaloon.getdirectiontosaloon());
        tvOpenhr.setText("Open Daily : "+beautysaloon.getOpendaily());
        String imageUrl =  beautysaloon.getPhoto();

        Glide.with(ivbeautysaloon.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivbeautysaloon);

        if (checkFavoriteItem(mbeauty)) {
            // ivbookmark.setImageResource(R.drawable.ic_star_black_24dp);

            int bookmarkcolor=BeautyApp.getContext().getResources().getColor(R.color.primary);
            ivbookmark.setColorFilter(bookmarkcolor);

        } else {
            int bookmarkcolor=BeautyApp.getContext().getResources().getColor(R.color.text_black_ish);
            ivbookmark.setColorFilter(bookmarkcolor);
            //  holder.favoriteImg.setTag("grey");
        }
    }

    @Override
    public void onClick(View view) {
        mController.onTapBeautysalon(mbeauty, ivbeautysaloon);
    }

    public interface ControllerBeautysalonItem {
        void onTapBeautysalon(BeautySaloonVO beautysalon, ImageView ivbeautysaloon);
    }
    @OnClick(R.id.iv_bookmark)
    public void onTabbookmark()
    {

        mbookmark = new BookmarkVO(mbeauty.getsaloonid(),mbeauty.getsaloonname(),mbeauty.getPhoto(),mbeauty.getfulladdr());
        sharedPreference.addbeautysaloonFavorite(BeautyApp.getContext(),mbookmark,mbeauty);



        int bookmarkcolor=BeautyApp.getContext().getResources().getColor(R.color.primary);
        ivbookmark.setColorFilter(bookmarkcolor);

    }

    public boolean checkFavoriteItem(BeautySaloonVO checkProduct) {
        boolean check = false;
        List<BookmarkVO> favorites = sharedPreference.getFavorites(BeautyApp.getContext());
        if (favorites != null) {
            for (BookmarkVO product : favorites) {
                //if (product.getid()==checkProduct.getid()) {
                if ((product.getBookmarkid()==checkProduct.getsaloonid()) && (TextUtils.equals(product.getBookmarktitle(),checkProduct.getsaloonname()))) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
}
