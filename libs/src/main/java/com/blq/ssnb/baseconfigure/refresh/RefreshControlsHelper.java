package com.blq.ssnb.baseconfigure.refresh;

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
public abstract class RefreshControlsHelper<T> {

    /**
     * 刷新控件
     */
    private T mRefreshControls;

    /**
     * 是否可用
     */
    private boolean isHelpEnable = true;

    public RefreshControlsHelper(T controls) {
        if (controls == null) {
            throw new NullPointerException("刷新控件不能为null");
        }
        this.mRefreshControls = controls;
        initRefreshControls(mRefreshControls);
    }

    /**
     * 对刷新控件的一些初始化
     *
     * @param controls 刷新控件
     */
    private void initRefreshControls(T controls) {

    }


    /**
     * 获得刷新控件
     *
     * @return 刷新控件
     */
    public T getRefreshControls() {
        return mRefreshControls;
    }

    /**
     * 当前控件是否处于刷新状态
     *
     * @return true:正处于刷新状态
     */
    public abstract boolean isRefreshing();

    /**
     * 关闭刷新状态
     */
    public abstract void closeRefreshing();

    /**
     * 打开刷新状态
     */
    public abstract void openRefreshing();

    /**
     * 设置刷新控件是否可用
     *
     * @param enable true:可用
     */
    public abstract void setRefreshControlsEnable(boolean enable);

    /**
     * 刷新控件是否可用
     *
     * @return true:可用
     */
    public abstract boolean getRefreshControlsEnable();

    /**
     * 设置控件help是否启用
     *
     * @param enable true: 启用,false:不启用，需要使用的时候判断然后关闭一些刷新操作
     */
    public void setHelpEnable(boolean enable) {
        this.isHelpEnable = enable;
        if (!isHelpEnable) {//当设置为禁用后
            if (isRefreshing()) {//判断是否处于刷新状态
                closeRefreshing();//结束刷新
            }
            setRefreshControlsEnable(false);//禁用控件的刷新功能
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
