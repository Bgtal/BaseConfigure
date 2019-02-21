package blq.ssnb.baseconfigure.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.blq.ssnb.baseconfigure.BaseActivity;

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
public class TestOneActivity extends BaseActivity {

    private View pageOneView;
    private View pageTwoView;

    @Override
    protected int contentView() {
        return R.layout.activity_test_one;
    }

    @Override
    protected void initView() {
        pageOneView = findViewById(R.id.fl_one_page);
        pageTwoView = findViewById(R.id.fl_two_page);

    }

    @Override
    protected void bindData() {
    }

    @Override
    protected void bindEvent() {
        pageOneView.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_one_page, new TestOneFragment())
                    .commit();

        });

        pageTwoView.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_two_page, new TestOneFragment())
                    .commit();
        });
    }

    @Override
    protected void onHandledActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onHandledActivityResult(requestCode, resultCode, data);
        SnbLog.e(">>>>>result:" + getClass().getSimpleName() + requestCode);
    }
}
