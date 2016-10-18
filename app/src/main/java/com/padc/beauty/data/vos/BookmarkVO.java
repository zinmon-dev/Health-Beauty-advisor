package com.padc.beauty.data.vos;

import android.content.ContentValues;
import android.database.Cursor;

import com.padc.beauty.data.persistence.BeautyContract;

/**
 * Created by windows on 10/7/2016.
 */
public class BookmarkVO {
    private long bookmarkid;
    private String bookmarktitle;
    private  String bookmarkimage;
    private String bookmarkscreen;
    public  BookmarkVO()
    {

    }
    public BookmarkVO(long bookmarkid,String bookmarktitle,String image,String bookmarkscreen) {
        this.bookmarkid = bookmarkid;
        this.bookmarktitle=bookmarktitle;
        this.bookmarkimage=image;
        this.bookmarkscreen=bookmarkscreen;
    }

    public long getBookmarkid() {
        return bookmarkid;
    }

    public void setBookmarkid(long bookmarkid) {
        this.bookmarkid = bookmarkid;
    }

    public String getBookmarktitle() {
        return bookmarktitle;
    }

    public void setBookmarktitle(String bookmarktitle) {
        this.bookmarktitle = bookmarktitle;
    }

    public String getBookmarkimage() {
        return bookmarkimage;
    }

    public void setBookmarkimage(String bookmarkimage) {
        this.bookmarkimage = bookmarkimage;
    }

    public String getBookmarkscreen() {
        return bookmarkscreen;
    }

    public void setBookmarkscreen(String bookmarkscreen) {
        this.bookmarkscreen = bookmarkscreen;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookmarkVO other = (BookmarkVO) obj;
        if (bookmarkid != other.bookmarkid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Bookmark [id=" + bookmarkid + ", title=" + bookmarktitle + "]";
    }

    public ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(BeautyContract.BookMarkEntry.COLUMN_BOOKMARKID,bookmarkid);
        cv.put(BeautyContract.BookMarkEntry.COLUMN_BOOKMARKTITLE,bookmarktitle);
        cv.put(BeautyContract.BookMarkEntry.COLUMN_IMAGE, bookmarkimage);
        cv.put(BeautyContract.BookMarkEntry.COLUMN_BOOKMARKSCREENNAME, bookmarkscreen);
        return cv;
    }

    public static BookmarkVO parseFromCursor(Cursor data) {
        BookmarkVO bookmark = new BookmarkVO();
        bookmark.bookmarkid = data.getLong(data.getColumnIndex(BeautyContract.BookMarkEntry.COLUMN_BOOKMARKID));
        bookmark.bookmarktitle = data.getString(data.getColumnIndex(BeautyContract.BookMarkEntry.COLUMN_BOOKMARKTITLE));
        bookmark.bookmarkimage = data.getString(data.getColumnIndex(BeautyContract.BookMarkEntry.COLUMN_IMAGE));
        bookmark.bookmarkscreen=data.getString(data.getColumnIndex(BeautyContract.BookMarkEntry.COLUMN_BOOKMARKSCREENNAME));
        return bookmark;
    }
}
