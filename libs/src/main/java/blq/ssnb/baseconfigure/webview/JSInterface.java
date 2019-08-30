package blq.ssnb.baseconfigure.webview;

import android.app.Activity;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019-08-30
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public abstract class JSInterface {

    private Activity mActivity;

    public JSInterface(Activity activity) {

    }

    public void onDestory() {
        mActivity = null;
    }

    protected Activity getActivity() {
        return mActivity;
    }

}
