package blq.ssnb.baseconfigure.refresh;

import blq.ssnb.baseconfigure.LogManager;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/3/28
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 单个刷新逻辑的helper
 * 如何想和加载更多逻辑一起使用的话，请更换RefreshAndLoadMoreLogicHelper
 * ================================================
 * </pre>
 */
public class RefreshLogicHelper<D> {

    public static final String TAG = "RefreshLoadMore";
    private RefreshControlsHelper mRefreshControlsHelper;
    private OnRefreshListener<D> mOnRefreshListener;

    public RefreshLogicHelper() {
        this(null);
    }

    public RefreshLogicHelper(RefreshControlsHelper refreshControlsHelper) {
        setRefreshControlsHelper(refreshControlsHelper);
    }

    public void setRefreshControlsHelper(RefreshControlsHelper refreshControlsHelper) {
        this.mRefreshControlsHelper = refreshControlsHelper;
    }

    private boolean isHelperCanUser() {
        return mRefreshControlsHelper != null;
    }

    public void performOnRefresh() {
        Log("调用刷新>>>>>>");
        if (isHelperCanUser()) {
            //如果可以刷新的话
            if (mRefreshControlsHelper.getHelperEnable()) {
                //且当前没有在刷新状态
                if (!mRefreshControlsHelper.isRefreshing()) {
                    //那么就打开刷新状态
                    Log("打开刷新状态");
                    mRefreshControlsHelper.openRefreshing();
                }
                if (mOnRefreshListener != null) {
                    mOnRefreshListener.requestRefresh();
                }

            } else {
                Log("刷新方法被禁用");
                if (mRefreshControlsHelper.isRefreshing()) {
                    mRefreshControlsHelper.closeRefreshing();
                    Log("关闭刷新方法");
                }
            }
        }
        Log("调用刷新<<<<<<");
    }

    public void onRefreshSuccess(D data) {

        Log("刷新成功>>>>>>");
        if (isHelperCanUser()) {
            //关闭刷新的方法
            Log("关闭刷新的方法");
            mRefreshControlsHelper.closeRefreshing();
            if (mOnRefreshListener != null) {
                mOnRefreshListener.onRefreshSuccess(data);
            }
        }
        Log("刷新成功<<<<<<");
    }

    public void onRefreshFail(int errorCode, String msg) {
        Log("刷新失败>>>>>>");
        if (isHelperCanUser()) {
            //关闭刷新动画
            Log("关闭刷新的方法");
            if (mRefreshControlsHelper.isRefreshing()) {
                mRefreshControlsHelper.closeRefreshing();
            }
        }
        if (mOnRefreshListener != null) {
            mOnRefreshListener.onRefreshFail(errorCode, msg);
        }
        Log("刷新失败<<<<<<");
    }

    private void Log(String msg) {
        LogManager.i(TAG + ":" + msg);
    }

    public void setOnRefreshListener(OnRefreshListener<D> onRefreshListener) {
        mOnRefreshListener = onRefreshListener;
    }
}
