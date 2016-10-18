package com.padc.beauty.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.padc.beauty.BeautyApp;

/**
 * Created by windows on 10/1/2016.
 */
public class FitnessandhealthActivity extends AppCompatActivity {
    public static Intent newIntent() {
        Intent intent = new Intent(BeautyApp.getContext(), FitnessandhealthActivity.class);
        return intent;
    }
}
