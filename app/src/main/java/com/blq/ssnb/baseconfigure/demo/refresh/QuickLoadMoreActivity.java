package com.blq.ssnb.baseconfigure.demo.refresh;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blq.ssnb.baseconfigure.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import blq.ssnb.baseconfigure.BaseActivity;
import blq.ssnb.baseconfigure.refresh.LoadMoreControlsHelper;
import blq.ssnb.baseconfigure.refresh.LoadMoreLogicHelper;
import blq.ssnb.baseconfigure.refresh.OnLoadMoreListener;
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
public class QuickLoadMoreActivity extends BaseActivity {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView contentListView;
    private LoadMoreLogicHelper<List<String>> mLoadMoreLogicHelper;
    private MAdapter mMAdapter;

    @Override
    protected int contentView() {
        return R.layout.activity_swipe_refresh;
    }

    @Override
    protected void initView() {
        contentListView = findViewById(R.id.rv_content_list);
        mRefreshLayout = findViewById(R.id.srl_refresh_layout);
    }

    @Override
    protected void bindData() {
        mMAdapter = new MAdapter();
        mLoadMoreLogicHelper = new LoadMoreLogicHelper<>();
        mLoadMoreLogicHelper.setLoadMoreControlsHelper(new QuickAdapterLoadMoreControlsHelper(mMAdapter));

        contentListView.setLayoutManager(new LinearLayoutManager(getContext()));
        contentListView.setAdapter(mMAdapter);

    }

    @Override
    protected void bindEvent() {
        mRefreshLayout.setEnabled(false);
        mLoadMoreLogicHelper.setOnLoadMoreListener(new OnLoadMoreListener<List<String>>() {
            @Override
            public void requestLoadMore() {
                requestData(data -> {
                    if (mMAdapter.getData().size() < 30) {
                        mLoadMoreLogicHelper.onLoadMoreSuccess(data);
                    } else {
                        if (System.currentTimeMillis() % 2 == 0) {
                            mLoadMoreLogicHelper.onLoadMoreSuccess(data);
                        } else {
                            mLoadMoreLogicHelper.onLoadMoreFail(123, "加载失败咯");
                        }
                    }

                });
            }

            @Override
            public void onLoadSuccess(List<String> data) {
                mMAdapter.addData(data);
            }

            @Override
            public void onLoadFail(int errorCode, String errorMsg) {
                SnbToast.showSmart("加载失败咯:" + errorMsg);
            }

            @Override
            public boolean canLoadMore(List<String> data) {
                return mMAdapter.getData().size() < 70;
            }
        });

        mMAdapter.setOnLoadMoreListener(() -> mLoadMoreLogicHelper.performOnLoadMore(), contentListView);
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
                        return 100;
                    }

                    @Override
                    public int dataSize() {
                        return 30;
                    }
                },
                callBack);
    }

    @Override
    protected void operation() {
        super.operation();
        mLoadMoreLogicHelper.performOnLoadMore();
    }

    public static class QuickAdapterLoadMoreControlsHelper extends LoadMoreControlsHelper<BaseQuickAdapter> {

        public QuickAdapterLoadMoreControlsHelper(BaseQuickAdapter controls) {
            super(controls);
        }

        @Override
        public void loadComplete() {
            getControl().loadMoreComplete();
        }

        @Override
        public void loadEnd() {
            getControl().loadMoreEnd();
        }

        @Override
        public void loadFail() {
            getControl().loadMoreFail();
        }

        @Override
        public boolean isLoading() {
            return getControl().isLoading();
        }

        @Override
        public void closeLoading() {
            getControl().loadMoreComplete();
        }

        @Override
        public void openLoading() {

        }

        @Override
        public void setControlsEnable(boolean enable) {
            if (getControl().isLoading()) {
                getControl().loadMoreComplete();
            }
            getControl().setEnableLoadMore(enable);
        }

        @Override
        public boolean getControlsEnable() {
            return getControl().isLoadMoreEnable();
        }
    }
}
