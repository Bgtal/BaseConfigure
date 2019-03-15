package com.blq.ssnb.baseconfigure.refresh;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/3/15
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      用于刷新成功或失败后的回调
 * ================================================
 * </pre>
 */
public interface IRefreshView<T> {

    /**
     * 刷新成功 返回请求到的数据
     * @param data 请求得到的数据
     */
    void onRefreshSuccess(T data);

    /**
     * 刷新失败回调
     * @param errorCode 错误码
     * @param msg 相关信息
     */
    void onRefreshFail(int errorCode, String msg);
}
