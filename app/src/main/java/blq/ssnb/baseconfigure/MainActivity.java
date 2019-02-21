package blq.ssnb.baseconfigure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blq.ssnb.baseconfigure.BaseFragmentContainerActivity;
import com.blq.ssnb.baseconfigure.BaseSimpleWebViewFragment;

import blq.ssnb.baseconfigure.activity.TestOneActivity;
import blq.ssnb.baseconfigure.activity.TestTwoActivity;
import blq.ssnb.baseconfigure.activity.TestTwoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAction(R.id.tv_base_activity,
                v -> startActivity(new Intent(getBaseContext(), TestOneActivity.class)));
        setAction(R.id.btn_f_c, v -> {
            startActivity(BaseFragmentContainerActivity.newIntent(getBaseContext(), TestTwoFragment.class, null));
        });

        setAction(R.id.btn_web_view, v -> startActivity(BaseFragmentContainerActivity.newIntent(
                getBaseContext(),
                BaseSimpleWebViewFragment.class,
                BaseSimpleWebViewFragment.newArgument("https://www.baidu.com"))));

    }

    public void setAction(int id, View.OnClickListener onClickListener) {
        findViewById(id).setOnClickListener(onClickListener);
    }

}
