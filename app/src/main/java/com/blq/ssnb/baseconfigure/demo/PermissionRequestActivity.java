package com.blq.ssnb.baseconfigure.demo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import blq.ssnb.baseconfigure.permission.Permission;
import blq.ssnb.baseconfigure.permission.PermissionCallBack;
import blq.ssnb.baseconfigure.simple.MenuBean;
import blq.ssnb.baseconfigure.simple.SimpleMenuActivity;
import blq.ssnb.snbutil.SnbLog;

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
public class PermissionRequestActivity extends SimpleMenuActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,PermissionRequestActivity.class);
        return intent;
    }

    private RxPermissions mRxPermissions;

    @Override
    protected String navigationTitle() {
        return "权限请求";
    }

    @Override
    protected void initData() {
        super.initData();
        mRxPermissions = new RxPermissions(this);
    }

    @Override
    protected List<MenuBean> getMenuBeans() {
        List<MenuBean> beanList = new ArrayList<>();
        beanList.add(new MenuBean().setMenuTitle("存储权限").setOnClickListener(v -> {
            Permission.requestPermission((FragmentActivity) getActivity(), new PermissionCallBack() {
                @Override
                public void onPassPermission() {
                    SnbLog.e(">>>存储权限通过");
                }

                @Override
                public void onRefusePermission() {
                    SnbLog.e(">>>存储权限拒绝");
                }

                @Override
                public void onError(Throwable msg) {
                    SnbLog.e(">>>存储权限出错");
                }
            }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }));

        beanList.add(new MenuBean().setMenuTitle("照相机权限").setOnClickListener(v -> {
            Permission.requestPermission((FragmentActivity) getActivity(), new PermissionCallBack() {
                @Override
                public void onPassPermission() {
                    SnbLog.e(">>>相机权限通过");
                }

                @Override
                public void onRefusePermission() {
                    SnbLog.e(">>>相机权限拒绝");
                }

                @Override
                public void onError(Throwable msg) {
                    SnbLog.e(">>>相机权限出错");
                }
            }, Manifest.permission.CAMERA);
        }));
        return beanList;
    }
}
