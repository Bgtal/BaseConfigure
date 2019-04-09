package com.blq.ssnb.baseconfigure.simple;

import android.app.Activity;

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

    public MenuBean(String menuTitle) {
        this(menuTitle, null);
    }

    public MenuBean(String menuTitle, String menuSubTitle) {
        this(menuTitle, menuSubTitle, null);
    }

    public MenuBean(String menuTitle, String menuSubTitle, Class<? extends Activity> activityClass) {
        this.mMenuTitle = menuTitle;
        this.mMenuSubTitle = menuSubTitle;
        this.mActivityClass = activityClass;
    }

    public String getMenuTitle() {
        return mMenuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        mMenuTitle = menuTitle;
    }

    public String getMenuSubTitle() {
        return mMenuSubTitle;
    }

    public void setMenuSubTitle(String menuSubTitle) {
        mMenuSubTitle = menuSubTitle;
    }

    public Class<? extends Activity> getActivityClass() {
        return mActivityClass;
    }

    public void setActivityClass(Class<? extends Activity> activityClass) {
        mActivityClass = activityClass;
    }

}
