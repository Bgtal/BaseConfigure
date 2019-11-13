package blq.ssnb.baseconfigure.search;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import blq.ssnb.baseconfigure.BaseActivity;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/22
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public abstract class BaseSearchActivity extends BaseActivity implements ISearchAction {


    private static final String BUNDLE_KEY_FRAGMENT_CLASS = "bundle_key_fragment_class";
    private static final String BUNDLE_KEY_ARGUMENT = "bundle_key_argument";
    private static final String BUNDLE_KEY_SCREEN_ORIENTATION = "bundle_key_screen_orientation";

    public static Bundle newBundle(Class<? extends Fragment> fragmentClass, Bundle argument) {
        return newBundle(fragmentClass, ActivityInfo.SCREEN_ORIENTATION_BEHIND, argument);
    }

    public static Bundle newBundle(Class<? extends Fragment> fragmentClass, int screenOrientation, Bundle argument) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY_FRAGMENT_CLASS, fragmentClass);
        bundle.putBundle(BUNDLE_KEY_ARGUMENT, argument);
        bundle.putInt(BUNDLE_KEY_SCREEN_ORIENTATION, screenOrientation);
        return bundle;
    }


    private Class<? extends Fragment> mClass;
    private Bundle mArgument;

    private ISearchAction mISearchAction;

    protected abstract int getContainerID();

    @Override
    protected void initBundle(@NonNull Bundle extras) {
        try {
            int screenOrientation = extras.getInt(BUNDLE_KEY_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_BEHIND);
            setRequestedOrientation(screenOrientation);
            mClass = (Class<? extends Fragment>) extras.getSerializable(BUNDLE_KEY_FRAGMENT_CLASS);
            mArgument = extras.getBundle(BUNDLE_KEY_ARGUMENT);
        } catch (Exception e) {
            mClass = null;
            mArgument = null;
        }
    }

    @Override
    protected void bindData() {
        try {
            Fragment fragment = mClass.newInstance();
            if (fragment instanceof ISearchAction) {
                mISearchAction = (ISearchAction) fragment;
            }
            fragment.setArguments(mArgument);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(getContainerID(), fragment)
                    .commit();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    protected ISearchAction getISearchAction() {
        return mISearchAction;
    }

    @Override
    public void onSearch(String msg) {
        if (mISearchAction != null) {
            mISearchAction.onSearch(msg);
        }
    }

    @Override
    public void onChange(String msg) {
        if (mISearchAction != null) {
            mISearchAction.onChange(msg);
        }
    }

    @Override
    public void onClear() {
        if (mISearchAction != null) {
            mISearchAction.onClear();
        }
    }
}
