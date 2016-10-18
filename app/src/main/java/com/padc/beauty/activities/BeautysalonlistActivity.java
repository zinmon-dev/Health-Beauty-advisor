package com.padc.beauty.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.padc.beauty.BeautyApp;

/**
 * Created by Asus on 10/2/2016.
 */
public class BeautysalonlistActivity extends AppCompatActivity {
    public static Intent newIntent() {
        Intent intent = new Intent(BeautyApp.getContext(), BeautysalonlistActivity.class);
        return intent;
    }
}