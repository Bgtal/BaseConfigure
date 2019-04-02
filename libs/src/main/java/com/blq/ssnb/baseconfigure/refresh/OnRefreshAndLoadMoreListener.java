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
public interface OnRefreshAndLoadMoreListener<T>
        extends OnRefreshListener<T>,
        OnLoadMoreListener<T> {
}
