package blq.ssnb.baseconfigure.permission;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019-05-29
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class PermissionObserver implements Observer<Boolean> {
    private Disposable mDisposable;
    private PermissionCallBack mPermissionCallBack;
    private boolean isAllPass = true;

    public PermissionObserver(PermissionCallBack permissionCallBack) {
        this.mPermissionCallBack = permissionCallBack;
    }

    @Override
    public void onSubscribe(Disposable d) {
        close();
        mDisposable = d;
    }

    @Override
    public void onNext(Boolean aBoolean) {
        if (!aBoolean) {//如果有一项为false 那么就弹出拒绝
            isAllPass = false;
        }
    }

    @Override
    public void onError(Throwable e) {
        close();
        if (mPermissionCallBack != null) {
            mPermissionCallBack.onError(e);
        }
    }

    @Override
    public void onComplete() {
        close();
        if (mPermissionCallBack != null) {
            if (isAllPass) {
                mPermissionCallBack.onPassPermission();
            } else {
                mPermissionCallBack.onRefusePermission();
            }
        }
    }

    private void close() {
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }
}
