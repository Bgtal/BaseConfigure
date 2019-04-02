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
public interface OnLoadMoreListener<D> {
    /**
     * 请求加载的时候调用
     */
    void requestLoadMore();

    /**
     * 加载成功后的回调
     *
     * @param data 返回数据，可能为null
     */
    void onLoadSuccess(D data);

    /**
     * 加载失败后的回调
     *
     * @param errorCode 失败码
     * @param errorMsg  失败原因
     */
    void onLoadFail(int errorCode, String errorMsg);

    /**
     * 根据返回数据 判断是否能够加载更多
     *
     * @param data 返回的数据 可能为null
     * @return true 表示能加载更多
     */
    boolean canLoadMore(D data);
}
