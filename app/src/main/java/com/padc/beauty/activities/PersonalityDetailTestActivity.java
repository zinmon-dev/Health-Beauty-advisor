package com.padc.beauty.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.components.PageIndicatorView;
import com.padc.beauty.fragments.PersonalityDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by windows on 10/1/2016.
 */
public class PersonalityDetailTestActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pager_personalitydetail)
    ViewPager pagerpersonalitydetail;

    @BindView(R.id.pi_personailty_slider)
    PageIndicatorView piAttractionImageSlider;
//    @BindView(R.id.btn_next)
//    Button btnnext;
//
//    @BindView(R.id.btn_previous)
//    Button btnprevious;
    private long tipid;
    private int presonalityIndex = -1;

    public static Intent newIntent(Long tipid) {
        Intent intent = new Intent(BeautyApp.getContext(), PersonalityDetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_detail_test);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            //presonalityIndex++;
            navigateToPersonality(presonalityIndex);
        }
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Toast.makeText(getApplicationContext(), R.string.msg_no_more_joke, Toast.LENGTH_SHORT).show();
    }

//    @OnClick(R.id.btn_next)
//    public void  onTapNext(){
//        presonalityIndex++;
//        if (presonalityIndex < 3) {
//            navigateToPersonality(presonalityIndex);
//        } else {
//            presonalityIndex =2;
//            //Toast.makeText(getApplicationContext(), R.string.msg_no_more_joke, Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @OnClick(R.id.btn_previous)
//    public void  onTapPrevious(){
//        presonalityIndex--;
//        if (presonalityIndex >=0) {
//            navigateToPersonality(presonalityIndex);
//        } else {
//            presonalityIndex =0;
//            //Toast.makeText(getApplicationContext(), R.string.msg_no_more_joke, Toast.LENGTH_SHORT).show();
//        }
//    }

    private void navigateToPersonality(int presonalityIndex) {
        //PersonalityDetailPagerAdapter.setNumPage();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, PersonalityDetailFragment.newInstance(tipid,presonalityIndex))
                .commit();
    }
}
