package blq.ssnb.baseconfigure.simple;

import android.app.Activity;
import android.view.View;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/4/3
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class MenuBean {
    private String mMenuTitle;
    private String mMenuSubTitle;
    private Class<? extends Activity> mActivityClass;
    private View.OnClickListener mOnClickListener;

    public String getMenuTitle() {
        return mMenuTitle;
    }

    public MenuBean setMenuTitle(String menuTitle) {
        mMenuTitle = menuTitle;
        return this;
    }

    public String getMenuSubTitle() {
        return mMenuSubTitle;
    }

    public MenuBean setMenuSubTitle(String menuSubTitle) {
        mMenuSubTitle = menuSubTitle;
        return this;
    }

    public Class<? extends Activity> getActivityClass() {
        return mActivityClass;
    }

    public MenuBean setActivityClass(Class<? extends Activity> activityClass) {
        mActivityClass = activityClass;
        return this;
    }

    public View.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public MenuBean setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
        return this;
    }
}
