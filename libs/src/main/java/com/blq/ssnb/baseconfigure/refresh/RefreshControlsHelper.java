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
public abstract class RefreshControlsHelper<V> extends ControlsHelper<V> {

    public RefreshControlsHelper(V controls) {
        super(controls);
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

    @Override
    protected void helperEnableChange(boolean isHelpEnable) {
        if (!isHelpEnable) {//被禁用的话
            if (isRefreshing()) {//判断是否处于刷新状态
                closeRefreshing();//结束刷新
            }
            lastControlEnableState = getControlsEnable();
            setControlsEnable(false);//更新控件的可用状态
        } else {
            setControlsEnable(lastControlEnableState);//更新控件的可用状态
        }
    }
}
