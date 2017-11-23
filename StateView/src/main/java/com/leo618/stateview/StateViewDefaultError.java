package com.leo618.stateview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * function:默认状态视图：加载错误
 *
 * <p>
 * Created by Leo on 2017/11/23.
 */
/*package*/ class StateViewDefaultError extends LinearLayout {
    public StateViewDefaultError(Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.state_view_layout_error, this, true);
    }
}
