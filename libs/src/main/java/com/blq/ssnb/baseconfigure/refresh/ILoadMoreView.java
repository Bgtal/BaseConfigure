package com.blq.ssnb.baseconfigure.refresh;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/3/15
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 加载更多成功或失败后的回调方法
 * ================================================
 * </pre>
 */
public interface ILoadMoreView<T> {

    /**
     * 加载更多成功 回调返回数据
     *
     * @param data 加载更多获得的数据
     */
    void onLoadMoreSuccess(T data);

    /**
     * 加载更多失败回调
     *
     * @param errorCode 错误码
     * @param msg       相关信息
     */
    void onLoadMoreFail(int errorCode, String msg);
}
