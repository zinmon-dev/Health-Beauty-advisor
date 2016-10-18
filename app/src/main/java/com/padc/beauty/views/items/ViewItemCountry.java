package com.padc.beauty.views.items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.padc.beauty.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by aung on 7/15/16.
 */
public class ViewItemCountry extends FrameLayout {

    @BindView(R.id.tv_bodyshape)
    TextView tvBodyshape;

    public ViewItemCountry(Context context) {
        super(context);
    }

    public ViewItemCountry(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewItemCountry(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(String country) {
        tvBodyshape.setText(country);
    }
}