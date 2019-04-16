package blq.ssnb.baseconfigure.demo.refresh;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

import java.util.List;

import blq.ssnb.baseconfigure.BaseActivity;
import blq.ssnb.baseconfigure.R;
import blq.ssnb.baseconfigure.refresh.OnRefreshListener;
import blq.ssnb.baseconfigure.refresh.RefreshControlsHelper;
import blq.ssnb.baseconfigure.refresh.RefreshLogicHelper;
import blq.ssnb.snbutil.SnbDateCreateUtil;
import blq.ssnb.snbutil.SnbLog;
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
public class SmaretSwipeRefreshActivity extends BaseActivity {

    private SmartRefreshLayout mSwipeRefreshLayout;
    private RecyclerView contentListView;

    private RefreshLogicHelper<List<String>> mRefreshLogicHelper;
    private MAdapter mMAdapter;

    @Override
    protected int contentView() {
        return R.layout.activity_smart_swipe_refresh;
    }


    @Override
    protected void initView() {
        mSwipeRefreshLayout = findViewById(R.id.srl_refresh_layout);
        contentListView = findViewById(R.id.rv_content_list);
    }

    @Override
    protected void bindData() {
        mRefreshLogicHelper = new RefreshLogicHelper<>();
        mRefreshLogicHelper.setRefreshControlsHelper(new SmartRefreshControlsHelper(mSwipeRefreshLayout));
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
        mSwipeRefreshLayout.setOnRefreshListener(refreshLayout -> {
            //这里触发刷,开始整个刷新逻辑
            SnbLog.e(">>>>触发刷新");
            mRefreshLogicHelper.performOnRefresh();
        });
    }

    @Override
    protected void operation() {
        super.operation();
        mSwipeRefreshLayout.postDelayed(() -> {
            mRefreshLogicHelper.performOnRefresh();
        }, 1000);
    }

    private static class SmartRefreshControlsHelper extends RefreshControlsHelper<SmartRefreshLayout> {

        public SmartRefreshControlsHelper(SmartRefreshLayout controls) {
            super(controls);
            controls.setRefreshHeader(new BezierRadarHeader(controls.getContext()).setEnableHorizontalDrag(true));
        }

        @Override
        public boolean isRefreshing() {
            return getControl().getState() == RefreshState.Refreshing
                    || getControl().getState() == RefreshState.RefreshReleased;
        }

        @Override
        public void closeRefreshing() {
            getControl().finishRefresh(500);
        }

        @Override
        public void openRefreshing() {
            getControl().autoRefreshAnimationOnly();
        }

        @Override
        public void setControlsEnable(boolean enable) {
            if (!enable) {
                closeRefreshing();
            }
            getControl().setEnabled(enable);
        }

        @Override
        public boolean getControlsEnable() {
            return getControl().isEnabled();
        }
    }
}
