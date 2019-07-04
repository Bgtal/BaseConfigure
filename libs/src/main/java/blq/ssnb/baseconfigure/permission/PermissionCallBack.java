package blq.ssnb.baseconfigure.permission;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019-05-29
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 * 权限请求的回调
 * ================================================
 * </pre>
 */
public interface PermissionCallBack {

    void onPassPermission();

    void onRefusePermission();

    void onError(Throwable msg);

}
