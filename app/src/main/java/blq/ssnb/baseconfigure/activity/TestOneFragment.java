package blq.ssnb.baseconfigure.activity;

import android.content.Intent;
import android.view.View;

import com.blq.ssnb.baseconfigure.BaseFragment;

import blq.ssnb.baseconfigure.R;
import blq.ssnb.snbutil.SnbLog;

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
public class TestOneFragment extends BaseFragment {
    @Override
    protected int rootLayout() {
        return R.layout.fragment_test_one;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.btn_start_activity).setOnClickListener(v -> startActivityForResult(new Intent(getContext(), TestTwoActivity.class), 1000));
    }

    @Override
    protected void initData() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fl_one_page, new TestTwoFragment())
                .commit();
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SnbLog.e(">>>>>result:" + getClass().getSimpleName() + requestCode);
    }

    @Override
    public boolean onBackPressed() {
        SnbLog.e(">>>>onBackPressed:" + getClass().getSimpleName());
        return super.onBackPressed();
    }
}
