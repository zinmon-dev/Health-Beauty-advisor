package com.padc.beauty.views.items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.padc.beauty.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by windows on 9/9/2016.
 */
public class ViewItemTipList extends FrameLayout {

    @BindView(R.id.tv_tip)
    TextView tvTip;

    public ViewItemTipList(Context context) {
        super(context);
    }

    public ViewItemTipList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewItemTipList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(String tips) {
        tvTip.setText(tips);
    }
}
