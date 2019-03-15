package com.blq.ssnb.baseconfigure.refresh;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/3/15
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 用于存放加载更多的控件
 * ================================================
 * </pre>
 */
public abstract class LoadMoreControlsHelper<T> {

    private T mLoadMoreControls;

    /**
     * 是否可用
     */
    private boolean isHelpEnable = true;

    public LoadMoreControlsHelper(T controls) {
        if (controls == null) {
            throw new NullPointerException("加载更多的控件不能为null");
        }
        this.mLoadMoreControls = controls;
        initLoadMoreControls(mLoadMoreControls);
    }

    /**
     * 对加载更多的控件的一些初始化
     *
     * @param controls 加载更多控件
     */
    private void initLoadMoreControls(T controls) {

    }

    public T getLoadMoreControls() {
        return mLoadMoreControls;
    }

    /**
     * 加载完成的时候调用
     * 表示还有下一次加载
     */
    public abstract void loadComplete();

    /**
     * 加载结束的时候调用
     * 表示没有更多的加载
     */
    public abstract void loadEnd();

    /**
     * 加载失败的时候调用
     */
    public abstract void loadFail();


    /**
     * 当前控件是否处在加载中状态
     *
     * @return true:正在加载中状态
     */
    public abstract boolean isLoading();

    /**
     * 关闭控件的加载中状态
     */
    public abstract void closeLoading();

    /**
     * 打开控件的正在加载状态
     */
    public abstract void openLoading();


    /**
     * 设置加载更多控件是否可用
     *
     * @param enable true:可用
     */
    public abstract void setLoadMoreControlsEnable(boolean enable);

    /**
     * 获得当前控件是否可用
     *
     * @return true 可用
     */
    public abstract boolean getLoadMoreControlsEnable();

    /**
     * 设置控件help是否启用
     *
     * @param enable true: 启用,false:不启用，需要使用的时候判断然后关闭一些刷新操作
     */
    public void setHelpEnable(boolean enable) {
        this.isHelpEnable = enable;
        if (!isHelpEnable) {//当设置为禁用后
            if (isLoading()) {//判断是否处于加载更多状态
                closeLoading();//结束加载更多
            }
            setLoadMoreControlsEnable(false);//禁用控件的加载更多功能
        }
    }

    /**
     * 当前help是否可用
     *
     * @return true:可用
     */
    public boolean getHelperEnable() {
        return isHelpEnable;
    }
}
