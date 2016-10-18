package com.padc.beauty.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.padc.beauty.data.persistence.BeautyContract;
/**
 * Created by windows on 9/24/2016.
 */
public class BeautyDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 15;

    public static final String DATABASE_NAME = "beauty.db";

    private static final String SQL_CREATE_TIP_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.TipEntry.TABLE_NAME + " (" +
            BeautyContract.TipEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.TipEntry.COLUMN_TIPID + " LONG NOT NULL,"+
            BeautyContract.TipEntry.COLUMN_TITLE + " TEXT NOT NULL, "+
            BeautyContract.TipEntry.COLUMN_IMAGE + " TEXT NOT NULL, "+
            BeautyContract.TipEntry.COLUMN_DESC + " TEXT NULL, "+
            BeautyContract.TipEntry.COLUMN_CATEGORY + " TEXT NOT NULL, "+

            " UNIQUE (" + BeautyContract.TipEntry.COLUMN_TIPID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_TIP_SKIN_COLOR_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.TipSkinColorEntry.TABLE_NAME + " (" +
            BeautyContract.TipSkinColorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.TipSkinColorEntry.COLUMN_TIPID + " LONG NOT NULL,"+
            BeautyContract.TipSkinColorEntry.COLUMN_SKINCOLOR + " TEXT  NULL, "+


            " UNIQUE (" + BeautyContract.TipSkinColorEntry.COLUMN_TIPID + ","+
            BeautyContract.TipSkinColorEntry.COLUMN_SKINCOLOR+") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_TIP_BODY_SHAPE_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.TipBodyShapeEntry.TABLE_NAME + " (" +
            BeautyContract.TipBodyShapeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.TipBodyShapeEntry.COLUMN_TIPID + " LONG NOT NULL,"+
            BeautyContract.TipBodyShapeEntry.COLUMN_BODYSHAPE + " TEXT  NULL, "+


            " UNIQUE (" + BeautyContract.TipBodyShapeEntry.COLUMN_TIPID + ","+
            BeautyContract.TipBodyShapeEntry.COLUMN_BODYSHAPE+") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_TIP_HAIR_COLOR_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.TipHairColorEntry.TABLE_NAME + " (" +
            BeautyContract.TipHairColorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.TipHairColorEntry.COLUMN_TIPID + " LONG  NULL,"+
            BeautyContract.TipHairColorEntry.COLUMN_HAIRCOLOR + " TEXT  NULL, "+


            " UNIQUE (" + BeautyContract.TipHairColorEntry.COLUMN_TIPID + ","+
            BeautyContract.TipHairColorEntry.COLUMN_HAIRCOLOR+") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_TIP_SKIN_TYPE_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.TipSkinTypeEntry.TABLE_NAME + " (" +
            BeautyContract.TipSkinTypeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.TipSkinTypeEntry.COLUMN_TIPID + " LONG NOT NULL,"+
            BeautyContract.TipSkinTypeEntry.COLUMN_SKINTYPE + " TEXT  NULL, "+


            " UNIQUE (" + BeautyContract.TipSkinTypeEntry.COLUMN_TIPID + ","+
            BeautyContract.TipSkinTypeEntry.COLUMN_SKINTYPE+") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_TIP_FACE_TYPE_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.TipFaceTypeEntry.TABLE_NAME + " (" +
            BeautyContract.TipFaceTypeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.TipFaceTypeEntry.COLUMN_TIPID + " LONG NOT NULL,"+
            BeautyContract.TipFaceTypeEntry.COLUMN_FACETYPE + " TEXT  NULL, "+


            " UNIQUE (" + BeautyContract.TipFaceTypeEntry.COLUMN_TIPID + ","+
            BeautyContract.TipFaceTypeEntry.COLUMN_FACETYPE+") ON CONFLICT IGNORE" +
            " );";


    private static final String SQL_CREATE_DRESSING_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.DressingEntry.TABLE_NAME + " (" +
            BeautyContract.DressingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.DressingEntry.COLUMN_DRESSINGID + " INTEGER NOT NULL,"+
            BeautyContract.DressingEntry.COLUMN_TYPE + " TEXT NOT NULL, "+
            BeautyContract.DressingEntry.COLUMN_IMAGE + " TEXT NOT NULL, "+
            BeautyContract.DressingEntry.COLUMN_DESC + " TEXT NULL, "+
            BeautyContract.DressingEntry.COLUMN_PRICE + " TEXT NOT NULL, "+
            BeautyContract.DressingEntry.COLUMN_RATING + " TEXT NOT NULL, "+
            BeautyContract.DressingEntry.COLUMN_SHOPID + " LONG NOT NULL, "+
            BeautyContract.DressingEntry.COLUMN_SHOPNAME + " TEXT NOT NULL, "+
            BeautyContract.DressingEntry.COLUMN_DIRECTIONTOSHOP + " TEXT NOT NULL, "+
            BeautyContract.DressingEntry.COLUMN_PROMOTION + " TEXT NULL, "+

            " UNIQUE (" + BeautyContract.DressingEntry.COLUMN_DRESSINGID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_DRESSING_SKIN_COLOR_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.DressingSkinColorEntry.TABLE_NAME + " (" +
            BeautyContract.DressingSkinColorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.DressingSkinColorEntry.COLUMN_DRESSINGID + " INTEGER NOT NULL,"+
            BeautyContract.DressingSkinColorEntry.COLUMN_SKINCOLOR + " TEXT  NULL, "+


            " UNIQUE (" + BeautyContract.DressingSkinColorEntry.COLUMN_DRESSINGID + ","+
            BeautyContract.DressingSkinColorEntry.COLUMN_SKINCOLOR+") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_DRESSING_BODY_SHAPE_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.DressingBodyShapeEntry.TABLE_NAME + " (" +
            BeautyContract.DressingBodyShapeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.DressingBodyShapeEntry.COLUMN_DRESSINGID + " INTEGER NOT NULL,"+
            BeautyContract.DressingBodyShapeEntry.COLUMN_BODYSHAPE + " TEXT  NULL, "+


            " UNIQUE (" + BeautyContract.DressingBodyShapeEntry.COLUMN_DRESSINGID + ","+
            BeautyContract.DressingBodyShapeEntry.COLUMN_BODYSHAPE+") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_DRESSING_HAIR_STYLE_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.DressingHairStyleEntry.TABLE_NAME + " (" +
            BeautyContract.DressingHairStyleEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.DressingHairStyleEntry.COLUMN_DRESSINGID + " INTEGER  NULL,"+
            BeautyContract.DressingHairStyleEntry.COLUMN_HAIRSTYLE + " TEXT  NULL, "+


            " UNIQUE (" + BeautyContract.DressingHairStyleEntry.COLUMN_DRESSINGID + ","+
            BeautyContract.DressingHairStyleEntry.COLUMN_HAIRSTYLE+") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_DRESSING_SKIN_TYPE_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.DressingSkinTypeEntry.TABLE_NAME + " (" +
            BeautyContract.DressingSkinTypeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.DressingSkinTypeEntry.COLUMN_DRESSINGID + " INTEGER NOT NULL,"+
            BeautyContract.DressingSkinTypeEntry.COLUMN_SKINTYPE + " TEXT  NULL, "+


            " UNIQUE (" + BeautyContract.DressingSkinTypeEntry.COLUMN_DRESSINGID + ","+
            BeautyContract.DressingSkinTypeEntry.COLUMN_SKINTYPE+") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_BEAUTY_SALON_TABLE ="CREATE TABLE " + BeautyContract.BeautySalonEntry.TABLE_NAME + " (" +
        BeautyContract.BeautySalonEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        BeautyContract.BeautySalonEntry.COLUMN_SALON_NAME + " TEXT NOT NULL, "+
        BeautyContract.BeautySalonEntry.COLUMN_DIRECTION_TO_SALON + " TEXT NOT NULL, "+
        BeautyContract.BeautySalonEntry.COLUMN_FULL_ADDRESS + " TEXT NOT NULL, " +
        BeautyContract.BeautySalonEntry.COLUMN_PHOTO + " TEXT NOT NULL, "+
        BeautyContract.BeautySalonEntry.COLUMN_OPEN + " TEXT NOT NULL, "+

        " UNIQUE (" + BeautyContract.BeautySalonEntry.COLUMN_ID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_SALON_SERVICES_TABLE ="CREATE TABLE " + BeautyContract.SalonServicesEntry.TABLE_NAME + " (" +
            BeautyContract.SalonServicesEntry.COLUMN_SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.SalonServicesEntry.COLUMN_SALON_ID + " INT NOT NULL, "+
            BeautyContract.SalonServicesEntry.COLUMN_SERVICE_TITLE + " TEXT NOT NULL, "+
            BeautyContract.SalonServicesEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +
            BeautyContract.SalonServicesEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "+

            " UNIQUE (" + BeautyContract.SalonServicesEntry.COLUMN_SERVICE_ID + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_FASHION_SHOP_TABLE ="CREATE TABLE " + BeautyContract.FashionshopEntry.TABLE_NAME + " (" +
            BeautyContract.FashionshopEntry.COLUMN_SHOP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.FashionshopEntry.COLUMN_SHOP_NAME + " INT NOT NULL, "+
            BeautyContract.FashionshopEntry.COLUMN_DIRECTION_TO_SHOP + " TEXT NOT NULL, "+
            BeautyContract.FashionshopEntry.COLUMN_ADDRESS + " TEXT NOT NULL, " +
            BeautyContract.FashionshopEntry.COLUMN_PHOTO + " TEXT NOT NULL, "+

            " UNIQUE (" + BeautyContract.FashionshopEntry.COLUMN_SHOP_ID  + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_BOOKMARK_TABLE = "CREATE TABLE IF NOT EXISTS " + BeautyContract.BookMarkEntry.TABLE_NAME + " (" +
            BeautyContract.BookMarkEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BeautyContract.BookMarkEntry.COLUMN_BOOKMARKID + " LONG NOT NULL,"+
            BeautyContract.BookMarkEntry.COLUMN_BOOKMARKTITLE + " TEXT NOT NULL, "+
            BeautyContract.BookMarkEntry.COLUMN_IMAGE + " TEXT NOT NULL, "+
            BeautyContract.BookMarkEntry.COLUMN_BOOKMARKSCREENNAME + " TEXT NOT NULL, "+
            " UNIQUE (" + BeautyContract.BookMarkEntry.COLUMN_BOOKMARKID + ") ON CONFLICT IGNORE" +
            " );";

    public BeautyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(SQL_CREATE_TIP_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_TIP_SKIN_COLOR_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_TIP_SKIN_TYPE_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_TIP_BODY_SHAPE_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_TIP_FACE_TYPE_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_TIP_HAIR_COLOR_TABLE);

            sqLiteDatabase.execSQL(SQL_CREATE_DRESSING_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_DRESSING_SKIN_COLOR_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_DRESSING_SKIN_TYPE_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_DRESSING_BODY_SHAPE_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_DRESSING_HAIR_STYLE_TABLE);

            sqLiteDatabase.execSQL(SQL_CREATE_BEAUTY_SALON_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_SALON_SERVICES_TABLE);
            sqLiteDatabase.execSQL(SQL_CREATE_FASHION_SHOP_TABLE);

            sqLiteDatabase.execSQL(SQL_CREATE_BOOKMARK_TABLE);
        }
        catch (Exception e) {
            //This happens on every launch that isn't the first one.
            Log.w("SpotTheStation", "Error while creating db: " + e.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.TipEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.TipBodyShapeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.TipFaceTypeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.TipSkinColorEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.TipSkinTypeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.TipHairColorEntry.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.DressingEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.DressingSkinColorEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.DressingSkinTypeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.DressingHairStyleEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.DressingBodyShapeEntry.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.BeautySalonEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.SalonServicesEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BeautyContract.FashionshopEntry.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +BeautyContract.BookMarkEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
