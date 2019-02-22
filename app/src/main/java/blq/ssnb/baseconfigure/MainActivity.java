package blq.ssnb.baseconfigure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blq.ssnb.baseconfigure.BaseFragmentContainerActivity;
import com.blq.ssnb.baseconfigure.search.SimpleSearchActivity;
import com.blq.ssnb.baseconfigure.webview.SimpleWebViewFragment;

import blq.ssnb.baseconfigure.activity.TestOneActivity;
import blq.ssnb.baseconfigure.activity.TestTwoFragment;
import blq.ssnb.baseconfigure.search.SearchFragment;

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
                SimpleWebViewFragment.class,
                SimpleWebViewFragment.newArgument("https://www.baidu.com"))));

        setAction(R.id.btn_simple_search, v -> {
            startActivity(SimpleSearchActivity.newIntent(getBaseContext(),
                    SimpleSearchActivity.newBundle(SearchFragment.class, null)));
        });

    }

    public void setAction(int id, View.OnClickListener onClickListener) {
        findViewById(id).setOnClickListener(onClickListener);
    }

}
