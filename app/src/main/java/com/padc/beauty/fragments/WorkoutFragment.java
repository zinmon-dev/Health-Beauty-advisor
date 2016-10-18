package com.padc.beauty.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.beauty.R;
import com.padc.beauty.activities.SpecialtipActivity;
import com.padc.beauty.activities.WorkoutDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Asus on 9/11/2016.
 */
public class WorkoutFragment extends Fragment {
    @BindView(R.id.tv_workout_main_title)
    TextView workoutMainTitle;
    @BindView(R.id.tv_workout_desc)
    TextView workoutDesc;
    @BindView(R.id.tv_workout_desc1)
    TextView workoutDesc1;
    @BindView(R.id.tv_workout_desc2)
    TextView workoutDesc2;
    @BindView(R.id.tv_workout_desc3)
    TextView workoutDesc3;
    @BindView(R.id.tv_workout_desc4)
    TextView workoutDesc4;


    public static WorkoutFragment newInstance(){
        WorkoutFragment workoutFragment = new WorkoutFragment();
        return workoutFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_workout, container, false);
        ButterKnife.bind(this, rootView);
        showdata();
        return rootView;
    }

    private void showdata()
    {
        workoutMainTitle.setText(Html.fromHtml(getString(R.string.workout_main_title)));
        workoutDesc.setText(Html.fromHtml(getString(R.string.workout_desc_for_pearbdtype)));
        workoutDesc1.setText(Html.fromHtml(getString(R.string.workout_desc_for_straightbdtype)));
        workoutDesc2.setText(Html.fromHtml(getString(R.string.workout_desc_for_athleticbdtype)));
        workoutDesc3.setText(Html.fromHtml(getString(R.string.workout_desc_for_curvybdtype)));
        workoutDesc4.setText(Html.fromHtml(getString(R.string.workout_desc_for_pearbdtype)));
    }
//    @OnClick(R.id.workoutlist)
//    public void onTapDetailWorkoutList(View view) {
//        //Toast.makeText(getContext(),"specific tips",Toast.LENGTH_SHORT).show();
////        Fragment fr=new WorkoutDetailFragment();
////        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
////        fc.replaceFragment(fr);
//        Intent intent = WorkoutDetailActivity.newIntent();
//        startActivity(intent);
//    }
}
