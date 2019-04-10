package blq.ssnb.baseconfigure;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public abstract class BaseFragment extends Fragment {

    private final String TAG = "Fragment-" + this.getClass().getSimpleName();


    public static <T extends Fragment> T newInstance(Class<T> cls, Bundle bundle) {
        T fragment = null;
        try {
            fragment = cls.newInstance();
            fragment.setArguments(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogManager.i(TAG + ":onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argument = getArguments();
        initArgumentData(argument == null ? new Bundle() : argument);
        if (savedInstanceState != null) {
            initSaveState(savedInstanceState);
        }
    }

    /**
     * 初始化 arguments 数据
     * 如果设置了argument那么会调用该方法
     *
     * @param arguments 传入的参数对象
     */
    protected void initArgumentData(Bundle arguments) {

    }

    /**
     * 恢复保存状态
     *
     * @param savedInstanceState 缓存数据
     */
    protected void initSaveState(Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        LogManager.i(TAG + ":onCreateView");
        View view = inflater.inflate(rootLayout(), container, false);
        view.setOnClickListener(v -> {
            //这个on点击事件监听是为了防止fragment叠加导致的事件传递到底下那层fragment中
        });

        view.setOnKeyListener((v, keyCode, event) -> event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK && onBackPressed());
        initView(view);
        initData();
        bindData();
        bindEvent();
        operation();

        return view;
    }

    /**
     * 内容View的id（R.layout.xxx）
     *
     * @return 资源id
     */
    protected abstract int rootLayout();

    /**
     * 当fragment在前面的时候点击回退按钮的时候调用该方法
     *
     * @return true 表示拦截 ，false 表示不拦截
     */
    public boolean onBackPressed() {
        return false;
    }

    /**
     * 初始化View
     *
     * @param view 背景view
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定数据
     */
    protected void bindData() {

    }

    /**
     * 绑定事件
     */
    protected abstract void bindEvent();

    /**
     * 完成后要执行的事
     */
    protected void operation() {

    }

    @Override
    public void onStart() {
        super.onStart();
        LogManager.i(TAG + ":onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogManager.i(TAG + ":onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogManager.i(TAG + ":onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogManager.i(TAG + ":onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogManager.i(TAG + ":onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogManager.i(TAG + ":onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogManager.i(TAG + ":onDetach");
    }

}
