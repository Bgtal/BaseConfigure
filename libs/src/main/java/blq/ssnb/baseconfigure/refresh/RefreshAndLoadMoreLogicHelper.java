package blq.ssnb.baseconfigure.refresh;

import blq.ssnb.baseconfigure.LogManager;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/3/15
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class RefreshAndLoadMoreLogicHelper<D> {

    public static final String TAG = "RefreshLoadMore";
    private RefreshControlsHelper mRefreshControlsHelper;
    private LoadMoreControlsHelper mLoadMoreControlsHelper;

    private OnRefreshAndLoadMoreListener<D> mRefreshAndLoadMoreListener;

    public RefreshAndLoadMoreLogicHelper(RefreshControlsHelper refreshControlsHelper, LoadMoreControlsHelper loadMoreControlsHelper) {
        setRefreshControlsHelper(refreshControlsHelper);
        setLoadMoreControlsHelper(loadMoreControlsHelper);
    }

    public RefreshAndLoadMoreLogicHelper() {
        this(null, null);
    }

    public void setRefreshControlsHelper(RefreshControlsHelper refreshControlsHelper) {
        this.mRefreshControlsHelper = refreshControlsHelper;
    }

    public void setLoadMoreControlsHelper(LoadMoreControlsHelper loadMoreControlsHelper) {
        mLoadMoreControlsHelper = loadMoreControlsHelper;
    }

    public void setRefreshAndLoadMoreListener(OnRefreshAndLoadMoreListener<D> refreshAndLoadMoreListener) {
        mRefreshAndLoadMoreListener = refreshAndLoadMoreListener;
    }

    private boolean isRefreshHelperCanUser() {
        return mRefreshControlsHelper != null;
    }

    private boolean isLoadMoreHelperCanUser() {
        return mLoadMoreControlsHelper != null;
    }


    public void performOnRefresh() {
        Log("调用刷新>>>>>>");
        if (isRefreshHelperCanUser()) {
            //如果可以刷新的话
            if (mRefreshControlsHelper.getHelperEnable()) {
                //且当前没有在刷新状态
                if (!mRefreshControlsHelper.isRefreshing()) {
                    //那么就打开刷新状态
                    Log("打开刷新状态");
                    mRefreshControlsHelper.openRefreshing();
                }
                if (mLoadMoreControlsHelper != null) {
                    //当界面可以刷新的时候需要关闭
                    if (mLoadMoreControlsHelper.isLoading()) {
                        mLoadMoreControlsHelper.closeLoading();
                    }
                    Log("临时禁用加载更多控件");
                    mLoadMoreControlsHelper.setControlsEnable(false);

                } else {
                    Log("加载更多控件为空");
                }
                if (mRefreshAndLoadMoreListener != null) {
                    Log("调用请求刷新方法");
                    mRefreshAndLoadMoreListener.requestRefresh();
                }
            } else {
                Log("刷新方法被禁用");
                if (mRefreshControlsHelper.isRefreshing()) {
                    mRefreshControlsHelper.closeRefreshing();
                    Log("关闭刷新方法");
                }
            }
        } else {
            Log("不存在刷新控件");
            if (mRefreshAndLoadMoreListener != null) {
                //那么就直接调用请求刷新的方法
                Log("调用请求刷新方法");
                mRefreshAndLoadMoreListener.requestRefresh();
            }
        }
        Log("调用刷新<<<<<<");
    }

    public void performOnLoadMore() {
        Log("调用加载更多>>>>>>");
        if (isRefreshHelperCanUser()) {
            //如果刷新控件正在执行刷新的话就关闭它
            if (mRefreshControlsHelper.isRefreshing()) {
                mRefreshControlsHelper.closeRefreshing();
            }
        }
        if (isLoadMoreHelperCanUser()) {
            if (mLoadMoreControlsHelper.getHelperEnable()) {
                if (!mLoadMoreControlsHelper.isLoading()) {
                    Log("打开加载更多状态");
                    mLoadMoreControlsHelper.openLoading();
                }
                if (mRefreshAndLoadMoreListener != null) {
                    Log("调用请求加载更多方法");
                    mRefreshAndLoadMoreListener.requestLoadMore();
                }
            } else {
                Log("加载更多方法被禁用");
                if (mLoadMoreControlsHelper.isLoading()) {
                    mLoadMoreControlsHelper.closeLoading();
                    Log("关闭加载更多");
                }
            }
        } else {
            if (mRefreshAndLoadMoreListener != null) {
                Log("调用请求加载更多方法");
                mRefreshAndLoadMoreListener.requestLoadMore();
            }
        }
        Log("调用加载更多<<<<<<");
    }

    public void onRefreshSuccess(D data) {

        Log("刷新成功>>>>>>");
        if (isRefreshHelperCanUser()) {
            //关闭刷新的方法
            Log("关闭刷新的方法");
            mRefreshControlsHelper.closeRefreshing();
            if (isLoadMoreHelperCanUser()) {
                //打开加载更多的方法
                Log("启用加载更多的方法");
                mLoadMoreControlsHelper.setControlsEnable(true);
            } else {
                Log("加载更多控件为空");
            }

            boolean canLoadMore = true;
            if (mRefreshAndLoadMoreListener != null) {
                Log("通知刷新成功");
                mRefreshAndLoadMoreListener.onRefreshSuccess(data);
                canLoadMore = mRefreshAndLoadMoreListener.canLoadMore(data);
            }

            if (isLoadMoreHelperCanUser()) {
                if (canLoadMore) {
                    Log("调用加载完成方法:loadComplete");
                    mLoadMoreControlsHelper.loadComplete();
                } else {
                    //否者就调用加载结束
                    Log("调用加载结束方法:loadEnd");
                    mLoadMoreControlsHelper.loadEnd();
                }
            }
        } else {
            Log("不存在刷新控件");
            if (mRefreshAndLoadMoreListener != null) {
                Log("通知刷新成功");
                mRefreshAndLoadMoreListener.onRefreshSuccess(data);
            }
        }
        Log("刷新成功<<<<<<");
    }

    public void onRefreshFail(int errorCode, String msg) {
        Log("刷新失败>>>>>>");
        if (isRefreshHelperCanUser()) {
            //关闭刷新动画
            Log("关闭刷新的方法");
            if (mRefreshControlsHelper.isRefreshing()) {
                mRefreshControlsHelper.closeRefreshing();
            }
            //当刷新失败的时候,加载更多开启
            if (isLoadMoreHelperCanUser()) {
                Log("启用加载更多的方法");
                mLoadMoreControlsHelper.setControlsEnable(true);
            } else {
                Log("加载更多控件为空");
            }
            if (mRefreshAndLoadMoreListener != null) {
                Log("通知刷新失败");
                mRefreshAndLoadMoreListener.onRefreshFail(errorCode, msg);
            }
        } else {
            if (mRefreshAndLoadMoreListener != null) {
                Log("通知刷新失败");
                mRefreshAndLoadMoreListener.onRefreshFail(errorCode, msg);
            }
        }
        Log("刷新失败<<<<<<");
    }

    public void onLoadMoreSuccess(D data) {

        Log("加载更多成功>>>>>>");
        if (isRefreshHelperCanUser()) {
            //关闭刷新控件
            if (mRefreshControlsHelper.isRefreshing()) {
                mRefreshControlsHelper.closeRefreshing();
            }
        }

        boolean canLoadMore = true;
        if (mRefreshAndLoadMoreListener != null) {
            Log("通知加载成功");
            mRefreshAndLoadMoreListener.onLoadSuccess(data);
            canLoadMore = mRefreshAndLoadMoreListener.canLoadMore(data);
        }
        if (isLoadMoreHelperCanUser()) {
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
        if (isRefreshHelperCanUser()) {
            if (mRefreshControlsHelper.isRefreshing()) {
                mRefreshControlsHelper.closeRefreshing();
            }
        }
        if (mRefreshAndLoadMoreListener != null) {
            Log("通知加载失败");
            mRefreshAndLoadMoreListener.onLoadFail(errorCode, msg);
        }
        if (isLoadMoreHelperCanUser()) {
            mLoadMoreControlsHelper.loadFail();
        }
        Log("加载更多失败<<<<<<");

    }

    private void Log(String msg) {
        LogManager.i(TAG + ":" + msg);
    }
}
