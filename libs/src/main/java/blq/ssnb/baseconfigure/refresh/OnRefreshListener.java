package blq.ssnb.baseconfigure.refresh;

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
public interface OnRefreshListener<D> {
    /**
     * 请求刷新的时候调用
     */
    void requestRefresh();
    /**
     * 刷新成功后的回调
     *
     * @param data 返回数据，可能为null
     */
    void onRefreshSuccess(D data);

    /**
     * 刷新失败后的回调
     *
     * @param errorCode 失败码
     * @param errorMsg  失败原因
     */
    void onRefreshFail(int errorCode, String errorMsg);

}
