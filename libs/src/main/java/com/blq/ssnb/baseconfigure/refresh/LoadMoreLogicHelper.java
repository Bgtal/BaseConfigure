package com.blq.ssnb.baseconfigure.refresh;

import com.blq.ssnb.baseconfigure.LogManager;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/3/28
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class LoadMoreLogicHelper<D> {

    public static final String TAG = "RefreshLoadMore";
    private LoadMoreControlsHelper mLoadMoreControlsHelper;

    private OnLoadMoreListener<D> mOnLoadMoreListener;

    public LoadMoreLogicHelper(LoadMoreControlsHelper loadMoreControlsHelper) {
        setLoadMoreControlsHelper(loadMoreControlsHelper);
    }

    public LoadMoreLogicHelper() {
        this(null);
    }

    public void setLoadMoreControlsHelper(LoadMoreControlsHelper loadMoreControlsHelper) {
        mLoadMoreControlsHelper = loadMoreControlsHelper;
    }

    public void setOnLoadMoreListener(OnRefreshAndLoadMoreListener<D> onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    private boolean isHelperCanUse() {
        return mLoadMoreControlsHelper != null;
    }

    public void performOnLoadMore() {
        Log("调用加载更多>>>>>>");
        if (isHelperCanUse()) {
            if (mLoadMoreControlsHelper.getHelperEnable()) {
                if (!mLoadMoreControlsHelper.isLoading()) {
                    Log("打开加载更多状态");
                    mLoadMoreControlsHelper.openLoading();
                }
                if (mOnLoadMoreListener != null) {
                    Log("调用请求加载更多方法");
                    mOnLoadMoreListener.requestLoadMore();
                }
            } else {
                Log("加载更多方法被禁用");
                if (mLoadMoreControlsHelper.isLoading()) {
                    mLoadMoreControlsHelper.closeLoading();
                    Log("关闭加载更多");
                }
            }
        } else {
            if (mOnLoadMoreListener != null) {
                Log("调用请求加载更多方法");
                mOnLoadMoreListener.requestLoadMore();
            }
        }
        Log("调用加载更多<<<<<<");
    }


    public void onLoadMoreSuccess(D data) {

        Log("加载更多成功>>>>>>");
        boolean canLoadMore = true;
        if (mOnLoadMoreListener != null) {
            Log("通知加载成功");
            mOnLoadMoreListener.onLoadSuccess(data);
            canLoadMore = mOnLoadMoreListener.canLoadMore(data);
        }
        if (isHelperCanUse()) {
            if (canLoadMore) {
                Log("调用加载完成方法:loadComplete");
                mLoadMoreControlsHelper.loadComplete();
            } else {
                Log("调用加载结束方法:loadEnd");
                mLoadMoreControlsHelper.loadEnd();
            }
        }
        Log("加载更多成功<<<<<<");
    }

    public void onLoadMoreFail(int errorCode, String msg) {
        Log("加载更多失败>>>>>>");
        if (mOnLoadMoreListener != null) {
            Log("通知加载失败");
            mOnLoadMoreListener.onLoadFail(errorCode, msg);
        }
        if (isHelperCanUse()) {
            mLoadMoreControlsHelper.loadFail();
        }
        Log("加载更多失败<<<<<<");

    }

    private void Log(String msg) {
        LogManager.i(TAG + ":" + msg);
    }
}

