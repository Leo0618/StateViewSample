package com.leo618.stateview;

import android.content.Context;
import android.view.View;

/**
 * function:状态视图创建器接口,不设置返回默认
 *
 * <p>
 * Created by Leo on 2017/11/23.
 */
public abstract class StateViewCreator {
    /**
     * 创建加载中视图
     */
    public View createStateViewLoading(Context context) { return new StateViewDefaultLoading(context);}

    /**
     * 创建加载空视图
     */
    public View createStateViewEmpty(Context context) {return new StateViewDefaultEmpty(context);}

    /**
     * 创建加载网络错误视图
     */
    public View createStateViewNetError(Context context) {return new StateViewDefaultNetError(context);}

    /**
     * 创建加载加载错误视图
     */
    public View createStateViewError(Context context) {return new StateViewDefaultError(context);}
}
