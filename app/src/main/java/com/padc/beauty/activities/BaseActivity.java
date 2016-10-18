package com.padc.beauty.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.utils.BeautyAppConstant;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asus on 10/2/2016.
 */
public class BaseActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 1001;
    private static final int REQUEST_IMAGE_CAPTURE_FULL_RESOLUTION = 1002;
    private static final int REQUEST_SELECT_IMAGE_ABOVE_KITKAT = 1003;
    private static final int REQUEST_SELECT_IMAGE = 1004;

    private String numberToCall = null;
    private String mCurrentPhotoPath;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void sendViaShareIntent(String msg) {
        startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(BaseActivity.this)
                .setType("text/plain")
                .setText(msg)
                .getIntent(), getString(R.string.action_share)));
    }
    public void sharemsg(String msg){
//        Toast.makeText(BeautyApp.getContext(), "share", Toast.LENGTH_SHORT).show();
//        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//        Uri screenshotUri = Uri.parse(MediaStore.Images.Media.EXTERNAL_CONTENT_URI + "/" + msg);
//
//        sharingIntent.setType("image/jpeg");
//        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
//
//        startActivity(Intent.createChooser(sharingIntent, "Share image using"));
       // Toast.makeText(BeautyApp.getContext(), "get activiyt", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT,msg);

        startActivity(Intent.createChooser(intent, "Share Image"));
    }
}
