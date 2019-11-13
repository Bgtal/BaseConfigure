package blq.ssnb.baseconfigure.splash;

import android.os.AsyncTask;

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
public class SqlTask<T> extends AsyncTask<Void, Integer, T> {

    private CallBack<T> mCallBack;

    public SqlTask() {}

    public SqlTask(CallBack<T> callBack) {
        mCallBack = callBack;
    }

    public void setCallBack(CallBack<T> callBack) {
        mCallBack = callBack;
    }

    @Override
    protected T doInBackground(Void... voids) {
        return mCallBack.doAction();
    }

    @Override
    protected void onPostExecute(T result) {
        if (mCallBack != null) {
            mCallBack.onResult(result);
        }
    }

    public interface CallBack<T> {
        T doAction();

        void onResult(T resultData);
    }

    public abstract static class VoidCallBack implements CallBack<Void> {

        @Override
        public void onResult(Void resultData) {

        }
    }
}

