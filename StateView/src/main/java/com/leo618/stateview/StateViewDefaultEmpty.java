package com.leo618.stateview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * function:默认状态视图：空数据
 *
 * <p>
 * Created by Leo on 2017/11/23.
 */
/*package*/ class StateViewDefaultEmpty extends LinearLayout {
    public StateViewDefaultEmpty(Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.state_view_layout_empty, this, true);
    }
}
