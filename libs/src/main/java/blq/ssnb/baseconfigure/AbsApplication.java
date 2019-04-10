package blq.ssnb.baseconfigure;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/20
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 基础的 application
 * ================================================
 * </pre>
 */
public abstract class AbsApplication extends Application {
    private static WeakReference<AbsApplication> weakReference;

    @Override
    public void onCreate() {
        super.onCreate();
        weakReference = new WeakReference<>(this);
        initSnb();
        initBugly();
        initNetWork();
        initSingle();
    }

    /**
     * 初始化snb相关控件
     */
    protected abstract void initSnb();

    /**
     * 初始化bug捕获工具
     */
    protected abstract void initBugly();

    /**
     * 初始化网络工具
     */
    protected abstract void initNetWork();

    /**
     * 初始化单例
     */
    protected abstract void initSingle();

    public static Context getContext() {
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
}
