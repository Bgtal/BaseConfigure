package com.blq.ssnb.baseconfigure.refresh;

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
public abstract class ControlsHelper<V> {
    /**
     * 控件
     */
    private V mControl;

    /**
     * 是否可用
     */
    private boolean isHelpEnable = true;
    /**
     * 当helper不可用或可用时候
     * 用于存储控件和恢复最后的状态
     */
    protected boolean lastControlEnableState = true;

    public ControlsHelper(V control) {
        if (control == null) {
            throw new NullPointerException("控件不能为null");
        }
        this.mControl = control;
        initControl(this.mControl);
    }

    /**
     * 对控件的一些初始化
     *
     * @param control 控件
     */
    protected void initControl(V control) {

    }

    /**
     * 获取当前控件
     *
     * @return 控件
     */
    public V getControl() {
        return mControl;
    }

    /**
     * 设置控件是否可用
     *
     * @param enable true:可用
     */
    public abstract void setControlsEnable(boolean enable);

    /**
     * 刷新控件是否可用
     *
     * @return true:可用
     */
    public abstract boolean getControlsEnable();


    /**
     * 设置控件help是否启用
     *
     * @param enable true: 启用,false:不启用，需要使用的时候判断然后关闭一些控件操作操作
     */
    public final void setHelpEnable(boolean enable) {
        if (this.isHelpEnable == enable) {//如果当前状态和传入状态一样就什么都不做
            return;
        }
        this.isHelpEnable = enable;
        helperEnableChange(isHelpEnable);
    }

    /**
     * 控件helper 是否可用 子控件需要改变一些状态
     *
     * @param isHelpEnable true 启用，false 不启用
     */
    protected abstract void helperEnableChange(boolean isHelpEnable);

    /**
     * 当前help是否可用
     *
     * @return true:可用
     */
    public boolean getHelperEnable() {
        return isHelpEnable;
    }

}
