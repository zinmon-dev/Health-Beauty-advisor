package com.padc.beauty.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.padc.beauty.BeautyApp;
import com.padc.beauty.data.models.BookMarkModel;
import com.padc.beauty.data.models.DressingModel;
import com.padc.beauty.data.models.FashionShopandBeautySaloonModel;
import com.padc.beauty.data.models.PersonalityDetailModel;
import com.padc.beauty.data.models.TipModel;
import com.padc.beauty.data.vos.BeautySaloonVO;
import com.padc.beauty.data.vos.BookmarkVO;
import com.padc.beauty.data.vos.DressingVO;
import com.padc.beauty.data.vos.FashionShopVO;
import com.padc.beauty.data.vos.PerDetailVO;
import com.padc.beauty.data.vos.PersonalityDetailVO;
import com.padc.beauty.data.vos.TipVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by windows on 10/7/2016.
 */
public class SharedPreference {
    public static final String PREFS_NAME = "BEAUTY_APP";
    public static final String FAVORITES = "Beauty_Favorite";

    public SharedPreference() {
        super();
    }
    private BookmarkVO mbookmarkvo;
    private DressingVO mdressingvo;
    private PersonalityDetailVO mpersonalitydetailvo;
    private PerDetailVO mperdtlvo;
    private TipVO mtipvo;
    private BeautySaloonVO mbeautysaloonvo;
    private FashionShopVO mfashionshopvo;

    public void adddressingFavorite(Context context, BookmarkVO bookmark,DressingVO dressing) {
    //public void adddressingFavorite(Context context, DressingVO dressing) {
        List<BookmarkVO> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<BookmarkVO>();
        mdressingvo=dressing;
        favorites.add(bookmark);
        savedressingFavorites(context, favorites);

    }

    public ArrayList<BookmarkVO> getFavorites(Context context) {
        SharedPreferences settings;
        List<BookmarkVO> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            BookmarkVO[] favoriteItems = gson.fromJson(jsonFavorites,
                    BookmarkVO[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<BookmarkVO>(favorites);
        } else
            return null;
        //Toast.makeText(BeautyApp.getContext(),"Bookmark"+favorites,Toast.LENGTH_SHORT).show();
        return (ArrayList<BookmarkVO>) favorites;
    }

    // This four methods are used for maintaining favorites.
    public void savedressingFavorites(Context context, List<BookmarkVO> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
        DressingModel.getInstance().addFavorite(mdressingvo);
    }

    public void removedressingFavorite(Context context, BookmarkVO bookmark,DressingVO dressing) {
        ArrayList<BookmarkVO> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(bookmark);
            mdressingvo=dressing;
            savedressingFavorites(context, favorites);
        }
    }

    public void addtipFavorite(Context context, TipVO tip,BookmarkVO bookmark) {
        List<BookmarkVO> favorites = getFavorites(context);

        if (favorites == null)
            favorites = new ArrayList<BookmarkVO>();
        mtipvo=tip;
        favorites.add(bookmark);
        savetipFavorites(context, favorites);

    }

    public void removetipFavorite(Context context, BookmarkVO bookmark,TipVO tip) {
        ArrayList<BookmarkVO> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(bookmark);
            mtipvo=tip;
            savedressingFavorites(context, favorites);
        }
    }

    // This four methods are used for maintaining favorites.
    public void savetipFavorites(Context context, List<BookmarkVO> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
        TipModel.getInstance().addFavorite(mtipvo);
    }

    public void addpersonalityFavorite(Context context,BookmarkVO bookmark,PersonalityDetailVO personalityVO,Integer index) {
        List<BookmarkVO> favorites = getFavorites(context);

        if (favorites == null)
            favorites = new ArrayList<BookmarkVO>();
        mpersonalitydetailvo=personalityVO;
        favorites.add(bookmark);
        savepersonalitFavorites(context, favorites,index);

    }

    // This four methods are used for maintaining favorites.
    public void savepersonalitFavorites(Context context, List<BookmarkVO> favorites,Integer index) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
        PersonalityDetailModel.getInstance().addFavorite(mpersonalitydetailvo,index);
    }
    public void addbeautysaloonFavorite(Context context,BookmarkVO bookmark,BeautySaloonVO beautysaloonVO) {
        List<BookmarkVO> favorites = getFavorites(context);

        if (favorites == null)
            favorites = new ArrayList<BookmarkVO>();
        mbeautysaloonvo=beautysaloonVO;
        favorites.add(bookmark);
        savesaloonFavorites(context, favorites);

    }

    // This four methods are used for maintaining favorites.
    public void savesaloonFavorites(Context context, List<BookmarkVO> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
        FashionShopandBeautySaloonModel.getInstance().addFavorite(mbeautysaloonvo);
    }

//    public ArrayList<BookmarkVO> gettipFavorites(Context context) {
//        SharedPreferences settings;
//        List<BookmarkVO> favorites;
//
//        settings = context.getSharedPreferences(PREFS_NAME,
//                Context.MODE_PRIVATE);
//
//        if (settings.contains(FAVORITES)) {
//            String jsonFavorites = settings.getString(FAVORITES, null);
//            Gson gson = new Gson();
//            BookmarkVO[] favoriteItems = gson.fromJson(jsonFavorites,
//                    BookmarkVO[].class);
//
//            favorites = Arrays.asList(favoriteItems);
//            favorites = new ArrayList<BookmarkVO>(favorites);
//        } else
//            return null;
//
//        return (ArrayList<BookmarkVO>) favorites;
//    }
}
