package com.leo618.stateview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * function:默认状态视图：网络错误
 *
 * <p>
 * Created by Leo on 2017/11/23.
 */
/*package*/ class StateViewDefaultNetError extends LinearLayout {
    public StateViewDefaultNetError(Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.state_view_layout_neterror, this, true);
    }
}
