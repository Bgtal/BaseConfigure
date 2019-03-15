package com.blq.ssnb.baseconfigure.refresh;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/3/15
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 刷新和加载更多过程中的监听
 * ================================================
 * </pre>
 */
public interface OnRefreshAndLoadMoreListener<T> {
    /**
     * 请求刷新的时候调用
     */
    void requestRefresh();

    /**
     * 请求加载的时候调用
     */
    void requestLoadMore();

    /**
     * 刷新成功后的回调
     *
     * @param data 返回数据，可能为null
     */
    void onRefreshSuccess(T data);

    /**
     * 刷新失败后的回调
     *
     * @param errorCode 失败码
     * @param errorMsg  失败原因
     */
    void onRefreshFail(int errorCode, String errorMsg);

    /**
     * 加载成功后的回调
     *
     * @param data 返回数据，可能为null
     */
    void onLoadSuccess(T data);

    /**
     * 加载失败后的回调
     *
     * @param errorCode 失败码
     * @param errorMsg  失败原因
     */
    void onLoadFail(int errorCode, String errorMsg);

    /**
     * 根据返回数据 判断是否能够加载更多
     * @param data 返回的数据 可能为null
     * @return true 表示能加载更多
     */
    boolean canLoadMore(T data);
}
