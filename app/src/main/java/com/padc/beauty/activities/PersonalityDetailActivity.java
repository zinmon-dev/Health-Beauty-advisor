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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.beauty.BeautyApp;
import com.padc.beauty.R;
import com.padc.beauty.adapters.PersonalityDetailPagerAdapter;
import com.padc.beauty.data.models.PersonalityDetailModel;
import com.padc.beauty.data.vos.PerDetailVO;
import com.padc.beauty.data.vos.PersonalityDetailVO;
import com.padc.beauty.fragments.PersonalityDetailFragment;
import com.padc.beauty.fragments.WorkoutDetailFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by windows on 9/17/2016.
 */
public class PersonalityDetailActivity extends AppCompatActivity {
    private static final String IE_TIPID = "IE_TIPID";
    private static final String IE_TITLE = "IE_TITLE";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_personalitytitle)
    TextView tvpersonalitytitle;
//    @BindView(R.id.pager_personalitydetail)
//    ViewPager pagerpersonalitydetail;
    @BindView(R.id.btn_next)
    Button btnnext;

    @BindView(R.id.btn_previous)
    Button btnprevious;
    private List<PerDetailVO> mperdtllist;
    private int presonalityIndex = -1;
    private long tipid;
    private PersonalityDetailVO mperDtlVO;

    public static Intent newIntent(Long tipid,String title) {
        Intent intent = new Intent(BeautyApp.getContext(), PersonalityDetailActivity.class);
        intent.putExtra(IE_TIPID, tipid);
        intent.putExtra(IE_TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            tipid=getIntent().getExtras().getLong(IE_TIPID);
            tvpersonalitytitle.setText(getIntent().getStringExtra(IE_TITLE));
          //  mperDtlVO = PersonalityDetailModel.getInstance().getPersonalityByID(tipid);

          //  List<PerDetailVO> perDtlVOList=mperDtlVO.getPersondtlVO();
            presonalityIndex++;
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

    @OnClick(R.id.btn_next)
    public void  onTapNext(){
        presonalityIndex++;
        if (presonalityIndex <3) {
            navigateToPersonality(presonalityIndex);
        } else {
            presonalityIndex =2;
            //Toast.makeText(getApplicationContext(), R.string.msg_no_more_joke, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_previous)
    public void  onTapPrevious(){
        presonalityIndex--;
        if (presonalityIndex >=0) {
            navigateToPersonality(presonalityIndex);
        } else {
            presonalityIndex =0;
            //Toast.makeText(getApplicationContext(), R.string.msg_no_more_joke, Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToPersonality(int presonalityIndex) {
        //PersonalityDetailPagerAdapter.setNumPage();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, PersonalityDetailFragment.newInstance(tipid,presonalityIndex))
                .commit();
    }
}
