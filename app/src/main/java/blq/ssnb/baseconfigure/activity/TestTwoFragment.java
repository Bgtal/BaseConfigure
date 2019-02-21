package blq.ssnb.baseconfigure.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

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
public class TestTwoFragment extends BaseFragment {
    private Button mButton;

    @Override
    protected int rootLayout() {
        return R.layout.fragment_test_two;
    }

    @Override
    protected void initView(View view) {
        mButton = view.findViewById(R.id.btn_start_activity);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {
        mButton.setOnClickListener(v -> {
                    startActivityForResult(new Intent(getContext(), TestTwoActivity.class), 1010);
                }
        );
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
