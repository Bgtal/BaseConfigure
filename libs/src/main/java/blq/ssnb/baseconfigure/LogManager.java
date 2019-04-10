package blq.ssnb.baseconfigure;

import blq.ssnb.snbutil.SnbLog;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/20
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *  用于管理当前lib的log打印
 * ================================================
 * </pre>
 */
public class LogManager {
    private static final String LOG_TAG = "BaseConfigure";

    public static void initLog() {
        SnbLog.getBuilder(LOG_TAG).setTag(LOG_TAG);
    }

    public static void openLog(boolean isShowLocation, boolean isShowLine) {
        SnbLog.getBuilder(LOG_TAG)
                .isOpen(true)
                .isShowLocation(isShowLocation)
                .isShowBorderLine(isShowLine);
    }

    public static void closeLog() {
        SnbLog.getBuilder(LOG_TAG).isOpen(false);
    }

    public static void v(String msg) {
        SnbLog.sv(LOG_TAG, msg);
    }

    public static void d(String msg) {
        SnbLog.sd(LOG_TAG, msg);
    }

    public static void i(String msg) {
        SnbLog.i(LOG_TAG, msg);
    }

    public static void w(String msg) {
        SnbLog.w(LOG_TAG, msg);
    }

    public static void e(String msg) {
        SnbLog.se(LOG_TAG, msg);
    }

    public static void a(String msg) {
        SnbLog.a(LOG_TAG, msg);
    }
}
