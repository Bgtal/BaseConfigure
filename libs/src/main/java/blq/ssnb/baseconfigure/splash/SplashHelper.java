package blq.ssnb.baseconfigure.splash;

import java.util.List;

import blq.ssnb.baseconfigure.splash.db.SplashDatabase;
import blq.ssnb.baseconfigure.splash.db.SplashEntity;
import blq.ssnb.snbutil.SnbLog;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019-11-08
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public abstract class SplashHelper {

    private Option mOption;
    private SqlTask<List<SplashEntity>> dbTask;

    public SplashHelper() {
        mOption = new Option();
    }

    /**
     * 请求获取数据
     */
    public void requestEntityData() {
        mOption.isDbResult = false;
        mOption.isShowing = false;
        //从数据库获取数据
        dbTask = new SqlTask<>(new SqlTask.CallBack<List<SplashEntity>>() {
            @Override
            public List<SplashEntity> doAction() {
                return SplashDatabase.getInstance()
                        .mSplashDao()
                        .getSplashList(System.currentTimeMillis());
            }

            @Override
            public void onResult(List<SplashEntity> resultData) {
                mOption.isDbResult = true;
                SplashEntity entity = null;
                if (resultData != null && resultData.size() > 0) {
                    entity = resultData.get(0);
                }
                SnbLog.e(">>>>>>>"+(entity == null));
                entityInput(true, entity);
            }
        });
        dbTask.execute();
        //从网上获取数据
        requestNetData();
    }

    public abstract void requestNetData();

    /**
     * 展示数据
     *
     * @param isDbData
     * @param entity
     */
    public void entityInput(boolean isDbData, SplashEntity entity) {
        //如果返回数据为null
        if (entity != null) {
            if (!isDbData) {//如果不是从db获得的数据，那就将他插入到数据库
                new SqlTask<>(new SqlTask.VoidCallBack() {
                    @Override
                    public Void doAction() {
                        SplashDatabase.getInstance().mSplashDao().updateSplashInfo(entity);
                        return null;
                    }
                }).execute();
            }
            if (!mOption.isShowing) {
                mOption.isShowing = true;
                showEntityInfo(entity);
            }

        } else {
            mOption.isShowing = true;
            //告知界面展示null数据界面
            showNullInfo();
        }
    }

    public abstract void showEntityInfo(SplashEntity entity);

    public abstract void showNullInfo();

    public void onDestroy() {
        if (dbTask != null) {
            dbTask.cancel(true);
        }
        mOption.isRunning = false;
    }

    public static class Option {
        // <editor-fold defaultstate="collapsed" desc="内部参数">
        private boolean isDbResult = false;
        private boolean isShowing = false;
        private boolean isRunning = false;
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="外部参数">
        private long showTime = 5000L;
        // </editor-fold>
    }
}
