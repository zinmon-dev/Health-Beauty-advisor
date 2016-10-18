package com.padc.beauty.views.holders;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.activities.BaseActivity;
import com.padc.beauty.activities.HomeActivity;
import com.padc.beauty.data.models.DressingModel;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.utils.SharedPreference;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by windows on 9/23/2016.
 */
public class DressingViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_price)
    TextView tvprice;

    @BindView(R.id.iv_dress)
    ImageView ivdress;

    @BindView(R.id.tv_shopname)
    TextView tvShopName;

    @BindView(R.id.tv_shopdirection)
    TextView tvShopDirection;

    @BindView(R.id.tv_hairstyle)
    TextView tvHairStyle;

    @BindView(R.id.tv_bodyshape)
    TextView tvBodyShape;

    @BindView(R.id.tv_skincolor)
    TextView tvSkinColor;

    @BindView(R.id.tv_skintype)
    TextView tvSkinType;

    @BindView(R.id.iv_share)
    ImageView ivshare;

    @BindView(R.id.iv_bookmark)
    ImageView ivbookmark;

    private DressingVO mdress;
    private BookmarkVO mbookmark;
    private String skintypes="";
    private String skincolors="";
    private String imageUrl;

    private ControllerDressing mcontroller;
    SharedPreference sharedPreference;

    public DressingViewHolder(View itemView,ControllerDressing controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mcontroller=controller;
        sharedPreference =new SharedPreference();
    }

    public void bindData(DressingVO dress) {
        mdress = dress;
        String hairstyle="";
        tvShopName.setText(dress.getShopname());
        tvprice.setText(dress.getprice()+" Ks");
        tvShopDirection.setText(dress.getShopdirection());
        tvHairStyle.setText(showsuitablepersondata(dress.getHairstyles()));;
        tvSkinColor.setText(showsuitablepersondata(dress.getSkincolors()));
        tvSkinType.setText(showsuitablepersondata(dress.getSkintypes()));
        tvBodyShape.setText(showsuitablepersondata(dress.getBodyshapes()));

        imageUrl =  dress.getimgurl();
       // Log.d(BeautyApp.TAG,"imageurl:"+imageUrl);
        Glide.with(ivdress.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivdress);

        if (checkFavoriteItem(dress)) {
           // ivbookmark.setImageResource(R.drawable.ic_star_black_24dp);

            int bookmarkcolor=BeautyApp.getContext().getResources().getColor(R.color.primary);
            ivbookmark.setColorFilter(bookmarkcolor);

        } else {
            int bookmarkcolor=BeautyApp.getContext().getResources().getColor(R.color.text_black_ish);
            ivbookmark.setColorFilter(bookmarkcolor);
          //  holder.favoriteImg.setTag("grey");
        }

    }

    public boolean checkFavoriteItem(DressingVO checkProduct) {
        boolean check = false;
        List<BookmarkVO> favorites = sharedPreference.getFavorites(BeautyApp.getContext());
        if (favorites != null) {
            for (BookmarkVO product : favorites) {
               //if (product.getid()==checkProduct.getid()) {
                if ((product.getBookmarkid()==checkProduct.getid()) && (TextUtils.equals(product.getBookmarkscreen(),checkProduct.getdresstype()))) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    private String showsuitablepersondata(String[] suitableperson)
    {
        String suitablepersons="";

        for(int index=0;index<suitableperson.length;index++)
        {
            suitablepersons = suitablepersons + suitableperson[index] + "\n" ;
        }
        return suitablepersons;
    }

    @OnClick(R.id.iv_bookmark)
    public void onTabbookmark()
    {
      // String tag = ivbookmark.getTag().toString();

      // if (tag.equals("#6000")) {
        //sharedPreference.addFavorite(BeautyApp.getContext(), mdress.getid(),mdress.getDescription(),mdress.getimgurl());
          //  DressingModel.getInstance().addFavorite(BeautyApp.getContext(),mdress);

        //Toast.makeText(BeautyApp.getContext(),"color"+ivbookmark.getTag(),Toast.LENGTH_SHORT).show();
//         if(ivbookmark.getColorFilter()== BeautyApp.getContext().getResources().getColor(R.color.primary)){
//
//         }
         mbookmark = new BookmarkVO(mdress.getid(),mdress.getShopname(),mdress.getimgurl(),mdress.getdresstype());
         sharedPreference.adddressingFavorite(BeautyApp.getContext(),mbookmark,mdress);

//            Toast.makeText(BeautyApp.getContext(),
//                    R.string.add_favr+"id"+mdress.getid(),
//                    Toast.LENGTH_SHORT).show();

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
      // Toast.makeText(BeautyApp.getContext(),"ID"+mdress.getid()+"Image"+imageUrl+"Title"+mdress.getDescription(),Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.iv_share)
    public void onTabshare()
    {
        mcontroller.onTabshare(imageUrl,mdress.getprice(),mdress.getShopname(),mdress.getShopdirection());
    }

    public interface ControllerDressing{
        void onTabshare(String ImageUrl,String Price,String ShopName,String ShopDirection);
    }
}
