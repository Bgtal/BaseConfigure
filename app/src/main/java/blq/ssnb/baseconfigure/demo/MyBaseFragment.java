package blq.ssnb.baseconfigure.demo;

import android.view.View;
import android.widget.TextView;

import blq.ssnb.baseconfigure.BaseFragment;
import blq.ssnb.baseconfigure.R;

/**
 * <pre>
 * ================================================
 * 作者: BLQ_SSNB
 * 日期：2019/4/10
 * 邮箱: blq_ssnb@outlook.com
 * 修改次数: 1
 * 描述:
 *      添加描述
 * ================================================
 * </pre>
 */
public class MyBaseFragment extends BaseFragment {

    private TextView contentView;

    @Override
    protected int rootLayout() {
        return R.layout.fragment_my_base_fragment;
    }

    @Override
    protected void initView(View view) {
        contentView = view.findViewById(R.id.tv_content_view);
    }

    @Override
    protected void initData() {
        StringBuilder builder = new StringBuilder();
        String content = builder
                .append("1.使用静态方法 newInstance(Class<T> cls,Bundle) 来创建实例\n")
                .append("2.在onCreate中调用 initArgumentData()和initSaveState() 方法来获取argument中的数据\n")
                .append("3.onCreateView中实现一些基本的方法,和BaseActivity的一样\n")
                .append("4.跟布局设置了setOnClickListener 用于解决fragment 点击事件穿透的问题")
                .append("5.增加onBackPressed 方法用于拦截是否能返回")
                .toString();
        contentView.setText(content);
    }

    @Override
    protected void bindEvent() {

    }
}
