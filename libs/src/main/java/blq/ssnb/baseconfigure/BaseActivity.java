package blq.ssnb.baseconfigure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/2/20
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *  基础的activity
 * ================================================
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final String TAG = "Activity-" + this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView());
        Bundle bundle = getIntent().getExtras();
        initBundle(bundle == null ? new Bundle() : bundle);
        if (savedInstanceState != null) {
            initSaveState(savedInstanceState);
        }
        initView();
        initData();
        bindData();
        bindEvent();
        operation();
    }

    /**
     * 内容View的id（R.layout.xxx）
     *
     * @return 资源id
     */
    protected abstract @LayoutRes
    int contentView();

    /**
     * 初始化bundle
     *
     * @param extras 传入的参数
     */
    protected void initBundle(@NonNull Bundle extras) {

    }

    /**
     * 恢复保存的状态，只有在有状态保存的时候才会执行该方法
     *
     * @param savedInstanceState 保存的状态，不为空
     */
    protected void initSaveState(Bundle savedInstanceState) {

    }

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 绑定数据
     */
    protected abstract void bindData();

    /**
     * 绑定事件
     */
    protected abstract void bindEvent();

    /**
     * 一些其他操作
     */
    protected void operation() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogManager.i(TAG + ":onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogManager.i(TAG + ":onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogManager.i(TAG + ":onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogManager.i(TAG + ":onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogManager.i(TAG + ":onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogManager.i(TAG + ":onDestroy");
    }

    protected Context getContext() {
        return this;
    }

    protected Activity getActivity() {
        return this;
    }

    @Override
    protected final void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int requestIndex = requestCode >> 16;//先判断是否是fragment 发起的请求
        if (requestIndex != 0) {//不为0 表示是 fragment 进行的请求
            requestCode = requestCode & 0xffff;//得到真正的请求code
        }
        onHandledActivityResult(requestCode, resultCode, data);
    }

    /**
     * {@link #onActivityResult(int, int, Intent)} 设为final
     * 以后activity 回调都走该方法
     * 该方法会捕获到所有从这个activity发出的result
     * 例如:用于fragment之间的通行用
     */
    protected void onHandledActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }

    protected void simpleToActivity(Class<? extends Activity> activityClass) {
        if (activityClass != null) {
            startActivity(new Intent(getContext(), activityClass));
        }
    }

    protected void simpleToActivityForResult(Class<? extends Activity> activityClass, int requestCode) {
        if (activityClass != null) {
            startActivityForResult(new Intent(getContext(), activityClass), requestCode);
        }
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof BaseFragment) {
                if (((BaseFragment) fragment).onBackPressed()) {
                    return;
                }
            }
        }

        super.onBackPressed();
    }
}
