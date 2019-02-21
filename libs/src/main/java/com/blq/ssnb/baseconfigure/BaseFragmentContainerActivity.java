package com.blq.ssnb.baseconfigure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

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
public class BaseFragmentContainerActivity extends BaseActivity {
    private static final String BUNDLE_KEY_FRAGMENT_CLASS = "bundle_key_fragment_class";
    private static final String BUNDLE_KEY_ARGUMENT = "bundle_key_argument";

    public static Intent newIntent(Context context, Class<? extends Fragment> fragmentClass, Bundle argument) {
        Intent intent = new Intent(context, BaseFragmentContainerActivity.class);
        intent.putExtra(BUNDLE_KEY_FRAGMENT_CLASS, fragmentClass);
        intent.putExtra(BUNDLE_KEY_ARGUMENT, argument);
        return intent;
    }

    private Class<? extends Fragment> mClass;
    private Bundle mArgument;


    @Override
    protected int contentView() {
        return R.layout.base_activity_fragment_container;
    }

    @Override
    protected void initBundle(@NonNull Bundle extras) {
        try {
            mClass = (Class<? extends Fragment>) extras.getSerializable(BUNDLE_KEY_FRAGMENT_CLASS);
            mArgument = extras.getBundle(BUNDLE_KEY_ARGUMENT);
        } catch (Exception e) {
            mClass = null;
            mArgument = null;
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindData() {
        try {
            Fragment fragment = mClass.newInstance();
            fragment.setArguments(mArgument);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container,fragment)
                    .commit();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void bindEvent() {

    }
}
