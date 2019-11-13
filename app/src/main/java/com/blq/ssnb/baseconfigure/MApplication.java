package com.blq.ssnb.baseconfigure;

import com.facebook.stetho.Stetho;

import blq.ssnb.baseconfigure.AbsApplication;
import blq.ssnb.baseconfigure.LogManager;
import blq.ssnb.baseconfigure.splash.db.SplashDatabase;
import blq.ssnb.snbutil.SnbLog;
import blq.ssnb.snbutil.SnbToast;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/20
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class MApplication extends AbsApplication {

    @Override
    protected void initSnb() {
        SnbLog.getGlobalBuilder().isOpen(true);
        LogManager.initLog();
        LogManager.openLog(false, false);
        SnbToast.init(getContext());
        SplashDatabase.init(this);
        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected void initBugly() {

    }

    @Override
    protected void initNetWork() {

    }

    @Override
    protected void initSingle() {

    }
}
