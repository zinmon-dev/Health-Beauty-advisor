package com.padc.beauty.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.padc.beauty.BeautyApp;

/**

 * Created by windows on 9/24/2016.
 */
public class BeautyContract {
    public static final String CONTENT_AUTHORITY = BeautyApp.class.getPackage().getName();

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_TIPS = "tips";
    public static final String PATH_TIP_SKINCOLOR = "tip_skincolor";
    public static final String PATH_TIP_BODYSHAPE = "tip_bodyshape";
    public static final String PATH_TIP_HAIRCOLOR = "tip_haircolor";
    public static final String PATH_TIP_SKINTYPE = "tip_skintype";
    public static final String PATH_TIP_FACETYPE = "tip_facetype";

    public static final String PATH_BEAUTY_SALON = "beautysalon";
    public static final String PATH_FASHION_SHOP = "fashionshop";
    public static final String PATH_SALON_SERVICES = "salon_services";


    public static final String PATH_DRESSING = "dressing";
    public static final String PATH_DRESSING_SKINCOLOR = "dressing_skincolor";
    public static final String PATH_DRESSING_BODYSHAPE = "dressing_bodyshape";
    public static final String PATH_DRESSING_HAIRSTYLE = "dressing_hairstyle";
    public static final String PATH_DRESSING_SKINTYPE = "dressing_skintype";

    public static final String PATH_BOOKMARK = "bookmark";

    public static final class TipEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TIPS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIPS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIPS;

        public static final String TABLE_NAME = "tips";

        public static final String COLUMN_TIPID = "tipid";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_DESC = "desc";

