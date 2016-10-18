package com.padc.beauty.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.adapters.FitnessandHealthDetailAdapter;
import com.padc.beauty.adapters.FitnessandhealthAdapter;
import com.padc.beauty.data.models.TipModel;
import com.padc.beauty.data.persistence.BeautyContract;
import com.padc.beauty.data.vos.TipVO;
import com.padc.beauty.events.DataEvent;
import com.padc.beauty.fragments.WorkoutDetailFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 9/15/2016.
 */
public class WorkoutDetailActivity extends AppCompatActivity{


    private static final String IE_TIP_ID = "TIP_ID";
    private Long mTipId;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private TipVO tipVO;
    private List<TipVO> mtiplist;


    public static Intent newIntent(long tipid) {
        Intent intent = new Intent(BeautyApp.getContext(), WorkoutDetailActivity.class);
        intent.putExtra(IE_TIP_ID,tipid);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mTipId = getIntent().getExtras().getLong(IE_TIP_ID);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, WorkoutDetailFragment.newInstance(mTipId))
                    .commit();
        }
    }

    private void binddata(){

    }


}
