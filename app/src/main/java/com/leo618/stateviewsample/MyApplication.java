package com.leo618.stateviewsample;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.leo618.stateview.StateViewCreator;
import com.leo618.stateview.StateViewHelper;

/**
 * function:应用程序入口
 *
 * <p>
 * Created by Leo on 2017/11/23.
 */
public class MyApplication extends Application {

    static {
        StateViewHelper.setStateViewCreator(new StateViewCreator() {
            @Override
            public View createStateViewLoading(Context context) {
                return super.createStateViewLoading(context);
            }

            @Override
            public View createStateViewEmpty(Context context) {
                return super.createStateViewEmpty(context);
            }

            @Override
            public View createStateViewNetError(Context context) {
                return super.createStateViewNetError(context);
            }

            @Override
            public View createStateViewError(Context context) {
                return super.createStateViewError(context);
            }
        });
    }
}