        public static Uri buildTipUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildTipUriWithTipId(Long tipId) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions?title="Yangon"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TIPID, String.valueOf(tipId))
                    .build();
        }

        public static String getTipFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_CATEGORY);
        }
    }

    public static final class TipSkinColorEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TIP_SKINCOLOR).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIP_SKINCOLOR;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIP_SKINCOLOR;

        public static final String TABLE_NAME = "tip_skincolor";

        public static final String COLUMN_TIPID = "tipid";
        public static final String COLUMN_SKINCOLOR = "skincolor";

        public static Uri buildSkinColorTipUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildSkinColorUriWithTipID(long tipid) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TIPID, String.valueOf(tipid))
                    .build();
        }

        public static String getTipFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TIPID);
        }
    }

    public static final class TipBodyShapeEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TIP_BODYSHAPE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIP_BODYSHAPE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIP_BODYSHAPE;

        public static final String TABLE_NAME = "tip_bodyshape";

        public static final String COLUMN_TIPID = "tipid";
        public static final String COLUMN_BODYSHAPE = "bodyshape";

        public static Uri buildBodyShapeUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildBodyShapeUriWithTipID(long tipid) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TIPID, String.valueOf(tipid))
                    .build();
        }

        public static String getTipFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TIPID);
        }
    }

    public static final class TipHairColorEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TIP_HAIRCOLOR).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIP_HAIRCOLOR;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIP_HAIRCOLOR;

        public static final String TABLE_NAME = "tip_haircolor";

        public static final String COLUMN_TIPID = "tipid";
        public static final String COLUMN_HAIRCOLOR = "haircolor";

        public static Uri buildHairColorTipUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildHairColorUriWithTipID(long tipid) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TIPID, String.valueOf(tipid))
                    .build();
        }

        public static String getTipFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TIPID);
        }
    }

    public static final class TipSkinTypeEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TIP_SKINTYPE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIP_SKINTYPE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIP_SKINTYPE;

        public static final String TABLE_NAME = "tip_skintype";

        public static final String COLUMN_TIPID = "tipid";
        public static final String COLUMN_SKINTYPE = "skintype";

        public static Uri buildSkinTypeUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildSkinTypeUriWithTipID(long tipid) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TIPID, String.valueOf(tipid))
                    .build();
        }

        public static String getTipFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TIPID);
        }
    }

    public static final class TipFaceTypeEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TIP_FACETYPE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIP_FACETYPE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIP_FACETYPE;

        public static final String TABLE_NAME = "tip_facetype";

        public static final String COLUMN_TIPID = "tipid";
        public static final String COLUMN_FACETYPE = "facetype";

        public static Uri buildFaceTypeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildFaceTypeUriWithTipID(long tipid) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TIPID, String.valueOf(tipid))
                    .build();
        }

        public static String getTipFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TIPID);
        }
    }

    public static final class DressingEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DRESSING).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRESSING;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRESSING;

        public static final String TABLE_NAME = "dressing";

        public static final String COLUMN_DRESSINGID = "dressingid";
        public static final String COLUMN_TYPE = "dressingtype";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_SHOPID = "shopid";
        public static final String COLUMN_SHOPNAME = "shopname";
        public static final String COLUMN_DIRECTIONTOSHOP = "shopdirection";
        public static final String COLUMN_PROMOTION = "promotion";

        public static Uri buildDressingUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getTipFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_DRESSINGID);
        }
    }

    public static final class DressingSkinColorEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DRESSING_SKINCOLOR).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRESSING_SKINCOLOR;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRESSING_SKINCOLOR;

        public static final String TABLE_NAME = "dressing_skincolor";

        public static final String COLUMN_DRESSINGID = "dressingid";
        public static final String COLUMN_SKINCOLOR = "skincolor";

        public static Uri buildSkinColorTipUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildSkinColorUriWithDressingID(long dressingid) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_DRESSINGID, String.valueOf(dressingid))
                    .build();
        }
        public static String getDressingSkinColorFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_DRESSINGID);
        }
    }

    public static final class DressingBodyShapeEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DRESSING_BODYSHAPE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRESSING_BODYSHAPE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRESSING_BODYSHAPE;

        public static final String TABLE_NAME = "dressing_bodyshape";

        public static final String COLUMN_DRESSINGID = "dressingid";
        public static final String COLUMN_BODYSHAPE = "bodyshape";

        public static Uri buildBodyShapeUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildBodyShapeUriWithDressingID(long dressingid) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_DRESSINGID, String.valueOf(dressingid))
                    .build();
        }
        public static String getDressingBodyShapeFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_DRESSINGID);
        }
    }

    public static final class DressingHairStyleEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DRESSING_HAIRSTYLE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRESSING_HAIRSTYLE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRESSING_HAIRSTYLE;

        public static final String TABLE_NAME = "dressing_hairstyle";

        public static final String COLUMN_DRESSINGID = "dressingid";
        public static final String COLUMN_HAIRSTYLE = "hairstyle";

        public static Uri buildHairStyleTipUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildHairStyleUriWithDressingID(long dressingid) {

            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_DRESSINGID, String.valueOf(dressingid))
                    .build();
        }

        public static String getDressingHairStyleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_DRESSINGID);
        }
    }

    public static final class DressingSkinTypeEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DRESSING_SKINTYPE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRESSING_SKINTYPE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRESSING_SKINTYPE;

        public static final String TABLE_NAME = "dressing_skintype";

        public static final String COLUMN_DRESSINGID = "dressingid";
        public static final String COLUMN_SKINTYPE = "skintype";

        public static Uri buildSkinTypeUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildSkinTypeUriWithDressingID(long dressingid) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_DRESSINGID, String.valueOf(dressingid))
                    .build();
        }
        public static String getDressingSkinTypeFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_DRESSINGID);
        }
    }

    public static final class BeautySalonEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BEAUTY_SALON).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BEAUTY_SALON;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BEAUTY_SALON;

        public static final String TABLE_NAME = "beautysalon";

        public static final String COLUMN_ID = "saloon_id";
        public static final String COLUMN_SALON_NAME = "saloon_name";
        public static final String COLUMN_DIRECTION_TO_SALON = "direction_to_saloon";
        public static final String COLUMN_FULL_ADDRESS = "full_address";
        public static final String COLUMN_PHOTO = "photos";
        public static final String COLUMN_OPEN = "open_daily";

        public static Uri buildBeautySalonUri(long id) {

            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static Uri buildBeautySalonUriWithId(long salon_id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions?title="Yangon"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_ID, String.valueOf(salon_id))
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_ID);
        }
    }

    public static final class SalonServicesEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SALON_SERVICES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SALON_SERVICES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SALON_SERVICES;

        public static final String TABLE_NAME = "salon_services";

        public static final String COLUMN_SERVICE_ID = "service_id";
        public static final String COLUMN_SALON_ID = "saloon_id";
        public static final String COLUMN_SERVICE_TITLE = "service_title";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_DESCRIPTION = "description";

        public static Uri buildBeautySalonUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static Uri buildBeautySalonUriWithId(long salon_id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions?title="Yangon"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_SALON_ID, String.valueOf(salon_id))
                    .build();
        }
        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_SALON_ID);
        }
    }

    public static final class FashionshopEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FASHION_SHOP).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FASHION_SHOP;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FASHION_SHOP;

        public static final String TABLE_NAME = "fashionshop";

        public static final String COLUMN_SHOP_ID = "shop_id";
        public static final String COLUMN_SHOP_NAME = "shop_name";
        public static final String COLUMN_DIRECTION_TO_SHOP = "direction_to_shop";
        public static final String COLUMN_ADDRESS = "full_address";
        public static final String COLUMN_PHOTO = "photos";

        public static Uri buildFashionshopUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_SHOP_ID);
        }
    }

    public static final class BookMarkEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BOOKMARK).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKMARK;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKMARK;

        public static final String TABLE_NAME = "bookmark";

        public static final String COLUMN_BOOKMARKID = "bookmarkid";
        public static final String COLUMN_BOOKMARKSCREENNAME = "screenname";
        public static final String COLUMN_BOOKMARKTITLE = "bookmarktitle";
        public static final String COLUMN_IMAGE = "image";


        public static Uri buildBookMarkUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getTipFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_BOOKMARKID);
        }
    }

}