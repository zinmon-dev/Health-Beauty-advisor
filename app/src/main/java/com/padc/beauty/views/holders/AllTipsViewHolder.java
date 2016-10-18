package com.padc.beauty.views.holders;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by windows on 9/25/2016.
 */
public class AllTipsViewHolder extends RecyclerView.ViewHolder  {
    @BindView(R.id.tv_tips_title)
    TextView tvskintypetitle;

    @BindView(R.id.tv_tips_desc)
    TextView tvskintypedesc;

    @BindView(R.id.iv_tips)
    ImageView ivskintype;

    @BindView(R.id.iv_bookmark)
    ImageView ivbookmark;

    private TipVO mtip;
    SharedPreference sharedPreference;
    private  BookmarkVO mbookmarkvo;
    public AllTipsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        sharedPreference =new SharedPreference();
    }

    public void bindData(TipVO tip) {
        mtip = tip;
        tvskintypetitle.setText(tip.getTitle());
        tvskintypedesc.setText(tip.getDescription());


        String imageUrl =  tip.getImg_url();
        // Log.d(BeautyApp.TAG,"imageurl:"+imageUrl);
        Glide.with(ivskintype.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivskintype);

        if (checkFavoriteItem(tip)) {
            // ivbookmark.setImageResource(R.drawable.ic_star_black_24dp);

            int bookmarkcolor= BeautyApp.getContext().getResources().getColor(R.color.primary);
            ivbookmark.setColorFilter(bookmarkcolor);

        } else {
            int bookmarkcolor=BeautyApp.getContext().getResources().getColor(R.color.text_black_ish);
            ivbookmark.setColorFilter(bookmarkcolor);
            //  holder.favoriteImg.setTag("grey");
        }

    }

    public boolean checkFavoriteItem(TipVO checkTip) {
        boolean check = false;
        List<BookmarkVO> favorites = sharedPreference.getFavorites(BeautyApp.getContext());
        if (favorites != null) {
            for (BookmarkVO bookmark : favorites) {
                if ((bookmark.getBookmarkid()==checkTip.getTipid()) && (TextUtils.equals(bookmark.getBookmarkscreen(),checkTip.getTipcategory()))) {
            //    if (tip.getTipid()==checkTip.getTipid()) {
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
        mbookmarkvo = new BookmarkVO(mtip.getTipid(),mtip.getTitle(),mtip.getImg_url(),mtip.getTipcategory());
        sharedPreference.addtipFavorite(BeautyApp.getContext(),mtip,mbookmarkvo);

//        Toast.makeText(BeautyApp.getContext(),
//                R.string.add_favr+"id"+mtip.getTipid(),
//                Toast.LENGTH_SHORT).show();

        int bookmarkcolor=BeautyApp.getContext().getResources().getColor(R.color.primary);
        ivbookmark.setColorFilter(bookmarkcolor);
        //} else {
//            sharedPreference.removeFavorite(BeautyApp.getContext(), products.get(position));
//           ivbookmark.setTag("grey");
//           ivbookmark.setImageResource(R.drawable.ic_import_contacts_black_24dp);
//           Toast.makeText(BeautyApp.getContext(),
//                   R.string.remove_favr,
//                   Toast.LENGTH_SHORT).show();
//        }
//        Toast.makeText(BeautyApp.getContext(),"ID"+mtip.getTipid()+"Image"+mtip+"Title"+mtip.getDescription(),Toast.LENGTH_SHORT).show();
    }
}
