package blq.ssnb.baseconfigure;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/20
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      一个简单的包裹fragment布局的Activity
 *      使用场景:例如一个界面之前是作为fragment的，
 *      现在需要某个地方点击跳转到这个界面，那么就直接用这个activity
 *      不需要重新写一个包裹fragment的activity了
 * ================================================
 * </pre>
 */
public class BaseFragmentContainerActivity extends BaseActivity {
    private static final String BUNDLE_KEY_FRAGMENT_CLASS = "bundle_key_fragment_class";
    private static final String BUNDLE_KEY_ARGUMENT = "bundle_key_argument";
    private static final String BUNDLE_KEY_SCREEN_ORIENTATION = "bundle_key_screen_orientation";

    public static Intent newIntent(Context context, Class<? extends Fragment> fragmentClass, Bundle argument) {
        return newIntent(context, fragmentClass, ActivityInfo.SCREEN_ORIENTATION_BEHIND, argument);
    }

    public static Intent newIntent(Context context, Class<? extends Fragment> fragmentClass, int screenOrientation, Bundle argument) {
        Intent intent = new Intent(context, BaseFragmentContainerActivity.class);
        intent.putExtra(BUNDLE_KEY_FRAGMENT_CLASS, fragmentClass);
        intent.putExtra(BUNDLE_KEY_ARGUMENT, argument);
        intent.putExtra(BUNDLE_KEY_SCREEN_ORIENTATION, screenOrientation);
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
    protected void initView() {

    }

    @Override
    protected void bindData() {
        try {
            Fragment fragment = mClass.newInstance();
            fragment.setArguments(mArgument);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void bindEvent() {

    }
}
