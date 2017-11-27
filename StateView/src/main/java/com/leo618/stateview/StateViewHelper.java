package com.leo618.stateview;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

/**
 * function:状态视图显示帮助类.
 *
 * <p>
 * Created by Leo on 2017/11/23.
 */
@SuppressWarnings("unused")
public class StateViewHelper {
    private static final int STATE_NORMAL   = 1;
    private static final int STATE_LOADING  = 1 << 1;
    private static final int STATE_EMPTY    = 1 << 2;
    private static final int STATE_NETERROR = 1 << 3;
    private static final int STATE_ERROR    = 1 << 4;
    private          FrameLayout              mRootContainer;
    private          Context                  mContext;
    private volatile int                      mCurrentState;
    private          View.OnClickListener     mOnClickListener;
    private          FrameLayout.LayoutParams mLayoutParams;
    private int mGravity = Gravity.CENTER;

    public StateViewHelper(FrameLayout frameLayout) {
        mRootContainer = frameLayout;
        mContext = mRootContainer.getContext();
        mLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
    }

    /**
     * 设置在出现空视图或者错误时执行重新加载的点击事件操作
     */
    public StateViewHelper onReloadClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
        return this;
    }

    /**
     * 显示当前状态为：数据视图
     */
    public void stateNormal() {
        mCurrentState = STATE_NORMAL;
        notifyStateView();
    }

    /**
     * 显示当前状态为：加载中视图
     */
    public void stateLoading() {
        mCurrentState = STATE_LOADING;
        notifyStateView();
    }

    /**
     * 显示当前状态为：无数据空视图
     */
    public void stateEmpty() {
        mCurrentState = STATE_EMPTY;
        notifyStateView();
    }

    /**
     * 显示当前状态为：网络错误视图
     */
    public void stateNetError() {
        mCurrentState = STATE_NETERROR;
        notifyStateView();
    }

    /**
     * 显示当前状态为：加载出错视图
     */
    public void stateError() {
        mCurrentState = STATE_ERROR;
        notifyStateView();
    }

    /**
     * 自定义状态视图(优先级高于创建器)：加载中
     */
    public StateViewHelper setLoadingView(View view) {
        mViewLoading = view;
        focusInitItemView(mViewLoading);
        return this;
    }

    /**
     * 自定义状态视图(优先级高于创建器)：无数据
     */
    public StateViewHelper setEmptyView(View view) {
        mViewEmpty = view;
        focusInitItemView(mViewEmpty);
        mViewEmpty.setOnClickListener(mInternalClickListener);
        return this;
    }

    /**
     * 自定义状态视图(优先级高于创建器)：网络错误
     */
    public StateViewHelper setNetErrorView(View view) {
        mViewNetError = view;
        focusInitItemView(mViewNetError);
        mViewNetError.setOnClickListener(mNetErrorClickListener);
        return this;
    }

    /**
     * 自定义状态视图(优先级高于创建器)：加载错误
     */
    public StateViewHelper setErrorView(View view) {
        mViewError = view;
        focusInitItemView(mViewError);
        mViewError.setOnClickListener(mInternalClickListener);
        return this;
    }

    /**
     * 设置容器背景色
     */
    public StateViewHelper setBackgroundColor(int color) {
        mRootContainer.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置子视图位置
     */
    public StateViewHelper setGravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    /**
     * 设置状态视图创建器，可以在Application的静态代码块中进行全应用统一设置
     *
     * @param creator 状态视图创建器
     */
    public static void setStateViewCreator(StateViewCreator creator) {
        iStateViewCreator = creator;
    }

    //默认状态视图创建器
    private static StateViewCreator iStateViewCreator = new StateViewCreator() {
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
    };

    private View mViewLoading, mViewEmpty, mViewNetError, mViewError;

    private void notifyStateView() {
        if (iStateViewCreator == null) {
            throw new IllegalArgumentException("StateViewCreator can not be null.");
        }
        if (mViewLoading == null) {
            mViewLoading = iStateViewCreator.createStateViewLoading(mContext);
            focusInitItemView(mViewLoading);
        }
        if (mViewEmpty == null) {
            mViewEmpty = iStateViewCreator.createStateViewEmpty(mContext);
            focusInitItemView(mViewEmpty);
            mViewEmpty.setOnClickListener(mInternalClickListener);
        }
        if (mViewNetError == null) {
            mViewNetError = iStateViewCreator.createStateViewNetError(mContext);
            focusInitItemView(mViewNetError);
            mViewNetError.setOnClickListener(mNetErrorClickListener);
        }
        if (mViewError == null) {
            mViewError = iStateViewCreator.createStateViewError(mContext);
            focusInitItemView(mViewError);
            mViewError.setOnClickListener(mInternalClickListener);
        }
        mRootContainer.removeView(mViewLoading);
        mRootContainer.removeView(mViewEmpty);
        mRootContainer.removeView(mViewNetError);
        mRootContainer.removeView(mViewError);
        mLayoutParams.gravity = mGravity;
        switch (mCurrentState) {
            case STATE_NORMAL:
                break;
            case STATE_LOADING:
                mRootContainer.addView(mViewLoading, mLayoutParams);
                break;
            case STATE_EMPTY:
                mRootContainer.addView(mViewEmpty, mLayoutParams);
                break;
            case STATE_NETERROR:
                mRootContainer.addView(mViewNetError, mLayoutParams);
                break;
            case STATE_ERROR:
                mRootContainer.addView(mViewError, mLayoutParams);
                break;
        }
    }

    private void focusInitItemView(View view) {
        view.setClickable(true);
    }

    private View.OnClickListener mInternalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stateLoading();
            if (mOnClickListener != null) mOnClickListener.onClick(v);
        }
    };
    private View.OnClickListener mNetErrorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    };
}
