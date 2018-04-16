package com.leo618.stateviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.leo618.stateview.StateViewHelper;

/**
 * function:测试StateViewHelper
 *
 * <p>
 * Created by Leo on 2017/11/23.
 */
public class StateActivity extends AppCompatActivity {

    private StateViewHelper mStateViewHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        FrameLayout container = findViewById(R.id.mContainer);
        mStateViewHelper = new StateViewHelper(container).onReloadClickListener(mClickListener);
        mStateViewHelper.stateLoading();
        requestDatas();
    }

    //视图重加载点击事件
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestDatas();
        }
    };

    //网络请求数据
    private void requestDatas() {
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                //网络请求数据返回结果
                mStateViewHelper.stateNormal();
            }
        }, 1500);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_state, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.stateNormal://显示数据视图
                mStateViewHelper.stateNormal();
                break;
            case R.id.stateLoading://显示加载中视图
                mStateViewHelper.stateLoading();
                break;
            case R.id.stateEmpty://显示空数据视图
                mStateViewHelper.stateEmpty();
                break;
            case R.id.stateNetError://显示网络错误视图
                mStateViewHelper.stateNetError();
                break;
            case R.id.stateError://显示加载错误视图
                mStateViewHelper.stateError();
                break;
        }
        return true;
    }

    public void dataLayoutViewClick(View view) {
        Toast.makeText(this, "dataLayoutViewClick", Toast.LENGTH_SHORT).show();
    }
}
