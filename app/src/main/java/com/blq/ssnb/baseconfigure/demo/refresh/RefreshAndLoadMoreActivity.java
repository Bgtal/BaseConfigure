package com.blq.ssnb.baseconfigure.demo.refresh;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blq.ssnb.baseconfigure.R;

import java.util.List;

import blq.ssnb.baseconfigure.BaseActivity;
import blq.ssnb.baseconfigure.refresh.OnRefreshAndLoadMoreListener;
import blq.ssnb.baseconfigure.refresh.RefreshAndLoadMoreLogicHelper;
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
public class RefreshAndLoadMoreActivity extends BaseActivity {


    @Override
    protected int contentView() {
        return R.layout.activity_refresh_and_load_more;
    }

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView contentListView;

    private RefreshAndLoadMoreLogicHelper<List<String>> mRefreshAndLoadMoreLogicHelper;
    private MAdapter mMAdapter;

    @Override
    protected void initView() {
        mSwipeRefreshLayout = findViewById(R.id.srl_refresh_layout);
        contentListView = findViewById(R.id.rv_content_list);
    }

    @Override
    protected void bindData() {
        mMAdapter = new MAdapter();
        mRefreshAndLoadMoreLogicHelper = new RefreshAndLoadMoreLogicHelper<>();
        mRefreshAndLoadMoreLogicHelper.setRefreshControlsHelper(new SwipeRefreshActivity.SwipeRefreshControlsHelper(mSwipeRefreshLayout));
        mRefreshAndLoadMoreLogicHelper.setLoadMoreControlsHelper(new QuickLoadMoreActivity.QuickAdapterLoadMoreControlsHelper(mMAdapter));
        mRefreshAndLoadMoreLogicHelper.setRefreshAndLoadMoreListener(new OnRefreshAndLoadMoreListener<List<String>>() {
            @Override
            public void requestLoadMore() {
                requestData(data -> {
                            //这里请求数据的事件回调，这时候需要触发刷新的后半段逻辑
                            //成功或失败
                            if (System.currentTimeMillis() % 2 == 0) {
                                mRefreshAndLoadMoreLogicHelper.onLoadMoreSuccess(data);
                            } else {
                                mRefreshAndLoadMoreLogicHelper.onLoadMoreFail(1001, "加载失败咯！");
                            }
                        }
                );
            }

            @Override
            public void onLoadSuccess(List<String> data) {
                mMAdapter.addData(data);
            }

            @Override
            public void onLoadFail(int errorCode, String errorMsg) {
                SnbToast.showSmart("加载失败:" + errorMsg);

            }

            @Override
            public boolean canLoadMore(List<String> data) {
                return mMAdapter.getData().size() < 70;
            }

            @Override
            public void requestRefresh() {
                requestData(data -> {
                            //这里请求数据的事件回调，这时候需要触发刷新的后半段逻辑
                            //成功或失败
                            if (System.currentTimeMillis() % 2 == 0) {
                                mRefreshAndLoadMoreLogicHelper.onRefreshSuccess(data);
                            } else {
                                mRefreshAndLoadMoreLogicHelper.onRefreshFail(1001, "刷新失败咯！");
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
            mRefreshAndLoadMoreLogicHelper.performOnRefresh();
        });

        mMAdapter.setOnLoadMoreListener(() -> mRefreshAndLoadMoreLogicHelper.performOnLoadMore(), contentListView);

    }

    @Override
    protected void operation() {
        super.operation();
        mSwipeRefreshLayout.postDelayed(() -> {
            mRefreshAndLoadMoreLogicHelper.performOnRefresh();
        }, 1000);
    }
}
