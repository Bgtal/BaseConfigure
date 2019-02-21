package blq.ssnb.baseconfigure.activity;

import android.view.View;
import android.widget.Button;

import com.blq.ssnb.baseconfigure.BaseActivity;

import blq.ssnb.baseconfigure.R;

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
public class TestTwoActivity extends BaseActivity {

    private Button resultBtn;

    @Override
    protected int contentView() {
        return R.layout.activity_test_two;
    }

    @Override
    protected void initView() {
        resultBtn = findViewById(R.id.btn_activity_result);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindEvent() {
        resultBtn.setOnClickListener(v -> {
            setResult(9999);
            finish();
        });

        resultBtn.performClick();
    }
}
