package blq.ssnb.baseconfigure.search;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/21
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public interface ISearchAction {

    /**
     * 触发搜索的时候
     *
     * @param msg 搜索文字
     */
    void onSearch(String msg);

    /**
     * 文字改变
     *
     * @param msg 文字改变的时候
     */
    void onChange(String msg);

    /**
     * 清空文字
     */
    void onClear();

}
