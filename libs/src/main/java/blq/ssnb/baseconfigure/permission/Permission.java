package blq.ssnb.baseconfigure.permission;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

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
public class Permission {

    public static void requestPermission(FragmentActivity activity, PermissionCallBack callBack, String... p) {
        new RxPermissions(activity).request(p)
                .subscribe(new PermissionObserver(callBack));
    }

    public static void requestPermission(Fragment fragment, PermissionCallBack callBack, String... p) {
        new RxPermissions(fragment).request(p)
                .subscribe(new PermissionObserver(callBack));
    }

}
