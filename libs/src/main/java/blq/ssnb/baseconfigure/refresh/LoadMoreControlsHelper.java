package blq.ssnb.baseconfigure.refresh;

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
public abstract class LoadMoreControlsHelper<V> extends ControlsHelper<V> {

    public LoadMoreControlsHelper(V controls) {
        super(controls);
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


    @Override
    protected void helperEnableChange(boolean isHelpEnable) {
        if (!isHelpEnable) {//如果被禁用了
            if (isLoading()) {//判断是否处于加载更多状态
                closeLoading();//结束加载更多
            }
            lastControlEnableState = getControlsEnable();
            setControlsEnable(false);//禁用控件的加载更多功能
        }
        setControlsEnable(lastControlEnableState);
    }
}
