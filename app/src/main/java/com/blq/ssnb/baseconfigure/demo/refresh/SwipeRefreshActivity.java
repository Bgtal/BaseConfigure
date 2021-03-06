package com.blq.ssnb.baseconfigure.demo.refresh;

import android.graphics.Color;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blq.ssnb.baseconfigure.R;

import java.util.List;

import blq.ssnb.baseconfigure.BaseActivity;
import blq.ssnb.baseconfigure.refresh.OnRefreshListener;
import blq.ssnb.baseconfigure.refresh.RefreshControlsHelper;
import blq.ssnb.baseconfigure.refresh.RefreshLogicHelper;
import blq.ssnb.snbutil.SnbDateCreateUtil;
import blq.ssnb.snbutil.SnbToast;


/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/4/10
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class SwipeRefreshActivity extends BaseActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView contentListView;

    private RefreshLogicHelper<List<String>> mRefreshLogicHelper;
    private MAdapter mMAdapter;

    @Override
    protected int contentView() {
        return R.layout.activity_swipe_refresh;
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = findViewById(R.id.srl_refresh_layout);
        contentListView = findViewById(R.id.rv_content_list);
    }

    @Override
    protected void bindData() {
        mRefreshLogicHelper = new RefreshLogicHelper<>();
        mRefreshLogicHelper.setRefreshControlsHelper(new SwipeRefreshControlsHelper(mSwipeRefreshLayout));
        mRefreshLogicHelper.setOnRefreshListener(new OnRefreshListener<List<String>>() {
            @Override
            public void requestRefresh() {
                //这里触发的请求数据的方法
                requestData(data -> {
                            //这里请求数据的事件回调，这时候需要触发刷新的后半段逻辑
                            //成功或失败
                            if (System.currentTimeMillis() % 2 == 0) {
                                mRefreshLogicHelper.onRefreshSuccess(data);
                            } else {
                                mRefreshLogicHelper.onRefreshFail(1001, "失败咯！");
                            }
                        }
                );
            }

            @Override
            public void onRefreshSuccess(List<String> data) {
                mMAdapter.replaceData(data);
            }

            @Override
            public void onRefreshFail(int errorCode, String errorMsg) {
                SnbToast.showSmart("刷新失败:" + errorMsg);
            }
        });

        contentListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMAdapter = new MAdapter();
        contentListView.setAdapter(mMAdapter);
    }

    protected void requestData(SnbDateCreateUtil.DataCallBack<List<String>> callBack) {
        SnbDateCreateUtil.asyCreateListData(
                new SnbDateCreateUtil.DataCreateFactory<String>() {
                    @Override
                    public String createData(int index) {

                        return "我是数据:" + index;
                    }

                    @Override
                    public long minDelayTime() {
                        return 500;
                    }

                    @Override
                    public int dataSize() {
                        return 30;
                    }
                },
                callBack);
    }

    @Override
    protected void bindEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            //这里触发刷,开始整个刷新逻辑
            mRefreshLogicHelper.performOnRefresh();
        });
    }

    @Override
    protected void operation() {
        super.operation();
        mSwipeRefreshLayout.postDelayed(() -> {
            mRefreshLogicHelper.performOnRefresh();
        },1000);
    }

    public static class SwipeRefreshControlsHelper extends RefreshControlsHelper<SwipeRefreshLayout> {

        public SwipeRefreshControlsHelper(SwipeRefreshLayout controls) {
            super(controls);
        }

        @Override
        protected void initControl(SwipeRefreshLayout control) {
            control.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE);
        }

        @Override
        public boolean isRefreshing() {
            return getControl().isRefreshing();
        }

        @Override
        public void closeRefreshing() {
            getControl().setRefreshing(false);
        }

        @Override
        public void openRefreshing() {
            getControl().setRefreshing(true);
        }

        @Override
        public void setControlsEnable(boolean enable) {
            if (!enable && getControl().isRefreshing()) {
                getControl().setRefreshing(false);
            }
            getControl().setEnabled(enable);
        }

        @Override
        public boolean getControlsEnable() {
            return getControl().isEnabled();
        }
    }
}
